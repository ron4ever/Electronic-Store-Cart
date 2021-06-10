public abstract class Appliance extends Product{
    private int wattage;
    private String color;
    private String brand;
    /**
     * Constructor of the class
     * @param initPrice the price of a certain appliance (as a double)
     * @param initStock the stock of a certain appliance (as an int)
     * @param initWattage the wattage rating of a certain appliance as an int
     * @param initColor the color of a certain appliance as a string
     * @param initBrand the brand name of a certain appliance  as a string
     */
    public Appliance(double initPrice, int initStock, int initWattage, String initColor, String initBrand) {
        super(initPrice, initStock);
        wattage = initWattage;
        color = initColor;
        brand = initBrand;
    }
    /**
     * Get method used for the toString() of the classes that extend appliance in order to format a nice toString()
     * @return the brand type of the product
     */
    public String getBrand(){return this.brand;}
    /**
     * Overrides the Product sellUnits for clarity as the class has the same sell behaviour
     * @param amount of a specific product to sell as an int
     * @return a double representing the cost (else -1.0 to represent sale did not go through)
     */
    public double sellUnits(int amount){
        return super.sellUnits(amount);
    }
    /**
     * Overrides toString() method in Product
     * @return a string indicating the attributes of the object
     */
    public String toString(){
        String description =  "(" + color + ", " + wattage + " watts)";
        return description + super.toString();
    }
    /**
     * Returns a nicer string of the product description
     * @return a string indicating the attributes of the object
     */
    public String storeDescription(){
        String description =  "(" + color + ", " + wattage + " watts)";
        return description;
    }
}
