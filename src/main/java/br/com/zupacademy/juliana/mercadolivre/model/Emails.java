package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.stereotype.Component;

@Component
public class Emails {

    public void send(Question question) {
        System.out.println("======================================");
        System.out.println("EMAIL");
        System.out.println("Subject: New question...");
        System.out.println("NameFrom: " + question.getNewUser().getEmail());
        System.out.println("From: newquestion@ml.com");
        System.out.println("To: " + question.getOwnerProduct().getEmail());
        System.out.println("======================================");
    }

}