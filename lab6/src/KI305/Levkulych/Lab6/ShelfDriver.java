package KI305.Levkulych.Lab6;

import java.io.PrintStream;
// Імпорт StandardCharsets більше не критичний, якщо текст англійською,
// але можна залишити для надійності.
import java.nio.charset.StandardCharsets;

/**
 * Клас-драйвер для демонстрації роботи узагальненого класу Shelf.
 */
public class ShelfDriver {
    public static void main(String[] args) throws Exception {

        // Налаштування виводу (можна залишити або прибрати, для англійської це не
        // вплине на помилки)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        // 1. Створення полички для КНИГ (параметр типу Book)
        Shelf<Book> bookShelf = new Shelf<>(5, "Living Room");

        // Додавання книг
        bookShelf.addItem(new Book("The Hobbit", "J.R.R. Tolkien", 310));
        bookShelf.addItem(new Book("1984", "George Orwell", 328));
        bookShelf.addItem(new Book("Java Programming", "John Doe", 450));

        // Пошук книги з найменшою кількістю сторінок
        // Book::compareTo - посилання на метод, що діє як Comparator
        Book minBook = bookShelf.findMinElement(Book::compareTo);
        System.out.println("Book with the least pages: " + minBook);

        // 2. Створення полички для DVD (параметр типу DVD)
        Shelf<DVD> dvdShelf = new Shelf<>(10, "TV Stand");

        // Додавання дисків
        dvdShelf.addItem(new DVD("The Matrix", 136, 1999));
        dvdShelf.addItem(new DVD("Inception", 148, 2010));
        dvdShelf.addItem(new DVD("Avatar", 162, 2009));

        // Пошук DVD з найменшою тривалістю
        DVD minDVD = dvdShelf.findMinElement(DVD::compareTo);
        System.out.println("DVD with the shortest duration: " + minDVD);

        // Вивід загальної інформації про кількість предметів
        System.out.println("Number of books on the shelf: " + bookShelf.getItemCount());
        System.out.println("Number of DVDs on the shelf: " + dvdShelf.getItemCount());

        // Перевірка наявності конкретної книги
        Book searchBook = new Book("1984", "George Orwell", 328);
        System.out.println("Is '1984' on the shelf: " + bookShelf.containsItem(searchBook));

        // Видалення найкоротшого фільму та перевірка кількості
        dvdShelf.removeItem(minDVD);
        System.out.println("Number of DVDs after removal: " + dvdShelf.getItemCount());
    }
}