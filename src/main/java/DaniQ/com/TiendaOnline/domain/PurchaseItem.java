package DaniQ.com.TiendaOnline.domain;

public class PurchaseItem {

    private String productId;


    private int quantyItem;

    private double totaPrice;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantyItem() {
        return quantyItem;
    }

    public void setQuantyItem(int quantyItem) {
        this.quantyItem = quantyItem;
    }

    public double getTotaPrice() {
        return totaPrice;
    }

    public void setTotaPrice(double totaPrice) {
        this.totaPrice = totaPrice;
    }
}
