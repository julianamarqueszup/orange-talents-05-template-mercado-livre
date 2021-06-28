package br.com.zupacademy.juliana.mercadolivre.model;

import org.springframework.web.util.UriComponentsBuilder;

public enum FormPayment {
    paypal {
        String createdUrlRetorn(Purchase purchase, UriComponentsBuilder uriComponentsBuilder) {
            String uriRetornPaypal = uriComponentsBuilder.path("/retorn-paypal/{id}")
                    .buildAndExpand(purchase.getId())
                    .toString();

            return "paypal.com?buyerId=" + purchase.getId() + "&redirectUrl=" + uriRetornPaypal;
        }
    },
    pagseguro {
        String createdUrlRetorn(Purchase purchase, UriComponentsBuilder uriComponentsBuilder) {
            String uriRetornPaypal = uriComponentsBuilder.path("/retorn" +
                    "-pagseguro" +
                    "/{id}")
                    .buildAndExpand(purchase.getId())
                    .toString();

            return "pagseguro.com?returnId=" + purchase.getId() + "&redirectUrl=" + uriRetornPaypal;
        }
    };

    abstract String createdUrlRetorn(Purchase purchase,
                                   UriComponentsBuilder uriComponentsBuilder);
}
