package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.repository.AdminRepository;
import DaniQ.com.TiendaOnline.domain.repository.AdminRepositoryInter;
import DaniQ.com.TiendaOnline.domain.util.validation.AdminValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements AdminRepositoryInter {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        if (!AdminValidation.validateSave(admin)) {
            return null;
        }
        return adminRepository.saveAdmin(admin);
    }

    @Override
    public void deleteAdmin(String adminId) {

    }

    @Override
    public Optional<Admin> getAdminById(String adminId) {
        return adminRepository.getAdminById(adminId);
    }
}
