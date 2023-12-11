package DaniQ.com.TiendaOnline.mapper;

import DaniQ.com.TiendaOnline.domain.PurchaseItem;
import DaniQ.com.TiendaOnline.persistence.model.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "catidad", target = "quantyItem"),
            @Mapping(source = "total", target = "totaPrice")


    })
    PurchaseItem toPurchaseItem(CompraProducto compraProducto);
    List<PurchaseItem> items (List<CompraProducto> productos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)

    })
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}
