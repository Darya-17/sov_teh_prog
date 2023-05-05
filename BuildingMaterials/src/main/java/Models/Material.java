package Models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Material {


    private int id;

    private String name;

    private Category category;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate createdAt;

    @JsonFormat(pattern = "dd.MM.yyyy")

    private LocalDate updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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

    @Override
    public String toString() {
        return name;
    }
}
