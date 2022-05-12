package classes;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Stock {
    private final BlockingQueue<String> products;
    private boolean isSold;

    public Stock(int capacity) {
        this.products = new ArrayBlockingQueue<>(capacity);
        this.isSold = false;
    }

    public synchronized void addProduct(String product) {
        String[] dummy = product.split("; ");
        products.add(dummy[0]);
    }

    public synchronized String getProduct() {
        String product = null;
        if (products.size() > 0) {
            product = products.poll();
            isSold = false;
        }
        return product;
    }

    public synchronized boolean isSoldProducts() {
        if (isSold) {
            getProduct();
        } else {
            isSold = true;
        }
        return isSold;
    }
}