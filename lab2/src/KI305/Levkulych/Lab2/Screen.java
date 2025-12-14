package KI305.Levkulych.Lab2;

/**
 * Клас {@code Screen} моделює екранну підсистему телевізора.
 * <p>
 * Інкапсулює такі характеристики, як діагональ, роздільна здатність
 * та тип матриці.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class Screen {
    // Приватні поля для інкапсуляції
    private int diagonal;
    private int resolutionWidth;
    private int resolutionHeight;
    private String type;

    /**
     * Основний конструктор для створення екрана.
     *
     * @param diagonal Діагональ у дюймах.
     * @param width    Ширина в пікселях.
     * @param height   Висота в пікселях.
     * @param type     Тип матриці.
     */
    public Screen(int diagonal, int width, int height, String type) {
        this.diagonal = diagonal;
        this.resolutionWidth = width;
        this.resolutionHeight = height;
        this.type = type;
    }

    /**
     * КОНСТРУКТОР КОПІЮВАННЯ.
     * <p>
     * Створений для реалізації "глибокого копіювання".
     * Коли клас {@code Television} отримує зовнішній об'єкт Screen,
     * він використовує цей конструктор, щоб створити власний,
     * повністю незалежний екземпляр.
     *
     * @param other Об'єкт {@code Screen}, з якого копіюються дані.
     */
    public Screen(Screen other) {
        // Копіювання значень. Оскільки int - примітив,
        // а String - незмінний (immutable), простого
        // присвоєння достатньо для глибокого копіювання.
        this.diagonal = other.diagonal;
        this.resolutionWidth = other.resolutionWidth;
        this.resolutionHeight = other.resolutionHeight;
        this.type = other.type;
    }

    // --- Сетери та гетери ---

    public void setType(String type) {
        this.type = type;
    }

    public void setResolution(int width, int height) {
        this.resolutionWidth = width;
        this.resolutionHeight = height;
    }

    /**
     * Повертає роздільну здатність у форматі "Ширина x Висота".
     * Конкатенацію (+) замінено на String.format().
     *
     * @return Рядок, що описує роздільну здатність.
     */
    public String getResolution() {
        // Було: return resolutionWidth + "x" + resolutionHeight;
        return String.format("%dx%d", resolutionWidth, resolutionHeight);
    }

    /**
     * Метод toString() для виведення інформації про екран.
     */
    @Override
    public String toString() {
        return String.format(
                "Екран[Діагональ: %d\", Роздільна здатність: %dx%d, Тип: %s]",
                diagonal, resolutionWidth, resolutionHeight, type);
    }
}