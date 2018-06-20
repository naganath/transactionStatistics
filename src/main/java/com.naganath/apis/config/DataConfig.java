package com.naganath.apis.config;

import com.naganath.dal.models.TransactionData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ramanathan.k on 19/06/18.
 */
@Configuration
public class DataConfig {

   @Bean
   public TransactionData transactionData() {
      return new TransactionData();
   }
}
