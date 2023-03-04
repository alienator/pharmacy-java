package viscarra.ronald.Product;

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
}