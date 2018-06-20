package com.naganath.apis;

import com.naganath.apis.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by ramanathan.k on 19/06/18.
 */

@SpringBootApplication
@EnableScheduling
public class Application { // NOSONAR

   public static void main(final String[] args) {

      SpringApplication.run(ApplicationConfig.class, args); // NOSONAR
   }
}
