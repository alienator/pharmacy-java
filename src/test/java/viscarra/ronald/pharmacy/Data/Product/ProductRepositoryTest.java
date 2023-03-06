package viscarra.ronald.pharmacy.Data.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    protected void tearDown() {
        this.sessionFactory.close();
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

    @Test
    public void testAProductCanBeAdded() {
        // Given
        Product expected = new Product("desc", new Date(), 5.8);

        // When
        this.underTest.save(expected);
        expected.setId(1);

        // Then
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product actual = entityManager
                .createQuery("from Product order by id desc", Product.class)
                .setMaxResults(1)
                .getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        assertEquals(expected, actual);

    }

    @Test
    public void testAProductCanBeUpdated() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Product p : this.expected) {
            entityManager.persist(p);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        Product expected2 = new Product(1, "desc desc desc", new Date(), 5.8);
        this.underTest.save(expected2);

        entityManager = sessionFactory.createEntityManager();
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product actual = entityManager
                .createQuery("from Product order by id asc", Product.class)
                .setMaxResults(1)
                .getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        assertEquals(expected2, actual);
    }

    @Test
    public void testAProductCanBeDelted() {
        /**
         * Aproduct is not delted but instead is disabled!
         */
        // Given
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        for (Product p : this.expected) {
            entityManager.persist(p);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        Product expected = this.expected.get(0);

        // When
        this.underTest.delete(expected);

        // Then
        entityManager = sessionFactory.createEntityManager();
        entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product actual = entityManager
                .createQuery("from Product where id=1", Product.class)
                .setMaxResults(1)
                .getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        assertFalse(actual.isEnable());
    }
}
