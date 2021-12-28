
public class Product {
    private int proNo,qty;
    private String proName;
    private float proMrp,price;

    public Product() {}

    public Product(int proNo, int qty, String proName, float proMrp, float price) {
        super();
        this.proNo = proNo;
        this.qty = qty;
        this.proName = proName;
        this.proMrp = proMrp;
        this.price = price;
    }

    public int getProNo() {
        return proNo;
    }

    public void setProNo(int proNo) {
        this.proNo = proNo;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public float getProMrp() {
        return proMrp;
    }

    public void setProMrp(float proMrp) {
        this.proMrp = proMrp;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
