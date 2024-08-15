package com.hb.system.ecommerce.shoes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.entity.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
  Optional<Transaction> findByOrder(Order order);
}
