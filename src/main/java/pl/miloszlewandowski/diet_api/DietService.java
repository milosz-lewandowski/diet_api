package pl.miloszlewandowski.diet_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DietService {

    Set<Diet> dietsRepo;
    ObjectMapper objectMapper = new ObjectMapper();

    public Set<Diet> getDiets() {
        if (dietsRepo == null) {
            dietsRepo = new HashSet<>();
//            loadSampleDiet();
        }
        return dietsRepo;
    }

    @SneakyThrows
    public String getDietsJson() {
        return objectMapper.writeValueAsString(getDiets());
    }
    @SneakyThrows
    public String getDietByIDJson(Integer id)  {
        return objectMapper.writeValueAsString(getDietById(id));
    }
    @SneakyThrows
    public void saveDietFromJson(String json) {
        saveDiet(objectMapper.readValue(json, Diet.class));
    }
    @SneakyThrows
    public void updateDietFromJson(String json) {
        editDiet(objectMapper.readValue(json, Diet.class));
    }

    public void setDietsRepo(Set<Diet> dietsRepo) {
        this.dietsRepo = dietsRepo;
    }

    public Diet getDietById(Integer id) {
        return dietsRepo.stream().filter(
                diet -> diet.getDietId().equals(id)).findFirst().orElse(null);
    }

    public Integer deleteDiet(Integer id) {
        Diet toDeleteDiet = getDietById(id);
        dietsRepo.remove(toDeleteDiet);
        return toDeleteDiet.getDietId();
    }

    public Integer saveDiet(Diet diet) {
        dietsRepo.add(diet);
        return diet.getDietId();
    }

    public Integer editDiet(Diet newDiet){
        Diet editedDiet = updateDietObject(newDiet);
        deleteDiet(newDiet.getDietId());
        saveDiet(editedDiet);
        return newDiet.getDietId();
    }

    private Diet updateDietObject(Diet newDietData) {
        Diet editedDiet =
                getDietById(newDietData.getDietId());
        editedDiet.setName(
                newDietData.getName());
        editedDiet.setDescription(
                newDietData.getDescription());
        editedDiet.setDietOptions(
                newDietData.getDietOptions());
        return editedDiet;
    }

    private void loadSampleDiet() {
        //  create empty collections
        Set<Diet> diets = new HashSet<>();
        Set<DietOption> dietOptionSet1 = new HashSet<>();
        Set<DietCalories> dietCaloriesSet1 = new HashSet<>();

        // create Diet
        Diet diet1 = new Diet();
        diet1.setDietId(1);
        diet1.setName("diet1");
        diet1.setDescription("dieta 1");

        //  create DietOption
        DietOption dietOption1 = new DietOption();
        dietOption1.setDietOptionId(1);
        dietOption1.setDiet(diet1);
        dietOption1.setAbbreviation("abbreviation1");

        //  create DietCalories
        DietCalories dietCalories1 = new DietCalories();
        dietCalories1.setDietCaloriesId(1);
        dietCalories1.setCalories(1000);
//        dietCalories1.setDietOption(dietOption1);

        //  fill collections
        dietCaloriesSet1.add(dietCalories1);
        dietOptionSet1.add(dietOption1);

        //  set collections for instances
//        dietOption1.setDietCalories(dietCaloriesSet1);
        diet1.setDietOptions(dietOptionSet1);
        diets.add(diet1);

        setDietsRepo(diets);
    }

}
