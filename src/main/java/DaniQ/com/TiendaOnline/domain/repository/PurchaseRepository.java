package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Purchase;
import DaniQ.com.TiendaOnline.mapper.PurchaseMapper;
import DaniQ.com.TiendaOnline.persistence.crud.CrudCompra;
import DaniQ.com.TiendaOnline.persistence.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepository implements  PurchaseRepositoryInter{
    private final CrudCompra crudCompra;

    private final PurchaseMapper mapper;

    @Autowired
    public PurchaseRepository(CrudCompra crudCompra, PurchaseMapper mapper) {
        this.crudCompra = crudCompra;
        this.mapper = mapper;
    }


    @Override
    public Purchase savePurchase(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.tOPurchase(crudCompra.save(compra));

    }

}
