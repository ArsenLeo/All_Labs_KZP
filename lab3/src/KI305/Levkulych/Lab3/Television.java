package KI305.Levkulych.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Абстрактний клас {@code Television}, що слугує фундаментальною моделлю для
 * всіх типів телевізорів.
 * <p>
 * Цей клас інкапсулює базові компоненти, такі як екран, аудіосистема та
 * смарт-функціонал,
 * які є спільними для всіх похідних класів. Він реалізує {@link AutoCloseable}
 * для
 * автоматичного керування файлом логів.
 * <p>
 * Оскільки клас є абстрактним, створення його екземплярів напряму заборонено.
 * Для створення конкретного телевізора необхідно створити підклас, що
 * успадковує цей клас.
 *
 * @author Levkulych
 * @version 3.0
 * @since 2025
 */
public abstract class Television implements AutoCloseable {
    // КОМПОНЕНТИ ТЕЛЕВІЗОРА
    protected Screen screen;
    protected AudioSystem audioSystem;
    protected SmartSystem smartSystem;

    // КОМПОНЕНТИ ДЛЯ ЛОГУВАННЯ
    private PrintWriter logWriter;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Захищений конструктор, призначений для виклику з конструкторів підкласів.
     * <p>
     * Ініціалізує всі базові компоненти телевізора, виконуючи глибоке копіювання
     * об'єктів, наданих через патерн "Будівельник" (Builder), щоб забезпечити
     * повну незалежність стану об'єкта. Також відкриває файл для логування.
     *
     * @param builder Об'єкт {@code TelevisionBuilder}, що містить конфігурацію для
     *                ініціалізації.
     * @throws IOException Виникає, якщо не вдається відкрити або створити файл
     *                     логів "log_lab3.txt".
     */
    protected Television(TelevisionBuilder builder) throws IOException {
        this.screen = new Screen(builder.screen);
        this.audioSystem = new AudioSystem(builder.audioSystem);
        this.smartSystem = new SmartSystem(builder.smartSystem);
        this.logWriter = new PrintWriter(new FileWriter("log_lab3.txt", true));
        log("Базовий екземпляр телевізора ініціалізовано.");
    }

    /**
     * Внутрішній статичний клас {@code TelevisionBuilder}, що реалізує патерн
     * проєктування "Будівельник".
     * <p>
     * Дозволяє гнучко та покроково створювати конфігурацію для об'єкта
     * {@code Television},
     * задаючи його компоненти окремими методами. Це робить код створення об'єкта
     * більш читабельним та запобігає появі конструкторів з великою кількістю
     * параметрів.
     */
    public static class TelevisionBuilder {
        // Поля для тимчасового зберігання компонентів з конфігураціями за замовчуванням
        private Screen screen = new Screen(42, 1920, 1080, "LED");
        private AudioSystem audioSystem = new AudioSystem(20, 2, false);
        private SmartSystem smartSystem = new SmartSystem("Generic OS", "1.0");

        /**
         * Встановлює конфігурацію екрана для телевізора.
         * 
         * @param screen Об'єкт {@code Screen} з бажаними параметрами.
         * @return Поточний екземпляр {@code TelevisionBuilder} для ланцюжкових
         *         викликів.
         */
        public TelevisionBuilder withScreen(Screen screen) {
            this.screen = screen;
            return this;
        }

        /**
         * Встановлює конфігурацію аудіосистеми для телевізора.
         * 
         * @param audioSystem Об'єкт {@code AudioSystem} з бажаними параметрами.
         * @return Поточний екземпляр {@code TelevisionBuilder}.
         */
        public TelevisionBuilder withAudioSystem(AudioSystem audioSystem) {
            this.audioSystem = audioSystem;
            return this;
        }

        /**
         * Встановлює конфігурацію смарт-системи для телевізора.
         * 
         * @param smartSystem Об'єкт {@code SmartSystem} з бажаними параметрами.
         * @return Поточний екземпляр {@code TelevisionBuilder}.
         */
        public TelevisionBuilder withSmartSystem(SmartSystem smartSystem) {
            this.smartSystem = smartSystem;
            return this;
        }
    }

    /**
     * Абстрактний метод для перемикання телевізійного каналу.
     * <p>
     * Цей метод не має реалізації в базовому класі, оскільки логіка перемикання
     * каналів
     * залежить від наявності та типу тюнера. Кожен конкретний підклас, що має
     * тюнер,
     * зобов'язаний надати власну реалізацію цього методу.
     *
     * @param channel Номер каналу, на який потрібно перемкнутися.
     */
    public abstract void switchChannel(int channel);

    /**
     * Виводить детальну інформацію про всі основні компоненти телевізора в консоль.
     */
    public void showDetails() {
        System.out.println(screen);
        System.out.println(audioSystem);
        System.out.println(smartSystem);
    }

    /**
     * Збільшує поточний рівень гучності на задану величину.
     * 
     * @param amount Значення, на яке буде збільшено гучність.
     */
    public void increaseVolume(int amount) {
        audioSystem.increaseVolume(amount);
        log("Гучність збільшено на %d. Новий рівень: %d", amount, audioSystem.getVolume());
    }

    /**
     * Перемикає стан Wi-Fi з'єднання (вмикає, якщо було вимкнено, і навпаки).
     */
    public void toggleWifi() {
        if (smartSystem.isWifiConnected()) {
            smartSystem.disconnectWifi();
            log("WiFi вимкнено.");
        } else {
            smartSystem.connectWifi();
            log("WiFi увімкнено.");
        }
    }

    /**
     * Внутрішній захищений метод для запису повідомлень у лог-файл.
     * <p>
     * Додає до кожного повідомлення часову мітку та забезпечує примусовий запис
     * даних у файл (flush), щоб уникнути їх втрати при несподіваному завершенні
     * програми.
     *
     * @param format Рядок форматування, аналогічний до `String.format()`.
     * @param args   Аргументи для форматування рядка.
     */
    protected void log(String format, Object... args) {
        String timestamp = LocalDateTime.now().format(formatter);
        String message = String.format(format, args);
        if (logWriter != null) {
            logWriter.println("[" + timestamp + "] " + message);
            logWriter.flush();
        }
    }

    /**
     * Метод для закриття файлу логів, що реалізує інтерфейс {@link AutoCloseable}.
     * <p>
     * Цей метод автоматично викликається при виході з блоку try-with-resources,
     * що гарантує коректне закриття файлу та звільнення ресурсів.
     */
    @Override
    public void close() {
        if (logWriter != null) {
            log("Закриття файлу логів.");
            logWriter.close();
        }
    }
}