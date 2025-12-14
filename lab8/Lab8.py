import math
import struct
import sys
import os

def calculate_expression(x):
    """
    Обчислює вираз y = cos(x) / tg(2x) (Варіант 14).
    :param x: вхідне значення
    :return: результат обчислення
    :raises ValueError: якщо tg(2x) == 0 (ділення на нуль)
    """
    tan_val = math.tan(2 * x)
    
    if tan_val == 0:
        raise ValueError("Значення tan(2x) дорівнює 0. Ділення на нуль неможливе.")
    
    return math.cos(x) / tan_val

def write_res_txt(f_name, result):
    """
    Записує результат у текстовий файл.
    """
    try:
        with open(f_name, 'w', encoding='utf-8') as f:
            f.write(str(result))
    except IOError as e:
        print(f"Помилка запису у текстовий файл: {e}")
        sys.exit(1)

def read_res_txt(f_name):
    """
    Зчитує результат з текстового файлу.
    """
    try:
        if os.path.exists(f_name):
            with open(f_name, 'r', encoding='utf-8') as f:
                result = float(f.read())
        else:
            raise FileNotFoundError(f"Файл {f_name} не знайдено.")
    except (FileNotFoundError, ValueError) as e:
        print(f"Помилка читання з текстового файлу: {e}")
        sys.exit(1)
    return result

def write_res_bin(f_name, result):
    """
    Записує результат у бінарний файл використовуючи модуль struct.
    """
    try:
        with open(f_name, 'wb') as f:
            # 'd' означає double (8 байт).
            packed_data = struct.pack('d', result)
            f.write(packed_data)
    except IOError as e:
        print(f"Помилка запису у бінарний файл: {e}")
        sys.exit(1)

def read_res_bin(f_name):
    """
    Зчитує результат з бінарного файлу використовуючи модуль struct.
    """
    try:
        if os.path.exists(f_name):
            with open(f_name, 'rb') as f:
                # Читаємо 8 байт, бо формат 'd' (double) займає 8 байт
                packed_data = f.read(struct.calcsize('d'))
                result = struct.unpack('d', packed_data)[0]
        else:
            raise FileNotFoundError(f"Файл {f_name} не знайдено.")
    except (FileNotFoundError, struct.error) as e:
        print(f"Помилка читання з бінарного файлу: {e}")
        sys.exit(1)
    return result

def main():
    """
    Головна функція програми.
    """
    txt_file = "result.txt"
    bin_file = "result.bin"

    try:
        print("Варіант 14: y = cos(x) / tg(2x)")
        x_input = input("Введіть значення x: ")
        
        try:
            x = float(x_input)
        except ValueError:
            print("Помилка: Введено не число.")
            sys.exit(1)

        # 1. Обчислення
        result = calculate_expression(x)
        print(f"Обчислений результат: {result}")

        # 2. Запис у файли
        write_res_txt(txt_file, result)
        print(f"Результат записано у текстовий файл '{txt_file}'.")

        write_res_bin(bin_file, result)
        print(f"Результат записано у бінарний файл '{bin_file}'.")

        # 3. Читання з файлів для перевірки
        res_from_txt = read_res_txt(txt_file)
        print(f"Зчитано з текстового файлу: {res_from_txt}")

        res_from_bin = read_res_bin(bin_file)
        print(f"Зчитано з бінарного файлу: {res_from_bin}")

    except ValueError as e:
        print(f"Математична помилка: {e}")
    except Exception as e:
        print(f"Непередбачена помилка: {e}")

if __name__ == "__main__":
    main()