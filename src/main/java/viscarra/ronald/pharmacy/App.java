package viscarra.ronald.pharmacy;

import java.util.List;
import java.util.Scanner;

import viscarra.ronald.pharmacy.Data.Product.HibernateProductRepository;
import viscarra.ronald.pharmacy.Product.Product;
import viscarra.ronald.pharmacy.Product.ProductService;
import viscarra.ronald.pharmacy.Ui.Menu;

public class App {
    static private ProductService service = new ProductService(new HibernateProductRepository());

    public static void main(String[] args) {
        Menu menu = new Menu();

        Scanner scanner = new Scanner(System.in);
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
                case 2:
                    System.out.println("2");
                    break;
            }
        }
        scanner.close();
        System.exit(0);
    }
}
