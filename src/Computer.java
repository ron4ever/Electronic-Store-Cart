public abstract class Computer extends Product{
    private double cpuSpeed;
    private int ram;
    private boolean ssd;
    private int storage;
    /**
     * Constructor of the class
     * @param innitPrice the price of a certain computer type (as a double)
     * @param quantity the stock of a certain computer type (as an int)
     * @param initCpuSpeed the CPU speed of a certain computer type in Ghz (as a double)
     * @param initRam the amount of RAM of a certain computer type in GB (as an int)
     * @param initSsd whether the hard-drive is an SSD (true) or HDD (false)
     * @param initStorage the size of the hard-drive of a certain computer type in GB (as an int)
     */
    public Computer(double innitPrice, int quantity, double initCpuSpeed, int initRam, boolean initSsd, int initStorage) {
        super(innitPrice, quantity);
        cpuSpeed = initCpuSpeed;
        ram = initRam;
        ssd = initSsd;
        storage = initStorage;
    }
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
        String description = "PC with " + cpuSpeed + "ghz CPU, " + ram + "GB RAM, " + storage + "GB ";
        //trying out ternary operator to reduce code from tutorial 2 recommendation
        description = ssd ? description + "SSD drive." : description + "HDD drive.";
                                //Calls super to minimize code duplication
        return description + super.toString();
    }
    /**
     * Returns a nicer string of the product description
     * @return a string indicating the attributes of the object
     */
    public String storeDescription(){
        String description = "PC with " + cpuSpeed + "ghz CPU, " + ram + "GB RAM, " + storage + "GB ";
        description = ssd ? description + "SSD drive." : description + "HDD drive.";
        return description;
    }
}
