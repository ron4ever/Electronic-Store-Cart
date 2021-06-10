import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreAppView view;

    public ElectronicStoreApp() { model = ElectronicStore.createStore();}
    @Override
    public void start(Stage stage) throws Exception {
        Pane aPane = new Pane();
        view = new ElectronicStoreAppView();
        aPane.getChildren().add(view);
        //Action event for when the stock list is selected to update the view accordingly (disable/enable certain buttons)
        view.getCurrentProductsList().setOnMousePressed(new EventHandler<MouseEvent>() {public void handle(MouseEvent mouseEvent){
            view.update(model);
        }});
        //Action event for when the cart list is selected to update the view accordingly (disable/enable certain buttons)
        view.getCartProductsList().setOnMousePressed(new EventHandler<MouseEvent>() {public void handle(MouseEvent mouseEvent){
            view.update(model);
        }});
        //Action event for the add button, gets the string of the selected element and passes it to model then updates the view
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String name = view.getCurrentProductsList().getSelectionModel().getSelectedItem();
                model.addToCart(name);
                view.update(model);
            }});
        //Action event for the reset button, sets model to the pre-set createStore() method data and updates the view.
        view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model = ElectronicStore.createStore();
                view.update(model);
            }});
        //Action event for the remove button, receives the selected index passes it to model and updates
        view.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                int index = view.getCartProductsList().getSelectionModel().getSelectedIndex();
                model.removeFromCart(model.getCartItems()[index]);
                view.update(model);
            }});
        //Action event for the sell button, call the model method to sell the cart and updates the view
        view.getSellButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
               model.sellCart();
               view.update(model);
            }});
        view.update(model);
        stage.setTitle("Electronic Store Application - " + model.getName());
        stage.setResizable(false);
        stage.setScene(new Scene(aPane));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
