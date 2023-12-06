package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.util.results.SaveResult;

import java.util.Optional;

public interface AdminServiceInter {

    String saveAdmin (Admin admin);

    boolean deleteAdmin(String adminId);

    Optional<Admin> getAdminById(String adminId);

}
