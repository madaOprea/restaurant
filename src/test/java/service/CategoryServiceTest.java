package service;

import com.app.restaurant.data.Category;
import com.app.restaurant.repository.CategoryRepository;
import com.app.restaurant.service.CategoryService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryService service;

    @Test
    void saveACategory() {
        Category category = new Category(1L, "category one", null);
        service.save(category);
        verify(repository, times(1)).save(category);
    }

    @Test
    void deleteACategory() {
        Category specialOne = new Category(10L, "special one", null);
        when(repository.findById(10L)).thenReturn(Optional.of(specialOne));
        service.deleteById(10L);

        verify(repository, times(1)).delete(specialOne);
    }

    @Test
    void getAllCategories() {
        Category category1 = new Category(1L, "category one", null);
        Category category2 = new Category(2L, "category two", null);
        Category category3 = new Category(3L, "category three", null);
        List<Category> categoryList = new ArrayList<>(Arrays.asList(category1, category2, category3));

        when(repository.findAll()).thenReturn(categoryList);
        List<Category> categoriesFromDB = service.findAll();

        assertThat(categoriesFromDB).isEqualTo(categoryList);
        AssertionsForClassTypes.assertThat(categoryList.size()).isNotZero();
    }
}
