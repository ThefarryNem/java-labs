import java.util.Objects;

// Основной класс приложения
public class Main {

    // Класс Техническое Задание
    static class ТЗ {
        private String описание;
        private int этажность;
        private double площадь;

        public ТЗ(String описание, int этажность, double площадь) {
            if (описание == null || описание.isEmpty()) {
                throw new IllegalArgumentException("Описание ТЗ не может быть пустым");
            }
            if (этажность <= 0) {
                throw new IllegalArgumentException("Этажность должна быть положительной");
            }
            if (площадь <= 0) {
                throw new IllegalArgumentException("Площадь должна быть положительной");
            }
            this.описание = описание;
            this.этажность = этажность;
            this.площадь = площадь;
        }

        public String getОписание() {
            return описание;
        }

        public int getЭтажность() {
            return этажность;
        }

        public double getПлощадь() {
            return площадь;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ТЗ тз = (ТЗ) obj;
            return этажность == тз.этажность &&
<<<<<<< HEAD
                    Double.compare(тз.площадь, площадь) == 0 &&
                    описание.equals(тз.описание);
=======
                   Double.compare(тз.площадь, площадь) == 0 &&
                   описание.equals(тз.описание);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public int hashCode() {
            return Objects.hash(описание, этажность, площадь);
        }

        @Override
        public String toString() {
            return "ТЗ{описание='" + описание + "', этажность=" + этажность + ", площадь=" + площадь + " м?}";
        }
    }

    // Класс Бригада
    static class Бригада {
        private String название;
        private int количествоКонструкторов;

        public Бригада(String название, int количествоКонструкторов) {
            if (название == null || название.isEmpty()) {
                throw new IllegalArgumentException("Название бригады не может быть пустым");
            }
            if (количествоКонструкторов <= 0) {
                throw new IllegalArgumentException("Количество конструкторов должно быть положительным");
            }
            this.название = название;
            this.количествоКонструкторов = количествоКонструкторов;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Бригада бригада = (Бригада) obj;
            return количествоКонструкторов == бригада.количествоКонструкторов &&
                    название.equals(бригада.название);
        }

        @Override
        public int hashCode() {
            return Objects.hash(название, количествоКонструкторов);
        }

        @Override
        public String toString() {
            return "Бригада{название='" + название + "', количествоКонструкторов=" + количествоКонструкторов + "}";
        }
    }

    // Класс Конструкторское Бюро
    static class КонструкторскоеБюро {
        private String названиеБюро;

        public КонструкторскоеБюро(String названиеБюро) {
            if (названиеБюро == null || названиеБюро.isEmpty()) {
                throw new IllegalArgumentException("Название бюро не может быть пустым");
            }
            this.названиеБюро = названиеБюро;
        }

        // Метод регистрирует ТЗ
        public void зарегистрироватьТЗ(ТЗ тз) {
            System.out.println("ТЗ зарегистрировано: " + тз);
        }

        // Метод определяет стоимость проектирования (условная формула)
        public double рассчитатьСтоимостьПроектирования(ТЗ тз) {
            double стоимость = тз.getПлощадь() * 50 + тз.getЭтажность() * 1000;
            System.out.println("Стоимость проектирования: " + стоимость + " у.е.");
            return стоимость;
        }

        // Метод создаёт бригаду конструкторов
        public Бригада создатьБригаду(String название, int количество) {
            Бригада бригада = new Бригада(название, количество);
            System.out.println("Создана бригада: " + бригада);
            return бригада;
        }

        // Метод выставляет счёт заказчику
        public void выставитьСчет(double сумма) {
            System.out.println("Счёт выставлен заказчику: " + сумма + " у.е.");
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            КонструкторскоеБюро бюро = (КонструкторскоеБюро) obj;
            return названиеБюро.equals(бюро.названиеБюро);
        }

        @Override
        public int hashCode() {
            return Objects.hash(названиеБюро);
        }

        @Override
        public String toString() {
            return "КонструкторскоеБюро{названиеБюро='" + названиеБюро + "'}";
        }
    }

    // Основной метод
    public static void main(String[] args) {
        try {
            КонструкторскоеБюро бюро = new КонструкторскоеБюро("СтройПроект");

            ТЗ тз = new ТЗ("Многоэтажный жилой дом", 10, 1500.0);
            бюро.зарегистрироватьТЗ(тз);

            double стоимость = бюро.рассчитатьСтоимостьПроектирования(тз);
            бюро.выставитьСчет(стоимость);

            Бригада бригада = бюро.создатьБригаду("ГлавнаяБригада", 5);

            // Проверка equals и hashCode
            Бригада бригада2 = new Бригада("ГлавнаяБригада", 5);
            System.out.println("Бригады равны? " + бригада.equals(бригада2));
            System.out.println("HashCode первой бригады: " + бригада.hashCode());
            System.out.println("HashCode второй бригады: " + бригада2.hashCode());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
