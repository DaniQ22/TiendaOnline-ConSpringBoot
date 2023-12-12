package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.mapper.AdminMapper;
import DaniQ.com.TiendaOnline.persistence.crud.CrudAdministrador;
import DaniQ.com.TiendaOnline.persistence.model.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdminRepository implements AdminRepositoryInter{

    private final AdminMapper mapper;
    private final CrudAdministrador crudAdministrador;

    @Autowired
    public AdminRepository(AdminMapper mapper, CrudAdministrador crudAdministrador) {
        this.mapper = mapper;
        this.crudAdministrador = crudAdministrador;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        Administrador administrador = mapper.toAdministrador(admin);
        return mapper.toAdmin(crudAdministrador.save(administrador));
    }

    @Override
    public void deleteAdmin(String adminId) {
        crudAdministrador.deleteById(adminId);

    }

    @Override
    public Optional<Admin> getAdminById(String adminId) {
         Optional<Administrador> administrador = crudAdministrador.findById(adminId);
         return administrador.map(mapper::toAdmin);
    }

    @Override
    public  Optional<Admin> getAdminByUsernme(Admin admin) {
        Optional<Administrador> administrador = crudAdministrador.getAdminByUsername(admin.getUserName());
        return administrador.map(mapper::toAdmin);
    }
}
