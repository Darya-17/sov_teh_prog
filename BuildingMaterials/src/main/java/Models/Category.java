package Models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public class Category {

    private int id;
    private String name;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate createdAt;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate updatedAt;

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return name;
    }
}
