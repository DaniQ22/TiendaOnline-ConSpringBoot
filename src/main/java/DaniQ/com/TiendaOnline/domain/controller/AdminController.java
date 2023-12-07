package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.service.AdminService;
import DaniQ.com.TiendaOnline.domain.util.results.AdminExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("Admi")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
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
}
