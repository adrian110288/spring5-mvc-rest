package guru.springfamework.controller;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.service.CategoryService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testGetAllCategories() throws Exception {

        List<CategoryDTO> categories = Arrays.asList(new CategoryDTO(), new CategoryDTO());

        when(categoryService.getAllCategories()).thenReturn(categories);


        mockMvc.perform(get("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", Matchers.hasSize(2)));
    }

    @Test
    public void getCategoryByName() throws Exception {

        Long ID = 1L;
        String NAME = "Joe";

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/v1/categories/Joe").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo(NAME)));
    }
}