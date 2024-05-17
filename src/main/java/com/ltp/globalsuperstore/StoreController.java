package com.ltp.globalsuperstore;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StoreController {

    StoreService storeService = new StoreService();

    //Presentation of the Form View with a Model Object
    @GetMapping("/")
    //validation of the Unique ID of the Object on the Form View
    public String getForm(Model model, @RequestParam(required = false) String id) {
        //Form to be returned assuming ID already exists
        model.addAttribute("item", storeService.getItemfromId(id));
        //Form View getting returned
        return "form";
    }

    //Collection of Form View Data
    @PostMapping("/submitItem")

    //Input Validation of the Fields for the Object being Created, or Updated by CRUD Operations.
    //The validation is done before submission here or the Discount Field of the Object about to be submitted
    public String handleSubmit(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
        if (item.getPrice() < item.getDiscount()) {
            result.rejectValue("price", "", "Price cannot be less than discount");
        }
        if (result.hasErrors()) return "form";
        //This is the point where the final submission is happening on the StoreService layer.
        String status = storeService.handleSubmit(item);
        //The status is flashed to the View
        redirectAttributes.addFlashAttribute("status", status);
        //The Shopping Kart (Inventory View) is then revealed
        return "redirect:/inventory";
    }

    //Presentation of the Inventory View with a Model Object
    @GetMapping("/inventory")
    public String getInventory(Model model) {
        //Retrieval of the Shooping Kart Items
        model.addAttribute("items", storeService.getItems());
        return "inventory";
    }

    //Input Validation of the Fields for the Object being Created, or Updated by CRUD Operations.
    //The validation is done before submission here or the Date Field of the Object about to be submitted
    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }

}
