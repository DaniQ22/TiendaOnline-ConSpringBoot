package DaniQ.com.TiendaOnline.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.util.List;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @Column(name = "id_admin")
    private String idAdmin;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    private  String contrasena;


    @OneToMany(mappedBy = "administrador")
    private List<Producto> productos;

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
