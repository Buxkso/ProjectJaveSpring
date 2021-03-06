package sk.bucekm.springCrm.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "style_id")
    private int style_id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "styleList", fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();

    public Style() {

    }

    public int getStyle_id() {
        return style_id;
    }

    public void setStyle_id(int style_id) {
        this.style_id = style_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


}
