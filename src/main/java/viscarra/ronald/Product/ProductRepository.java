package viscarra.ronald.Product;

import java.util.List;

public interface ProductRepository {

    public List<Product> find();

    public List<Product> findNotExpired();

    public List<Product> findExpired();
}