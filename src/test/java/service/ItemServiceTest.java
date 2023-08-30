package service;

import com.app.restaurant.data.*;
import com.app.restaurant.repository.ItemRepository;
import com.app.restaurant.service.ItemService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    ItemRepository repository;

    @InjectMocks
    ItemService service;

    @Test
    void saveAnItem() {
        Category vegetables = new Category(1L, "vegetables", null);
        Ingredient tomato = new Ingredient(1L, "tomato", "vegetable", null);
        Ingredient onion = new Ingredient(2L, "onion", "vegetable", null);
        Ingredient oliveOil = new Ingredient(3L, "olive", "vegetable", null);
        List<Ingredient> saladIngredientsList = Stream.of(tomato, onion, oliveOil).collect(Collectors.toList());

        Item tomatoesSalad = new Item(10L, vegetables, "vegan", Type.kitchen, "png", LocalDate.now(ZoneId.of("Europe/Bucharest")), saladIngredientsList);
        service.save(tomatoesSalad);
        verify(repository, times(1)).save(tomatoesSalad);
    }

    @Test
    void deleteAnItem() {
        Category sweet = new Category(1L, "sweet", null);
        Ingredient sugar = new Ingredient(1L, "sugar", "brown sugar", null);
        List<Ingredient> ingredientsList = Stream.of(sugar).collect(Collectors.toList());
        Item cottonCandy = new Item(1L, sweet, "calories", Type.kitchen, "png", LocalDate.now(ZoneId.of("Europe/Bucharest")), ingredientsList);

        when(repository.findById(1L)).thenReturn(Optional.of(cottonCandy));
        service.deleteById(1L);

        verify(repository, times(1)).delete(cottonCandy);
    }

    @Test
    void getAllItems() {
        Category vegan = new Category(1L, "vegan", null);
        Ingredient soya = new Ingredient(1L, "soya", "gluten free", null);
        Ingredient salt = new Ingredient(2L, "salt", "iodized salt", null);
        Ingredient caraway = new Ingredient(3L, "caraway", "seeds", null);
        Ingredient milk = new Ingredient(4L, "milk", "vegetarian", null);
        Ingredient cornstarch = new Ingredient(4L, "cornstarch", "cornstarch", null);
        Ingredient vanilla = new Ingredient(4L, "vanilla", "vanilla", null);
        List<Ingredient> ingredientsListForTofu = Stream.of(soya, milk, caraway, salt).collect(Collectors.toList());
        List<Ingredient> ingredientsListForPudding = Stream.of(cornstarch, vanilla, milk).collect(Collectors.toList());

        Item tofu = new Item(1L, vegan, "white", Type.kitchen, "png", LocalDate.now(ZoneId.of("Europe/Bucharest")), ingredientsListForTofu);
        Item pudding = new Item(2L, vegan, "yellow", Type.kitchen, "png", LocalDate.now(ZoneId.of("Europe/Bucharest")), ingredientsListForPudding);
        List<Item> itemsList = new ArrayList<>(Arrays.asList(tofu, pudding));

        when(repository.findAll()).thenReturn(itemsList);
        List<Item> itemsFromDB = service.findAll();

        //then
        assertThat(itemsFromDB).isEqualTo(itemsList);
        AssertionsForClassTypes.assertThat(itemsList.size()).isNotZero();
    }

}
