package KI305.Levkulych.Lab3;

/**
 * Клас {@code Screen} інкапсулює характеристики дисплея телевізора.
 * <p>
 * Зберігає дані про діагональ, роздільну здатність та тип матриці.
 * Надає методи для доступу та модифікації цих параметрів.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class Screen {
    /** Діагональ екрана в дюймах. */
    private double diagonal;

    /** Роздільна здатність по ширині (кількість пікселів). */
    private int width;

    /** Роздільна здатність по висоті (кількість пікселів). */
    private int height;

    /** Тип матриці екрана (наприклад, "OLED", "QLED"). */
    private String type;

    /**
     * Основний конструктор для створення екрана з заданими параметрами.
     *
     * @param diagonal Діагональ у дюймах (наприклад, 55.0).
     * @param width    Ширина в пікселях (наприклад, 3840).
     * @param height   Висота в пікселях (наприклад, 2160).
     * @param type     Тип матриці (наприклад, "OLED").
     */
    public Screen(double diagonal, int width, int height, String type) {
        this.diagonal = diagonal;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    /**
     * Конструктор копіювання.
     * Створює глибоку копію іншого об'єкта {@code Screen}.
     *
     * @param other Об'єкт {@code Screen} для копіювання.
     */
    public Screen(Screen other) {
        this.diagonal = other.diagonal;
        this.width = other.width;
        this.height = other.height;
        this.type = other.type;
    }

    /**
     * Повертає діагональ екрана.
     *
     * @return Діагональ у дюймах.
     */
    public double getDiagonal() {
        return diagonal;
    }

    /**
     * Повертає роздільну здатність у вигляді форматованого рядка.
     *
     * @return Рядок формату "ширина x висота" (наприклад, "1920x1080").
     */
    public String getResolution() {
        return String.format("%dx%d", width, height);
    }

    /**
     * Повертає тип матриці екрана.
     *
     * @return Тип матриці (наприклад, "OLED").
     */
    public String getType() {
        return type;
    }

    /**
     * Встановлює нове значення діагоналі.
     *
     * @param diagonal Нова діагональ у дюймах.
     */
    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    /**
     * Встановлює нову роздільну здатність екрана.
     *
     * @param width  Нова ширина в пікселях.
     * @param height Нова висота в пікселях.
     */
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Встановлює новий тип матриці.
     *
     * @param type Новий тип (наприклад, "QLED").
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Повертає рядкове представлення об'єкта {@code Screen}.
     *
     * @return Рядок з інформацією про екран.
     */
    @Override
    public String toString() {
        return String.format("Екран[Діагональ: %.1f\", Роздільність: %s, Тип: %s]", diagonal, getResolution(), type);
    }
}