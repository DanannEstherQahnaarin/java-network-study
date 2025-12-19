package com.mtcoding.cafe;

import java.util.ArrayList;
import java.util.List;

class Barista {
    private Item workItem;

    public void Order(Item item){
        this.workItem = item;
    }

    public Coffee workToCoffeeMachine(){
        return new Machine().makeCoffee(workItem);
    }
}

abstract class Item{
    abstract String getName();
    abstract int getPrice();
}

class Coffee extends Item{
    private String name;
    private int price;

    public Coffee(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}

class Cake extends Item {
    private String name;
    private int price;

    public Cake(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

class Menu {
    private List<Item> menuList;

    public Menu() {
        this.menuList = new ArrayList<>();
        setMenu();
    }

    private void setMenu(){
        menuList.add(new Coffee("Espresso", 3000));
        menuList.add(new Coffee("Americano", 1500));
        menuList.add(new Coffee("Cappuccino", 2500));
        menuList.add(new Cake("Cake", 10000));
    }

    public List<Item> getMenuList() {
        return menuList;
    }
}

class Guest {
    private String orderMenu;

    public Guest(String orderMenu) {
        this.orderMenu = orderMenu;
    }

    public Item Order(Menu menuList) {
        for(Item item : menuList.getMenuList()){
            if(orderMenu.equals(item.getName())){
                return item;
            }
        }

        return null;
    }
}

class Machine {
    public Coffee makeCoffee(Item item){
        return (Coffee)item;
    }
}

public class Cafe {
    public static void main(String[] args) {
        String guestOrder = "Americano";
        Menu menu = new Menu();

        Guest guest = new Guest(guestOrder);
        Item item = guest.Order(menu);

        Barista barista = new Barista();
        barista.Order(item);
        Coffee coffe = barista.workToCoffeeMachine();

        System.out.println(coffe.toString());

    }
}
