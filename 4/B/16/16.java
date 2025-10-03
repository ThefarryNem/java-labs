import java.util.Objects;

// Основной класс
public class Main {

    // Класс Автомобиль
    static class Автомобиль {
        private String марка;
        private String модель;
        private String номер;

        public Автомобиль(String марка, String модель, String номер) {
            if (марка == null || марка.isEmpty()) throw new IllegalArgumentException("Марка не может быть пустой");
            if (модель == null || модель.isEmpty()) throw new IllegalArgumentException("Модель не может быть пустой");
            if (номер == null || номер.isEmpty()) throw new IllegalArgumentException("Номер не может быть пустым");
            this.марка = марка;
            this.модель = модель;
            this.номер = номер;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Автомобиль)) return false;
            Автомобиль that = (Автомобиль) o;
            return марка.equals(that.марка) && модель.equals(that.модель) && номер.equals(that.номер);
        }

        @Override
        public int hashCode() {
            return Objects.hash(марка, модель, номер);
        }

        @Override
        public String toString() {
            return марка + " " + модель + " (" + номер + ")";
        }
    }

    // Класс Клиент
    static class Клиент {
        private String фио;
        private String паспорт;

        public Клиент(String фио, String паспорт) {
            if (фио == null || фио.isEmpty()) throw new IllegalArgumentException("ФИО не может быть пустым");
            if (паспорт == null || паспорт.isEmpty()) throw new IllegalArgumentException("Паспортные данные обязательны");
            this.фио = фио;
            this.паспорт = паспорт;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Клиент)) return false;
            Клиент клиент = (Клиент) o;
            return фио.equals(клиент.фио) && паспорт.equals(клиент.паспорт);
        }

        @Override
        public int hashCode() {
            return Objects.hash(фио, паспорт);
        }

        @Override
        public String toString() {
            return фио + " (паспорт: " + паспорт + ")";
        }
    }

    // Класс Заказ
    static class Заказ {
        private Клиент клиент;
        private Автомобиль автомобиль;
        private int срокДней;
        private double сумма;
        private String статус; // "создан", "подтвержден", "отклонен"
        private String пометки; // например, повреждения

        public Заказ(Клиент клиент, Автомобиль автомобиль, int срокДней) {
            if (срокДней <= 0) throw new IllegalArgumentException("Срок аренды должен быть положительным");
            this.клиент = клиент;
            this.автомобиль = автомобиль;
            this.срокДней = срокДней;
            this.статус = "создан";
            this.сумма = 0;
            this.пометки = "";
        }

        // Вычисляем сумму аренды
        public void рассчитатьСумму(double ценаЗаДень) {
            сумма = ценаЗаДень * срокДней;
        }

        public void подтвердить() {
            статус = "подтвержден";
            System.out.println("Заказ подтвержден: " + this);
        }

        public void отклонить(String причина) {
            статус = "отклонен";
            пометки = причина;
            System.out.println("Заказ отклонён: " + this);
        }

        public void добавитьПометку(String пометка) {
            if (!пометки.isEmpty()) пометки += "; ";
            пометки += пометка;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Заказ)) return false;
            Заказ заказ = (Заказ) o;
            return срокДней == заказ.срокДней &&
                    Double.compare(заказ.сумма, сумма) == 0 &&
                    Objects.equals(клиент, заказ.клиент) &&
                    Objects.equals(автомобиль, заказ.автомобиль) &&
                    Objects.equals(статус, заказ.статус) &&
                    Objects.equals(пометки, заказ.пометки);
        }

        @Override
        public int hashCode() {
            return Objects.hash(клиент, автомобиль, срокДней, сумма, статус, пометки);
        }

        @Override
        public String toString() {
            return "Заказ{клиент=" + клиент + ", автомобиль=" + автомобиль +
                    ", срок=" + срокДней + " дней, сумма=" + сумма +
                    ", статус='" + статус + "', пометки='" + пометки + "'}";
        }
    }

    // Класс Администратор
    static class Администратор {
        private String имя;

        public Администратор(String имя) {
            if (имя == null || имя.isEmpty()) throw new IllegalArgumentException("Имя администратора не может быть пустым");
            this.имя = имя;
        }

        public void подтвердитьЗаказ(Заказ заказ) {
            заказ.подтвердить();
        }

        public void отклонитьЗаказ(Заказ заказ, String причина) {
            заказ.отклонить(причина);
        }

        public void зафиксироватьПовреждение(Заказ заказ, String повреждение) {
            заказ.добавитьПометку("Повреждение: " + повреждение);
        }

        @Override
        public String toString() {
            return "Администратор{" + имя + "}";
        }
    }

    // Основной метод
    public static void main(String[] args) {
        try {
            Автомобиль авто1 = new Автомобиль("Toyota", "Camry", "А123ВС");
            Клиент клиент1 = new Клиент("Иванов И.И.", "1234 567890");

            Заказ заказ1 = new Заказ(клиент1, авто1, 5);
            заказ1.рассчитатьСумму(3000); // 3000 у.е. за день

            Администратор админ = new Администратор("Петров П.П.");

            // Подтверждаем заказ
            админ.подтвердитьЗаказ(заказ1);

            // Добавляем повреждение
            админ.зафиксироватьПовреждение(заказ1, "царапина на двери");

            System.out.println("Финальный заказ: " + заказ1);

            // Пример отклонённого заказа
            Заказ заказ2 = new Заказ(new Клиент("Сидоров С.С.", "9876 543210"),
                    new Автомобиль("BMW", "X5", "B456CD"), 3);
            заказ2.рассчитатьСумму(5000);
            админ.отклонитьЗаказ(заказ2, "Не хватает документов");

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
