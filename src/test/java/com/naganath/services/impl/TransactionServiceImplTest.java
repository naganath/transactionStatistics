package com.naganath.services.impl;

import com.naganath.apis.dto.TransactionRequest;
import com.naganath.dal.models.Transaction;
import com.naganath.dal.models.TransactionData;
import com.naganath.services.ITransactionService;
import com.naganath.services.exceptions.TimeExpiredException;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Created by ramanathan.k on 21/06/18.
 */
public class TransactionServiceImplTest extends TestCase {

   private ITransactionService transactionService;
   private TransactionRequest transactionRequest;

   @Mock
   private TransactionData transactionData;


   @Override
   public void setUp() {
      MockitoAnnotations.initMocks(this);
      transactionService = new TransactionServiceImpl(transactionData);
      transactionRequest = new TransactionRequest();
      transactionRequest.setAmount(2d);
   }

   @Test
   public void testGetStatistics() {
      when(transactionData.getStatistics()).thenReturn(new HashMap<>());
      assertNotNull(transactionService.getStatistics());
   }

   @Test
   public void testSaveTransaction() {
      transactionRequest.setTimestamp(System.currentTimeMillis());
      doNothing().when(transactionData).saveTransaction(any(Transaction.class));
      transactionService.saveTransaction(transactionRequest);
   }

   @Test(expected = TimeExpiredException.class)
   public void shouldThrowExceptionSaveTransaction () {
      transactionRequest.setTimestamp(0L);
      transactionService.saveTransaction(transactionRequest);
   }

 }
