package dev.renato.waiter.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private int id;

    @Basic @Column(name = "time")
    private Time timeOrdered;

    @OneToMany(cascade = CascadeType.ALL) @JoinColumn(name = "order_id")
    private List<Item> itemsOrdered = new ArrayList<>();

    public Order() {
        this(Time.valueOf(LocalTime.now()));
    }

    public Order(Time timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public Order(int id, Time timeOrdered, List<Item> itemsOrdered) {
        this.id = id;
        this.timeOrdered = timeOrdered;
        this.itemsOrdered = itemsOrdered;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeOrdered(Time timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public void setItemsOrdered(List<Item> itemsOrdered) {
        if(itemsOrdered == null)
            itemsOrdered = new ArrayList<>();
        this.itemsOrdered = itemsOrdered;
    }

    public int getId() {
        return id;
    }

    public Time getTimeOrdered() {
        return timeOrdered;
    }

    public List<Item> getItemsOrdered() {
        return itemsOrdered;
    }

    public Map<Item, Integer> getMapItemsOrdered() {
        Map<Item, Integer> mapItemsOrdered = new LinkedHashMap<>();
        for(var item : itemsOrdered){
            mapItemsOrdered.merge(item, 1, Integer::sum);
        }
        return mapItemsOrdered;
    }

    public void addItem(Item item){
        if(itemsOrdered == null){
            itemsOrdered = new ArrayList<>();
        }
        itemsOrdered.add(item);
    }

    public void removeItem(Item item){
        if(itemsOrdered == null)
            return;
        itemsOrdered.remove(item);
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeOrderedLocalTime = timeOrdered.toLocalTime();
        int total = 0;
        for(var item : itemsOrdered){
            total += item.getQtd();
        }
        return timeOrderedLocalTime.format(timeFormatter) + " pedido de " + total + (total == 1 ? " lanche" : " lanches");
    }

}
