package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepositoryInter {
    Admin saveAdmin(Admin admin);

    void deleteAdmin(String adminId);

    Optional<Admin> getAdminById(String adminId);

    Optional<Admin> getAdminByUsernme(Admin admin);
}
