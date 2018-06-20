package com.naganath.apis.config;

import com.naganath.apis.resources.TransactionResource;
import com.naganath.services.ITransactionService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by ramanathan.k on 19/06/18.
 */
@Configuration
@ComponentScan({"com.naganath"})
@Import({BusinessConfig.class})
@EnableAutoConfiguration
public class ApplicationConfig {

   @Bean
   public TransactionResource transactionResource(ITransactionService transactionService) {
      return new TransactionResource(transactionService);
   }
}
