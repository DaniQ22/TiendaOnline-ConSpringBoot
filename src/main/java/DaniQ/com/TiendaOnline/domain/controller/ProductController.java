package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.domain.service.ProductService;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Product")
public class ProductController {

    private final ProductService productService;
    private final JWTtoken jwTtoken;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);



    @Autowired
    public ProductController(ProductService productService,  JWTtoken jwTtoken) {
        this.productService = productService;
        this.jwTtoken = jwTtoken;
    }



    @PostMapping("/api/product")
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        try {
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto creado");
        }catch (MensaggeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/api/products")
    public ResponseEntity<?> getAll(@RequestHeader(value = "Authorization") String token) {
        logger.info("Token recibido en la solicitud: {}", token);
        try {
            jwTtoken.validateToken(token);
            List<Product> products = productService.getAll();
            return new ResponseEntity<>(products, HttpStatus.OK);

        }catch (Exception e){
            logger.error("La validación del token ha fallado para el token: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error: No tienes permiso para acceder a esta acción. Verifica tu token de autorización.");

        }
    }
}
