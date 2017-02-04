package com.chetan.HibernateManyToManyAnnotation;

/**
 * Created by chetan on 15/1/17.
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITEM")
public class Item {
    public long getId() {
        return id;
    }


    @Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="item_price")
	private double price;

	@Column(name="item_desc")
	private String description;

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
