package ru.kino.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kino.demo.domian.Armchair;
import ru.kino.demo.domian.Row;

import java.util.List;
import java.util.Set;


public interface ArmchairRepo extends JpaRepository<Armchair, String> {

    List<Armchair> findAllByKey(String kay);

}
