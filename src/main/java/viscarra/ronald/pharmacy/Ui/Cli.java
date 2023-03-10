package viscarra.ronald.pharmacy.Ui;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import viscarra.ronald.pharmacy.Data.Product.HibernateProductRepository;
import viscarra.ronald.pharmacy.Product.Product;
import viscarra.ronald.pharmacy.Product.ProductService;

public class Cli {
    private ProductService service = new ProductService(new HibernateProductRepository());
    private Scanner scanner;

    public void run() {
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
                    listNotExpired();
                    break;
                case 2:
                    listExpired();
                    break;
                case 3:
                    listAllProduct();
                    break;
                case 4:
                    sellProduct();
                    break;
                case 5:
                    addProduct();
                    break;
                case 6:
                    editProduct();
                    break;
                case 7:
                    deleteProduct();
                    break;
            }

        }
        scanner.close();
        System.exit(0);
    }

    private void editProduct() {
        System.out.println("\n---Edit a product---");
        System.out.print("Id: ");

        String in;
        in = scanner.nextLine();

        int id = 0;
        try {
            id = Integer.parseInt(in);
        } catch (Exception e) {
            System.out.println(e);
        }

        Product product = service.findById(id);
        if (product.getId() <= 0) {
            System.out.println("Wrong product!");
            return;
        }

        System.out.println("Product details:");
        System.out.println(product.toString());

        System.out.print("New Desripciton: (empty to not change): ");
        in = scanner.nextLine();
        if (!in.trim().isEmpty()) {
            product.setDescription(in);
        }

        System.out.print("New Expiritaion date: (empty to not change): ");
        in = scanner.nextLine();
        if (!in.trim().isEmpty()) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                product.setExpiresAt(df.parse(in));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.print("New Price: (empty to not change): ");
        in = scanner.nextLine();
        if (!in.trim().isEmpty()) {
            try {
                double p = Double.parseDouble(in);
                product.setPrice(p);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        service.save(product);
        System.out.println("\n---end Edit a product---");
    }

    private void deleteProduct() {
        System.out.println("\n---Delete a product---");
        System.out.print("Id: ");

        String in;
        in = scanner.nextLine();

        int id = 0;
        try {
            id = Integer.parseInt(in);
        } catch (Exception e) {
            System.out.println(e);
        }

        Product product = service.findById(id);
        if (product.getId() <= 0) {
            System.out.println("Wrong product!");
            return;
        }

        service.delete(product);

        System.out.println("\n---end Delete a product---");
    }

    private void sellProduct() {
        System.out.println("\n---Sell a product---");
        System.out.print("Id: ");

        String in;
        in = scanner.nextLine();

        int id = 0;
        try {
            id = Integer.parseInt(in);
        } catch (Exception e) {
            System.out.println(e);
        }

        Product product = service.findById(id);
        if (product.getId() <= 0) {
            System.out.println("Wrong product!");
            return;
        }

        service.sell(product);

        System.out.println("\n---end Sell a product---");
    }

    private void listExpired() {
        System.out.println("\n---List expired products---");

        List<Product> results = service.listExpired();
        for (Product p : results) {
            System.out.println(p.toString());
        }

        System.out.println("\n---end List expired products---");
    }

    private void listNotExpired() {
        System.out.println("\n---List not expired products---");

        List<Product> results = service.listNotExpired();
        for (Product p : results) {
            System.out.println(p.toString());
        }

        System.out.println("\n---end List not expired products---");
    }

    private void listAllProduct() {
        System.out.println("\n---List all products---");

        List<Product> results = service.listAll();
        for (Product p : results) {
            System.out.println(p.toString());
        }

        System.out.println("\n---end List all products---");
    }

    private void addProduct() {
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
