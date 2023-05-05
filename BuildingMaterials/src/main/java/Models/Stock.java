package Models;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Stock {

    private int id;

    private Material material;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    private int count;
    @JsonFormat(pattern = "dd.MM.yyyy")

    private LocalDate createdAt;

    @JsonFormat(pattern = "dd.MM.yyyy")

    private LocalDate updatedAt;

}
