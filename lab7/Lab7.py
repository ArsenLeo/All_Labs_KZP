"""
Модуль Lab7
-----------
Цей модуль реалізує функціонал лабораторної роботи №7.
Він генерує зубчатий масив (список списків), заповнюючи його символами
згідно з варіантом завдання (горизонтальні смуги).

Автор: Левкулич А. М.
Група: КІ-305
Дата: 2025

"""

import sys

def main():
    """
    Головна функція програми.
    
    Виконує наступні дії:
    1. Зчитує розмір матриці та символ-заповнювач.
    2. Генерує зубчатий список (list of lists).
    3. Виводить результат на екран.
    4. Записує результат у файл 'output.txt'.
    
    Raises:
        ValueError: Якщо введено не числове значення для розміру матриці.
        IOError: Якщо виникає помилка при записі у файл.
    """
    output_file = "output.txt"

    try:
        # 1. Зчитування розміру матриці
        print("Введіть розмір квадратної матриці (кратний 6): ", end="")
        try:
            rows_num = int(input())
        except ValueError:
            print("Помилка: введено не число.")
            sys.exit(1)

        if rows_num <= 0 or rows_num % 6 != 0:
            print("Помилка: Розмір має бути додатнім і кратним 6!")
            sys.exit(1)

        # 2. Зчитування символу-заповнювача
        print("Введіть символ-заповнювач: ", end="")
        filler = input().strip()

        if len(filler) == 0:
            print("Помилка: Не введено символ-заповнювач")
            sys.exit(1)
        elif len(filler) > 1:
            print("Помилка: Забагато символів-заповнювачів")
            sys.exit(1)

        # 3. Генерація ІСТИННО зубчастого списку
        lst = []
        block_size = rows_num // 6
        
        for i in range(rows_num):
            row = [] # Створюємо новий рядок
            
            # Визначаємо, чи має цей рядок бути заштрихованим
            is_shaded = (i // block_size) % 2 != 0
            
            if is_shaded:
                # Тільки якщо заштрихований - заповнюємо символами
                for j in range(rows_num):
                    row.append(filler)
            
            # Додаємо рядок (повний або пустий) у список
            lst.append(row)

        # 4. Вивід масиву на екран
        print("\nЗгенерований масив:")
        for row in lst:
            if len(row) > 0:
                for char in row:
                    print(char, end=" ")
            else:
                # Якщо рядок пустий - малюємо пробіли
                for _ in range(rows_num):
                    print(" ", end=" ")
            print()

        # 5. Збереження масиву у файл
        try:
            with open(output_file, "w", encoding="utf-8") as f:
                for row in lst:
                    if len(row) > 0:
                        for char in row:
                            f.write(char + " ")
                    else:
                        for _ in range(rows_num):
                            f.write(" " + " ")
                    f.write("\n")
            print(f"\nМасив збережено у файл '{output_file}'")
            
        except IOError as e:
            print(f"Помилка запису у файл: {e}")

    except KeyboardInterrupt:
        print("\nПрограму перервано користувачем.")
        sys.exit(0)

if __name__ == "__main__":
    main()