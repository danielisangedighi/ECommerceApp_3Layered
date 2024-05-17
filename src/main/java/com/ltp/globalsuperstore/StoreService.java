package com.ltp.globalsuperstore;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StoreService {
     
    StoreRepository storeRepository = new StoreRepository();
    
    //Read in CRUD Operations
    public Item getItem(int index) {
        return storeRepository.getItem(index);

    }

    //Create in CRUD Operations
    public void addItem(Item item){
        storeRepository.addItem(item);
    }

    //Update in CRUD Operations
    public void updateItem(Item item, int index) {
        storeRepository.updateItem(item, index);
    }

    //Retrieve the Shopping Kart Items
    //The only accessto the Shopping Kart made Public
    public List<Item> getItems() {
        return storeRepository.getItems();
    }

    public int getIndexFromId(String id) {
        for (int i = 0; i < getItems().size(); i++) {
            if (getItem(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }
    public String handleSubmit() {
        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;
        if (index == Constants.NOT_FOUND) {
            storeService.addItem(item);
        } else if (within5Days(item.getDate(), storeService.getItem(index).getDate())) {
            storeService.updateItem(item, index);
        } else {
            status = Constants.FAILED_STATUS;
        }
    }

}
