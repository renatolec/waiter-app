package dev.renato.waiter.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private int id;

    @Column(name = "qtd")
    private int qtd;

    @Column(name = "sausage")
    private boolean hasSausage;

    @Column(name = "tomato")
    private boolean hasTomato;

    @Column(name = "ketchup")
    private boolean hasKetchup;

    @Column(name = "mustard")
    private boolean hasMustard;

    @Column(name = "mayonnaise")
    private boolean hasMayonnaise;

    @Column(name = "bacon")
    private boolean hasBacon;

    @Column(name = "cheese")
    private boolean hasCheese;

    @Column(name = "ham")
    private boolean hasHam;

    @Column(name = "duplo")
    private boolean hasDouble;

    public static Item ItemFactory(int qtd, String name){
        name = name.toUpperCase();
        if(name.equals("SIMPLES"))
            return new Item(qtd, true, true, true, true, true, false, false, false, false);
        return new Item(qtd, true, true, true, true, true, true, true, true, false);
    }

    public Item() {
    }

    public Item(int qtd, boolean hasSausage, boolean hasTomato, boolean hasKetchup,
                boolean hasMustard, boolean hasMayonnaise, boolean hasBacon,
                boolean hasCheese, boolean hasHam, boolean hasDouble) {
        this.qtd = qtd;
        this.hasSausage = hasSausage;
        this.hasTomato = hasTomato;
        this.hasKetchup = hasKetchup;
        this.hasMustard = hasMustard;
        this.hasMayonnaise = hasMayonnaise;
        this.hasBacon = hasBacon;
        this.hasCheese = hasCheese;
        this.hasHam = hasHam;
        this.hasDouble = hasDouble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasSausage() {
        return hasSausage;
    }

    public boolean isHasTomato() {
        return hasTomato;
    }

    public boolean isHasKetchup() {
        return hasKetchup;
    }

    public boolean isHasMustard() {
        return hasMustard;
    }

    public boolean isHasMayonnaise() {
        return hasMayonnaise;
    }

    public boolean isHasBacon() {
        return hasBacon;
    }

    public boolean isHasCheese() {
        return hasCheese;
    }

    public boolean isHasHam() {
        return hasHam;
    }

    public boolean isHasDouble() {
        return hasDouble;
    }

    public int getQtd() {
        return qtd;
    }

    public void setHasSausage(boolean hasSausage) {
        this.hasSausage = hasSausage;
    }

    public void setHasTomato(boolean hasTomato) {
        this.hasTomato = hasTomato;
    }

    public void setHasKetchup(boolean hasKetchup) {
        this.hasKetchup = hasKetchup;
    }

    public void setHasMustard(boolean hasMustard) {
        this.hasMustard = hasMustard;
    }

    public void setHasMayonnaise(boolean hasMayonnaise) {
        this.hasMayonnaise = hasMayonnaise;
    }

    public void setHasBacon(boolean hasBacon) {
        this.hasBacon = hasBacon;
    }

    public void setHasCheese(boolean hasCheese) {
        this.hasCheese = hasCheese;
    }

    public void setHasHam(boolean hasHam) {
        this.hasHam = hasHam;
    }

    public void setHasDouble(boolean hasDouble) {
        this.hasDouble = hasDouble;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    @Override
    public String toString() {



        String itemDescription = "Simples";

        if(hasBacon && hasCheese && hasHam){
            itemDescription = "Especial";
        }else if(hasBacon && hasCheese){
            itemDescription = "Especial s/presunto";
        }else if(hasBacon && hasHam){
            itemDescription = "Especial s/queijo";
        }else if(hasHam && hasCheese){
            itemDescription = "Especial s/bacon";
        }else if(hasHam){
            itemDescription = "Simples c/presunto";
        }else if(hasBacon){
            itemDescription = "Simples c/bacon";
        }else if(hasCheese){
            itemDescription = "Simples c/queijo";
        }

        if(!hasKetchup){
            itemDescription += " s/ketchup";
        }
        if(!hasMustard){
            itemDescription += " s/mostarda";
        }
        if(!hasMayonnaise){
            itemDescription += " s/maionese";
        }

        if(hasDouble && hasSausage){
            itemDescription = itemDescription.replace("Simples", "Simples duplo");
            itemDescription = itemDescription.replace("Especial", "Especial duplo");
        }else if(!hasSausage){
            itemDescription = itemDescription.replace("Simples", "Simples s/salsicha");
            itemDescription = itemDescription.replace("Especial", "Especial s/salsicha");
        }

        return itemDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return hasSausage == item.hasSausage && hasTomato == item.hasTomato && hasKetchup == item.hasKetchup && hasMustard == item.hasMustard && hasMayonnaise == item.hasMayonnaise && hasBacon == item.hasBacon && hasCheese == item.hasCheese && hasHam == item.hasHam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasSausage, hasTomato, hasKetchup, hasMustard, hasMayonnaise, hasBacon, hasCheese, hasHam);
    }
}
