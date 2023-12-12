package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.domain.service.CategoryService;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Category")
public class CategoryController {


    private  final CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final JWTtoken jwTtoken;

    @Autowired
    public CategoryController(CategoryService categoryService, JWTtoken jwTtoken) {
        this.categoryService = categoryService;
        this.jwTtoken = jwTtoken;
    }


    @PostMapping("/api/category")
    public ResponseEntity<String> saveCategory(@RequestHeader(value = "Authorization") String token,
                                               @RequestBody Category category){
        logger.info("Token recibido en la solicitud: {}", token);

        try {
            jwTtoken.validateToken(token);
            Category categorySaved = categoryService.saveCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria creada correctamente"+categorySaved);
        }catch (MensaggeException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, no estas autorizado");
        }
    }

    @DeleteMapping("/api/category/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId,
                                            @RequestHeader (value = "Authorization") String token ){
        try {
            jwTtoken.validateToken(token);
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body("Categoria elimada");
        }catch (MensaggeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e){
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " +
                        "no tienes acceso a esta ruta");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede eliminar esta categoria");
            }
        }
    }
}
