package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.stereotype.Component;

@Component
public class Emails {

    public void send(Question question){
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: New question...");
        System.out.println("NameFrom: " + question.getNewUser().getEmail());
        System.out.println("From: newquestion@ml.com");
        System.out.println("To: " + question.getOwnerProduct().getEmail());
        System.out.println("======================================");
    }

    public void sendNewPurchase(Purchase compra) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: New purchase...");
        System.out.println("NameFrom: " + compra.getBuyer().getEmail());
        System.out.println("From: purchases@ml.com");
        System.out.println("To: " + compra.getProduct().getOwner().getEmail());
        System.out.println("======================================");
    }

    public void sendPurchaseProcessed(Purchase purchase) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: Purchase Processed...");
        System.out.println("NameFrom: process@payments.com");
        System.out.println("From: purchases@ml.com");
        System.out.println("To: " + purchase.getProduct().getOwner().getEmail() + ", " +  purchase.getBuyer().getEmail());
        System.out.println("======================================");
    }

    public void sendPurchaseNotProcessed(Purchase purchase) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: Purchase not processed...");
        System.out.println("NameFrom: process@payments.com");
        System.out.println("From: purchase@ml.com");
        System.out.println("To: " + purchase.getProduct().getOwner().getEmail() + ", " +  purchase.getBuyer().getEmail());
        System.out.println("======================================");
    }
}