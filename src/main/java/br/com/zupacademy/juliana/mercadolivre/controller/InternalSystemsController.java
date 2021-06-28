package br.com.zupacademy.juliana.mercadolivre.controller;

import br.com.zupacademy.juliana.mercadolivre.dto.NewPurchaseInvoiceDTO;
import br.com.zupacademy.juliana.mercadolivre.dto.RankingNewPurchaseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InternalSystemsController {

    @PostMapping("/invoice")
    public void criaNota(@Valid @RequestBody NewPurchaseInvoiceDTO request) throws InterruptedException {
        System.out.println("=====================================================================================");
        System.out.println("Created invoice: " + request);
        System.out.println("=====================================================================================");
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody RankingNewPurchaseDTO request) throws InterruptedException {
        System.out.println("======================================================================================");
        System.out.println("Created ranking: " + request);
        System.out.println("======================================================================================");
        Thread.sleep(150);
    }

}
