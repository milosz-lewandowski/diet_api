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

    public Set<Diet> getDietsRepo() {
        if (dietsRepo == null) dietsRepo = new HashSet<>();
        return dietsRepo;
    }

    @SneakyThrows
    public String getDietsJson() {
        return objectMapper.writeValueAsString(getDietsRepo());
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

}
