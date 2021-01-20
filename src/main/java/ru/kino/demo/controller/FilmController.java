package ru.kino.demo.controller;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kino.demo.domian.Armchair;
import ru.kino.demo.domian.Film;
import ru.kino.demo.domian.Row;
import ru.kino.demo.dto.BronDto;
import ru.kino.demo.dto.FilmDTO;
import ru.kino.demo.repo.ArmchairRepo;
import ru.kino.demo.repo.FilmRepo;
import ru.kino.demo.repo.RowRepo;
import ru.kino.demo.service.impl.FilmService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@CrossOrigin
@RequestMapping("/api/film")
public class FilmController {
    private final FilmService filmService;
    private final FilmRepo filmRepo;
    private final RowRepo rowRepo;
    private final ArmchairRepo armchairRepo;

    public FilmController(FilmService filmService, FilmRepo filmRepo, RowRepo rowRepo,
                          ArmchairRepo armchairRepo) {
        this.filmService = filmService;
        this.filmRepo = filmRepo;
        this.rowRepo = rowRepo;
        this.armchairRepo = armchairRepo;
    }


    @GetMapping("/syt")
    public Set<String> nameSyt() {
        return Set.of("Кино");
    }

    @PostMapping("/syt/cratefilm")
    @ResponseBody
    public Film crateFilm(@RequestBody() @Valid FilmDTO filmDTO,
                          BindingResult bindingResult) {

        return filmService.crate(filmDTO);

    }

    @GetMapping()
    @ResponseBody
    public Set<Film> get() {
        Set<Film> allByDate = new TreeSet<>(Comparator.comparing(Film::getDateStart));
        allByDate.addAll(filmRepo.findAll());
        return allByDate;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Film getId(@PathVariable String id) {
        Film film = filmRepo.findById(id).get();
        return film;
    }

    @GetMapping("/filmSearch/{search}")
    @ResponseBody
    public Set<Film> search(@PathVariable String search) {
        List<Armchair> allByKey = armchairRepo.findAllByKey(search);
        Set<Film> filmList = new HashSet<>();
        allByKey.stream().forEach(armchair -> {
            try {
                Row row = rowRepo.findByHall(armchair);
                Film byHalls = filmRepo.findByHalls(row);
                AtomicBoolean b = new AtomicBoolean(false);
                filmList.stream().filter(film -> film.getId().equals(byHalls.getId())).forEach(film -> b.set(true));
                if (!b.get()) {
                    filmList.add(byHalls);
                }

            } catch (Exception e) {
            }

        });
        return filmList;
    }

    @PostMapping("/bron")
    @ResponseBody
    public Object bron(@RequestBody() BronDto bronDto) {
        Film film = filmRepo.findById(bronDto.getId()).get();
        film.setHalls(bronDto.getHalls());
        filmRepo.save(film);
        return film;
    }

}
