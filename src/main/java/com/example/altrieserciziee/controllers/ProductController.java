package com.example.altrieserciziee.controllers;

import com.example.altrieserciziee.entities.Product;
import com.example.altrieserciziee.entities.User;
import com.example.altrieserciziee.exceptions.BadRequestException;
import com.example.altrieserciziee.payloads.NewProductDTO;
import com.example.altrieserciziee.payloads.NewProductResponseDTO;
import com.example.altrieserciziee.payloads.NewUserDTO;
import com.example.altrieserciziee.payloads.NewUserResponseDTO;
import com.example.altrieserciziee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public NewProductResponseDTO productResponseDTO (@RequestBody @Validated NewProductDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewProductResponseDTO(this.productService.save(body).getId(), body.title(), body.price(), body.category(), body.img(), body.description(), body.details(), body.isOnSale(), body.discountedPercentage());
    }

    @GetMapping
    public List<Product> getAll(@RequestParam(defaultValue = "id") String sortBy){
        return this.productService.getAll(sortBy);
    }

    @GetMapping("/{productId:[0-9a-fA-F-]{36}}")
    public Optional<Product> getProductById(@PathVariable UUID productId) {
        return Optional.ofNullable(this.productService.findById(productId));
    }


    @GetMapping("/onSale")
    public List<Product> getProductByIsOnSale(
            @RequestParam(name = "isOnSale", required = false, defaultValue = "true") Boolean isOnSale) {
        return this.productService.findByIsOnSale(isOnSale);
    }

    @GetMapping("/allProduct")
    public List<Product> getAllProduct(@RequestParam(defaultValue = "title") String sortBy){
        return this.productService.getAll(sortBy);
    }




    @GetMapping("/title/{productTitle}")
    public Optional<Product> getProductByTitle(@PathVariable String productTitle){
        return Optional.ofNullable(this.productService.findByTitle(productTitle));
    }



    @PutMapping("/{productId}/patch")
    public ResponseEntity<Product> patchProduct (
            @PathVariable UUID productId,
            @RequestBody @Validated NewProductDTO body,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }


        Product updatedProduct = productService.patchProduct(

                body.title(),
                body.price(),
                body.category(),
                body.img(),
                body.description(),
                body.details(),
                body.isOnSale(),
                body.discountedPercentage());

return ResponseEntity.ok(updatedProduct);
    }}