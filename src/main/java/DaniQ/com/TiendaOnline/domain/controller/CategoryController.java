package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.domain.service.CategoryService;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Category")
public class CategoryController {


    private  final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/api/category")
    public ResponseEntity<String> saveCategory(@RequestBody Category category){
        try {
            Category categorySaved = categoryService.saveCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria creada correctamente"+categorySaved);
        }catch (MensaggeException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
