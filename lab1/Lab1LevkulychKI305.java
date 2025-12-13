import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Клас Lab1ПрізвищеГрупа
 * 
 * Призначення:
 * Програма створює квадратну матрицю заданого розміру,
 * розділену на чергування блоків пустих рядків та
 * рядків із символом-заповнювачем.
 * 
 * Логіка:
 * - Розмір матриці n має бути кратним 6.
 * - Кожен блок має розмір n / 6 рядків.
 * - Чергування блоків відбувається за схемою: 3 пустих блоки,
 * потім 3 заштрихованих блоки.
 * 
 * Результат виводиться на екран та зберігається у файл "output.txt".
 * 
 * @version 1.0
 * @since 2025
 */
public class Lab1LevkulychKI305 {

    /** Імʼя файлу для збереження результату */
    private static final String OUTPUT_FILE = "output.txt";

    /**
     * Головний метод програми.
     * Послідовність операцій:
     * 1. Зчитування розміру матриці.
     * 2. Зчитування символу-заповнювача.
     * 3. Генерація зубчастого масиву.
     * 4. Вивід масиву на екран.
     * 5. Збереження масиву у файл.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // створення об'єкта Scanner для зчитування вводу

        try {
            // 1. Зчитування розміру матриці
            int n = readMatrixSize(scanner);

            // 2. Зчитування символу-заповнювача
            char fillChar = readFillCharacter(scanner);

            // 3. Генерація зубчастого масиву
            char[][] jaggedArray = generateJaggedArray(n, fillChar);

            // 4. Вивід масиву на екран
            System.out.println("\nЗгенерований зубчастий масив:");
            printArray(jaggedArray);

            // 5. Збереження масиву у файл
            saveToFile(jaggedArray, OUTPUT_FILE);
            System.out.println("\nМасив збережено у файл '" + OUTPUT_FILE + "'");

        } catch (IllegalArgumentException e) {
            // Якщо користувач ввів некоректні дані
            System.err.println("Помилка вводу: " + e.getMessage());
        } catch (IOException e) {
            // Якщо сталася помилка при записі у файл
            System.err.println("Помилка запису у файл: " + e.getMessage());
        } finally {
            scanner.close(); // закриття ресурсу Scanner
        }
    }

    /**
     * Зчитує розмір квадратної матриці з клавіатури.
     * 
     * Логіка:
     * - Виводить повідомлення користувачу.
     * - Зчитує введене число.
     * - Перевіряє, чи число додатнє і кратне 6.
     * - Якщо умови не виконані — викидає виключення IllegalArgumentException.
     * 
     * @param scanner обʼєкт Scanner для зчитування вводу
     * @return розмір матриці n
     */
    private static int readMatrixSize(Scanner scanner) {
        System.out.print("Введіть розмір квадратної матриці (кратний 6): ");
        try {
            int n = scanner.nextInt(); // зчитування числа
            scanner.nextLine(); // очищення буфера вводу
            if (n <= 0 || n % 6 != 0) {
                // перевірка: число має бути >0 і кратним 6
                throw new IllegalArgumentException("Розмір має бути додатнім і кратним 6!");
            }
            return n;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // очищення буфера вводу при помилці
            throw new IllegalArgumentException("Необхідно ввести ціле число!");
        }
    }

    /**
     * Зчитує символ-заповнювач з клавіатури.
     * 
     * Логіка:
     * - Запитує користувача ввести символ.
     * - Очищує пробіли на початку і в кінці.
     * - Перевіряє, чи введено саме один символ.
     * 
     * @param scanner обʼєкт Scanner
     * @return символ-заповнювач
     */
    private static char readFillCharacter(Scanner scanner) {
        System.out.print("Введіть символ-заповнювач: ");
        String input = scanner.nextLine().trim(); // зчитування рядка та видалення пробілів
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Символ не введено!");
        }
        if (input.length() > 1) {
            throw new IllegalArgumentException("Введено більше одного символу!");
        }
        return input.charAt(0); // повертає перший символ рядка
    }

    /**
     * Генерує зубчастий масив.
     * 
     * Логіка:
     * - Розмір блоку визначається як n / 6.
     * - Кожен блок складається з blockSize рядків.
     * - Чергування блоків: 3 пусті, 3 заповнені.
     * - Використовується формула:
     * (i / blockSize) % 2 != 0
     * Щоб визначити, чи даний рядок має бути заштрихованим.
     * 
     * @param n        розмір матриці
     * @param fillChar символ-заповнювач
     * @return двовимірний масив символів
     */
    private static char[][] generateJaggedArray(int n, char fillChar) {
        char[][] array = new char[n][n]; // створення матриці n×n

        // Розрахунок розміру блоку
        int blockSize = n / 6; // наприклад: n=12 → blockSize=2

        // Заповнення матриці
        for (int i = 0; i < n; i++) {
            // Визначення типу блоку (пустий чи заштрихований)
            boolean shaded = ((i / blockSize) % 2 != 0);

            // Заповнення рядка символами
            for (int j = 0; j < n; j++) {
                array[i][j] = shaded ? fillChar : ' '; // тернарний оператор
            }
        }
        return array;
    }

    /**
     * Виводить матрицю у консоль.
     * 
     * @param array масив символів
     */
    private static void printArray(char[][] array) {
        for (char[] row : array) {
            for (char ch : row) {
                System.out.print(ch + " "); // вивід символу з пробілом
            }
            System.out.println(); // перехід на новий рядок
        }
    }

    /**
     * Зберігає масив у текстовий файл.
     * 
     * Логіка:
     * - Створює PrintWriter для запису у файл.
     * - Проходить по всіх рядках і записує їх.
     * 
     * @param array    масив символів
     * @param filename імʼя файлу
     * @throws IOException якщо сталася помилка запису
     */
    private static void saveToFile(char[][] array, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (char[] row : array) {
                for (char ch : row) {
                    writer.print(ch + " "); // запис символу з пробілом
                }
                writer.println(); // перехід на новий рядок у файлі
            }
        }
    }
}
