package com.Ecommerce.wallet.controller;

import com.Ecommerce.wallet.model.dto.BalanceDto;
import com.Ecommerce.wallet.model.entity.Wallet;
import com.Ecommerce.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/wallets")
public class walletController {

    @Autowired
    WalletService walletService;

   @GetMapping("/get/user/wallet")
   public Optional<Wallet> getUserWallet(@RequestParam Long user_id){
       return walletService.getWalletByUserId(user_id);
   }

   @PostMapping("/charge")
   public void chargeBalance(@RequestBody BalanceDto dto){
       walletService.chargeBalance(dto);
   }

}
