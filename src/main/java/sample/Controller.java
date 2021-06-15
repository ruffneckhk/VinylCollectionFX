package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ImageView newVinylArrow;
    @FXML
    private ImageView collectionArrow;
    @FXML
    private ImageView exitArrow;
    @FXML
    private ImageView searchArrow;

    @FXML
    private AnchorPane newVinylPane;
    @FXML
    private AnchorPane collectionPane;
    @FXML
    private AnchorPane searchPane;

    @FXML
    private ComboBox<String> comboBoxState;
    @FXML
    private ComboBox<String> comboBoxVinylArtist;
    @FXML
    private ComboBox<String> comboBoxVinylTittle;
    @FXML
    private ComboBox<String> comboBoxVinylStyle;
    @FXML
    private ComboBox<String> comboBoxVinylState;

    @FXML
    private JFXTextField txtAuthor;
    @FXML
    private JFXTextField txtTittle;
    @FXML
    private JFXTextField txtStyle;

    @FXML
    private JFXListView<String> listViewAll;

    @FXML
    private JFXButton btnSave;

    //State combobox content
    ObservableList<String> comboStateContent =
            FXCollections.observableArrayList(
                    "Nuevo",
                    "Seminuevo",
                    "Usado",
                    "Muy usado"
            );

    //Vinyl artist combobox content
    //Hay que anadir un array con el contenido de la consulta de los artistas
    //Add this ObservableList<String> list = FXCollections.observableArrayList(Arrays.toString(listOfCityData));
    ObservableList<String> comboVinylArtistContent =
            FXCollections.observableArrayList(
                    "Artista"
            );

    //Vinyl tittle combobox content
    //Hay que anadir un array con el contenido de la consulta de los titulos
    //Add this ObservableList<String> list = FXCollections.observableArrayList(Arrays.toString(listOfCityData));
    ObservableList<String> comboVinylTittleContent =
            FXCollections.observableArrayList(
                    "Titulo"
            );

    //Vinyl style combobox content
    //Hay que anadir un array con el contenido de la consulta de los estilos
    //Add this ObservableList<String> list = FXCollections.observableArrayList(Arrays.toString(listOfCityData));
    ObservableList<String> comboVinylStyleContent =
            FXCollections.observableArrayList(
                    "Estilo"
            );

    //Vinyl state combobox content
    //Hay que anadir un array con el contenido de la consulta de los estados
    //Add this ObservableList<String> list = FXCollections.observableArrayList(Arrays.toString(listOfCityData));
    ObservableList<String> comboVinylStateContent =
            FXCollections.observableArrayList(
                    "Estado"
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MySQLite sqLite = new MySQLite();
        sqLite.createNewTable();
        sqLite.selectAll();
        showAllCollection();

        comboBoxState.setItems(comboStateContent);
        comboBoxVinylArtist.setItems(comboVinylArtistContent);
        comboBoxVinylTittle.setItems(comboVinylTittleContent);
        comboBoxVinylStyle.setItems(comboVinylStyleContent);
        comboBoxVinylState.setItems(comboVinylStateContent);
    }

    //Exit button
    public void onExitButtonClicked(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    //Add new vinyl button
    public void onNewVinylButtonClicked(MouseEvent event) {
        //On double click set pane invisible
        if (newVinylPane.isVisible()) {
            newVinylPane.setVisible(false);
            newVinylArrow.setVisible(false);
            return;
        }
        //Set new vinyl pane and new vinyl arrow visible
        newVinylPane.setVisible(true);
        newVinylArrow.setVisible(true);

        //Set other panels and arrows invisible
        collectionPane.setVisible(false);
        collectionArrow.setVisible(false);

        searchPane.setVisible(false);
        searchArrow.setVisible(false);

    }

    //View collection
    public void onCollectionButtonClicked(MouseEvent event) {
        //On double click set pane invisible
        if (collectionPane.isVisible()) {
            collectionPane.setVisible(false);
            collectionArrow.setVisible(false);
            return;
        }
        //Set collection pane and collection arrow visible
        collectionPane.setVisible(true);
        collectionArrow.setVisible(true);

        //Set other panels and arrows invisible
        newVinylPane.setVisible(false);
        newVinylArrow.setVisible(false);

        searchPane.setVisible(false);
        searchArrow.setVisible(false);

        listViewAll.getItems().clear();

        showAllCollection();
    }

    //View searchCollection
    public void onSearchButtonClicked(MouseEvent event) {
        //On double click set pane invisible
        if (searchPane.isVisible()) {
            searchPane.setVisible(false);
            searchArrow.setVisible(false);
            return;
        }
        //Set collection pane and collection arrow visible
        searchPane.setVisible(true);
        searchArrow.setVisible(true);

        //Set other panels and arrows invisible
        newVinylPane.setVisible(false);
        newVinylArrow.setVisible(false);

        collectionPane.setVisible(false);
        collectionArrow.setVisible(false);
    }

    public void insertVinyl(MouseEvent event) {
        MySQLite sqLite = new MySQLite();
        sqLite.insert(txtAuthor.getText(), txtTittle.getText(), txtStyle.getText(), comboBoxState.getSelectionModel().getSelectedItem());
    }

    private void showAllCollection() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list;

        list = sqLite.selectAll();
        listViewAll.getItems().addAll(list);
    }

/*    public void onComboAuthorsChanges(ActionEvent event) {
        int i = this.comboBoxVinylArtist.getSelectionModel().getSelectedIndex();
        this.com
    }*/
}
