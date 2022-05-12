import classes.Consumer;
import classes.Producer;
import classes.Stock;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Runner {

    public static void main(String[] args) {
        int capacityStock = 100;

        List<String> products = createList();
        System.out.println("Products which will be created and added by producer:");
        for (String str : products) {
            System.out.println(Consumer.GREEN + str + Consumer.DEFAULT);
        }
        System.out.println();

        Stock stock = new Stock(capacityStock);
        Producer producer = new Producer("\"OSCORP\"", stock, products);
        String[] consumersList = new String[]{
                "Alex", "Piter", "Pablo", "William", "Vadim"//, "Boris", "Nick", "Demetrio",      // 5 names
//                "Yahor", "Nathan", "Barry", "Aramis", "Atos", "Portos", "Ludwig", "Amadeus",
//                "Vladimir", "Vasily", "Maxim", "Constantine", "Eugene", "Nikita", "Frederic",
//                "Yuri", "Oleg", "Valdemar", "Antoine", "Vitally", "Sergio", "Adolf"             // 30 names
        };
        ExecutorService es = Executors.newFixedThreadPool(consumersList.length + 1);
        es.submit(producer);
        for (String s : consumersList) {
            es.submit(new Consumer(s, stock));
        }
        es.shutdown();
    }

    public static List<String> createList() {
        List<String> products = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("src/classes/products.txt"))) {
            while (sc.hasNextLine()) {
                products.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
}

                //  HELP ME PLEASE