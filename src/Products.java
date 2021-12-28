import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Products {
    private int id;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Products(int id, List<Product> products) {
        super();
        this.id = id;
        this.products = products;
    }

    public Products() {}

    private List<Product> products;

}
