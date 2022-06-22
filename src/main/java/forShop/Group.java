package forShop;

import java.util.ArrayList;

public class Group {
    public String name;
    public String description;
    public ArrayList<Product> products;

    /**
     * Конструктор для створення групи товарів
     *
     * @param name
     */
    public Group(String name, String description) {
        this.name = name;
        this.description = description;
        products = new ArrayList<>(5);
    }

    public Group(String name) {
        this.name = name;
    }

    /**
     * Додає об'єкт класу forShop.Product до групи товарів
     *
     * @param product товар, який необхідно додати до групи
     * @return true - якщо додавання успішне, в іншому разі - false
     */
    public boolean addProduct(Product product) {
        return products.add(product);
    }


}