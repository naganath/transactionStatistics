package com.naganath.apis.config;

import com.naganath.dal.models.TransactionData;
import com.naganath.services.ITransactionService;
import com.naganath.services.impl.TransactionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ramanathan.k on 19/06/18.
 */
@Import({DataConfig.class})
@Configuration
public class BusinessConfig {

   @Bean
   public ITransactionService transactionService(TransactionData transactionData) {
      return new TransactionServiceImpl(transactionData);
   }


}
