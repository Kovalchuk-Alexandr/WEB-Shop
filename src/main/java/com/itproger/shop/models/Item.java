package com.itproger.shop.models;

//import javax. persistence.Entity;
import jakarta.persistence.*;

@Entity     // Технология, позволяющая создавать таблицы внутри БД
public class Item {
    @Id     // Поле id с автогенерацией
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, info, image;
    private short price;
    //id, title, info, image, price

    // Связываем таблицу item с user по полю "user_id"
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)           // Тип связи: один автор -> одна статья
    private User user;

    public Item() {
    }

    public Item(String title, String info, String image, short price, User user) {
        this.title = title;
        this.info = info;
        this.image = image;
        this.price = price;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }
}
