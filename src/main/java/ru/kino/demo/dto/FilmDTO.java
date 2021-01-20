package ru.kino.demo.dto;

import java.time.LocalDate;

public class FilmDTO {
    String name;
    String lable;
    String description;
    String[] listImg;
    LocalDate data;
    String timeStart;
    String timeStop;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(String timeStop) {
        this.timeStop = timeStop;
    }

    public String[] getListImg() {
        return listImg;
    }

    public void setListImg(String[] listImg) {
        this.listImg = listImg;
    }

}
