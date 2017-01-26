package com.chetan.HibernateOneToManyAnnotation;

/**
 * Created by chetan on 15/1/17.
 */


import javax.persistence.*;

@Entity
@Table(name = "ITEMS")
public class Items {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart1() {
        return cart1;
    }

    public void setCart1(Cart cart1) {
        this.cart1 = cart1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private long id;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_total")
    private double itemTotal;

    @Column(name = "quantity")
    private int quantity;
    //It should be noted that On side of Items it is a Many To One relationship because
    //Many items can fall for one cart(ManyToOne)
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart1;
//JoinColumn   -->Specifies a column for joining an entity association or element collection.
    //Hibernate requires no-args constructor
    public Items() {
    }

    public Items(String itemId, double total, int qty, Cart c) {
        this.itemId = itemId;
        this.itemTotal = total;
        this.quantity = qty;
        this.cart1 = c;
    }

}
