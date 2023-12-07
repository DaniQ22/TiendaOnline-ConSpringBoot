package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.mapper.CategoryMapper;
import DaniQ.com.TiendaOnline.persistence.crud.CrudCategoria;
import DaniQ.com.TiendaOnline.persistence.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements CategoryRepositoryInter{

    private final CategoryMapper mapper;

    private final CrudCategoria crudCategoria;

    @Autowired
    public CategoryRepository(CategoryMapper mapper, CrudCategoria crudCategoria) {
        this.mapper = mapper;
        this.crudCategoria = crudCategoria;
    }


    @Override
    public Category saveCategory(Category category) {
        Categoria categoria = mapper.categoria(category);
        return mapper.toCategory(crudCategoria.save(categoria));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        crudCategoria.deleteById(categoryId);

    }

    @Override
    public List<Category> getAll() {
        List<Categoria> categorias = (List<Categoria>) crudCategoria.findAll();
        return mapper.toCategories(categorias);
    }


    @Override
    public Optional<Category> getById(Integer categoryId) {
        Optional<Categoria> categoria = crudCategoria.findById(categoryId);
        return categoria.map(cat -> mapper.toCategory(cat));
    }
}
