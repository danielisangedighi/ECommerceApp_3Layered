package com.ltp.globalsuperstore;

import java.util.ArrayList;
import java.util.List;

public class StoreRepository {
    
    //List of Item(s) selected
    //It constructs the Shopping Kart
    private List<Item> items = new ArrayList<>();

    //Read in CRUD Operations
    public Item getItem(int index) {
        return items.get(index);
    }

    //Create in CRUD Operations
    public void addItem(Item item){
        items.add(item);
    }

    //Update in CRUD Operations
    public void updateItem(Item item, int index) {
       items.set(index, item);
    }

    //Retrieve the Shopping Kart Items
    //The only accessto the Shopping Kart made Public
    public List<Item> getItems() {
        return items;
    }



}
