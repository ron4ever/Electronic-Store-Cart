// used in the 3 most bought products method to easily just get the first 3 spots after array is sorted.
import java.util.Arrays;
public class ElectronicStore {
    private final int MAX_PRODUCTS = 10;
    private String name;
    private double revenue;
    // counts for the product array
    private int productCount;
    private int numberOfSales;
    // help track exact amount in the arrays that are displayed in the view
    private int stockCount;
    private int amountInCart;
    // store the strings of the products that are displayed in the view
    private String[] cartItems = new String[MAX_PRODUCTS];
    private String[] stockItems = new String[MAX_PRODUCTS];
    //stores an array of electronic store products
    private Product[] products = new Product[MAX_PRODUCTS];
    /**
     * Constructor of the class
     * @param initName the store name
     */
    public ElectronicStore(String initName){
        name = initName;
        numberOfSales = 0;
        stockCount = 0;
        amountInCart = 0;
        revenue = 0;
        productCount = 0;
    }
    /**
     * Get method to view the store name
     * @return the name of a store
     */
    public String getName(){return name;}
    /**
     * Get method to view the store revenue
     * @return the store revenue
     */
    public double getRevenue(){return revenue;}
    // more get methods
    public String[] getCartItems(){return cartItems;}
    public String[] getStockItems(){return stockItems;}
    public int getNumberOfSales(){return numberOfSales;}
    public int getAmountInCart(){return amountInCart;}
    public int getStockCount(){return stockCount;}
    /**
     * Calculates the stores average money earned per sale
     * @return the average or -1 if no sales were made.
     */
    public double average(){
        if (numberOfSales !=0){
            return revenue/numberOfSales;
        }
       return -1;
    }
     /**
     * Finds the 3 most sold products using reverse bubble sort, not the optimal sort but good enough ;)
     * @return a list of the 3 most sold products utilizing the array class so I don't have to write
     * another for loop to get the info, so it saves a few lines.
     */
    public Product[] mostSold() {
        for (int i = 0; i < productCount - 1; i++) {
            for (int j = 0; j < productCount - i - 1; j++) {
                if (products[j].getSoldQuantity() < products[j + 1].getSoldQuantity()) {
                    Product temp = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = temp;
                }
            }
        }
        Product[] popProducts = Arrays.copyOfRange(products, 0, 3);
        return popProducts;
    }
    /**
     * loops through the string cart array and calculates the cost of it when the sell button would be pressed
     * @return the cost of all the current items in the string cart array.
     */
    public double estimateInCart(){
        double estimate = 0;
        for (int i = 0; i < amountInCart; i++) {
                estimate = estimate + findProduct(cartItems[i]).getPrice()*findProduct(cartItems[i]).getCartAmount();
        }
        return estimate;
    }
    /**
     * helper method to find the index of a certain product in the Products array
     * @param p the product to find the index for. used in the sellCart() method since it receives the items to be sold
     * as strings and needs to find the actual product and it's index in order to utilize sellProducts().
     * @return the index of the product or -1 if it was not found.
     */
    public int giveIndex(Product p){
        for (int i = 0; i < productCount; i++) {
            if (products[i] == p) {
                return i;
            }
        }
        return -1;
    }
    /**
     * helper method used to find a certain item type Product based on it's string used and return it for it to be
     * modified or allow the use of get method on it.
     * @param name the name of the product that needs to be found
     * @return the item type Product that matches the strings description or null if a product was not found based on
     * it's string.
     */
    public Product findProduct(String name) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].storeDescription().equals(name)) {
                return products[i];
            }
        }
        return null;
    }
    /**
     * helper method to remove products from the string stock array that is displayed in the view
     * @param name the name (string) of the item to remove
     */
    public void remove(String name){
        for (int i=0; i<stockCount; i++) {
            if (findProduct(stockItems[i]).storeDescription().equals(name)) {
                stockItems[i] = stockItems[stockCount-1];
                stockCount--;
            }
        }
    }
    /**
     * Adds a string of an item to the cary string array, checks if the product string is already in the cart, if it is not
     * it creates it there and if the products amount in cart reaches 10 uses a helper method to remove the string of that
     * product from the products array.
     * @param name the name (string) of the item to remove
     */
    public void addToCart(String name){
        boolean isFound = false;
        for (int i = 0; i < amountInCart; i++) {
            if (cartItems[i].equals(name)){
                findProduct(name).increaseCartAmount();
                isFound = true;
            }
            if(findProduct(name).getCartAmount() == 10 ||
                    findProduct(name).getCartAmount() >= findProduct(name).getStockQuantity()){
                remove(name);
            }
        }
        if (!isFound){
            if (amountInCart < MAX_PRODUCTS){
                cartItems[amountInCart++] = name;
                findProduct(name).increaseCartAmount();
                if (findProduct(name).getCartAmount() >= findProduct(name).getStockQuantity()){
                    remove(name);
                }
            }
        }
    }
    /**
     * Removes the string of the item from the cart based on the stock array,
     * if item was not found in the stock it adds it to the array otherwise it just decreases the items amount in the array
     * and if that particular item's amount reaches zero it simply shrinks the array replacing its spot with the last item in
     * the array.
     * @param name the name (string) of the item to remove
     */
    public void removeFromCart(String name) {
        boolean isFound = false;
        for (int i = 0; i < stockCount; i++) {
            if (stockItems[i].equals(name)) {
                findProduct(name).decreaseCartAmount();
                isFound = true;
            }
        }
        if (!isFound) {
            if (stockCount < MAX_PRODUCTS) {
                stockItems[stockCount++] = name;
                findProduct(name).decreaseCartAmount();
            }
        }
         for (int i = 0; i < amountInCart; i++) {
            if (findProduct(cartItems[i]).getCartAmount() <= 0){
                cartItems[i] = cartItems[amountInCart-1];
                amountInCart--;
            }
        }
    }
    /**
     * Adds a product to a store products array and view string array if space permits
     * @param p the product to add to the store array
     * @return true if successful or false if adding a product failed
     */
    public boolean addProduct(Product p){
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            if (products[i] == null ){
                products[i] = p;
                productCount++;
                stockItems[i] = p.storeDescription();
                stockCount++;
                return true;
            }
        }
        return false;
    }
    /**
     * Loops through the current items in cart and sells them using the SellProducts method, as well as resets the amount
     * of each item in the cart and the size of the cart array back to 0.
     */
    public void sellCart(){
        for (int i = 0; i < amountInCart; i++) {
            sellProducts(giveIndex(findProduct(cartItems[i])),findProduct(cartItems[i]).getCartAmount());
            findProduct(cartItems[i]).resetCartAmount();
        }
        amountInCart = 0;
        numberOfSales++;
    }
    /**
     * Sells amount units of the product stored at index item in the products array, if possible.
     * Used by the user operated sellProducts() method to check if the index and amount given can be sold
     * @param item the item in the store array items to sell (i.e its spot)
     * @param amount the amount of that specific items to sell
     * @return true if sale was successful or false otherwise
     */
    public boolean sellProducts(int item, int amount){
       if (item > MAX_PRODUCTS-1){ return false;}
       if (products[item] != null){
           double tradePass = this.products[item].sellUnits(amount);
           if (tradePass != -1.0){
               this.revenue = this.revenue + tradePass;
               return true;
           }
        }
        return false;
    }
    //gives all the stuff for the store
    public static ElectronicStore createStore(){
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }

}
