package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Invoice extends EventNewPurchase {
    @Override
    public void process(Purchase purchase) {
        Assert.isTrue(purchase.processedSuccessfully(), "Purchase not successful: " + purchase);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idPurchase", purchase.getId(), "idBuyer", purchase.getBuyer().getId());

        restTemplate.postForEntity("http://localhost:8080/invoice", request, String.class);
    }
}
