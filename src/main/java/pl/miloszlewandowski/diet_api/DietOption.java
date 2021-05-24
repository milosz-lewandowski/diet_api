package pl.miloszlewandowski.diet_api;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.util.Set;

@Data
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class DietOption { //wariant diety, np. "5 posiłków" lub "3 posiłki"
    private Integer dietOptionId;
    private String name;
    private String abbreviation;
    @JsonManagedReference
    private Diet diet;
    @JsonBackReference
    private Set<DietCalories> dietCalories;
}
