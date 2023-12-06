package DaniQ.com.TiendaOnline.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @Column(name = "id_compra")
    private Integer idCompra;


    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @OneToMany
    @JoinColumn(name = "compra")
    private List<CompraProducto> productos;

    @ManyToOne
    @JoinColumn(name = "id_dni")
    private Cliente cliente;


    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public List<CompraProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<CompraProducto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
