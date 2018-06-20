package com.naganath.dal.models;

import com.google.common.collect.MinMaxPriorityQueue;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;

/**
 * Created by ramanathan.k on 19/06/18.
 */
@Log
public class TransactionData {
   private MinMaxPriorityQueue<Transaction> transactionsQ;
   private MinMaxPriorityQueue<Transaction> statisticalTransactionQ;
   private Double sum;

   private static final String MAX = "max";
   private static final String MIN = "min";
   private static final String COUNT = "count";
   private static final String SUM = "sum";
   private static final String AVERAGE = "average";

   public Double getSum() {
      return sum;
   }

   public TransactionData() {
      transactionsQ = MinMaxPriorityQueue.orderedBy(new TransactionComparator()).create();
      statisticalTransactionQ = MinMaxPriorityQueue.orderedBy(new MaxValueComparator()).create();
      sum = 0.0;
   }

   public void saveTransaction(Transaction transaction) {
      transactionsQ.add(transaction);
      statisticalTransactionQ.add(transaction);
      sum += transaction.getAmount();
   }

   public void deleteOlderTransaction(Long timestamp) {


      List<Transaction> deletedTransactions = removeOldTransaction(timestamp, transactionsQ,MinMaxPriorityQueue::peekFirst, MinMaxPriorityQueue::removeFirst);
      sum -= deletedTransactions.stream().mapToDouble(Transaction::getAmount).sum();
      log.log(Level.INFO, "deleted Transactions: " + deletedTransactions );
      removeOldTransaction(timestamp, statisticalTransactionQ,MinMaxPriorityQueue::peekLast, MinMaxPriorityQueue::removeLast);
      removeOldTransaction(timestamp, statisticalTransactionQ,MinMaxPriorityQueue::peekFirst, MinMaxPriorityQueue::removeFirst);
   }

   public Map<String,Object> getStatistics() {
      Map<String,Object> data = new HashMap<>();
      Transaction t = statisticalTransactionQ.peekLast();
      data.put(MAX, t == null? null:t.getAmount());
      t = statisticalTransactionQ.peekFirst();
      data.put(MIN, t == null? null:t.getAmount());
      data.put(COUNT, transactionsQ.size());
      data.put(SUM, sum);
      data.put(AVERAGE, transactionsQ.size() == 0? 0: sum / transactionsQ.size());
      return data;
   }

   private List<Transaction> removeOldTransaction(Long timestamp, MinMaxPriorityQueue<Transaction> transactions,
                                                  Function<MinMaxPriorityQueue<Transaction>,Transaction> getFunc,
                                                  Function<MinMaxPriorityQueue<Transaction>,Transaction> delFunc) {
      List<Transaction> deletedTransactions = new ArrayList<>();
      while (true) {
         Transaction t = getFunc.apply(transactions);
         if(t == null) {
            break;
         }
         if(t.getTimestamp() < timestamp) {
            delFunc.apply(transactions);
            deletedTransactions.add(t);
         } else {
            break;
         }
      }
      return deletedTransactions;

   }






   private class TransactionComparator implements Comparator<Transaction> {

      public int compare (Transaction t1, Transaction t2) {
         return t1.getTimestamp().compareTo(t2.getTimestamp());
      }
   }

   private class MaxValueComparator implements Comparator<Transaction> {

      public int compare (Transaction t1, Transaction t2) {
         return t1.getAmount().compareTo(t2.getAmount());
      }

   }

}
