package KI305.Levkulych.Lab5;

import java.io.IOException;
import java.util.Scanner;

/**
 * Драйвер для запуску програми обчислення виразу та тестування методів читання
 * і запису.
 */
public class EquationsApp {
    public static void main(String[] args) {
        ExpressionCalculator calculator = new ExpressionCalculator();
        Scanner scanner = new Scanner(System.in);

        try {
            // Отримання значення x від користувача
            System.out.print("Введіть значення x: ");
            double x = scanner.nextDouble();

            // Обчислення результату
            double result = calculator.calculate(x);
            System.out.println("Результат: " + result);

            // Запис результату у текстовий файл
            String textFilePath = "result.txt";
            calculator.writeResultToFile(result, textFilePath);
            System.out.println("Результат записано у текстовий файл: " + textFilePath);

            // Запис результату у двійковий файл
            String binaryFilePath = "result.bin";
            calculator.writeResultToBinaryFile(result, binaryFilePath);
            System.out.println("Результат записано у двійковий файл: " + binaryFilePath);

            // Читання результату з текстового файлу
            double textResult = calculator.readResultFromFile(textFilePath);
            System.out.println("Результат, зчитаний з текстового файлу: " + textResult);

            // Читання результату з двійкового файлу
            double binaryResult = calculator.readResultFromBinaryFile(binaryFilePath);
            System.out.println("Результат, зчитаний з двійкового файлу: " + binaryResult);

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка запису або читання файлу: " + e.getMessage());
        } finally {
            // Закриття сканера для уникнення витоку ресурсів
            scanner.close();
        }
    }
}
