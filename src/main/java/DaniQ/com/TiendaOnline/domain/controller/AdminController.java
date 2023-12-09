package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.service.AdminService;
import DaniQ.com.TiendaOnline.domain.util.results.AdminExistsException;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import org.aspectj.weaver.patterns.IToken;
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

    @Autowired
    public AdminController(AdminService adminService, JWTtoken token) {
        this.adminService = adminService;
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
    public Optional<Admin> getAdminById(@PathVariable String adminId){
        return adminService.getAdminById(adminId);
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
}
