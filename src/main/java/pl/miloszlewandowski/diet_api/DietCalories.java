package pl.miloszlewandowski.diet_api;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class DietCalories { //kaloryczność, np. 1500, 2000 kcal
    private Integer dietCaloriesId;
    private Integer calories;

    @JsonBackReference
    private DietOption dietOption;
}
