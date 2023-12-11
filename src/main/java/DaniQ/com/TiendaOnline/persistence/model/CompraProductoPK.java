package DaniQ.com.TiendaOnline.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serializable;

@Embeddable
public class CompraProductoPK implements Serializable {

    @Column(name = "id_producto")
    private String idProducto;

    @Column(name = "id_compra")
    private Integer idCompra;

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }
}
