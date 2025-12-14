package KI305.Levkulych.Lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Параметризований клас (Generic), що реалізує поличку для зберігання
 * предметів.
 *
 * @param <T> тип предметів, що зберігаються на поличці (наприклад, Book або
 *            DVD).
 */
public class Shelf<T> {
    // Список для зберігання предметів типу T
    private List<T> items;
    // Максимальна кількість предметів, яку може вмістити поличка
    private int capacity;
    // Опис розташування полички (наприклад, "Спальня")
    private String location;

    /**
     * Конструктор класу Shelf
     *
     * @param capacity максимальна місткість полички
     * @param location розташування полички
     */
    public Shelf(int capacity, String location) {
        // Ініціалізуємо список як ArrayList (динамічний масив)
        this.items = new ArrayList<>();
        this.capacity = capacity;
        this.location = location;
    }

    /**
     * Додає предмет на поличку, якщо є вільне місце.
     *
     * @param item предмет типу T для додавання
     * @return true якщо предмет додано успішно, false якщо поличка заповнена
     */
    public boolean addItem(T item) {
        if (items.size() < capacity) {
            return items.add(item);
        }
        // Якщо місця немає, повертаємо false
        return false;
    }

    /**
     * Видаляє предмет з полички.
     *
     * @param item предмет для видалення
     * @return true якщо предмет знайдено і видалено успішно
     */
    public boolean removeItem(T item) {
        return items.remove(item);
    }

    /**
     * Знаходить мінімальний елемент на поличці за заданим критерієм (компаратором).
     *
     * @param comparator об'єкт, який визначає правила порівняння двох об'єктів типу
     *                   T
     * @return мінімальний елемент або null, якщо поличка порожня
     */
    public T findMinElement(Comparator<T> comparator) {
        if (items.isEmpty()) {
            return null;
        }
        // Використовуємо стандратний метод Collections.min, передаючи наш список і
        // правило порівняння
        return Collections.min(items, comparator);
    }

    /**
     * Перевіряє чи є конкретний предмет на поличці.
     * Увага: для коректної роботи класи Book/DVD повинні мати перевизначений метод
     * equals().
     *
     * @param item предмет для пошуку
     * @return true якщо предмет знайдено
     */
    public boolean containsItem(T item) {
        return items.contains(item);
    }

    /**
     * Повертає поточну кількість предметів на поличці.
     *
     * @return кількість предметів (розмір списку)
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Очищає поличку (видаляє всі предмети).
     */
    public void clear() {
        items.clear();
    }

    /**
     * Повертає копію списку всіх предметів.
     * Важливо повертати новий список, щоб зовнішній код не міг напряму зламати
     * внутрішню структуру items.
     *
     * @return новий список, що містить усі предмети з полички
     */
    public List<T> getAllItems() {
        return new ArrayList<>(items);
    }
}