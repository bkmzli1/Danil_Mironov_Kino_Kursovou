package ru.kino.demo.dto;

import ru.kino.demo.domian.Row;

import java.time.LocalDate;
import java.util.Set;

public class BronDto {
    private String id;
    private Set<Row> halls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Row> getHalls() {
        return halls;
    }

    public void setHalls(Set<Row> halls) {
        this.halls = halls;
    }
}
