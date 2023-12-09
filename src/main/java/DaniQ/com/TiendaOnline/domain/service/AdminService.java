package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.repository.AdminRepository;
import DaniQ.com.TiendaOnline.domain.util.results.AdminExistsException;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.validation.AdminValidation;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class AdminService implements AdminServiceInter {
    private final AdminRepository adminRepository;
    private final JWTtoken token;


    @Autowired
    public AdminService(AdminRepository adminRepository, JWTtoken token) {
        this.adminRepository = adminRepository;
        this.token = token;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        Optional<Admin> ad = getAdminById(admin.getAdminId());
        if (ad.isPresent()){
            throw new AdminExistsException("El admin con Id " + admin.getAdminId() + " ya existe");
        }

        if (!AdminValidation.validateSave(admin)) {
            throw new AdminExistsException("Error en la validacion " +
                    "Ten en cuenta los sisguiente para registar un Admin: " +
                    "1. El campo Id no debe estar vacido" +
                    "2. Escribe un nombre y un apellido" +
                    "3. Tu nombre de usuario debe tener una letra Mayuscula" +
                    "4. Tu contraseña debe tener una letra mayuscula, debe tener un caracter " +
                    "especial y debe se mayor a 8 ");
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, admin.getPassword());
        admin.setPassword(hash);
        return adminRepository.saveAdmin(admin);
    }

    @Override
    public void deleteAdmin(String adminId) {
        Optional<Admin> adminToDelete = getAdminById(adminId);

        if (adminToDelete.isEmpty()) {
            throw new MensaggeException("Error, el Admin no existe");
        }
        adminRepository.deleteAdmin(adminId);
    }

    @Override
    public Optional<Admin> getAdminById(String adminId) {
        return adminRepository.getAdminById(adminId);
    }


    //Este metodo me permite buscar un admin por credenciales en este caso por el nombre de usuario
    //Si el ususario este presente comparo las contraseña ingresada con la almacenada en la base de datos
    @Override
    public Optional<Admin> getAdminCredentials(Admin admin) {
        Optional<Admin> optionalAdmin = adminRepository.getAdminByUsernme(admin);
        if (optionalAdmin.isPresent()){
            Admin ad = optionalAdmin.get();
            String passwordHashed = ad.getPassword();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (argon2.verify(passwordHashed, admin.getPassword())){
                return optionalAdmin;
            }
        }
        return Optional.empty();
        }


    @Override
    public String loginAdmin(Admin admin){
        Optional<Admin> optionalAdmin = getAdminCredentials(admin);
        if (optionalAdmin.isPresent()){
            Admin validAdmin = optionalAdmin.get();
            return token.create(String.valueOf(validAdmin.getAdminId()), validAdmin.getUserName());
        }
        throw new MensaggeException("Credenciales de administrador inválidas");

    }
}
