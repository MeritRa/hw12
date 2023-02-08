package ru.netology.manager.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    Repository repo = new Repository();
    ProductManager manager = new ProductManager(repo);
    Product book1 = new Book(1, "Война и мир", 700, "Достоевский");
    Product book2 = new Book(2, "Благославение небожителей", 2000, "Мосян Тусню");
    Product book3 = new Book(3, "Мы вас построим", 1000, "Филлип К. Дик");
    Product phone1 = new Smartphone(4, "Nokia", 10000, "HMD Global");
    Product phone2 = new Smartphone(5, "Honor", 20000, "Huawei");
    Product phone3 = new Smartphone(6, "iPhone", 30000, "Apple");

    @Test
    public void addProductsTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {book1, book2, book3, phone1, phone2, phone3};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void addExistedIdTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        Product phone1 = new Smartphone(1, "Nokia", 10000, "HMD Global");

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.add(phone1);
        });
    }

    @Test
    public void getZeroProductsTest() {
        Product[] expected = {};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {book1, book2, book3, phone1, phone3};
        Product[] actual = repo.removeById(phone2.getId());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByWrongIdTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(7);
        });
    }

    @Test
    public void removeByUnrealIdTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(-1);
        });
    }

    @Test
    public void searchByTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {phone2};
        Product[] actual = manager.searchBy("Honor");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByWrongTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Samsung");
        Assertions.assertArrayEquals(expected, actual);
    }
}
