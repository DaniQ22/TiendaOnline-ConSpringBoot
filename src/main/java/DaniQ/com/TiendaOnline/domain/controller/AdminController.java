package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.service.AdminService;
import DaniQ.com.TiendaOnline.domain.util.results.AdminExistsException;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("Admi")
public class AdminController {

    private final AdminService adminService;

    private final JWTtoken jToken;



    @Autowired
    public AdminController(AdminService adminService, JWTtoken jToken) {
        this.adminService = adminService;
        this.jToken = jToken;
    }

    @PostMapping("/api/admin")
    public ResponseEntity<String> saveAdmin(@RequestBody Admin admin){
        try{
            Admin adminSaved = adminService.saveAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("El admin ha sido creado correctamente" + adminSaved);
        }catch (AdminExistsException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
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
    public ResponseEntity<?> loginAdmin(@RequestBody Admin admin){

        Map<String, String> response = new HashMap<>();

        try {
            String token = adminService.loginAdmin(admin);
            response.put("token", token);
            response.put("menssage", "Usuario logueado correctamente!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (MensaggeException e){
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
