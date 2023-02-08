package ru.netology.manager.products;

public class Smartphone extends Product {
    private String maker;


    public Smartphone(int id, String name, int price, String maker) {
        super(id, name, price);
        this.maker = maker;
    }
}
