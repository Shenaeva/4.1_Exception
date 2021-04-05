package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private Product first = new Book(1, "Harry Potter", 3000, "Joanne Rowling", 700, 1999);
    private Product second = new Book(2, "Метель", 300, "А.С. Пушкин", 100, 2010);
    private Product third = new Book(3, "Нос", 250, "Н.В. Гоголь", 50, 200);
    private Product fourth = new TShirt(4, "Nike", 2300, "red", 44);
    private Product fifth = new TShirt(5, "Puma", 2000, "green", 40);
    private Product sixth = new TShirt(6, "Puma", 2300, "blue", 46);

    @Test
    void shouldRemoveById() {
        ProductManager manager = new ProductManager(repository);
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        int id = 1;
        manager.removeById(id);
        Product[] actual = manager.getAll();
        Product[] expected = new Product[]{sixth, fifth, fourth, third, second};
        assertArrayEquals(actual, expected);
    }
    @Test
    void shouldExceptionRemoteId() {
        ProductManager manager = new ProductManager(repository);
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        int id = 10;
        assertThrows(NotFoundException.class, () -> manager.removeById(id));
    }

}