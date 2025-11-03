package com.Ecommerce.wallet.service;


import com.Ecommerce.wallet.model.dto.BalanceDto;
import com.Ecommerce.wallet.model.entity.Wallet;
import com.Ecommerce.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

   @Autowired
    WalletRepository walletRepository;


   public Optional<Wallet> getWalletByUserId(Long user_id){
       return  walletRepository.findByUserId(user_id);
   }
   public void chargeBalance(BalanceDto dto){
       Wallet walletEntity = walletRepository.findByUserId(dto.getWallet_id()).get();
       walletEntity.setBalance(walletEntity.getBalance()+dto.getBalance());
       walletRepository.save(walletEntity);
   }
}
