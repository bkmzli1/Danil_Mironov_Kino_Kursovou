package ru.kino.demo.service;

import org.springframework.stereotype.Service;
import ru.kino.demo.domian.Armchair;
import ru.kino.demo.domian.Film;
import ru.kino.demo.domian.Img;
import ru.kino.demo.domian.Row;
import ru.kino.demo.dto.FilmDTO;
import ru.kino.demo.repo.FilmRepo;
import ru.kino.demo.repo.ImgRepo;
import ru.kino.demo.service.impl.FilmService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepo filmRepo;
    private final ImgRepo imgRepo;

    public FilmServiceImpl(FilmRepo filmRepo, ImgRepo imgRepo) {
        this.filmRepo = filmRepo;
        this.imgRepo = imgRepo;
    }

    @Override public Film crate(FilmDTO filmDTO) {
        Film film = new Film();
        film.setName(filmDTO.getName());
        film.setDescription(filmDTO.getDescription());
        film.setLable(filmDTO.getLable());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMMM d yyyy", Locale.ENGLISH);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(" MMMM d yyyy", Locale.ENGLISH);

        LocalDateTime date = LocalDateTime
                .parse(filmDTO.getTimeStart() + formatterDate.format(filmDTO.getData()), formatter);
        LocalDateTime dates = LocalDateTime
                .parse(filmDTO.getTimeStop() + formatterDate.format(filmDTO.getData()), formatter);
        film.setDateStart(date);
        film.setDateStop(dates);
        film.setDateStop(dates);
        film.setDate(filmDTO.getData());
        film.setImg(new HashSet<Img>());
        Arrays.stream(filmDTO.getListImg()).forEach(s -> film.getImg().add(imgRepo.findById(s).get()));


        HashSet<Row> rows = new HashSet<>();


        for (int i = 0; i < 10; i++) {
            HashSet<Armchair> armchairs = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                armchairs.add(new Armchair(j + 1));
            }
            rows.add(new Row(i + 1, armchairs));
        }
        film.setHalls(rows);
        filmRepo.save(film);
        return film;
    }
}
