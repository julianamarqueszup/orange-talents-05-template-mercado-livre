package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.PurchaseDTO;
import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import br.com.zupacademy.juliana.mercadolivre.model.*;
import br.com.zupacademy.juliana.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ClosePurchaseController {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private Emails emails;

    @Autowired
    private EventNewPurchase eventNewPurchase;


    @PostMapping("/v1/purchases")
    @Transactional
    public ResponseEntity<String> purchase(@RequestBody @Valid PurchaseDTO request,
                                           UriComponentsBuilder uriComponentsBuilder, br.com.zupacademy.juliana.mercadolivre.repository.UserRepository userRepository) {
        Product product = manager.find(Product.class, request.getIdProduct());

        if (product == null) {
            throw new BusinessException("Product not found");
        }

        int amount = request.getAmount();
        boolean decrease = product.decreaseStock(amount);

        if (decrease) {
            NewUser buyer = userRepository.findByEmail("b@b.com")
                    .orElseThrow(() -> new BusinessException("User " +
                            "uninformed"));

            FormPayment formPayment = request.getFormPayment();

            Purchase purchase = new Purchase(product, amount, buyer, formPayment);
            manager.persist(purchase);
            emails.sendNewPurchase(purchase);

            return ResponseEntity.status(HttpStatus.FOUND).body(purchase.urlRedirection(uriComponentsBuilder));
        } else {
            throw new BusinessException("It was not possible to make the purchase due to the stock");
        }

    }

    @PostMapping("/retorn-pagseguro/{id}")
    @Transactional
    public ResponseEntity<String> processingPagSeguro(@PathVariable("id") Long idPurchase,
                                                         @RequestBody @Valid RetornPagseguroRequest request) {
        return ResponseEntity.ok(process(idPurchase, request).toString());
    }

    @PostMapping("/retorn-paypal/{id}")
    @Transactional
    public ResponseEntity<String> processingPaypal(@PathVariable("id") Long idPurchase,
                                                      @RequestBody @Valid RetornPaypalRequest request) {
        return ResponseEntity.ok(process(idPurchase, request).toString());
    }

    private Purchase process(Long idPurchase, RetornFormPayment retornFormPayment) {
        Purchase purchase = manager.find(Purchase.class, idPurchase);
        if(purchase == null) {
            throw new BusinessException("This purchase does not exist");
        }
        purchase.addTransactionPayment(retornFormPayment);
        manager.merge(purchase);
        EventNewPurchase.process(purchase);

        return purchase;
    }
}

