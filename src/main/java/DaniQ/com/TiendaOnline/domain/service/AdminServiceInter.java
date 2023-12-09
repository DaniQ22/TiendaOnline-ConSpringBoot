package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.util.results.SaveResult;

import java.util.Optional;

public interface AdminServiceInter {

    Admin saveAdmin (Admin admin);

    void deleteAdmin(String adminId);

    Optional<Admin> getAdminById(String adminId);

    Optional<Admin> getAdminCredentials(Admin admin);

    String loginAdmin(Admin admin);
}
