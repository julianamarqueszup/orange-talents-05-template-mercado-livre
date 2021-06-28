package br.com.zupacademy.juliana.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class NewPurchaseInvoiceDTO {
    @NotNull
    private Long idPurchase;
    @NotNull
    private Long idBuyer;

    public NewPurchaseInvoiceDTO(Long idPurchase, Long idBuyer) {
        this.idPurchase = idPurchase;
        this.idBuyer = idBuyer;
    }

    @Override
    public String toString() {
        return "NewPurchaseInvoiceDTO{" +
                "idPurchase=" + idPurchase +
                ", idBuyer=" + idBuyer +
                '}';
    }
}