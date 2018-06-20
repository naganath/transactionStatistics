package com.naganath.apis.resources;

import com.naganath.apis.dto.TransactionRequest;
import com.naganath.services.ITransactionService;
import com.naganath.services.exceptions.TimeExpiredException;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by ramanathan.k on 21/06/18.
 */
public class TransactionResourceTest extends TestCase {

   private TransactionResource transactionResource;
   private TransactionRequest request;

   @Mock
   private ITransactionService transactionService;


   @Override
   public void setUp() {
      MockitoAnnotations.initMocks(this);
      transactionResource = new TransactionResource(transactionService);
      request = new TransactionRequest();
      request.setAmount(1d);
      request.setTimestamp(System.currentTimeMillis());
   }

   @Test
   public void testSaveTransaction() {
      doNothing().when(transactionService).saveTransaction(any(TransactionRequest.class));
      assertEquals(transactionResource.saveTransaction(request).getStatusCode(), HttpStatus.OK);
   }

   @Test
   public void testSaveTransactionWithException() {
      doThrow(new TimeExpiredException("TEST")).when(transactionService).saveTransaction(any(TransactionRequest.class));
      assertEquals(transactionResource.saveTransaction(request).getStatusCode(), HttpStatus.NO_CONTENT);
   }

   @Test
   public void testGetStatistics() {
      when(transactionService.getStatistics()).thenReturn(new HashMap<>());
      assertNotNull(transactionResource.getStatistics().getBody());
   }

}
