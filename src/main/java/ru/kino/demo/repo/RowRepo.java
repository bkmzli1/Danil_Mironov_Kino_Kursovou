package ru.kino.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kino.demo.domian.Armchair;
import ru.kino.demo.domian.Film;
import ru.kino.demo.domian.Row;

import java.time.LocalDate;
import java.util.Set;


public interface RowRepo extends JpaRepository<Row, String> {
    Row findByHall(Armchair hall);
}
