package viscarra.ronald.pharmacy.Data.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import junit.framework.TestCase;
import viscarra.ronald.pharmacy.Product.Product;
import viscarra.ronald.pharmacy.Product.ProductRepository;

public class ProductRepositoryTest extends TestCase {

    private ProductRepository underTest;
    private EntityManagerFactory sessionFactory;
    private List<Product> expected;

    @Override
    protected void setUp() throws Exception {
        this.sessionFactory = Persistence.createEntityManagerFactory("PHARMACY");
        this.underTest = new HibernateProductRepository();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        this.expected = new ArrayList<Product>();
        expected.add(new Product("desc1", df.parse("2000-01-01 10:10:10"), 4.5));
        expected.add(new Product("desc1", df.parse("2050-01-01 10:10:10"), 4.5));
        expected.add(new Product("desc1", df.parse("2025-01-01 10:10:10"), 4.5));
    }

    @Test
    public void testAllProductsCanBeFinded() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Product p : this.expected) {
            entityManager.persist(p);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        List<Product> actual = this.underTest.find();
        assertEquals(expected, actual);
    }

    @Test
    public void testOnlyExpiredProductscanBeFinded() {
        List<Product> expected2 = new ArrayList<>();
        expected2.add(this.expected.get(0));

        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Product p : this.expected) {
            entityManager.persist(p);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        List<Product> actual = this.underTest.findExpired();
        assertEquals(expected2, actual);
    }

    @Test
    public void testOnlyNotExpiredProductscanBeFinded() {
        List<Product> expected2 = new ArrayList<>();
        expected2.add(this.expected.get(1));
        expected2.add(this.expected.get(2));

        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Product p : this.expected) {
            entityManager.persist(p);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        List<Product> actual = this.underTest.findNotExpired();
        assertEquals(expected2, actual);
    }
}
