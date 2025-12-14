package KI305.Levkulych.Lab2;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Клас-драйвер {@code TelevisionDriver} (точка входу).
 * <p>
 * Тут демонструється створення та використання
 * об'єкта {@link Television}.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class TelevisionDriver {

    // Допоміжний метод для красивого виводу дати
    private static void printDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy HH:mm:ss",
                Locale.forLanguageTag("uk-UA"));
        String now = LocalDateTime.now().format(formatter);
        System.out.println("\n=== " + now + " ===");
    }

    /**
     * Головний метод {@code main}.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        // 1. Створення "зовнішніх" об'єктів-компонентів.
        Screen customScreen = new Screen(55, 3840, 2160, "QLED");
        AudioSystem powerfulAudio = new AudioSystem(100, 6, true);
        SmartSystem webOs = new SmartSystem("WebOS", "7.0");

        // 2. Використання 'try-with-resources'.
        // Автоматично викличе tv.close() в кінці.
        try (
                // 3. Створення телевізора.
                // В нього передаються ЗОВНІШНІ об'єкти.
                // Конструктор Television створює їхні ВНУТРІШНІ копії.
                Television tv = new Television(customScreen, powerfulAudio, webOs)) {

            // 4. Демонстрація початкового стану
            printDateTime();
            System.out.println("--- ПОЧАТКОВИЙ СТАН TV ---");
            tv.showDetails();

            // 5. Виконання різних дій з телевізором
            System.out.println("\n--- ВИКОНАННЯ ДІЙ ---");
            tv.changeVolume(70);
            tv.increaseVolume(15);
            tv.toggleWifi(); // Ввімкнення Wi-Fi
            tv.installApp("Netflix");

            // 6. Демонстрація фінального стану
            printDateTime();
            System.out.println("\n--- ФІНАЛЬНИЙ СТАН TV ---");
            tv.showDetails();

        } catch (IOException e) {
            // Конкатенацію (+) замінено на System.err.printf()
            // Було: System.err.println("Помилка при роботі з лог-файлом: " +
            // e.getMessage());
            System.err.printf("Помилка при роботі з лог-файлом: %s%n", e.getMessage());
        }
    }
}