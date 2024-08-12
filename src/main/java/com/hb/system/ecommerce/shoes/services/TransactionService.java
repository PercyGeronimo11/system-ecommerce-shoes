package com.hb.system.ecommerce.shoes.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hb.system.ecommerce.shoes.entity.Transaction;
import com.hb.system.ecommerce.shoes.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  public void createTransaction( Transaction transaction  , MultipartFile archivo) {
 
        transaction.setTra_image(saveFile(archivo));

        transactionRepository.save(transaction);
    }

    public java.util.List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public java.util.Optional<Transaction> getTransactionById(Integer tra_id) {
        return transactionRepository.findById(tra_id);
    }




    private String saveFile(MultipartFile archivo) {
 
        try {
            String uploadDir = "vouchers"; 
            Path uploadPath = Paths.get(uploadDir);

            if (!uploadPath.toFile().exists()) {
                uploadPath.toFile().mkdirs();
            }

            String fileName = archivo.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName; 
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el archivo: " + ex.getMessage());
        }
    }


 
}
