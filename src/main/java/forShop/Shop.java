package forShop;

import java.util.ArrayList;

public class Shop {
    ArrayList<Group> groups; //список груп товарів

    /**
     * Конструктор створення складу
     */
    public Shop() {
        groups = new ArrayList<>(1);
    }

    /**
     * Додає групу товарів до складу
     *
     * @param group група товарів, яку необхідно додати
     * @return true, якщо додавання успішне, в іншому випадку - false
     */
    public boolean addGroup(Group group) {
        return groups.add(group);
    }
}