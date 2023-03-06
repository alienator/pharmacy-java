package viscarra.ronald.pharmacy.Product;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ProductServiceTest {

    private ProductService serviceUnderTest;
    private ProductRepository repository;

    @Before
    public void setup() {
        this.repository = mock(ProductRepository.class);
        this.serviceUnderTest = new ProductService(repository);
    }

    @Test
    public void testListAllProdcuts() {
        // Given

        // When
        this.serviceUnderTest.listAll();

        // Then
        Mockito.verify(this.repository).find();
    }

    @Test
    public void testListNotExpiredProducts() {
        // Given

        // When
        this.serviceUnderTest.listNotExpired();

        // Then
        Mockito.verify(this.repository).findNotExpired();
    }

    @Test
    public void testListExpiredProducts() {
        // Given

        // When
        this.serviceUnderTest.listExpired();

        // Then
        Mockito.verify(this.repository).findExpired();
    }

    @Test
    public void testItCanSaveProducts() {
        // Given
        Date d = new Date();
        Product product = new Product(1, "Some product", d, 3.3);

        // When
        this.serviceUnderTest.save(product);

        // Then
        Mockito.verify(this.repository).save(product);
    }

    @Test
    public void testItCanDeleteProducts() {
        // Given
        Date d = new Date();
        Product product = new Product(1, "Some product", d, 3.3);

        // When
        this.serviceUnderTest.delete(product);

        // Then
        Mockito.verify(this.repository).delete(product);
    }

    @Test
    public void testItCanSellProducts() {
        // Given
        Date d = new Date();
        Product product = new Product(1, "Some product", d, 3.3);

        // When
        ArgumentCaptor<Product> prod = ArgumentCaptor.forClass(Product.class);
        this.serviceUnderTest.sell(product);

        // Then
        Mockito.verify(this.repository).save(prod.capture());
        assertFalse(prod.getValue().isEnable());
    }
}
