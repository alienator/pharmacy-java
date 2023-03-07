package viscarra.ronald.pharmacy.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> find();

    public List<Product> findNotExpired();

    public List<Product> findExpired();

    public void save(Product product);

    public void delete(Product product);

    public Product findById(int id);
}
