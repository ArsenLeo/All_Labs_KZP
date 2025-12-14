package KI305.Levkulych.Lab4;

import static java.lang.Math.*;

/**
 * Class <code>Equations</code> implements method for (cos(x) / tg(2x))
 * expression
 * calculation
 * 
 * @author Levkulych
 * @version 1.0
 */
class Equations {

    /**
     * Method calculates the (cos(x) / tg(2x)) expression
     * 
     * @param x Angle in degrees
     * @return Result of calculation
     * @throws CalcException
     */
    public double calculate(int x) throws CalcException {
        double y, rad;
        rad = x * PI / 180.0;

        try {
            // Обчислюємо tg(2x)
            double tan2x = tan(2 * rad);

            // Обчислюємо y = cos(x) / tg(2x)
            y = cos(rad) / tan2x;

            // Як і в методичці, ми перевіряємо не тільки
            // на NaN/Infinity, але й на самі проблемні значення X.
            // Для нас це будь-яке x, що ділиться на 45.
            if (y == Double.NaN || y == Double.NEGATIVE_INFINITY || y == Double.POSITIVE_INFINITY || (x % 45 == 0)) {

                // Примусово генеруємо виключення, щоб
                // перейти в блок catch
                throw new ArithmeticException();
            }
        } catch (ArithmeticException ex) {
            // Тепер ми в блоці catch і з'ясовуємо причину

            // Випадок 1: x = 45, 135, ... (непарні, кратні 45)
            // (cos(2x) дорівнює 0)
            if (x % 90 != 0 && x % 45 == 0) {
                throw new CalcException(
                        "Exception reason: Illegal value of X for tangent calculation (cos(2x) is zero) at x = " + x);
            }
            // Випадок 2: x = 0, 90, 180, ... (кратні 90)
            // (tg(2x) дорівнює 0)
            else if (x % 90 == 0) {
                throw new CalcException("Exception reason: Division by zero (tg(2x) is zero) at x = " + x);
            }
            // Інша невідома причина
            else {
                // Цей блок тепер не повинен виконуватись,
                // залишаємо для надійності
                throw new CalcException("Unknown reason of the exception during calculation");
            }
        }
        return y;
    }
}