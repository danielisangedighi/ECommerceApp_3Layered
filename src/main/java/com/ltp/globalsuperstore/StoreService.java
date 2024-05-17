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

    //The only Public access to all the Inventory Items
    public List<Item> getItems() {
        return storeRepository.getItems();
    }

    //Checking for the existence the Unique ID of an Item of a given Index - Parsed as a String
    //If found, the Item related to that Unique ID is returned
    public int getIndexFromId(String id) {
        //The search iteration...
        for (int i = 0; i < getItems().size(); i++) {
            if (getItem(i).getId().equals(id)) return i;
        }
        //if there is no such in existence
        return Constants.NOT_FOUND;
    }

    //Retrieving an Item with a given Unique ID
    public Item getItemfromId(String id) {
        //Check for the item using getIndexFromId method
        int index = getIndexFromId(id);
        //Return the item if found, else create a new item
        return index == Constants.NOT_FOUND ? new Item() : getItem(index);
    }
    //Calculation of the dates validity based on a logical rule
    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }

    //Field Validations for Final Submission (returning the item) after Discount Validation in main StoreController
    public String handleSubmit(Item item) {
        //set a valiable called "index" to carry the value of the Unique ID being assessed
        int index = getIndexFromId(item.getId());
        //set a variable called "status" from the Constants Constructor to be success
        String status = Constants.SUCCESS_STATUS;
        //If the status is not successful, then create a new item
        if (index == Constants.NOT_FOUND) {
            addItem(item);
        //However if the date is valid, update the item that is retireved wit the existing index.
        } else if (within5Days(item.getDate(), getItem(index).getDate())) {
            updateItem(item, index);
        //Else it fails to update.
        } else {
            status = Constants.FAILED_STATUS;
        }
        //This is where the status is returned.
        return status;
    }

}
