package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.repository.AdminRepository;
import DaniQ.com.TiendaOnline.domain.util.validation.AdminValidation;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements AdminServiceInter {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        Optional<Admin> ad = getAdminById(admin.getAdminId());
        if (ad.isPresent()){
            return null;
        }

        if (!AdminValidation.validateSave(admin)) {
            return null;
        }
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, admin.getPassword());
        admin.setPassword(hash);
        return adminRepository.saveAdmin(admin);
    }

    @Override
    public boolean deleteAdmin(String adminId) {
        return false;
    }

    @Override
    public Optional<Admin> getAdminById(String adminId) {
        return adminRepository.getAdminById(adminId);
    }

    //Este metodo me permite buscar un admin por credenciales en este caso por el nombre de usuario
    //Si el ususario este presente comparo las contraseña ingresada con la almacenada en la base de datos
    @Override
    public Optional<Admin> getAdminByUserName(String userName) {
        Optional<Admin> admin = adminRepository.getAdminByUsernme(userName);
        if (admin.isPresent()){
            String passwordHashed = admin.get().getPassword();
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (argon2.verify(passwordHashed, admin.get().getPassword())){
                return admin;
            }
        }
        return Optional.empty();
        }
}
