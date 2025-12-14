package KI305.Levkulych.Lab3;

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
    /** Назва операційної системи (наприклад, "WebOS", "Android TV"). */
    private String os;

    /** Версія операційної системи (наприклад, "7.0"). */
    private String version;

    /** Поточний стан Wi-Fi з'єднання. */
    private boolean wifi;

    /** Список назв встановлених застосунків. */
    private List<String> apps;

    /**
     * Основний конструктор для створення смарт-системи.
     *
     * @param os      Назва операційної системи.
     * @param version Версія операційної системи.
     */
    public SmartSystem(String os, String version) {
        this.os = os;
        this.version = version;
        this.wifi = false; // Wi-Fi вимкнений за замовчуванням.
        this.apps = new ArrayList<>();
    }

    /**
     * Конструктор копіювання.
     * Створює глибоку копію іншого об'єкта {@code SmartSystem},
     * включаючи копіювання списку застосунків.
     *
     * @param other Об'єкт {@code SmartSystem} для копіювання.
     */
    public SmartSystem(SmartSystem other) {
        this.os = other.os;
        this.version = other.version;
        this.wifi = other.wifi;
        this.apps = new ArrayList<>(other.apps); // Створюємо копію списку, а не посилання.
    }

    /**
     * Вмикає Wi-Fi з'єднання.
     */
    public void connectWifi() {
        wifi = true;
    }

    /**
     * Вимикає Wi-Fi з'єднання.
     */
    public void disconnectWifi() {
        wifi = false;
    }

    /**
     * Перевіряє, чи увімкнене Wi-Fi з'єднання.
     *
     * @return {@code true}, якщо Wi-Fi увімкнене, інакше {@code false}.
     */
    public boolean isWifiConnected() {
        return wifi;
    }

    /**
     * Встановлює новий застосунок.
     * Якщо застосунок вже встановлений, дублікат не додається.
     *
     * @param app Назва застосунку для встановлення.
     */
    public void installApp(String app) {
        if (!apps.contains(app)) {
            apps.add(app);
        }
    }

    /**
     * Видаляє застосунок зі списку встановлених.
     *
     * @param app Назва застосунку для видалення.
     */
    public void removeApp(String app) {
        apps.remove(app);
    }

    /**
     * Повертає список встановлених застосунків.
     * Для захисту інкапсуляції повертається незмінна "обгортка" списку.
     *
     * @return Незмінний список (unmodifiable list) назв застосунків.
     */
    public List<String> getApps() {
        return Collections.unmodifiableList(apps);
    }

    /**
     * Повертає рядкове представлення об'єкта {@code SmartSystem}.
     *
     * @return Рядок з інформацією про смарт-систему.
     */
    @Override
    public String toString() {
        return String.format("Смарт-система[ОС: %s %s, WiFi: %s, Додатки: %d]", os, version, wifi ? "Увімк." : "Вимк.",
                apps.size());
    }
}