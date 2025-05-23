package com.course.service;

import com.course.entity.Category;
import com.course.entity.Menu;
import com.course.entity.Restaurant;
import com.course.repository.CategoryRepository;
import com.course.repository.MenuRepository;
import com.course.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MenuServiceTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuService menuService;
    @InjectMocks
    private CategoryService categoryService;
    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void findMenuById_shouldReturnMenu() {
        Menu menu = new Menu();
        menu.setId(1);
        menu.setDescription("Burrito de carne");
        menu.setPrice(120.0);

        Mockito.when(menuRepository.findById(1)).thenReturn(Optional.of(menu));
        Menu resultado = menuService.getMenuById(1);

        assertNotNull(resultado);
        assertEquals("Burrito de carne", resultado.getDescription());
        assertEquals(120.0, resultado.getPrice());
    }

    @Test
    void createMenu() {
        Menu newMenu = new Menu(0, "Burrito longaniza", new Category(0, "Burrito"), new Restaurant(0, "Villa"), 85.0);
        Menu savedMenu = new Menu(0, "Burrito longaniza", new Category(0, "Burrito"), new Restaurant(0, "Villa"), 85.0);

        Mockito.when(menuRepository.save(newMenu)).thenReturn(savedMenu);

        Menu result = menuService.save(newMenu);

        assertEquals("Burrito longaniza", result.getDescription());
        assertEquals(85.0, result.getPrice());
    }
}
