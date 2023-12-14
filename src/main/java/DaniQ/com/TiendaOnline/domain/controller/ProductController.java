package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.domain.service.AdminService;
import DaniQ.com.TiendaOnline.domain.service.ProductService;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Product")
public class ProductController {

    private final ProductService productService;
    private final JWTtoken jwTtoken;
    private final AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService, JWTtoken jwTtoken, AdminService adminService) {
        this.productService = productService;
        this.jwTtoken = jwTtoken;
        this.adminService = adminService;
    }



    @PostMapping("/api/product")
    public ResponseEntity<?> saveProduct(@RequestHeader(value = "Authorization") String token, @RequestBody Product product) {

        logger.info("Token recibido en la solicitud: {}", token);
        try {
            //Aqui traigo las credenciales del admin que creo el token
            Claims claims = jwTtoken.validateToken(token);
            String adminId = claims.get("adminId", String.class);
            logger.info("Admin ID from token: {}", adminId);

            Admin admin = adminService.getAdminById(adminId)
                    .orElseThrow(() -> new MensaggeException("No se encontró el administrador correspondiente."));
            product.setAdminId(admin.getAdminId());
            return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);

        }catch (MensaggeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }catch (Exception e){
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " + "no tienes acceso a esta ruta");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear el producto");
            }
        }
    }

    @GetMapping("/api/products")
    public ResponseEntity<?> getAll(@RequestHeader(value = "Authorization") String token) {
        logger.info("Token recibido en la solicitud: {}", token);
        try {
            jwTtoken.validateToken(token);
            List<Product> products = productService.getAll();
            return new ResponseEntity<>(products, HttpStatus.OK);

        } catch (Exception e) {
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " + "no tienes acceso a esta ruta");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al ver los productos");
            }
        }
    }

    @DeleteMapping("/api/product/{productId}")
    public ResponseEntity<?> deleteProduct(@RequestHeader(value = "Authorization") String token,
                                           @PathVariable String productId) {
        try {
            jwTtoken.validateToken(token);
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado de la base de datos");
        } catch (MensaggeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " + "no tienes acceso a esta ruta");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al eliminar el producto");
            }
        }
    }

    @PutMapping("/api/product")
    public ResponseEntity<?> updateProduct(@RequestHeader(value = "Authorization") String token,
                                           @RequestBody Product product){
        try {
            jwTtoken.validateToken(token);
            productService.updateProduct(product);
            return ResponseEntity.status(HttpStatus.OK).body("Producto actualizado de la base de datos");
            }catch (MensaggeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " + "no tienes acceso a esta ruta");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al actualizar el producto");
            }
        }
    }
}
