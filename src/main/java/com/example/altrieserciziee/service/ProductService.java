package com.example.altrieserciziee.service;

import com.example.altrieserciziee.entities.Product;
import com.example.altrieserciziee.entities.User;
import com.example.altrieserciziee.exceptions.BadRequestException;
import com.example.altrieserciziee.exceptions.NotFoundException;
import com.example.altrieserciziee.payloads.NewProductDTO;
import com.example.altrieserciziee.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public Product save(NewProductDTO body){
        if (this.productRepository.findByTitle(body.title()).isPresent()) {
            throw new BadRequestException("Product already exists!");
        }
        Product newProduct = new Product(
                body.details(),
                body.discountedPercentage(),
                body.isOnSale(),
                body.description(),
                body.img(),
                body.category(),
                body.price(),
                body.title());
        newProduct.calculateDiscountedPrice();

        return this.productRepository.save(newProduct);
    }

    // patch product

    public Product patchProduct(String title, Float price, String category, List<String> img, String description, String details, Boolean isOnSale, Integer discountedPercentage ){
       Product product = productRepository.findByTitle(title).orElseThrow(()-> new NotFoundException("Product with title: " + title + " not found!"));
       product.setTitle(title);
       product.setPrice(price);
       product.setCategory(category);
       product.setImg(img);
       product.setDescription(description);
       product.setDetails(details);
       product.setIsOnSale(isOnSale);
       product.setDiscountedPercentage(discountedPercentage);
       return productRepository.save(product);
    }

    public List<Product> getAll(String sortBy){
        return productRepository.findAll(Sort.by(sortBy));
    }

    public Product findById(UUID productId){
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException(productId));
    }

    public Product findByTitle(String title){
        return productRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(title));
    }

    public List<Product> findByCategories(String categories){
        return productRepository.findByCategory(categories);
    }

    public List<Product> findByIsOnSale(Boolean isOnSale){
        return productRepository.findByIsOnSale(isOnSale);
    }

    public List<Product> findAllByOrderByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<Product> findAllByOrderByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }
}
