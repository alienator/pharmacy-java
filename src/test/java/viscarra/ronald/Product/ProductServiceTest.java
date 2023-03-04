package viscarra.ronald.Product;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
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
        Mockito.verify(repository).find();
    }

    @Test
    public void testListNotExpiredProducts() {
        // Given

        // When
        this.serviceUnderTest.listNotExpired();

        // Then
        Mockito.verify(repository).findNotExpired();
    }

    public void testListExpiredProducts() {
        // Given

        // When
        this.serviceUnderTest.listExpired();

        // Then
        Mockito.verify(repository).findExpired();
    }
}
