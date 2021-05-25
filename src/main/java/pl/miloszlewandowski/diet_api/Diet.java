package pl.miloszlewandowski.diet_api;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Set;

@Data
public class Diet { //typ diety, np. wegetariańska, sportowa
    private Integer dietId;
    private String name;
    private String description;
    @JsonManagedReference
    private Set<DietOption> dietOptions;
}
