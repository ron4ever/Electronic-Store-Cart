public class ToasterOven extends Appliance{
    private int width;
    private boolean convection;
    /**
     * Constructor of the class
     * @param initPrice represents how much the toaster oven costs as a double
     * @param initStock represents how many units of this toaster are in stock as an int
     * @param initWattage the wattage rating of the toaster oven as an int
     * @param initColor the color of the toaster oven as a string
     * @param initBrand the brand name of the toaster oven as a string
     * @param initWidth the width of the toaster oven as an int
     * @param initConvection whether the toaster has convection heating or not (T -> has it)
     */
    public ToasterOven(double initPrice, int initStock, int initWattage,
                       String initColor, String initBrand, int initWidth, boolean initConvection) {
        super(initPrice, initStock, initWattage, initColor, initBrand);
        width = initWidth;
        convection = initConvection;
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
        String description = width + " inch " + this.getBrand() + " Toaster ";
        //trying out ternary operator to reduce code from tutorial 2 recommendation
        description = convection ? description + "with convection " : description + "";
         return description + super.toString();
    }
    /**
     * Returns a nicer string of the product description
     * @return a string indicating the attributes of the object
     */
    public String storeDescription(){
        String description = width + " inch " + this.getBrand() + " Toaster ";
        description = convection ? description + "with convection " : description + "";
        return description + super.storeDescription();
    }
}
