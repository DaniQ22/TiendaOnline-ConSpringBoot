package DaniQ.com.TiendaOnline.mapper;

import DaniQ.com.TiendaOnline.domain.Purchase;
import DaniQ.com.TiendaOnline.domain.PurchaseItem;
import DaniQ.com.TiendaOnline.persistence.model.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class, CustomerMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target ="purchaseId"),
            @Mapping(source = "fechaCompra", target ="purchaseDate"),
            @Mapping(source = "productos", target ="items"),
            @Mapping(source = "cliente", target ="customer"),
            @Mapping(source = "idCliente", target ="customerId"),
            @Mapping(source = "detallesCompra", target ="dateailPurchase"),

    })
    Purchase tOPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    Compra toCompra(Purchase purchase);


}
