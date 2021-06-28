package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventNewPurchase {
    @Autowired
    private Set<EventPurchaseSuccess> eventPurchaseSuccess;
    @Autowired
    private Emails emails;

    public static void process(Purchase purchase) {
        if (purchase.processedSuccessfully()) {
            eventPurchaseSuccess.forEach(event -> event.process(purchase));
            emails.sendPurchaseProcessed(purchase);
        } else {
            emails.sendPurchaseNotProcessed(purchase);
            throw new BusinessException("\n" +
                    "Error processing purchase event");
        }
    }
}