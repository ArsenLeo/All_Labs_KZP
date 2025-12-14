package KI305.Levkulych.Lab6;

/**
 * Клас, що представляє DVD диск.
 * Також реалізує Comparable для порівняння дисків за тривалістю.
 */
class DVD implements Comparable<DVD> {
    private String title; // Назва фільму/диску
    private int duration; // Тривалість у хвилинах
    private int year; // Рік випуску

    /**
     * Конструктор класу DVD
     *
     * @param title    назва диску
     * @param duration тривалість у хвилинах
     * @param year     рік випуску
     */
    public DVD(String title, int duration, int year) {
        this.title = title;
        this.duration = duration;
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Повертає рядковий опис об'єкта DVD.
     */
    @Override
    public String toString() {
        return "DVD{title='" + title + "', duration=" + duration + ", year=" + year + "}";
    }

    /**
     * Порівнює поточний DVD з іншим на основі тривалості.
     *
     * @param other інший DVD
     * @return результат порівняння тривалості
     */
    @Override
    public int compareTo(DVD other) {
        return Integer.compare(this.duration, other.duration);
    }
}