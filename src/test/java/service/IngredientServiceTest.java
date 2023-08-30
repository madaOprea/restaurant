package service;

import com.app.restaurant.data.Ingredient;
import com.app.restaurant.repository.IngredientRepository;
import com.app.restaurant.service.IngredientService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    @Mock
    IngredientRepository repository;

    @InjectMocks
    IngredientService service;

    @Test
    void saveAnIngredient() {
        Ingredient milk = new Ingredient(1L, "milk", "vegetarian", null);
        service.save(milk);
        verify(repository, times(1)).save(milk);
    }

    @Test
    void deleteAnIngredient() {
        Ingredient sugar = new Ingredient(1L, "sugar", "brown sugar", null);
        when(repository.findById(1L)).thenReturn(Optional.of(sugar));
        service.deleteById(1L);

        verify(repository, times(1)).delete(sugar);

    }

    @Test
    void getAllIngredients() {
        Ingredient soya = new Ingredient(1L, "soya", "gluten free", null);
        Ingredient sugar = new Ingredient(2L, "sugar", "brown sugar", null);
        Ingredient caraway = new Ingredient(3L, "caraway", "seeds", null);
        Ingredient milk = new Ingredient(4L, "milk", "vegetarian", null);
        List<Ingredient> ingredientsList = new ArrayList<>(Arrays.asList(soya, sugar, caraway, milk));

        when(repository.findAll()).thenReturn(ingredientsList);
        List<Ingredient> ingredientsFromDB = service.findAll();

        //then
        assertThat(ingredientsFromDB).isEqualTo(ingredientsList);
        AssertionsForClassTypes.assertThat(ingredientsList.size()).isNotZero();
    }
}
