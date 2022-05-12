package classes;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {
    private final List<String> products;
    private final Stock stock;
    private final String name;

    public Producer(String name, Stock stock, List<String> productList) {
        this.name = name;
        this.stock = stock;
        this.products = new ArrayList<>();
        products.addAll(productList);
    }

    public String getProductToStock() {
        return products.remove(0);
    }

    public boolean isPutProduct() {
        return products.size() > 0;
    }

    @Override
    public String toString() {
        return Consumer.GREEN + "Producer " + name + " put all products to stock!"
                + Consumer.DEFAULT;
    }

    @Override
    public void run() {
        while (isPutProduct()) {
            for (int i = 0; i < 3; i++) {
                String product = getProductToStock();
                try {
                    String[] temp = product.split("; ");
                    stock.addProduct(product);
                    System.out.println(name + " added product " + temp[0] + " to stock");
                } catch (NullPointerException e) {
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (stock.isSoldProducts()) {
            System.out.println(this);
        }
    }
}