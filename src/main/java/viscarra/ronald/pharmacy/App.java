package viscarra.ronald.pharmacy;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import viscarra.ronald.pharmacy.Data.Product.HibernateProductRepository;
import viscarra.ronald.pharmacy.Product.Product;
import viscarra.ronald.pharmacy.Product.ProductService;
import viscarra.ronald.pharmacy.Ui.Menu;

public class App {
    static private ProductService service = new ProductService(new HibernateProductRepository());
    static private Scanner scanner;

    public static void main(String[] args) {
        Menu menu = new Menu();

        scanner = new Scanner(System.in);
        int opt = -1;
        while (opt != 0) {
            menu.display();

            String in = scanner.nextLine();

            try {
                opt = Integer.parseInt(in);
            } catch (Exception e) {
                // System.out.println(e);
                System.out.println(">> ERROR! Please pulse a number between 0 and 7");
                System.out.println("");
            }

            switch (opt) {
                case 1:
                    List<Product> result = service.listAll();
                    System.out.println(result);
                    break;
                case 5:
                    addProduct();
                    break;
                case 3:
                    listAllProduct();
                    break;
            }

        }
        scanner.close();
        System.exit(0);
    }

    static private void listAllProduct() {
        System.out.println("\n---List all products---");

        List<Product> results = service.listAll();
        for (Product p : results) {
            System.out.println(p.toString());
        }

        System.out.println("\n---end List all products---");
    }

    static private void addProduct() {
        Product product = new Product();
        String in;

        System.out.println("\n---Add a product---");
        System.out.print("Description: ");
        in = scanner.nextLine();
        product.setDescription(in);

        System.out.print("Expires at: ");
        in = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            product.setExpiresAt(sdf.parse(in));
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.print("Price: ");
        in = scanner.nextLine();
        product.setPrice(Double.parseDouble(in));

        product.setEnable(true);

        service.save(product);

        System.out.println("\n---end Add a product---");
    }
}
