package KI305.Levkulych.Lab4;

/**
 * Клас <code>CalcException</code> деталізує виключення
 * <code>ArithmeticException</code>.
 * * Цей клас створюється, щоб ми могли генерувати та перехоплювати
 * конкретний тип арифметичних помилок, пов'язаних з нашими обчисленнями,
 * замість загального <code>ArithmeticException</code>.
 * Це відповідає прикладу з методичних вказівок. 
 *
 * @author Levkulych
 * @version 1.0
 */
class CalcException extends ArithmeticException {

    /**
     * Конструктор за замовчуванням.
     * Створює <code>CalcException</code> без повідомлення.
     */
    public CalcException() {
    }

    /**
     * Конструктор з повідомленням про причину.
     * Дозволяє встановити конкретне повідомлення про помилку.
     *
     * @param cause Рядок, що описує причину виключення.
     */
    public CalcException(String cause) {
        // Виклик конструктора батьківського класу (ArithmeticException)
        // щоб зберегти повідомлення про помилку.
        super(cause);
    }
}