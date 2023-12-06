package DaniQ.com.TiendaOnline.mapper;

import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.persistence.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, AdminMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source ="idProducto" , target = "productId"),
            @Mapping(source ="nombreProducto" , target = "productName"),
            @Mapping(source ="cantidad" , target = "quanty"),
            @Mapping(source ="precio" , target = "price"),
            @Mapping(source ="disponible" , target = "available"),
            @Mapping(source ="administrador" , target = "admin"),
            @Mapping(source ="categoria" , target = "category")


    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);
}
