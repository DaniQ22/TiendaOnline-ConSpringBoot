package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.service.AdminService;
import DaniQ.com.TiendaOnline.domain.util.results.AdminExistsException;
import DaniQ.com.TiendaOnline.domain.util.results.CustomMessage;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("Admin")
public class AdminController {

    private final AdminService adminService;

    private final JWTtoken jToken;



    @Autowired
    public AdminController(AdminService adminService, JWTtoken jToken) {
        this.adminService = adminService;
        this.jToken = jToken;
    }

    @PostMapping("/api/admin")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CustomMessage> saveAdmin(@RequestBody Admin admin){
        try {
            Admin adminSaved = adminService.saveAdmin(admin);
            CustomMessage response = new CustomMessage("El admin ha sido creado correctamente: " + adminSaved);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (MensaggeException e) {
            CustomMessage response = new CustomMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @GetMapping("/api/admin/{adminId}")
    public ResponseEntity<?> getAdminById(@RequestHeader(value = "Authorization") String token,
                                        @PathVariable String adminId){

        try{
            jToken.validateToken(token);
            Optional<Admin> admin =  adminService.getAdminById(adminId);
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        }catch (MensaggeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " +
                    "no tienes acceso a esta ruta");
        }
    }

    @PostMapping("/api/login")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin) {
        try {
            String token = adminService.loginAdmin(admin);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token); // Ajusta según el esquema de tu token
            return ResponseEntity.status(HttpStatus.OK).headers(headers).body("Login Correcto");
        } catch (MensaggeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/admin/{adminId}")
    public ResponseEntity<?> deleteAdmin(@RequestHeader(value = "Authorization") String token,
                                         @PathVariable String adminId){

        try {
            jToken.validateToken(token);
            adminService.deleteAdmin(adminId);
            return ResponseEntity.status(HttpStatus.OK).body("Admin eliminado correctamente");
        } catch (MensaggeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            if (token == null || token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " +
                        "no tienes acceso a esta ruta");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede eliminar este admin");
            }
        }
    }
}
