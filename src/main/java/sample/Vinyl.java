package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Vinyl {

    ArrayList<ObservableList<String>> authorList = new ArrayList<>();
    ArrayList<ObservableList<String>> tittleList = new ArrayList<>();
    ArrayList<ObservableList<String>> styleList = new ArrayList<>();

    private String author;
    private String tittle;
    private String style;
    private String State;

    ObservableList<String> authorItems =
            FXCollections.observableArrayList(
              "Pinkfloyd",
              "Jarabe de Palo",
              "Triana",
              "Dire Straits"
            );

    ObservableList<String> tittleItems =
            FXCollections.observableArrayList(
                    "The Wall",
                    "La Flaca",
                    "El patio",
                    "Live"
            );

    ObservableList<String> styleItems =
            FXCollections.observableArrayList(
                    "Rock Extranjero",
                    "Rock Espanol"
            );

    public Vinyl() {
        authorList.add(authorItems);
        tittleList.add(tittleItems);
        styleList.add(styleItems);
    }

    public ObservableList<String> getAuthors() {
        return authorItems;
    }

    public ObservableList<String> getTittles() {
        return tittleItems;
    }

    public ObservableList<String> getStyles() {
        return styleItems;
    }

    public ObservableList<String> getAuthors(int index) {
        return authorList.get(index);
    }

    public ObservableList<String> getTittles(int index) {
        return tittleList.get(index);
    }

    public ObservableList<String> getStyles(int index) {
        return tittleList.get(index);
    }

    public String getAuthor(int index){
        return authorItems.get(index);
    }

    public String getTittle(int index){
        return tittleItems.get(index);
    }

    public String getStyle(int index){
        return styleItems.get(index);
    }

    public int getAuthorSize(){
        return authorItems.size();
    }

    public int getTittleSize(){
        return tittleItems.size();
    }

    public int getStyleSize(){
        return styleItems.size();
    }

    public Vinyl(String author, String tittle, String style, String state) {
        this.author = author;
        this.tittle = tittle;
        this.style = style;
        State = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
