package br.com.zupacademy.juliana.mercadolivre.model;

public interface RetornFormPayment {
    TransactionPayment toTransactionPayment(Purchase purchase);
}