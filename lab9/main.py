# Модуль main.py
# Імпортуємо обидва класи, щоб створити їх екземпляри
from Television import Television
from TunerTV import TunerTV

def main():
    """Головна функція програми."""
    
    # 1. Робота з базовим класом
    print("--- Демонстрація базового класу (Television) ---")
    
    # Створюємо об'єкт (екземпляр) класу Television
    simple_tv = Television("Samsung", 42, 15000)
    
    # Викликаємо методи об'єкта
    print(simple_tv.get_info()) # Перевіряємо початковий стан
    simple_tv.turn_on()         # Вмикаємо
    simple_tv.turn_off()        # Вимикаємо
    print("\n")

    # 2. Робота з похідним класом (Спадкування)
    print("--- Демонстрація похідного класу (TunerTV) ---")
    
    # Створюємо об'єкт класу-нащадка, передаючи додатковий параметр "Satellite"
    smart_tv = TunerTV("LG", 55, 25000, "Satellite")
    
    # Перевіряємо, що методи батька (turn_on) працюють і тут
    smart_tv.turn_on()
    
    # Перевіряємо унікальні методи нащадка
    smart_tv.set_channel(5)     # Перемикаємо на 5 канал
    smart_tv.set_channel(12)    # Перемикаємо на 12 канал
    
    # Виводимо повну інформацію
    print(smart_tv.get_full_info())
    
    smart_tv.turn_off()

# Перевірка: чи запущено цей файл як головну програму
if __name__ == "__main__":
    main()