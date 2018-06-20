package com.naganath.services;

import com.naganath.apis.dto.TransactionRequest;

import java.util.Map;

/**
 * Created by ramanathan.k on 19/06/18.
 */
public interface ITransactionService {


   void saveTransaction(TransactionRequest transactionRequest);
   Map<String,Object> getStatistics();
}
