package DaniQ.com.TiendaOnline.mapper;

import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.persistence.model.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mappings({
            @Mapping(source ="idCategoria" , target ="categoryId" ),
            @Mapping(source ="nombre" , target ="name" ),
            @Mapping(source ="descripcion" , target ="description" )
    })
    Category toCategory(Categoria categoria);
    List<Category> toCategories(List<Categoria> categorias);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria categoria(Category category);
    List<Categoria> toCategoria(List<Category> categories);
}
