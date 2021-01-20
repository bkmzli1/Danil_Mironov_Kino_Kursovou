package ru.kino.demo.service;

import org.springframework.stereotype.Service;
import ru.kino.demo.domian.Img;
import ru.kino.demo.model.ImgCreateModel;
import ru.kino.demo.repo.ImgRepo;
import ru.kino.demo.service.impl.ImgService;

@Service
public class ImgServiceImpl implements ImgService {
    private final ImgRepo imgRepo;

    public ImgServiceImpl(ImgRepo imgRepo) {

        this.imgRepo = imgRepo;
    }

    @Override
    public Img imgCrate(ImgCreateModel taskCreate) {
        Img img = new Img();
        img.setImg(taskCreate.getImg());
        img.setName(taskCreate.getName());
        return img;
    }

    @Override
    public Img imgCrate(Img img) {
        imgRepo.save(img);
        return null;
    }

}
