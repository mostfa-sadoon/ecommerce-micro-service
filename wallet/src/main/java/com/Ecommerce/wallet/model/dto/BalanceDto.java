package com.Ecommerce.wallet.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceDto {
   private Long    wallet_id;
   private Integer balance;
}
