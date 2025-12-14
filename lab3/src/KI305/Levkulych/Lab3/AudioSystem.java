package KI305.Levkulych.Lab3;

/**
 * Клас {@code AudioSystem} моделює звукову підсистему телевізора.
 * <p>
 * Він інкапсулює такі характеристики, як потужність, кількість динаміків,
 * підтримку Dolby Digital та поточний рівень гучності. Надає методи
 * для керування гучністю.
 *
 * @author Levkulych
 * @version 2.2
 * @since 2025
 */
public class AudioSystem {
    /** Потужність звукової системи у ватах (W). */
    private int power;

    /** Кількість фізичних динаміків. */
    private int speakers;

    /** Прапорець підтримки технології об'ємного звуку Dolby Digital. */
    private boolean dolby;

    /** Поточний рівень гучності, що регулюється в межах від 0 до 100. */
    private int volume;

    /**
     * Основний конструктор для створення звукової системи з заданими параметрами.
     *
     * @param power    Потужність у ватах (наприклад, 50).
     * @param speakers Кількість динаміків (наприклад, 2).
     * @param dolby    {@code true}, якщо є підтримка Dolby, інакше {@code false}.
     */
    public AudioSystem(int power, int speakers, boolean dolby) {
        this.power = power;
        this.speakers = speakers;
        this.dolby = dolby;
        this.volume = 50; // Початковий рівень гучності за замовчуванням.
    }

    /**
     * Конструктор копіювання.
     * Створює глибоку копію іншого об'єкта {@code AudioSystem},
     * забезпечуючи повну незалежність об'єктів.
     *
     * @param other Об'єкт {@code AudioSystem} для копіювання.
     */
    public AudioSystem(AudioSystem other) {
        this.power = other.power;
        this.speakers = other.speakers;
        this.dolby = other.dolby;
        this.volume = other.volume;
    }

    /**
     * Повертає поточний рівень гучності.
     *
     * @return Значення гучності від 0 до 100.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Встановлює новий рівень гучності.
     * Якщо значення виходить за межі [0, 100], воно буде автоматично обмежене.
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
     * Збільшує гучність на задану величину.
     * Максимальний рівень гучності обмежений 100.
     *
     * @param amount Значення, на яке потрібно збільшити гучність.
     */
    public void increaseVolume(int amount) {
        this.volume += amount;
        if (this.volume > 100) {
            this.volume = 100;
        }
    }

    /**
     * Зменшує гучність на задану величину.
     * Мінімальний рівень гучності обмежений 0.
     *
     * @param amount Значення, на яке потрібно зменшити гучність.
     */
    public void decreaseVolume(int amount) {
        this.volume -= amount;
        if (this.volume < 0) {
            this.volume = 0;
        }
    }

    /**
     * Перевіряє наявність підтримки Dolby Digital.
     *
     * @return {@code true}, якщо Dolby підтримується, інакше {@code false}.
     */
    public boolean hasDolby() {
        return dolby;
    }

    /**
     * Повертає рядкове представлення об'єкта {@code AudioSystem}.
     *
     * @return Рядок з інформацією про аудіосистему.
     */
    @Override
    public String toString() {
        return String.format(
                "Аудіосистема[Потужність: %dВт, Динаміки: %d, Dolby: %s, Гучність: %d]",
                power,
                speakers,
                dolby ? "Так" : "Ні",
                volume);
    }
}