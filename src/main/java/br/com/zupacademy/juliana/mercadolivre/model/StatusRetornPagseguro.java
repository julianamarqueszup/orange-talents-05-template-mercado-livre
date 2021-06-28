package br.com.zupacademy.juliana.mercadolivre.model;

public enum StatusRetornPagseguro {
    SUCESS, ERRO;

    public StatusTransactionPayment normalize() {
        if (this.equals(SUCESS)) {
            return StatusTransactionPayment.SUCESS;
        }

        return StatusTransactionPayment.ERRO;
    }
}