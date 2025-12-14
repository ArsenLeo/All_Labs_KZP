package KI305.Levkulych.Lab3;

import java.io.IOException;

/**
 * Клас-драйвер {@code TelevisionDriver}, що є точкою входу в програму (main).
 * <p>
 * Його головне завдання — продемонструвати створення та використання об'єкта
 * {@link TunerTelevision}, а також показати в дії ключові принципи ООП:
 * <ul>
 * <li><b>Спадкування:</b> Об'єкт `TunerTelevision` має доступ до методів свого
 * батька {@link Television}.</li>
 * <li><b>Поліморфізм:</b> Об'єкт підкласу (`TunerTelevision`) присвоюється
 * змінній типу суперкласу (`Television`).</li>
 * <li><b>Робота з інтерфейсом:</b> Демонстрація виклику методів з інтерфейсу
 * {@link Tuner} через приведення типів.</li>
 * </ul>
 *
 * @author Levkulych
 * @version 3.1
 * @since 2025
 */
public class TelevisionDriver {

    /**
     * Головний метод, що запускає демонстрацію.
     * <p>
     * Створює екземпляр телевізора з тюнером, виконує над ним низку операцій
     * для показу функціоналу та коректно завершує роботу, закриваючи лог-файл.
     *
     * @param args Аргументи командного рядка (в цій програмі не використовуються).
     */
    public static void main(String[] args) {

        // Використовуємо конструкцію try-with-resources.
        // Оскільки Television реалізує AutoCloseable, його екземпляр можна оголосити
        // в круглих дужках блоку try. Це гарантує, що метод close() для закриття файлу
        // буде викликаний автоматично при завершенні блоку, навіть якщо виникне
        // помилка.
        // Це вирішує проблему "Resource leak".
        try (Television myTV = new TunerTelevision(
                new Television.TelevisionBuilder()
                        .withScreen(new Screen(55, 3840, 2160, "OLED"))
                        .withAudioSystem(new AudioSystem(60, 4, true))
                        .withSmartSystem(new SmartSystem("Android TV", "11.0")),
                "Вбудований T2/S2 тюнер" // Додатковий параметр для конструктора TunerTelevision
        )) {

            System.out.println("--- Початковий стан телевізора ---");
            // Демонстрація ПОЛІМОРФІЗМУ: викликаємо перевизначений метод showDetails(),
            // який покаже інформацію і з базового класу, і з дочірнього.
            myTV.showDetails();

            // Демонстрація роботи з ІНТЕРФЕЙСОМ:
            // Змінна myTV має тип Television, в якому немає методів scanChannels() або
            // getTunerStatus().
            // Щоб їх викликати, ми маємо перевірити, чи реалізує наш об'єкт інтерфейс
            // Tuner.
            if (myTV instanceof Tuner) {
                // Якщо так, ми можемо безпечно виконати приведення типів (type casting).
                Tuner tunerDevice = (Tuner) myTV;

                // Тепер ми можемо викликати методи, визначені в інтерфейсі Tuner.
                tunerDevice.scanChannels();
                System.out.println("\n" + tunerDevice.getTunerStatus());
            }

            System.out.println("\n--- Демонстрація роботи методів ---");
            // Викликаємо метод з базового класу Television.
            myTV.increaseVolume(20);

            // Викликаємо реалізований абстрактний метод switchChannel().
            // Цей метод є у Television, тому приведення типів не потрібне.
            myTV.switchChannel(3);
            myTV.switchChannel(99); // Спроба перемкнути на неіснуючий канал.

            System.out.println("\n--- Фінальний стан телевізора ---");
            myTV.showDetails();

            System.out.println("\nПрограма завершує роботу. Ресурси будуть звільнені автоматично.");

        } catch (IOException e) {
            // Обробка можливої помилки при роботі з файлом логів (наприклад, немає прав на
            // запис).
            System.err.println("Помилка при роботі з лог-файлом: " + e.getMessage());
        }
        // Метод myTV.close() буде викликано тут автоматично завдяки try-with-resources.
    }
}