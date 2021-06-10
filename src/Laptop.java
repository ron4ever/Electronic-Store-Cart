public class Laptop extends Computer{
    private double screenSize;
    /**
     * Constructor of the class
     * @param initPrice represents how much the laptop costs as a double
     * @param initQuantity represents how many units of this laptop there are in stock as an int
     * @param initCpuSpeed the CPU speed, in Ghz (as a double)
     * @param initRam the amount of RAM in GB (as an int)
     * @param initSsd whether the hard-drive is an SSD (true) or HDD (false)
     * @param initStorage the size of the hard-drive in GB (as an int)
     * @param initScreenSize the size of the screen in inches (as a double)
     */
    public Laptop(double initPrice, int initQuantity, double initCpuSpeed, int initRam,
                  boolean initSsd, int initStorage, double initScreenSize) {
        super(initPrice, initQuantity, initCpuSpeed, initRam, initSsd, initStorage);
        screenSize = initScreenSize;
    }
    /**
     * Overrides the Computer sellUnits (and by extension the Product sellUnits) for clarity as the class has
     * the same sell behaviour
     * @param amount of a specific product to sell as an int
     * @return a double representing the cost (else -1.0 to represent sale did not go through)
     */
    public double sellUnits(int amount){
        return super.sellUnits(amount);
    }
    /**
     * Overrides toString() method in Computer
     * @return a string indicating the attributes of the object
     */
    public String toString(){
        String description = screenSize + " inch Laptop ";
        return description + super.toString();
    }
    /**
     * Returns a nicer string of the product description
     * @return a string indicating the attributes of the object
     */
    public String storeDescription(){
        String description = screenSize + " inch Laptop ";
        return description + super.storeDescription();
    }
}
