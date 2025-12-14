package KI305.Levkulych.Lab2;

/**
 * Клас {@code AudioSystem} моделює звукову підсистему телевізора.
 * <p>
 * Він інкапсулює такі характеристики, як потужність, кількість динаміків,
 * підтримку Dolby Digital та поточний рівень гучності.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class AudioSystem {
    // Приватні поля
    private int power;
    private int speakers;
    private boolean dolby;
    private int volume;

    /**
     * Основний конструктор для аудіосистеми.
     *
     * @param power    Потужність у ватах.
     * @param speakers Кількість динаміків.
     * @param dolby    Наявність Dolby.
     */
    public AudioSystem(int power, int speakers, boolean dolby) {
        this.power = power;
        this.speakers = speakers;
        this.dolby = dolby;
        this.volume = 50; // Значення гучності за замовчуванням
    }

    /**
     * КОНСТРУКТОР КОПІЮВАННЯ.
     * <p>
     * Використовується класом {@code Television} для створення
     * "захисної копії". Це гарантує, що телевізор
     * матиме власний, незалежний об'єкт аудіосистеми.
     *
     * @param other Об'єкт {@code AudioSystem}, з якого копіюються дані.
     */
    public AudioSystem(AudioSystem other) {
        // Усі поля є примітивними типами (int, boolean),
        // тому просте копіювання їхніх значень
        // автоматично створює глибоку копію.
        this.power = other.power;
        this.speakers = other.speakers;
        this.dolby = other.dolby;
        this.volume = other.volume;
    }

    public int getVolume() {
        return volume;
    }

    /**
     * "Розумний" сетер, що обмежує гучність
     * в межах [0, 100].
     *
     * @param volume Новий рівень гучності.
     */
    public void setVolume(int volume) {
        if (volume < 0) {
            this.volume = 0;
        } else if (volume > 100) {
            this.volume = 100;
        } else {
            this.volume = volume;
        }
    }

    /**
     * Збільшення гучності. Використовує setVolume,
     * щоб не дублювати логіку обмежень (принцип DRY).
     */
    public void increaseVolume(int amount) {
        this.setVolume(this.volume + amount);
    }

    /**
     * Зменшення гучності. Також використовує setVolume.
     */
    public void decreaseVolume(int amount) {
        this.setVolume(this.volume - amount);
    }

    public boolean hasDolby() {
        return dolby;
    }

    /**
     * Метод toString() для виведення інформації про звук.
     */
    @Override
    public String toString() {
        return String.format(
                "Аудіосистема[Потужність: %dВт, Динаміки: %d, Dolby: %s, Гучність: %d]",
                power,
                speakers,
                dolby ? "Так" : "Ні", // Компактний вивід
                volume);
    }
}