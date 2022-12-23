package Models;

import java.util.Date;

public class Task {
    // Класс задания
    private int id;
    private String name;
    private boolean Completed;
    private Date completedDate;
    private Date createdDate;
    private Date realCompletedDate;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRealCompletedDate() {
        if (realCompletedDate == null)
            return null;
        return realCompletedDate;
    }

    public void setRealCompletedDate(Date realCompletedDate) {
        this.realCompletedDate = realCompletedDate;
    }

    public Date getCreationDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return Completed;
    }
    public void setCompleted(boolean completed) {
        Completed = completed;
        if (Completed)
            setRealCompletedDate(new Date());
        else
            setRealCompletedDate(null);

    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

}
