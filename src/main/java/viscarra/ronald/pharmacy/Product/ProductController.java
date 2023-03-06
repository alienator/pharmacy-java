package viscarra.ronald.pharmacy.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductController {

    public List<Product> list() {
        Date nd = new Date();
        List<Product> expected = new ArrayList<Product>();

        for (int i = 0; i < 3; i++) {
            Product p1 = new Product(1, "Description", nd, 2.2);
            expected.add(p1);
        }

        return expected;
    }
}
