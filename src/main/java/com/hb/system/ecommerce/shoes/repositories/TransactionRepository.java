package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
  
}
