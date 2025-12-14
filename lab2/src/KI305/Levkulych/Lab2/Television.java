package KI305.Levkulych.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Головний клас – {@code Television}, що моделює телевізор.
 * <p>
 * Об'єднує в собі компоненти: {@link Screen}, {@link AudioSystem}
 * та {@link SmartSystem}.
 * <p>
 * Патерн "Будівельник" (Builder) видалено.
 * Замість нього використовується "захисне глибоке копіювання"
 * прямо в конструкторі для "відв'язки" від зовнішніх факторів.
 * <p>
 * Реалізовано {@link AutoCloseable} для автоматичного
 * керування лог-файлом.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class Television implements AutoCloseable {
    // ... (всі поля залишаються без змін) ...
    private Screen screen;
    private AudioSystem audioSystem;
    private SmartSystem smartSystem;

    private PrintWriter logWriter;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ... (Конструктор Television залишається без змін) ...
    public Television(Screen screen, AudioSystem audioSystem, SmartSystem smartSystem) throws IOException {
        this.screen = new Screen(screen);
        this.audioSystem = new AudioSystem(audioSystem);
        this.smartSystem = new SmartSystem(smartSystem);

        this.logWriter = new PrintWriter(new FileWriter("log.txt", true));
        log("Екземпляр телевізора створено (через глибоке копіювання).");
    }

    // ... (Усі публічні методи: showDetails, changeVolume і т.д. залишаються без
    // змін) ...

    /**
     * Метод для показу всієї інформації про телевізор.
     * <p>
     * Оновлено: тепер записує деталі не лише в консоль,
     * але й у лог-файл.
     */
    public void showDetails() {
        // 1. Вивід у КОНСОЛЬ (як і було)
        System.out.println("--- Поточний стан телевізора ---");
        System.out.println(screen);
        System.out.println(audioSystem);
        System.out.println(smartSystem);
        System.out.println("---------------------------------");

        // 2. Запис у ЛОГ-ФАЙЛ (ВИПРАВЛЕНО)
        log("Викликано показ деталей:");
        log(screen.toString());
        log(audioSystem.toString());
        log(smartSystem.toString());
    }

    public void changeVolume(int value) {
        audioSystem.setVolume(value);
        log("Гучність змінено на: %d", value);
    }

    public void toggleWifi() {
        if (smartSystem.isWifiConnected()) {
            smartSystem.disconnectWifi();
            log("WiFi вимкнено.");
        } else {
            smartSystem.connectWifi();
            log("WiFi увімкнено.");
        }
    }

    public void installApp(String app) {
        smartSystem.installApp(app);
        log("Встановлено додаток: %s", app);
    }

    public void removeApp(String app) {
        smartSystem.removeApp(app);
        log("Видалено додаток: %s", app);
    }

    public void increaseVolume(int amount) {
        audioSystem.increaseVolume(amount);
        log("Гучність збільшено на: %d. Новий рівень: %d", amount, audioSystem.getVolume());
    }

    public void decreaseVolume(int amount) {
        audioSystem.decreaseVolume(amount);
        log("Гучність зменшено на: %d. Новий рівень: %d", amount, audioSystem.getVolume());
    }

    public void setScreenType(String type) {
        screen.setType(type);
        log("Тип екрана змінено на: %s", type);
    }

    public void setResolution(int width, int height) {
        screen.setResolution(width, height);
        log("Роздільну здатність змінено на: %s", screen.getResolution());
    }

    @Override
    public void close() {
        if (logWriter != null) {
            log("Закриття файлу логів.");
            logWriter.close();
        }
    }

    /**
     * Приватний хелпер-метод для логування.
     * Конкатенацію (+) замінено на logWriter.printf().
     */
    private void log(String format, Object... args) {
        String timestamp = LocalDateTime.now().format(formatter);
        String message = String.format(format, args);

        // Було: logWriter.println("[" + timestamp + "] " + message);
        // Стало:
        logWriter.printf("[%s] %s%n", timestamp, message);
        // %n - це коректний символ нового рядка для будь-якої ОС

        // Використовується flush, щоб бачити логи одразу в файлі.
        logWriter.flush();
    }
}