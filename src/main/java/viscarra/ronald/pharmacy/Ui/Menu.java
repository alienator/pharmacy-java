package viscarra.ronald.pharmacy.Ui;

public class Menu {
    public enum OPTIONS {
        EXIT(0),
        LIST_NOT_EXPIRED_PRODUCTS(1),
        LIST_EXPIRED_PRODUCTS(2),
        LIST_ALL_PRODUCTS(3),
        SELL_PRODUCT(4),
        ADD_PRODUCT(5),
        EDIT_PRODUCT(6),
        DELETE_PRODUCT(7);

        private final int opt;

        OPTIONS(int opt) {
            this.opt = opt;
        }

        public int getValue() {
            return this.opt;
        }
    };

    private String[] options = {
            "1. List Not Expired Products",
            "2. List Expired Products",
            "3. List All Products",
            "4. Sell Product",
            "5. Add Product",
            "6. Edit Product",
            "7. Delete Product",
            "0. Exit"
    };

    public void display() {
        System.out.println("======PHARMACY v.1.0======");

        for (String option : options) {
            System.out.println(option);
        }

        System.out.print(":: ");
    }
}
