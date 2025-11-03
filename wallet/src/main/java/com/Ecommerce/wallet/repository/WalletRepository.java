package com.Ecommerce.wallet.repository;

import com.Ecommerce.wallet.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
   Optional<Wallet> findByUserId(Long user_id);
}
