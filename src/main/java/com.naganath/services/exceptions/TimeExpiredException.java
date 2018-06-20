package com.naganath.services.exceptions;

/**
 * Created by ramanathan.k on 19/06/18.
 */
public class TimeExpiredException extends  RuntimeException {
   public TimeExpiredException(String message) {
      super(message);
   }
}
