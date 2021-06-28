package br.com.zupacademy.juliana.mercadolivre.dto;

import javax.validation.constraints.NotNull;

public class RankingNewPurchaseDTO {
    @NotNull
    private Long idPurchase;
    @NotNull
    private Long idOwnerProduct;

    public RankingNewPurchaseDTO(Long idPurchase, Long idOwnerProduct) {
        this.idPurchase = idPurchase;
        this.idOwnerProduct = idOwnerProduct;
    }

    @Override
    public String toString() {
        return "RankingNewPurchaseDTO{" +
                "idPurchase=" + idPurchase +
                ", idOwnerProduct=" + idOwnerProduct +
                '}';
    }
}
