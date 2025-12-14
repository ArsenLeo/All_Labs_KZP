package KI305.Levkulych.Lab5;

import java.io.*;

/**
 * Клас для обчислення виразу y = cos(x)/tg(2x).
 * Використовується для демонстрації механізму виключень і запису результатів у
 * файл.
 */
public class ExpressionCalculator {

    /**
     * Обчислює вираз y = cos(x)/tg(2x).
     *
     * @param x значення змінної x
     * @return результат обчислення виразу
     * @throws IllegalArgumentException якщо x = 0, оскільки вираз не визначений
     */
    public double calculate(double x) throws IllegalArgumentException {
        if (x == 0) {
            throw new IllegalArgumentException("Значення x не може бути 0, оскільки вираз не визначений.");
        }

        return Math.cos(x) / Math.tan(2 * x);

    }

    /**
     * Записує результат обчислення у текстовий файл.
     *
     * @param result   результат обчислення
     * @param filePath шлях до файлу
     * @throws IOException якщо виникає помилка при записі у файл
     */
    public void writeResultToFile(double result, String filePath) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write("Результат обчислення: " + result);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Записує результат обчислення у двійковий файл.
     *
     * @param result   результат обчислення
     * @param filePath шлях до двійкового файлу
     * @throws IOException якщо виникає помилка при записі у файл
     */
    public void writeResultToBinaryFile(double result, String filePath) throws IOException {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(filePath));
            dos.writeDouble(result);
        } finally {
            if (dos != null) {
                dos.close();
            }
        }
    }

    /**
     * Читає результат з текстового файлу.
     *
     * @param filePath шлях до файлу
     * @return результат обчислення, зчитаний з файлу
     * @throws IOException якщо виникає помилка при читанні файлу
     */
    public double readResultFromFile(String filePath) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            return Double.parseDouble(line.replaceAll("[^\\d.-]", ""));
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * Читає результат з двійкового файлу.
     *
     * @param filePath шлях до двійкового файлу
     * @return результат обчислення, зчитаний з файлу
     * @throws IOException якщо виникає помилка при читанні файлу
     */
    public double readResultFromBinaryFile(String filePath) throws IOException {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(filePath));
            return dis.readDouble();
        } finally {
            if (dis != null) {
                dis.close();
            }
        }
    }
}
