package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.domain.repository.CategoryRepository;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInter{

    private  final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        if (category.getDescription().isEmpty()
                || category.getDescription().isBlank()
                || category.getName().isEmpty()
                || category.getName().isBlank()) {
            throw new MensaggeException("Error al crear la categoria, asegurate que el nombre" +
                    "y la categoria no estan vacias");
        }
        return categoryRepository.saveCategory(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Optional<Category> categoryToDelete = getById(categoryId);
        if (categoryToDelete.isEmpty()){
            throw new MensaggeException("Categoria no encontrada");
        }
        categoryRepository.deleteCategory(categoryId);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Optional<Category> getById(Integer categoryId) {
        Optional<Category> category = categoryRepository.getById(categoryId);
        if (category.isPresent()){
            throw new MensaggeException("Categoria encontrada");
        }else {
            throw new MensaggeException("La categoria no existe");
        }
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        return Optional.empty();
    }
}
