package com.chetan.HibernateOneToManyAnnotation;

/**
 * Created by chetan on 15/1/17.
 */

import javax.persistence.*;
import java.util.Set;



@Entity
@Table(name="CART")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long id;

	@Column(name="total")
	private double total;

	@Column(name="name")
	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//Because one cart can have many items (OneToMany)
    //Here cart1 is
    // private Cart cart1
    //which has @JoinColumn and @ManyToOne annotated on it

    @OneToMany(mappedBy="cart1")
	private Set<Items> items;

//Getter Setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Set<Items> getItems() {
        return items;
    }

    public void setItems(Set<Items> items) {
        this.items = items;
    }
}
