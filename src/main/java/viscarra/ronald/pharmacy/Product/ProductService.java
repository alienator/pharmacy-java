package viscarra.ronald.pharmacy.Product;

import java.util.List;

public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAll() {
        return this.repository.find();
    }

    public List<Product> listNotExpired() {
        return this.repository.findNotExpired();
    }

    public List<Product> listExpired() {
        return this.repository.findExpired();
    }

    public void save(Product product) {
        this.repository.save(product);
    }

    public void delete(Product product) {
        this.repository.delete(product);
    }

    public void sell(Product product) {
        product.setEnable(false);
        this.repository.save(product);
    }

}
