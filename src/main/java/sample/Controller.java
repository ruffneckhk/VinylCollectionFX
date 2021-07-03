package sample;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private ImageView searchArrow;
    @FXML
    private ImageView imageUpdate;
    @FXML
    private ImageView imageSave;
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
    private Label idLabel;

    @FXML
    private JFXListView<String> listViewAll;

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
        ArrayList<String> list;
        list = sqLite.selectAllAuthors();
        comboVinylArtistContent =
                FXCollections.observableArrayList(
                        list
                );
    }

    //Vinyl tittle combobox content
    public void setComboTittle() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list;
        list = sqLite.selectAllTittles();
        comboVinylTittleContent =
                FXCollections.observableArrayList(
                        list
                );
    }

    //Vinyl style combobox content
    public void setComboStyle() {
        MySQLite sqLite = new MySQLite();
        ArrayList<String> list;
        list = sqLite.selectAllStyles();
        comboVinylStyleContent =
                FXCollections.observableArrayList(
                        list
                );
    }

    //Exit button
    public void onExitButtonClicked() {
        Platform.exit();
        System.exit(0);
    }

    //Add new vinyl button
    public void onNewVinylButtonClicked() {
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

        imageUpdate.setVisible(false);
        imageSave.setVisible(true);
    }

    //View collection
    public void onCollectionButtonClicked() {
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
    public void onSearchButtonClicked() {
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

        MySQLite sqLite = new MySQLite();
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

    public void insertVinyl() {
        if (txtAuthor.getText().isEmpty() && txtTittle.getText().isEmpty() && txtStyle.getText().isEmpty() && comboBoxState.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar Disco");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
        } else {
            MySQLite sqLite = new MySQLite();
            sqLite.insert(txtAuthor.getText(), txtTittle.getText(), txtStyle.getText(), comboBoxState.getSelectionModel().getSelectedItem());
            newVinylPane.setVisible(false);
            collectionPane.setVisible(true);

            listViewAll.getItems().clear();

            showAllCollection();
            setComboAuthor();
            setComboTittle();
            setComboStyle();
        }

    }

    public void updateVinyl() {
        if (txtAuthor.getText().isEmpty() && txtTittle.getText().isEmpty() && txtStyle.getText().isEmpty() && comboBoxState.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Actualizar Disco");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
        } else {
            MySQLite sqLite = new MySQLite();
            sqLite.update(Integer.parseInt(idLabel.getText()),txtAuthor.getText(), txtTittle.getText(), txtStyle.getText(), comboBoxState.getSelectionModel().getSelectedItem());

            newVinylPane.setVisible(false);
            collectionPane.setVisible(true);

            listViewAll.getItems().clear();

            showAllCollection();
        }
    }

    public void onButtonSearchClick() {
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
        String selected;
        if (comboBoxVinylArtist.isDisable() && comboBoxVinylStyle.isDisable()) {
            selected = "'" + comboBoxVinylState.getValue() + "'";
            MySQLite sqLite = new MySQLite();
            ArrayList<String> list;
            list = sqLite.selectAllByState(selected);
            listViewAll.getItems().addAll(list);
        } else if (comboBoxVinylState.isDisable() && comboBoxVinylStyle.isDisable()) {
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
        comboBoxVinylState.setDisable(false);
        comboBoxVinylStyle.setDisable(false);
        comboBoxVinylArtist.setDisable(false);

        comboBoxVinylArtist.getSelectionModel().clearSelection();
        comboBoxVinylStyle.getSelectionModel().clearSelection();
        comboBoxVinylState.getSelectionModel().clearSelection();
    }

    public void onComboAuthorsChanges() {
        comboBoxVinylState.setDisable(true);
        comboBoxVinylStyle.setDisable(true);
    }

    public void onComboStylesChanges() {
        comboBoxVinylState.setDisable(true);
        comboBoxVinylArtist.setDisable(true);
    }

    public void onComboStatesChanges() {
        comboBoxVinylArtist.setDisable(true);
        comboBoxVinylStyle.setDisable(true);
    }

    public void onListViewClick() {

        if (!listViewAll.getSelectionModel().isEmpty()) {
            String selected = listViewAll.getSelectionModel().getSelectedItem();
            int pointPosition = selected.indexOf('.');
            String stringId = selected.substring(0, pointPosition);
            int id = Integer.parseInt(stringId);
            MySQLite sqLite = new MySQLite();
            sqLite.delete(id);

            listViewAll.getItems().clear();
            ArrayList<String> list;
            list = sqLite.selectAll();
            listViewAll.getItems().addAll(list);
        }


    }

    public void sendToUpdateFromList() {

        if (!listViewAll.getSelectionModel().isEmpty()) {
            String selected = listViewAll.getSelectionModel().getSelectedItem();
            int pointPosition = selected.indexOf('.');
            String stringId = selected.substring(0, pointPosition);
            int id = Integer.parseInt(stringId);
            MySQLite sqLite = new MySQLite();

            Vinyl vinyl;
            vinyl = sqLite.selectById(id);

            System.out.println(selected);

            txtAuthor.setText(vinyl.getAuthor());
            txtTittle.setText(vinyl.getTittle());
            txtStyle.setText(vinyl.getStyle());
            idLabel.setText(String.valueOf(id));

            imageSave.setVisible(false);
            imageUpdate.setVisible(true);

            //Set new vinyl pane and new vinyl arrow visible
            newVinylPane.setVisible(true);
            newVinylArrow.setVisible(true);

            //Set other panels and arrows invisible
            collectionPane.setVisible(false);
            collectionArrow.setVisible(false);

            searchPane.setVisible(false);
            searchArrow.setVisible(false);

        }
    }
}
