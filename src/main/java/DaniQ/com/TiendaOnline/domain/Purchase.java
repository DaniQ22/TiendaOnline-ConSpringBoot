package DaniQ.com.TiendaOnline.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Purchase {
    private int purchaseId;

    private LocalDateTime purchaseDate;

    private String customerId;

    private String dateailPurchase;

    private List<PurchaseItem> items;

    private Customer customer;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDateailPurchase() {
        return dateailPurchase;
    }

    public void setDateailPurchase(String dateailPurchase) {
        this.dateailPurchase = dateailPurchase;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItem> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
