package viscarra.ronald.pharmacy.Data.Product;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import viscarra.ronald.pharmacy.Product.Product;
import viscarra.ronald.pharmacy.Product.ProductRepository;

public class HibernateProductRepository implements ProductRepository {
    private EntityManagerFactory sessionFactory;

    public HibernateProductRepository() {
        this.sessionFactory = Persistence.createEntityManagerFactory("PHARMACY");
    }

    public List<Product> find() {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Product> result = entityManager
                .createQuery("from Product", Product.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return result;
    }

    public List<Product> findNotExpired() {
        Date dt = new Date();

        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Product> results = entityManager
                .createQuery("select p from Product p where expiresAt > :dt", Product.class)
                .setParameter("dt", dt)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return results;

    }

    public List<Product> findExpired() {
        Date dt = new Date();

        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Product> results = entityManager
                .createQuery("select p from Product p where expiresAt < :dt", Product.class)
                .setParameter("dt", dt)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();

        return results;
    }

    public void save(Product product) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        // entityManager.persist(product);
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Product product) {
        product.setEnable(false);
        this.save(product);
    }

    public Product findById(int id) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Product result = new Product();
        try {
            result = entityManager
                    .createQuery("select p from Product p where id=:id", Product.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
        }

        entityManager.close();

        return result;
    }
}
