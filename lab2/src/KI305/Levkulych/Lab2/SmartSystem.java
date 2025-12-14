package KI305.Levkulych.Lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Клас {@code SmartSystem} моделює смарт-функціонал телевізора.
 * <p>
 * Відповідає за операційну систему, підключення до Wi-Fi та керування
 * встановленими застосунками.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class SmartSystem {
    private String os;
    private String version;
    private boolean wifi;

    // Список для зберігання назв додатків.
    private List<String> apps;

    /**
     * Основний конструктор.
     *
     * @param os      Назва операційної системи.
     * @param version Її версія.
     */
    public SmartSystem(String os, String version) {
        this.os = os;
        this.version = version;
        this.wifi = false; // Wi-Fi вимкнений за замовчуванням
        this.apps = new ArrayList<>(); // Важливо: ініціалізація списку
    }

    /**
     * КОНСТРУКТОР КОПІЮВАННЯ.
     * <p>
     * Це найважливіший конструктор копіювання, оскільки
     * він копіює не лише примітиви, але й список {@code apps}.
     *
     * @param other Об'єкт {@code SmartSystem} для копіювання.
     */
    public SmartSystem(SmartSystem other) {
        // Копіювання простих полів
        this.os = other.os;
        this.version = other.version;
        this.wifi = other.wifi;

        // !!! КРИТИЧНИЙ МОМЕНТ ГЛИБОКОГО КОПІЮВАННЯ !!!
        // Створюється НОВИЙ ArrayList, що наповнюється
        // елементами зі старого списку.
        //
        // Пряме присвоєння 'this.apps = other.apps' було б
        // ПОМИЛКОЮ (поверхневою копією), і "відв'язки" б не було.
        this.apps = new ArrayList<>(other.apps);
    }

    // --- Методи для керування системою ---

    public void connectWifi() {
        wifi = true;
    }

    public void disconnectWifi() {
        wifi = false;
    }

    public boolean isWifiConnected() {
        return wifi;
    }

    /**
     * Встановлення додатку. Додано перевірку на дублікати.
     */
    public void installApp(String app) {
        if (!apps.contains(app)) {
            apps.add(app);
        }
    }

    public void removeApp(String app) {
        apps.remove(app);
    }

    /**
     * Повертає список додатків.
     * <p>
     * Для захисту інкапсуляції повертається "незмінна"
     * (unmodifiable) версія списку. Це запобігає
     * прямому редагуванню списку ззовні.
     *
     * @return Незмінний список додатків.
     */
    public List<String> getApps() {
        return Collections.unmodifiableList(apps);
    }

    /**
     * Метод toString() для виведення інформації про смарт-систему.
     */
    @Override
    public String toString() {
        return String.format("Смарт-система[ОС: %s %s, WiFi: %s, Додатки: %d]",
                os, version,
                wifi ? "Увімк." : "Вимк.",
                apps.size());
    }
}