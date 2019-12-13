package lk.ijse.roomreservation.dto;

import java.io.Serializable;

public class MealPackageDTO implements Serializable {
    private int id;
    private String packageName;
    private String breakfast;
    private String lunch;
    private String dinner;

    public MealPackageDTO() {
    }

    public MealPackageDTO(String packageName, String breakfast, String lunch, String dinner) {
        this.packageName = packageName;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public MealPackageDTO(int id, String packageName, String breakfast, String lunch, String dinner) {
        this.id = id;
        this.packageName = packageName;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
