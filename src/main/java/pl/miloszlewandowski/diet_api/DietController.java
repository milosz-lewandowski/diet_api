package pl.miloszlewandowski.diet_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class DietController {

    @Autowired
    DietService dietService;

    @GetMapping("/diets")
    ResponseEntity<Set<Diet>> getDietsList() {
        Set<Diet> diets = dietService.getDiets();
        if (diets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(diets, HttpStatus.OK);
        }
    }

    @GetMapping("diets/{id}")
    ResponseEntity<Diet> getDiet (@PathVariable("id") Integer id){
        Diet diet = dietService.getDietById(id);
        return ResponseEntity.ok(diet);
    }
    @PostMapping("/diets")
    public void saveDiet (@RequestBody String diet){
        dietService.saveDietFromJson(diet);
    }

    @PutMapping("/diets/{id}")
    public void editDiet (@RequestBody Diet diet){
        dietService.editDiet(diet);
    }

    @DeleteMapping("/diets/{id}")
    public void deleteDiet (@PathVariable Integer id){
        dietService.deleteDiet(id);
    }
}
