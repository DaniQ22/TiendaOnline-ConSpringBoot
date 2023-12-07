package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryInter {

    Category saveCategory(Category category);

    void deleteCategory(Integer categoryId);

    List<Category> getAll();

    Optional<Category> getById(Integer categoryId);

}
