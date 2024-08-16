package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.dto.request.ProductCustomerReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductCustomerResp;
import com.hb.system.ecommerce.shoes.entity.Customer;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.entity.ProductCustomer;
import com.hb.system.ecommerce.shoes.exceptions.NotFoundException;
import com.hb.system.ecommerce.shoes.exceptions.ServiceException;
import com.hb.system.ecommerce.shoes.repositories.CustomerRepository;
import com.hb.system.ecommerce.shoes.repositories.ProductCustomerRepository;
import com.hb.system.ecommerce.shoes.repositories.ProductRepository;
import com.hb.system.ecommerce.shoes.services.ProductCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCustomerServiceImpl implements ProductCustomerService {
    @Autowired
    private ProductCustomerRepository productCustomerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void increaseClicksProductService(ProductCustomerReq productCustomer) {
        try {
            if (productCustomer.getCustomer_id() == null) {
                throw new ServiceException("El campo customer_id no puede ser nulo.");
            }
            if (productCustomer.getProduct_id() == null) {
                throw new ServiceException("El campo product_id no puede ser nulo.");
            }
            if (productCustomer.getClicks() == null) {
                throw new ServiceException("El campo rating no puede ser nulo.");
            }

            Integer customer_id = productCustomer.getCustomer_id();
            Integer product_id = productCustomer.getProduct_id();

            Optional<ProductCustomer> productCustomerFind = productCustomerRepository.findByCustomer_IdAndProduct_Id(customer_id, product_id);
            Optional<Product> productFind = productRepository.findById(product_id);
            Optional<Customer> customerFind = customerRepository.findById(customer_id);

            if (!productFind.isPresent()) {
                throw new ServiceException("Producto no encontrado con ID: " + product_id);
            }
            if (!customerFind.isPresent()) {
                throw new ServiceException("Cliente no encontrado con ID: " + customer_id);
            }

            if (productCustomerFind.isPresent()) {
                ProductCustomer existingProductCustomer = productCustomerFind.get();
                existingProductCustomer.setClicks(existingProductCustomer.getClicks()+1);
                productCustomerRepository.save(existingProductCustomer);
            } else {
                ProductCustomer newProductCustomer = ProductCustomer.builder()
                        .customer(customerFind.get())
                        .product(productFind.get())
                        .clicks(1)
                        .build();
                productCustomerRepository.save(newProductCustomer);
            }

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Aumento la interacción exitosamente", e);
        }
    }


    @Override
    public void saveProductCustomerService(ProductCustomerReq productCustomer) {
        try {
            if (productCustomer.getCustomer_id() == null) {
                throw new ServiceException("El campo customer_id no puede ser nulo.");
            }
            if (productCustomer.getProduct_id() == null) {
                throw new ServiceException("El campo product_id no puede ser nulo.");
            }
            if (productCustomer.getRating() == null) {
                throw new ServiceException("El campo rating no puede ser nulo.");
            }

            Integer customer_id = productCustomer.getCustomer_id();
            Integer product_id = productCustomer.getProduct_id();
            Integer rating = productCustomer.getRating();

            Optional<ProductCustomer> productCustomerFind = productCustomerRepository.findByCustomer_IdAndProduct_Id(customer_id, product_id);
            Optional<Product> productFind = productRepository.findById(product_id);
            Optional<Customer> customerFind = customerRepository.findById(customer_id);

            if (!productFind.isPresent()) {
                throw new ServiceException("Producto no encontrado con ID: " + product_id);
            }
            if (!customerFind.isPresent()) {
                throw new ServiceException("Cliente no encontrado con ID: " + customer_id);
            }

            if (productCustomerFind.isPresent()) {
                ProductCustomer existingProductCustomer = productCustomerFind.get();
                existingProductCustomer.setRating(rating);
                productCustomerRepository.save(existingProductCustomer);
            } else {
                ProductCustomer newProductCustomer = ProductCustomer.builder()
                        .customer(customerFind.get())
                        .product(productFind.get())
                        .rating(rating)
                        .build();
                productCustomerRepository.save(newProductCustomer);
            }
            List<ProductCustomer> productCustomerList = productCustomerRepository.findByProduct(productFind.get());
            int totalRating = productCustomerList.stream().mapToInt(ProductCustomer::getRating).sum();
            int average = totalRating / productCustomerList.size();
            productFind.get().setProAverageRating(average);
            productRepository.save(productFind.get());

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al guardar o actualizar la calificación", e);
        }
    }


    @Override
    public List<ProductCustomerResp> findByCustomerIdService(Integer customerId) {
        try {
            Optional<Customer> customer=customerRepository.findById(customerId);
            if(customer.isPresent()){
                List<ProductCustomer> productCustomers = productCustomerRepository.findByCustomer(customer.get());
                if (productCustomers.isEmpty()) {
                    throw new NotFoundException("No se encontraron productos para el cliente con ID " + customerId);
                }
                List<ProductCustomerResp> dtos = productCustomers.stream()
                        .map(pc -> new ProductCustomerResp(pc.getCustomer().getId(), pc.getProduct().getId(), pc.getRating(), pc.getClicks()))
                        .collect(Collectors.toList());
                return dtos;
            }else{
                throw new NotFoundException("No se encontro al cliente con ID " + customerId);
            }

        } catch (Exception e) {
            throw new ServiceException("Error al obtener los productos del cliente", e);
        }
    }
}