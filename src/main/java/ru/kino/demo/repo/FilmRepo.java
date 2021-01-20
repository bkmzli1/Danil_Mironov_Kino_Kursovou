package ru.kino.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kino.demo.domian.Film;
import ru.kino.demo.domian.Row;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


public interface FilmRepo extends JpaRepository<Film, String> {
    Set<Film> findAllByDate(LocalDate localDate);

    Film findByHalls(Row row);

}
