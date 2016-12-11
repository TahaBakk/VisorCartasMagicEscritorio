package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.json.JSONException;
import java.io.IOException;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import java.util.ArrayList;


public class Controller {

    @FXML
    public TextArea name;
    public TextArea text;
    public TextArea rarity;
    public TextArea color;
    public ImageView image;
    public ListView<Cartas> cartasListView;


    public void initialize() throws IOException, JSONException {

        ApiCartas cartas = new ApiCartas();


        ArrayList<Cartas> listacartas = cartas.getCartes();

        cartasListView.setCellFactory(new Callback<javafx.scene.control.ListView<Cartas>, ListCell<Cartas>>() {
            @Override
            public ListCell<Cartas> call(javafx.scene.control.ListView<Cartas> p) {

                ListCell<Cartas> cell = new ListCell<Cartas>() {

                    @Override
                    protected void updateItem(Cartas cart, boolean bl) {
                        super.updateItem(cart, bl);
                        if (cart != null) {
                            setText(cart.getName());
                        }
                    }
                };
                return cell;
            }
        });



        ObservableList<Cartas> items = FXCollections.observableArrayList(
                listacartas);
        cartasListView.setItems(items);

        cartasListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cartas>() {

            @Override
            public void changed(ObservableValue<? extends Cartas> observable, Cartas oldValue, Cartas newValue) {

                name.setText("Name: "+newValue.getName());
                rarity.setText("Rarity: "+newValue.getRarity());
                color.setText("Color: "+newValue.getColors());
                text.setText(newValue.getText());
                Image imageUrl = new Image (newValue.getImageUrl());
                image.setImage(imageUrl);

            }
        });




    }




}
