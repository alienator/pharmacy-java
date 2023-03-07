package viscarra.ronald.pharmacy.Ui;

public class Pharmacy {
    public void menu() {
        String[] options = {
                "1. List Not Expired Products",
                "2. List Expired Products",
                "3. List All Products",
                "4. Sell Product",
                "5. Add Product",
                "6. Edit Product",
                "7. Delete Product",
                "0. Exit"
        };

        for (String option : options) {
            System.out.println(option);
        }
    }
}
