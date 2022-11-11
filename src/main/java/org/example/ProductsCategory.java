package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ProductsCategory {

    private final Set<String> items = new TreeSet<>();
    private final String categoryName;
    private int sum;

    private final List<Request> addedItems = new ArrayList<>();

    public ProductsCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean itemCategorised(String itemName) {
        return items.contains(itemName);
    }

    public void addItem(String itemName) {
        items.add(itemName);
    }

    public boolean totalSum(Request request) {
        if (itemCategorised(request.title)) {
            sum += request.sum;
            addedItems.add(request);
            return true;
        }
        return false;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getSum() {
        return sum;
    }
}