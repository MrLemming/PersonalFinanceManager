package org.example;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Statistic {

    private final Map<String, ProductsCategory> itemsByCategory = new TreeMap<>();
    private final Map<String, String> itemsFromRequest = new TreeMap<>();


    public Statistic() {
        loadCategoriesFromTSV();
    }

    private void loadCategoriesFromTSV() {
        try {
            File tsvFile = new File("categories.tsv");

            Scanner reader = new Scanner(tsvFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] lineSplit = line.split("\t");

                ProductsCategory currentCategory;
                String itemName = lineSplit[0];
                String categoryName = lineSplit[1];
                if (itemsByCategory.containsKey(categoryName)) {
                    currentCategory = itemsByCategory.get(categoryName);
                } else {
                    currentCategory = new ProductsCategory(categoryName);
                }
                currentCategory.addItem(itemName);
                itemsFromRequest.put(itemName, categoryName);
                itemsByCategory.put(categoryName, currentCategory);
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductsCategory getMaxCategory() {
        int maxSum = Integer.MIN_VALUE;
        ProductsCategory maxCategory = null;

        for (Map.Entry<String, ProductsCategory> entry : itemsByCategory.entrySet()) {
            if (entry.getValue().getSum() > maxSum) {
                maxSum = entry.getValue().getSum();
                maxCategory = entry.getValue();
            }
        }
        return maxCategory;
    }

    public void addItem(Request request) {
        if (itemsFromRequest.containsKey(request.title)) {
            ProductsCategory existingCategory = itemsByCategory.get(itemsFromRequest.get(request.title));
            existingCategory.totalSum(request);
        } else {
            ProductsCategory newCategory;
            if (itemsByCategory.containsKey("другое")) {
                newCategory = itemsByCategory.get("другое");
            } else {
                newCategory = new ProductsCategory("другое");
            }
            newCategory.addItem(request.title);
            newCategory.totalSum(request);
            itemsFromRequest.put(request.title, "другое");
            itemsByCategory.put("другое", newCategory);
        }
    }

    public String getServerResponse() {
        ProductsCategory response = getMaxCategory();
        return "\"maxCategory\": {" +
                "    \"category\": \"" + response.getCategoryName() + "\"," +
                "    \"sum\": \"" + response.getSum() + "\"" +
                "  }";
    }
}