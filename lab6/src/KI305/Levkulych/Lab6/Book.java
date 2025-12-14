package KI305.Levkulych.Lab6;

/**
 * Клас, що представляє книгу.
 * Реалізує інтерфейс Comparable для можливості порівняння книг між собою.
 */
class Book implements Comparable<Book> {
    // Поля класу (інкапсульовані дані)
    private String title; // Назва книги
    private String author; // Автор книги
    private int pages; // Кількість сторінок

    /**
     * Конструктор класу Book
     *
     * @param title  назва книги
     * @param author автор книги
     * @param pages  кількість сторінок
     */
    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    // Геттер для отримання кількості сторінок
    public int getPages() {
        return pages;
    }

    // Геттер для отримання назви
    public String getTitle() {
        return title;
    }

    /**
     * Перевизначення методу toString для зручного виводу інформації про об'єкт.
     *
     * @return рядкове представлення книги
     */
    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', pages=" + pages + "}";
    }

    /**
     * Метод порівняння двох книг (реалізація інтерфейсу Comparable).
     * Визначає "природний порядок" сортування книг.
     *
     * @param other інша книга, з якою порівнюємо поточну
     * @return від'ємне число, якщо ця книга менша;
     *         нуль, якщо рівні;
     *         додатне число, якщо ця книга більша (за кількістю сторінок).
     */
    @Override
    public int compareTo(Book other) {
        // Використовуємо стандартний метод Integer.compare для порівняння примітивів
        // int
        return Integer.compare(this.pages, other.pages);
    }
}