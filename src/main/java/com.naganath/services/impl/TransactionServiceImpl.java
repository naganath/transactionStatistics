package com.naganath.services.impl;

import com.naganath.apis.dto.TransactionRequest;
import com.naganath.dal.models.Transaction;
import com.naganath.dal.models.TransactionData;
import com.naganath.services.ITransactionService;
import com.naganath.services.exceptions.TimeExpiredException;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

/**
 * Created by ramanathan.k on 19/06/18.
 */
public class TransactionServiceImpl implements ITransactionService {


   private TransactionData transactionData;
   private static final Long timeInterval = 60000L;

   public TransactionServiceImpl(TransactionData transactionData) {
      this.transactionData = transactionData;
   }


   public void saveTransaction(TransactionRequest transactionRequest) {
      if(transactionRequest.getTimestamp() < System.currentTimeMillis() - timeInterval) {
         throw new TimeExpiredException("Time expired");
      }
      synchronized(transactionData) {
         transactionData.saveTransaction(new Transaction(transactionRequest.getAmount(),
               transactionRequest.getTimestamp()));
      }
   }

   public Map<String,Object> getStatistics() {
      synchronized (transactionData) {
         return transactionData.getStatistics();
      }
   }


   @Scheduled(fixedRate = 2000)
   public void deleteOldTransactionData() {
      synchronized (transactionData) {
         transactionData.deleteOlderTransaction(System.currentTimeMillis() - timeInterval);
      }
   }




}
