package br.com.zupacademy.juliana.mercadolivre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PurchaseDTO {
    @Positive
    private int amount;
    @NotNull
    private Long idProduct;
    @NotNull
    private FormPayment formPayment;

    public int getAmount() {
        return amount;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public FormPayment getFormPayment() {
        return formPayment;
    }
}