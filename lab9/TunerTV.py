# Модуль TunerTV.py
# Імпортуємо клас Television з файлу Television.py, щоб використати його як основу.
from Television import Television 

class TunerTV(Television):
    """
    Похідний клас: Телевізор з тюнером.
    Успадковує всі властивості Television (бренд, ціна, методи вмикання)
    і додає нові можливості (керування каналами).
    """
    
    def __init__(self, brand, screen_size, price, tuner_type):
        """
        Конструктор розширеного класу.
        Приймає параметри для звичайного телевізора + тип тюнера.
        """
        # ВАЖЛИВО: Викликаємо конструктор батьківського класу (Television).
        # super() дозволяє не переписувати код ініціалізації бренду та ціни.
        super().__init__(brand, screen_size, price)
        
        # Додаємо нові атрибути, специфічні для цього класу
        self.tuner_type = tuner_type  # Тип тюнера (наприклад, T2, Satellite)
        self.current_channel = 1      # За замовчуванням стоїть 1-й канал

    def set_channel(self, channel_number):
        """
        Новий метод, доступний тільки для TunerTV.
        Дозволяє перемикати канали, але тільки якщо ТБ увімкнено.
        """
        # Перевіряємо атрибут is_on, який ми успадкували від Television
        if self.is_on:
            self.current_channel = channel_number
            print(f"Переключено на канал №{self.current_channel} (Тюнер: {self.tuner_type})")
        else:
            print("Помилка: Спочатку увімкніть телевізор!")

    def get_full_info(self):
        """
        Розширена версія методу отримання інформації.
        """
        # Спочатку беремо базовий текст від батьківського класу
        base_info = self.get_info() 
        
        # Дописуємо до нього нову інформацію про тюнер і канал
        return f"{base_info}, Тюнер: {self.tuner_type}, Поточний канал: {self.current_channel}"