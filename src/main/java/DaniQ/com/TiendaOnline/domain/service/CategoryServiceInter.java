package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInter {

    Category saveCategory(Category category);

    void deleteCategory(Integer categoryId);

    List<Category> getAll();

    Optional<Category> getById(Integer categoryId);

    Optional<Category> updateCategory(Category category);

}
