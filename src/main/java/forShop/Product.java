package forShop;

public class Product {
    private String name;
    private boolean deleted = false;
    private float price;
    private int number;

    public Product(String name, boolean deleted, float price, int number) {
        this.name = name;
        this.deleted = deleted;
        this.price = price;
        this.number = number;
    }


    /**
     * Конструктор лише для видалення об'єкта класу forShop.Product
     *
     * @param name назва товару
     */
    public Product(String name) {
        this.name = name;
    }

    //визначити кількість товару на складі
    public int getNumber() {
        return number;
    }

    //списати певну кількість товару
    public void remove(int n) {
        if (number == 0) return;
        if (n > number) {
            number = 0;
        } else {
            number -= n;
        }
    }

    //додати певну кількість товару
    public void add(int n) {
        number += n;
    }

    //встановити ціну на товар
    public void setPrice(int price) {
        this.price = price;
    }


}