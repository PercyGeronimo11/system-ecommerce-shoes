package com.hb.system.ecommerce.shoes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.entity.Transaction;
import com.hb.system.ecommerce.shoes.repositories.TransactionRepository;
import com.hb.system.ecommerce.shoes.services.OrderService;
import com.hb.system.ecommerce.shoes.services.TransactionService;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@RestControllerAdvice
public class TransactionController {

    private final TransactionService transactionService;
    private final OrderService orderService;
    private final TransactionRepository transactionRepository;


    @PostMapping
    public ResponseEntity<Void> createTransaction(@ModelAttribute Transaction transaction, @RequestParam("archivo") MultipartFile archivo) {
        transactionService.createTransaction(transaction, archivo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

       @GetMapping("/{tra_id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Integer tra_id) {
        Optional<Transaction> transaction = transactionRepository.findById(tra_id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/image/{ord_id}")
    public ResponseEntity<Resource> getImage(@PathVariable Integer ord_id) {
        Order order= orderService.getOrderById(ord_id).get();
        Transaction transaction = transactionRepository.findByOrder(order).get();
        try {
            Resource resource = new ClassPathResource("static/vouchers/" + transaction.getTra_image());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
