package ru.kino.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kino.demo.domian.Img;

public interface ImgRepo extends JpaRepository<Img, String> {


}
