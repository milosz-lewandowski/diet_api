package pl.miloszlewandowski.diet_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/diets")
public class DietController {

    @Autowired
    DietService dietService;

    @SneakyThrows
    @GetMapping("/")
    public
//    Map<String,
            String
//    >
    getDiets() throws JsonProcessingException {
        String str = dietService.getDietsJson();
//        Map<String, String> map= new HashMap<>();
//        map.put("response", str);
        return str;
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public String getDiet(@PathVariable Integer id) throws JsonProcessingException {
        String str = dietService.getDietByIDJson(id);
        return str;
    }

    @PostMapping("/save")
    public void saveDiet(@RequestBody String diet) {
        dietService.saveDietFromJson(diet);
    }

    @PutMapping("/edit")
    public void editDiet(@RequestBody Diet diet) {
        dietService.editDiet(diet);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDiet(@PathVariable Integer id) {
        dietService.deleteDiet(id);
    }

}
