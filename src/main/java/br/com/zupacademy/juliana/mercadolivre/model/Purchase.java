package br.com.zupacademy.juliana.mercadolivre.model;

import br.com.zupacademy.juliana.mercadolivre.exception.BusinessException;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @ManyToOne
    private Product product;
    @Positive
    private int amount;
    @NotNull

    @Valid
    @ManyToOne
    private NewUser buyer;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FormPayment formPayment;
    @Enumerated(EnumType.STRING)
    private StatusPurchase statusPurchase;
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.MERGE)
    private Set<TransactionPayment> transactionPayment = new HashSet<>();

    @Deprecated
    public Purchase() {
    }

    public Purchase(Product product, int amount, NewUser buyer, FormPayment formPayment) {
        this.product = product;
        this.amount = amount;
        this.buyer = buyer;
        this.formPayment = formPayment;
        this.statusPurchase = StatusPurchase.STARTED;
    }

    public Product getProduct() {
        return product;
    }

    public NewUser getBuyer() { return buyer;
    }

    public Long getId() {
        return id;
    }

    public String urlRedirection(UriComponentsBuilder uriComponentsBuilder) {
        return this.formPayment.createdUrlRetorn(this, uriComponentsBuilder);
    }

    public void addTransactionPayment(@Valid RetornFormPayment retornFormPayment) {
        TransactionPayment newTransactionPayment = retornFormPayment.toTransactionPayment(this);
        if(this.transactionPayment.contains(newTransactionPayment)){
            throw new BusinessException("There is already a transaction like the one processed "+newTransactionPayment);
        }
        Assert.isTrue(!this.transactionPayment.contains(newTransactionPayment),
                "There is already a transaction like the one processed "
                        + newTransactionPayment);
        if(!transactionsCompletedSuccessfully().isEmpty()){
            throw new BusinessException("\n" + "This purchase has already been successfully completed.");
        }
        Assert.isTrue(transactionsCompletedSuccessfully().isEmpty(), "This purchase has already been successfully completed.");
        this.transactionPayment.add(newTransactionPayment);
        this.statusPurchase = StatusPurchase.COMPLETED;
    }

    public boolean processedSuccessfully() {
        return !transactionsCompletedSuccessfully().isEmpty();
    }

    private Set<TransactionPayment> transactionsCompletedSuccessfully() {
        Set<TransactionPayment> transactionsCompletedSuccessfully = this.transactionPayment.stream()
                .filter(TransactionPayment::successfullyCompleted).collect(Collectors.toSet());

        Assert.isTrue(transactionsCompletedSuccessfully.size() <= 1, "\n" +
                "Oops! You have more than one transaction successfully completed for this purchase." + this.id);

        return transactionsCompletedSuccessfully;

    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", buyer=" + buyer +
                ", formPayment=" + formPayment +
                ", statusPurchase=" + statusPurchase +
                ", transactionPayment=" + transactionPayment +
                '}';
    }
}