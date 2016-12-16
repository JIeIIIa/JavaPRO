package ua.kiev.prog;

import javax.persistence.*;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "showAll", query = "SELECT m FROM Menu m"),
        @NamedQuery(name = "byCost", query = "SELECT m FROM Menu m WHERE m.cost BETWEEN :minCost AND :maxCost")
})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private int cost;
    @Column(nullable = false)
    private int discount;

    public Menu() {
    }

    public Menu(String title, double weight, int cost, int discount) {
        this.title = title;
        this.weight = weight;
        this.cost = cost;
        this.discount = discount;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", weight=" + String.format("%.3f", weight) +
                ", cost=" + String.format("%.2f", cost / 100.0) +
                ", discount=" + discount +
                '}';
    }
}

