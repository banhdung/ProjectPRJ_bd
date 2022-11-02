package dal;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<ItemCart> items;
    
    public Cart() {
        items = new ArrayList<>();
    }
    
    public Cart(List<ItemCart> items) {
        this.items = items;
    }
    
    public List<ItemCart> getItems() {
        return items;
    }
    
    public void setItems(List<ItemCart> items) {
        this.items = items;
    }
    
    private ItemCart getItemById(int id) {
        for (ItemCart i : items) {
            if (i.getProduct().getProductID() == id) {
                return i;
            }
        }
        return null;
    }
    
    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }

    //Add item to cart
    public void addItem(ItemCart t) {
        //The product already exists in the cart
        if (getItemById(t.getProduct().getProductID()) != null) {
            // Get products in cart
            ItemCart i = getItemById(t.getProduct().getProductID());
            //update Quantity ( i is old quantity  , t is new quantity to add)
            i.setQuantity(i.getQuantity() + t.getQuantity());
        } else {
            //There are no products in the cart yet
            items.add(t);            
        }
    }

    //Remove item in cart
    public void removeItem(int Id) {
        if (getItemById(Id) != null) {
            items.remove(getItemById(Id));
        }
    }
    
    //get total money in cart
    public double getToltalMoney(){
        double totalMoney = 0;
        for(ItemCart i : items){
            totalMoney += i.getQuantity()*i.getPrice();
        }
//        totalMoney=(double) Math.ceil(totalMoney*1000/1000);
        return totalMoney;
    }
    
}
