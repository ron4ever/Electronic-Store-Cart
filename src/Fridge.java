public class Fridge extends Appliance {
    private double cubicFeet;
    private boolean hasFreezer;
    /**
     * Constructor of the class
     * @param initPrice represents how much the fridge costs as a double
     * @param initStock represents how many units of this fridge there are in stock as an int
     * @param initWattage the wattage rating of the fridge as an int
     * @param initColor the color of the fridge as a string
     * @param initBrand the brand name of the fridge as a string
     * @param initCubeFeet The volume of the fridge in cubic feet as a double
     * @param initHasFreezer Whether the fridge has a freezer or not (T-> has a freezer)
     */
    public Fridge(double initPrice, int initStock, int initWattage,
                  String initColor, String initBrand, double initCubeFeet, boolean initHasFreezer) {
        super(initPrice, initStock, initWattage, initColor, initBrand);
        cubicFeet = initCubeFeet;
        hasFreezer = initHasFreezer;
    }
    /**
     * Overrides the Appliance sellUnits (and by extension the Product sellUnits) for clarity as the class has
     * the same sell behaviour
     * @param amount of a specific product to sell as an int
     * @return a double representing the cost (else -1.0 to represent sale did not go through)
     */
    public double sellUnits(int amount){
        return super.sellUnits(amount);
    }
    /**
     * Overrides toString() method in Appliance
     * @return a string indicating the attributes of the object
     */
    public String toString(){
        String description = cubicFeet + " cu. ft. " + this.getBrand() + " Fridge ";
        //trying out ternary operator to reduce code from tutorial 2 recommendation
        description = hasFreezer ? description + "with Freezer " :  description + "";
        return description + super.toString();
    }
    /**
     * Returns a nicer string of the product description
     * @return a string indicating the attributes of the object
     */
    public String storeDescription(){
        String description = cubicFeet + " cu. ft. " + this.getBrand() + " Fridge ";
        description = hasFreezer ? description + "with Freezer " :  description + "";
        return description + super.storeDescription();
    }
}
