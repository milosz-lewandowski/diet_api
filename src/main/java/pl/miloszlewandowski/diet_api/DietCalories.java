package pl.miloszlewandowski.diet_api;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class DietCalories { //kaloryczność, np. 1500, 2000 kcal
    private Integer dietCaloriesId;
    private Integer calories;

    @JsonBackReference
    private DietOption dietOption;
}
