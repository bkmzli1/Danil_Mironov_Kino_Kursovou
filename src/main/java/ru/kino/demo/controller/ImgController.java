package ru.kino.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kino.demo.domian.Img;
import ru.kino.demo.repo.ImgRepo;
import ru.kino.demo.repo.UserRepo;
import ru.kino.demo.service.impl.ImgService;


import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@CrossOrigin
@RequestMapping("/api/img")
public class ImgController {
    private final ImgRepo imgRepo;
    private final ImgService imgService;
    private final UserRepo userRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public ImgController(ImgRepo imgRepo, ImgService imgService, UserRepo userRepo) {
        this.imgRepo = imgRepo;
        this.imgService = imgService;
        this.userRepo = userRepo;
    }

    @PostMapping()
    @ResponseBody
    public Object addImg(@RequestBody @Valid MultipartFile[] mfImg) {
        File fileUploadPath = new File(uploadPath);

        Set<Img> images = new HashSet<>();
        try {
            for (MultipartFile mf : mfImg) {
                String upFile = "/img";
                File uploadPDir = new File(fileUploadPath.getAbsolutePath() + upFile);
                if (!uploadPDir.exists()) {
                    uploadPDir.mkdirs();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + mf.getOriginalFilename();

                try {
                    mf.transferTo(new File(uploadPDir + "/" + resultFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Img imgDB = new Img();
                imgDB.setName(mf.getResource().getFilename());

                imgDB.setImg("static" + upFile + "/" + resultFilename);

                images.add(imgDB);
            }

            images.forEach(imgRepo::save);


        } catch (NullPointerException e) {
            return null;
        }
        Set<String> imgIDs = new HashSet<>();
        images.forEach(img -> imgIDs.add(img.getId()));
        return imgIDs;
    }

}
