package ru.netology.manager.products;

public class Repository {
    protected Product[] products = new Product[0];

    public void save(Product product) {
        if (findById(product.id) != null) {
            throw new AlreadyExistsException("Element with id: " + product.id + "already exists");
        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product[] removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
        return tmp;
    }

    public Product[] getProducts() {
        return products;
    }

    public Product findById(int goalId) {
        for (Product product : products) {
            if (product.getId() == goalId) {
                return product;
            }
        }
        return null;
    }
}
