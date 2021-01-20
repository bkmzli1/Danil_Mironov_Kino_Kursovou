package ru.kino.demo.service.impl;


import ru.kino.demo.domian.Img;
import ru.kino.demo.model.ImgCreateModel;

public interface ImgService {
   Img imgCrate(ImgCreateModel taskCreate);
   Img imgCrate(Img img);
}
