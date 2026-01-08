package org.skypro.skyshop.model.basket;
import org.skypro.skyshop.model.product.Product;


public class ProductBasket {
    private final Product[] products = new Product[5];

    // Метод добавления продукта
    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                System.out.println("Продукт \"" + product.getName() + "\" добавлен в корзину.");
                return;
            }
        }
        System.out.println("Невозможно добавить продукт: корзина заполнена.");
    }

    // Метод получения общей стоимости корзины
    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getPrice();
            }
        }
        return total;
    }

    // Метод печати содержимого корзины
    public void printBasket() {
        boolean empty = true;
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
                empty = false;
            }
        }
        if (empty) {
            System.out.println("В корзине пусто.");
        } else {
            System.out.println("Итого: " + getTotalPrice());
        }
    }

    // Метод проверки продукта по имени
    public boolean contains(String name) {
        for (Product product : products) {
            if (product != null && product.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // Метод очистки корзины
    public void clearBasket() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
        System.out.println("Корзина очищена.");
    }
}
