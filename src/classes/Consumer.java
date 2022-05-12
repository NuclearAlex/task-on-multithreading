package classes;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable {
    public static final String GREEN = "\u001b[32m";
    public static final String BLUE = "\u001b[34m";
    public static final String DEFAULT = "\u001b[0m";

    private final List<String> products;
    private final Stock stock;
    private final String name;

    public Consumer(String name, Stock stock) {
        this.name = name;
        this.stock = stock;
        this.products = new ArrayList<>();
    }

    public void purchase(String product) {
        String[] temp = product.split("; ");
        products.add(temp[0]);
    }

    @Override
    public String toString() {
        return "Consumer " + name + " bought products: " + BLUE + products + DEFAULT + ';';
    }

    @Override
    public void run() {
        boolean isBought = true;
        while (isBought) {
            String product = stock.getProduct();
            if (product != null) {
                purchase(product);
                System.out.println(name + " purchase " + product);
            }
            isBought = products.size() < 4;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this);
    }
}