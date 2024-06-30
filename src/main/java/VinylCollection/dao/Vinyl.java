package VinylCollection.dao;

public class Vinyl {

    private final String author;
    private final String tittle;
    private final String style;
    private final String State;

    public Vinyl(String author, String tittle, String style, String state) {
        this.author = author;
        this.tittle = tittle;
        this.style = style;
        State = state;
    }

    public String getAuthor() {
        return author;
    }

    public String getTittle() {
        return tittle;
    }

    public String getStyle() {
        return style;
    }

    public String getState() {
        return State;
    }
}
