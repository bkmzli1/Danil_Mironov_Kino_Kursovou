package ru.kino.demo.domian;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "hall")
public class Row {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Armchair> hall;

    private int name;


    public Row() {
    }

    public Row(int name, Set<Armchair> hall) {
        this.hall = hall;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Armchair> getHall() {
        Set<Armchair> armchairs = new TreeSet<>(Comparator.comparing(Armchair::getName));
        armchairs.addAll(hall);
        return armchairs;
    }

    public void setHall(Set<Armchair> hall) {
        this.hall = hall;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
