package com.naganath.dal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ramanathan.k on 19/06/18.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

   private Double amount;
   private Long timestamp;

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Transaction that = (Transaction) o;

      if (!amount.equals(that.amount)) {
         return false;
      }
      return timestamp.equals(that.timestamp);

   }

   @Override
   public int hashCode() {
      int result = amount.hashCode();
      result = 31 * result + timestamp.hashCode();
      return result;
   }
}
