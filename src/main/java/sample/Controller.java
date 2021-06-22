package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    ObservableList<String> comboVinylArtistContent;
    ObservableList<String> comboVinylTittleContent;
    ObservableList<String> comboVinylStyleContent;

    //State combobox content
    ObservableList<String> comboStateContent =
            FXCollections.observableArrayList(
                    "Nuevo",
                    "Seminuevo",
                    "Usado",
                    "Muy usado"
            );


    //Vinyl state combobox content
    ObservableList<String> comboVinylStateContent =
            FXCollections.observableArrayList(
                    "Nuevo",
                    "Seminuevo",
                    "Usado",
                    "Muy usado"
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MySQLite sqLite = new MySQLite();
        sqLite.createNewTable();
        sqLite.selectAll();
        showAllCollection();
        setComboAuthor();
        setComboTittle();
        setComboStyle();

        comboBoxState.setItems(comboStateContent);
        comboBoxVinylArtist.setItems(comboVinylArtistContent);
        comboBoxVinylStyle.setItems(comboVinylStyleContent);
        comboBoxVinylState.setItems(comboVinylStateContent);

    }

    //Vinyl artist combobox content
    public void setComboAuthor() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list = new ArrayList<>();
        list = sqLite.selectAllAuthors();
        comboVinylArtistContent =
                FXCollections.observableArrayList(
                        list
                );
    }

    //Vinyl tittle combobox content
    public void setComboTittle() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list = new ArrayList<>();
        list = sqLite.selectAllTittles();
        comboVinylTittleContent =
                FXCollections.observableArrayList(
                        list
                );
    }

    //Vinyl style combobox content
    public void setComboStyle() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list = new ArrayList<>();
        list = sqLite.selectAllStyles();
        comboVinylStyleContent =
                FXCollections.observableArrayList(
                        list
                );
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

        txtAuthor.setText("");
        txtStyle.setText("");
        txtTittle.setText("");


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
        if (txtAuthor.getText().isEmpty()&&txtTittle.getText().isEmpty()&&txtStyle.getText().isEmpty()&&comboBoxState.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar Disco");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
        } else {
            MySQLite sqLite = new MySQLite();
            sqLite.insert(txtAuthor.getText(), txtTittle.getText(), txtStyle.getText(), comboBoxState.getSelectionModel().getSelectedItem());
        }

    }

    public void onButtonSearchClick(MouseEvent event) {
        //Set collection pane and collection arrow visible
        collectionPane.setVisible(true);
        collectionArrow.setVisible(true);

        //Set other panels and arrows invisible
        newVinylPane.setVisible(false);
        newVinylArrow.setVisible(false);

        searchPane.setVisible(false);
        searchArrow.setVisible(false);

        listViewAll.getItems().clear();
        showAllCollectionSearch();
    }

    private void showAllCollection() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list;

        list = sqLite.selectAll();
        listViewAll.getItems().addAll(list);
    }

    private void showAllCollectionSearch() {
        String selected = "";
        if(comboBoxVinylArtist.isDisable()&&comboBoxVinylStyle.isDisable()) {
            selected = "'" + comboBoxVinylState.getValue() + "'";
            MySQLite sqLite = new MySQLite();
            ArrayList<String> list;
            list = sqLite.selectAllByState(selected);
            listViewAll.getItems().addAll(list);
        } else if (comboBoxVinylState.isDisable()&&comboBoxVinylStyle.isDisable()){
            selected = "'" + comboBoxVinylArtist.getValue() + "'";
            MySQLite sqLite = new MySQLite();
            ArrayList<String> list;
            list = sqLite.selectAllByAuthor(selected);
            listViewAll.getItems().addAll(list);
        } else {
            selected = "'" + comboBoxVinylStyle.getValue() + "'";
            MySQLite sqLite = new MySQLite();
            ArrayList<String> list;
            list = sqLite.selectAllByStyle(selected);
            listViewAll.getItems().addAll(list);
        }
        comboBoxVinylArtist.setDisable(false);
        comboBoxVinylState.setDisable(false);
        comboBoxVinylStyle.setDisable(false);
    }

    public void onComboAuthorsChanges(ActionEvent event) {
        comboBoxVinylState.setDisable(true);
        comboBoxVinylStyle.setDisable(true);
    }

    public void onComboStylesChanges(ActionEvent event) {
        comboBoxVinylState.setDisable(true);
        comboBoxVinylArtist.setDisable(true);
    }

    public void onComboStatesChanges(ActionEvent event) {
        comboBoxVinylArtist.setDisable(true);
        comboBoxVinylStyle.setDisable(true);
    }
}
