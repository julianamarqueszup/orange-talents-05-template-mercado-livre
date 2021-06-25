package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import br.com.zupacademy.juliana.mercadolivre.model.Product;
import br.com.zupacademy.juliana.mercadolivre.model.ProductDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class ProductDetailController {
    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<ProductDetail> getProductDetail(@PathVariable("id") Long id) {
        Product product = manager.find(Product.class, id);

        if(product == null) {
            throw new BusinessException("Product informed does not exist", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(new ProductDetail(product));
    }
}
