package KI305.Levkulych.Lab4;

import java.util.Scanner;
import java.io.*; // Імпорт для роботи з файлами (PrintWriter, File, FileNotFoundException)
import static java.lang.System.out; // Імпорт out для скороченого запису System.out.print

/**
 * Клас <code>EquationsApp</code> реалізує головний клас-драйвер
 * для тестування класу <code>Equations</code>.
 * Він відповідає за зчитування даних, виклик обчислень та запис у файл.
 * [cite: 362]
 *
 * @author Levkulych
 * @version 1.0
 */
public class EquationsApp {

    /**
     * Головний метод програми.
     * 
     * @param args Аргументи командного рядка (не використовуються).
     */
    // Ця анотація приховує попередження "Resource leak: 'in' is never closed".
    // Ми свідомо не закриваємо Scanner(System.in), щоб уникнути закриття
    // стандартного потоку вводу, що відповідає практиці з методички.
    @SuppressWarnings("resource")
    public static void main(String[] args) {

        // Зовнішній блок try...catch для перехоплення помилок, пов'язаних
        // з відкриттям файлу (наприклад, неправильний шлях).
        try {
            // Виводимо підказку
            out.print("Enter file name: ");

            // Створюємо Scanner для читання з консолі
            Scanner in = new Scanner(System.in);

            // Читаємо назву файлу, введену користувачем
            String fName = in.nextLine();

            // Створюємо PrintWriter для запису у файл
            // Цей рядок може згенерувати FileNotFoundException,
            // яку перехопить зовнішній 'catch'.
            PrintWriter fout = new PrintWriter(new File(fName));

            // Внутрішній блок try...catch...finally для обчислень
            // Це дозволяє гарантовано закрити файл у 'finally',
            // навіть якщо обчислення в 'try' спричинять помилку.
            try {
                // Ще один вкладений try
                try {
                    Equations eq = new Equations(); // Створюємо об'єкт класу обчислень

                    // Виводимо підказку
                    out.print("Enter X (in degrees): ");

                    // Читаємо ціле число X.
                    // Може згенерувати InputMismatchException, якщо введено не число.
                    int x = in.nextInt();

                    // Викликаємо метод обчислення
                    double result = eq.calculate(x); // Може згенерувати CalcException

                    // Записуємо результат у файл
                    fout.print("Result for X=" + x + " is " + result);

                } finally {
                    // Блок finally виконується ЗАВЖДИ, незалежно від того,
                    // чи була помилка у 'try'.
                    // Ідеальне місце для закриття ресурсів, як-от файли.
                    out.flush(); // Очищуємо буфер виводу
                    fout.close(); // Закриваємо файл
                }
            } catch (CalcException ex) {
                // Перехоплюємо виключення, якщо воно виникло
                // під час eq.calculate(x) ].
                // Виводимо повідомлення про помилку
                out.print(ex.getMessage());
            }

        } catch (FileNotFoundException ex) {
            // Перехоплюємо помилку, якщо файл не вдалося створити.
            // Виводимо повідомлення про помилку
            out.print("Exception reason: Perhaps wrong file path or access denied");
        } catch (java.util.InputMismatchException ex) {
            // (Додатково) Перехоплюємо помилку, якщо користувач ввів не число.
            // Виводимо повідомлення про помилку
            out.print("Error: You must enter a valid integer for X.");
        }
    }
}