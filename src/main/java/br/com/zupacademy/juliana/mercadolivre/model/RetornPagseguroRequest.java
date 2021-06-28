package br.com.zupacademy.juliana.mercadolivre.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornPagseguroRequest implements RetornFormPayment {

    @NotBlank
    private String idTransactionPayment;
    @NotNull
    private StatusRetornPagseguro status;

    public RetornPagseguroRequest(String idTransactionPayment, StatusRetornPagseguro status) {
        this.idTransactionPayment = idTransactionPayment;
        this.status = status;
    }

    @Override
    public TransactionPayment toTransactionPayment(Purchase purchase) {
        return new TransactionPayment(status.normalize(), idTransactionPayment, purchase);
    }
}