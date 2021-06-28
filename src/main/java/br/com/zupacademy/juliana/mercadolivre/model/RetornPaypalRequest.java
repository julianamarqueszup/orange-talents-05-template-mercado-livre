package br.com.zupacademy.juliana.mercadolivre.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornPaypalRequest implements RetornFormPayment {

    @Min(0)
    @Max(1)
    private int status;
    @NotBlank
    private String idTransactionPayment;

    public RetornPaypalRequest(int status, String idTransactionPayment) {
        this.status = status;
        this.idTransactionPayment = idTransactionPayment;
    }

    @Override
    public TransactionPayment toTransactionPayment(Purchase purchase) {
        StatusTransactionPayment statusTransaction = this.status == 0 ? StatusTransactionPayment.ERRO : StatusTransactionPayment.SUCESS;

        return new TransactionPayment(statusTransaction, idTransactionPayment, purchase);
    }
}

