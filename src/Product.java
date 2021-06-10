public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartAmount;
    /**
     * Constructor of the abstract class
     * @param initPrice the price a certain product costs (as a double)
     * @param initStock the amount of a certain product available (as an int)
     */
    public Product(double initPrice, int initStock){
        price = initPrice;
        stockQuantity = initStock;
        cartAmount = 0;
    }
    //Modifier Methods to track how much of the product is in the cart
    public void increaseCartAmount(){
        cartAmount++;
    }
    public void decreaseCartAmount(){
        cartAmount--;
    }
    //get methods
    public int getStockQuantity(){
        return stockQuantity;
    }
    public int getCartAmount(){
        return cartAmount;
    }
    public int getSoldQuantity() {
        return soldQuantity;
    }
    public double getPrice(){return price;}
    //set method for when the cart is sold and there is no more product in the cart
    public void resetCartAmount(){ cartAmount = 0;}
    /**
     * Abstract method, returns a nice string to display in the view for user and not the full toString() of the product.
     * @return a string description of a certain product subclass
     */
    public abstract String storeDescription();
    /**
     * The sellUnits method for a product, takes amount of a certain product to sell and checks if it is less or equal-
     * to the stock of that product and if the stock of the product is bigger then 0 to allow the sale
     * updates the stock of the product and how many units of it were sold accordingly and returns the total price
     * if sale failed returns a -1.0 for ElectronicStore class to catch
     * @param amount of a specific product to sell as an int
     * @return a double representing the cost (else -1.0 to represent sale did not go through)
     */
    public double sellUnits(int amount){
        if ((amount <= this.stockQuantity)&&(this.stockQuantity > 0)){
                                //decrease stock quantity the specific product
            this.stockQuantity = this.stockQuantity-amount;
                                //increase sold quantity for the specific product
            this.soldQuantity = this.soldQuantity + amount;
            return (this.price*amount);
        }
        return -1.0;
    }
    /**
     * Overrides default toString() method in object
     * @return a string indicating the attributes of the object
     */
    public String toString(){
        return  "\n(" + this.price +
                " dollars each, " + this.stockQuantity + " in stock, "+ soldQuantity + " sold)";
    }
}
