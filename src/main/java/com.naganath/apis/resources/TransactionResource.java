package com.naganath.apis.resources;

import com.naganath.apis.dto.TransactionRequest;
import com.naganath.services.ITransactionService;
import com.naganath.services.exceptions.TimeExpiredException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.concurrent.ThreadSafe;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;

/**
 * Created by ramanathan.k on 19/06/18.
 */
@RestController
@ThreadSafe
@Log
@RequestMapping("")
public class TransactionResource {

   private ITransactionService transactionService;

   public TransactionResource(ITransactionService transactionService) {
      this.transactionService = transactionService;
   }

   @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, path = "/transaction")
   public ResponseEntity<String> saveTransaction(@NotNull @RequestBody TransactionRequest transactionRequest) {
      log.log(Level.INFO, " transaction data : " + transactionRequest);
      try {
         transactionService.saveTransaction(transactionRequest);
      } catch (TimeExpiredException e) {
         log.log(Level.SEVERE,"old transaction sent", transactionRequest);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }
      return ResponseEntity.status(HttpStatus.OK).build();
   }


   @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON, path = "/statistics")
   public ResponseEntity<Map<String,Object>> getStatistics() {
      log.log(Level.INFO, "getting statistics");
      return ResponseEntity.ok(transactionService.getStatistics());
   }






}


