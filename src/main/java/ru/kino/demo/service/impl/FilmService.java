package ru.kino.demo.service.impl;

import ru.kino.demo.domian.Film;
import ru.kino.demo.dto.FilmDTO;

public interface FilmService {
    Film crate(FilmDTO filmDTO);
}
