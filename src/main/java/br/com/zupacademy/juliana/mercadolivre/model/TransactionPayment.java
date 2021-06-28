package br.com.zupacademy.juliana.mercadolivre.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class TransactionPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTransactionPayment status;
    private String idTransactionGateway;
    @NotNull
    private OffsetDateTime timestamp;
    @NotNull
    @Valid
    @ManyToOne
    private Purchase purchase;

    @Deprecated
    public TransactionPayment() {
    }


    public TransactionPayment(StatusTransactionPayment statusTransactionPayment, String idTransactionPayment, Purchase purchase) {
        this.status = statusTransactionPayment;
        this.purchase = purchase;
        this.idTransactionGateway = idTransactionPayment;
        this.timestamp = OffsetDateTime.now();
    }

    public boolean successfullyCompleted() {
        return this.status.equals(StatusTransactionPayment.SUCESS);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", status=" + status +
                ", idTransactionGateway='" + idTransactionGateway + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}