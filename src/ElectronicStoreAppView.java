import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
class ElectronicStoreAppView extends Pane {
    //Active components in the view
    private ListView<String>    cartProductsList,currentProductsList,popularList ;
    private Button  addButton, removeButton, resetButton, sellButton;
    private Label currentTotal;
    private TextField numOfSales,revenue,average;
    //non-active labels
    private Label summaryTxt,salesTxt,revTxt,avgTxt,popItemTxt,strStockTxt;
    // Get Methods
    public ListView<String> getCurrentProductsList() { return currentProductsList; }
    public ListView<String> getCartProductsList() { return cartProductsList; }
    public Button getAddButton(){return addButton;}
    public Button getRemoveButton(){return removeButton;}
    public Button getResetButton(){return resetButton;}
    public Button getSellButton(){return sellButton;}
    /**
     * Constructor of the view, creates and positions all the various elements to be displayed in the App class
     */
    public ElectronicStoreAppView(){
       currentProductsList = new ListView<String>();
       currentProductsList.setPrefSize(270,290);
       currentProductsList.relocate(230,40);

        cartProductsList = new ListView<String>();
        cartProductsList.setPrefSize(270,290);
        cartProductsList.relocate(510,40);

        popularList = new ListView<String>();
        popularList.setPrefSize(210,160);
        popularList.relocate(10,170);

        strStockTxt = new Label("Store Stock:");
        strStockTxt.setStyle("-fx-font: 15 arial;");
        strStockTxt.relocate(325,20);

        currentTotal = new Label("Current Cart:");
        currentTotal.setStyle("-fx-font: 15 arial;");
        currentTotal.relocate(590,20);

        popItemTxt = new Label("Most Popular Items:");
        popItemTxt.setStyle("-fx-font: 15 arial;");
        popItemTxt.relocate(50,145);

        summaryTxt = new Label("Store Summary:");
        summaryTxt.setStyle("-fx-font: 15 arial;");
        summaryTxt.relocate(60,20);

        salesTxt = new Label("# Sales:");
        salesTxt.setStyle("-fx-font: 15 arial;");
        salesTxt.relocate(60,45);

        revTxt = new Label("Revenue:");
        revTxt.setStyle("-fx-font: 15 arial;");
        revTxt.relocate(50,80);

        avgTxt = new Label("$ / Sale:");
        avgTxt.setStyle("-fx-font: 15 arial;");
        avgTxt.relocate(58,115);

        numOfSales = new TextField();
        numOfSales.setPrefSize(100,30);
        numOfSales.relocate(120,40);
        numOfSales.setEditable(false);

        revenue = new TextField();
        revenue.setPrefSize(100,30);
        revenue.relocate(120,75);
        revenue.setEditable(false);

        average = new TextField();
        average.setPrefSize(100,30);
        average.relocate(120,110);
        average.setEditable(false);

        addButton = new Button("Add to Cart");
        addButton.setPrefSize(135,50);
        addButton.setStyle("-fx-font: 12 arial;");
        addButton.relocate(300,340);

        removeButton = new Button("Remove from Cart");
        removeButton.setPrefSize(135,50);
        removeButton.setStyle("-fx-font: 12 arial;");
        removeButton.relocate(510,340);

        sellButton = new Button("Complete Sale");
        sellButton.setPrefSize(135,50);
        sellButton.setStyle("-fx-font: 12 arial;");
        sellButton.relocate(645,340);

        resetButton = new Button("Reset Store");
        resetButton.setPrefSize(135,50);
        resetButton.setStyle("-fx-font: 12 arial;");
        resetButton.relocate(50,340);
        //Adding all the components to the pane
        getChildren().addAll(currentProductsList,cartProductsList,
                popularList,strStockTxt,currentTotal,popItemTxt,numOfSales,revenue,average,summaryTxt,salesTxt,revTxt,
                avgTxt,addButton,removeButton,sellButton,resetButton);
        setPrefSize(800, 400);
    }
    /**
     * General update method for the view, checks and updates every aspect of the view accordingly
     * @param model the model class whose data is used to update the view
     */
    public void update(ElectronicStore model){
        updatePopularList(model);
        updateProductList(model);
        updateCartList(model);
        updateEstimate(model);
        updateStoreSummary(model);
    }
    /**
     * Updates the 3 text boxes on the left corner of the store (#sales, avg, total) accordingly if various
     * products were sold
     * @param model the model class whose data is used to update the view
     */
    public void updateStoreSummary(ElectronicStore model){
        numOfSales.setText(""+model.getNumberOfSales());
        String rev = String.format("%.2f", model.getRevenue());
        revenue.setText(rev);
        if (model.average() == -1.0){
            average.setText("N/A");
        }
        else {
            String avg = String.format("%.2f", model.average());
            average.setText(avg);
        }
    }
    /**
     * Updates the current cart label on top right when a product is added/removed from the cart
     * @param model the model class whose data is used to update the view
     */
    public void updateEstimate(ElectronicStore model){
        String estimated = String.format("%.2f", model.estimateInCart());
        currentTotal.setText("Current Cart(" + estimated + "$):");
    }
    /**
     * Creates a string cart array based on the cart size, fills it in with a loop and sets it as the
     * current array of the cart in the view. Additionally checks if to disable or enable the sell & remove buttons.
     * @param model the model class whose data is used to update the view
     */
    public void updateCartList(ElectronicStore model){
        String[] cart = new String[model.getAmountInCart()];
        for (int i = 0; i < cart.length; i++) {
            cart[i] = model.findProduct(model.getCartItems()[i]).getCartAmount() +" X "+ model.getCartItems()[i];
        }
        ObservableList<String> listCart = FXCollections.observableArrayList(cart);
        cartProductsList.setItems(listCart);
        removeButton.setDisable(cartProductsList.getSelectionModel().getSelectedIndex() <0);
        sellButton.setDisable(cart.length < 1);
    }
    /**
     * Creates a string stock array based on the stock size, fills it in with a loop and sets it as the
     * current array of the stock in the view. Additionally checks if to disable or enable the add button.
     * @param model the model class whose data is used to update the view
     */
    public void updateProductList(ElectronicStore model){
        String[] stock = new String[model.getStockCount()];
        for (int i = 0; i < stock.length; i++) {
            stock[i] = model.getStockItems()[i];
        }
        ObservableList<String> listInStock = FXCollections.observableArrayList(stock);
        currentProductsList.setItems(listInStock);
        addButton.setDisable(currentProductsList.getSelectionModel().getSelectedIndex() <0);
    }
    /**
     * Creates a (currently product) array based on the mostPopular products array size, fills it in with a loop
     * and sets it as the current array of the popular products in the view.
     * @param model the model class whose data is used to update the view
     */
    public void updatePopularList(ElectronicStore model){
        String[] mostPopular = new String[model.mostSold().length];
        for (int i = 0; i < mostPopular.length; i++) {
            mostPopular[i] = model.mostSold()[i].storeDescription();
        }
        ObservableList<String> listPopular = FXCollections.observableArrayList(mostPopular);
        popularList.setItems(listPopular);
    }
}
