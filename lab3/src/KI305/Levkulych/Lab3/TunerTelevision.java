package KI305.Levkulych.Lab3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас {@code TunerTelevision} представляє конкретну модель телевізора,
 * оснащеного вбудованим тюнером.
 * <p>
 * Цей клас є прикладом **спадкування** та **реалізації інтерфейсу**:
 * <ul>
 * <li>Він **успадковує** (extends) весь базовий функціонал від абстрактного
 * класу {@link Television}.</li>
 * <li>Він **реалізує** (implements) інтерфейс {@link Tuner}, додаючи специфічну
 * логіку для роботи з каналами.</li>
 * </ul>
 *
 * @author Levkulych
 * @version 3.0
 * @since 2025
 */
public class TunerTelevision extends Television implements Tuner {
    private int currentChannel;
    private List<String> channelList;
    private final String tunerInfo;

    /**
     * Конструктор для створення екземпляра телевізора з тюнером.
     * <p>
     * Ініціалізує базові компоненти, викликаючи конструктор суперкласу
     * {@link Television},
     * а також встановлює специфічні для цієї моделі параметри, такі як інформація
     * про тюнер.
     *
     * @param builder   Об'єкт {@code TelevisionBuilder} з конфігурацією базових
     *                  компонентів (екран, аудіо тощо).
     * @param tunerInfo Рядок, що описує модель та тип вбудованого тюнера
     *                  (наприклад, "DVB-T2/S2").
     * @throws IOException Виникає, якщо не вдається ініціалізувати логер у
     *                     суперкласі.
     */
    public TunerTelevision(Television.TelevisionBuilder builder, String tunerInfo) throws IOException {
        // Першим кроком є виклик конструктора батьківського класу для ініціалізації
        // базових полів.
        super(builder);
        this.tunerInfo = tunerInfo;
        this.currentChannel = 1; // Встановлюємо початковий канал за замовчуванням
        this.channelList = new ArrayList<>(); // Ініціалізуємо порожній список каналів
        log("Створено екземпляр TunerTelevision з тюнером: %s", tunerInfo);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Цей метод надає конкретну реалізацію абстрактного методу з класу
     * {@link Television}.
     * Він перевіряє, чи існує канал із заданим номером у списку знайдених каналів,
     * і перемикається на нього, якщо він доступний.
     */
    @Override
    public void switchChannel(int channel) {
        if (!channelList.isEmpty() && channel > 0 && channel <= channelList.size()) {
            this.currentChannel = channel;
            log("Перемкнено на канал %d: %s", channel, channelList.get(channel - 1));
        } else {
            log("Помилка: канал %d не знайдено або список каналів порожній.", channel);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Реалізує метод з інтерфейсу {@link Tuner}. Цей метод імітує процес
     * автоматичного пошуку та збереження списку доступних телевізійних каналів.
     */
    @Override
    public void scanChannels() {
        channelList.clear();
        // Імітація сканування - додаємо статичний список каналів
        channelList.add("1+1");
        channelList.add("СТБ");
        channelList.add("ICTV");
        channelList.add("Новий Канал");
        log("Сканування завершено. Знайдено %d каналів.", channelList.size());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Реалізує метод з інтерфейсу {@link Tuner}. Повертає форматований рядок,
     * що містить інформацію про модель тюнера, поточний канал та загальну
     * кількість знайдених каналів.
     */
    @Override
    public String getTunerStatus() {
        return String.format("Тюнер: %s | Канал: %d | Всього каналів: %d",
                tunerInfo, currentChannel, channelList.size());
    }

    /**
     * Перевизначає метод {@code showDetails} батьківського класу для надання більш
     * повної інформації.
     * <p>
     * Спочатку викликає реалізацію методу з суперкласу
     * ({@code super.showDetails()}) для виведення
     * інформації про базові компоненти, а потім додає до виводу специфічну
     * інформацію про тюнер.
     */
    @Override
    public void showDetails() {
        super.showDetails(); // Викликаємо метод батька
        System.out.println(getTunerStatus()); // Додаємо свою інформацію
        log("Викликано розширений показ деталей.");
    }
}