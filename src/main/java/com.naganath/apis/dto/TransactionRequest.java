package com.naganath.apis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * Created by ramanathan.k on 19/06/18.
 */
@Getter
@Setter
@ToString
public class TransactionRequest {

   @NotNull
   @JsonProperty("amount")
   private Double amount;

   @NotNull
   @JsonProperty("timestamp")
   private Long timestamp;


}
