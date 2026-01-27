package org.skypro.skyshop.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.Searchable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    /**
     * Сценарий 1:
     * В StorageService нет объектов
     */
    @Test
    void search_whenStorageIsEmpty_returnsEmptyResult() {
        Mockito.when(storageService.getAllSearchables())
                .thenReturn(Collections.emptyList());

        Collection<Searchable> result = searchService.search("хлеб");

        Assertions.assertTrue(result.isEmpty());
    }

    /**
     * Сценарий 2:
     * Объекты есть, но ни один не подходит под запрос
     */
    @Test
    void search_whenNoMatchingObjects_returnsEmptyResult() {
        Searchable milk = Mockito.mock(Searchable.class);
        Mockito.when(milk.getSearchTerm()).thenReturn("Молоко");

        Mockito.when(storageService.getAllSearchables())
                .thenReturn(List.of(milk));

        Collection<Searchable> result = searchService.search("хлеб");

        Assertions.assertTrue(result.isEmpty());
    }

    /**
     * Сценарий 3:
     * Есть подходящий объект
     */
    @Test
    void search_whenMatchingObjectExists_returnsIt() {
        Searchable bread = Mockito.mock(Searchable.class);
        Mockito.when(bread.getSearchTerm()).thenReturn("Свежий хлеб");

        Mockito.when(storageService.getAllSearchables())
                .thenReturn(List.of(bread));

        Collection<Searchable> result = searchService.search("хлеб");

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.contains(bread));
    }

    /**
     * Дополнительный сценарий:
     * Поиск не чувствителен к регистру
     */
    @Test
    void search_isCaseInsensitive() {
        Searchable bread = Mockito.mock(Searchable.class);
        Mockito.when(bread.getSearchTerm()).thenReturn("ХЛЕБ");

        Mockito.when(storageService.getAllSearchables())
                .thenReturn(List.of(bread));

        Collection<Searchable> result = searchService.search("хлеб");

        Assertions.assertEquals(1, result.size());
    }
}
