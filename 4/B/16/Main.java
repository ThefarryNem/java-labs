import java.util.Objects;

<<<<<<< HEAD
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
=======
// Îñíîâíîé êëàññ
public class Main {

    // Êëàññ Àâòîìîáèëü
    static class Àâòîìîáèëü {
        private String ìàðêà;
        private String ìîäåëü;
        private String íîìåð;

        public Àâòîìîáèëü(String ìàðêà, String ìîäåëü, String íîìåð) {
            if (ìàðêà == null || ìàðêà.isEmpty()) throw new IllegalArgumentException("Ìàðêà íå ìîæåò áûòü ïóñòîé");
            if (ìîäåëü == null || ìîäåëü.isEmpty()) throw new IllegalArgumentException("Ìîäåëü íå ìîæåò áûòü ïóñòîé");
            if (íîìåð == null || íîìåð.isEmpty()) throw new IllegalArgumentException("Íîìåð íå ìîæåò áûòü ïóñòûì");
            this.ìàðêà = ìàðêà;
            this.ìîäåëü = ìîäåëü;
            this.íîìåð = íîìåð;
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
<<<<<<< HEAD
            if (!(o instanceof Автомобиль)) return false;
            Автомобиль that = (Автомобиль) o;
            return марка.equals(that.марка) && модель.equals(that.модель) && номер.equals(that.номер);
=======
            if (!(o instanceof Àâòîìîáèëü)) return false;
            Àâòîìîáèëü that = (Àâòîìîáèëü) o;
            return ìàðêà.equals(that.ìàðêà) && ìîäåëü.equals(that.ìîäåëü) && íîìåð.equals(that.íîìåð);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public int hashCode() {
<<<<<<< HEAD
            return Objects.hash(марка, модель, номер);
=======
            return Objects.hash(ìàðêà, ìîäåëü, íîìåð);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public String toString() {
<<<<<<< HEAD
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
=======
            return ìàðêà + " " + ìîäåëü + " (" + íîìåð + ")";
        }
    }

    // Êëàññ Êëèåíò
    static class Êëèåíò {
        private String ôèî;
        private String ïàñïîðò;

        public Êëèåíò(String ôèî, String ïàñïîðò) {
            if (ôèî == null || ôèî.isEmpty()) throw new IllegalArgumentException("ÔÈÎ íå ìîæåò áûòü ïóñòûì");
            if (ïàñïîðò == null || ïàñïîðò.isEmpty()) throw new IllegalArgumentException("Ïàñïîðòíûå äàííûå îáÿçàòåëüíû");
            this.ôèî = ôèî;
            this.ïàñïîðò = ïàñïîðò;
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
<<<<<<< HEAD
            if (!(o instanceof Клиент)) return false;
            Клиент клиент = (Клиент) o;
            return фио.equals(клиент.фио) && паспорт.equals(клиент.паспорт);
=======
            if (!(o instanceof Êëèåíò)) return false;
            Êëèåíò êëèåíò = (Êëèåíò) o;
            return ôèî.equals(êëèåíò.ôèî) && ïàñïîðò.equals(êëèåíò.ïàñïîðò);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public int hashCode() {
<<<<<<< HEAD
            return Objects.hash(фио, паспорт);
=======
            return Objects.hash(ôèî, ïàñïîðò);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public String toString() {
<<<<<<< HEAD
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
=======
            return ôèî + " (ïàñïîðò: " + ïàñïîðò + ")";
        }
    }

    // Êëàññ Çàêàç
    static class Çàêàç {
        private Êëèåíò êëèåíò;
        private Àâòîìîáèëü àâòîìîáèëü;
        private int ñðîêÄíåé;
        private double ñóììà;
        private String ñòàòóñ; // "ñîçäàí", "ïîäòâåðæäåí", "îòêëîíåí"
        private String ïîìåòêè; // íàïðèìåð, ïîâðåæäåíèÿ

        public Çàêàç(Êëèåíò êëèåíò, Àâòîìîáèëü àâòîìîáèëü, int ñðîêÄíåé) {
            if (ñðîêÄíåé <= 0) throw new IllegalArgumentException("Ñðîê àðåíäû äîëæåí áûòü ïîëîæèòåëüíûì");
            this.êëèåíò = êëèåíò;
            this.àâòîìîáèëü = àâòîìîáèëü;
            this.ñðîêÄíåé = ñðîêÄíåé;
            this.ñòàòóñ = "ñîçäàí";
            this.ñóììà = 0;
            this.ïîìåòêè = "";
        }

        // Âû÷èñëÿåì ñóììó àðåíäû
        public void ðàññ÷èòàòüÑóììó(double öåíàÇàÄåíü) {
            ñóììà = öåíàÇàÄåíü * ñðîêÄíåé;
        }

        public void ïîäòâåðäèòü() {
            ñòàòóñ = "ïîäòâåðæäåí";
            System.out.println("Çàêàç ïîäòâåðæäåí: " + this);
        }

        public void îòêëîíèòü(String ïðè÷èíà) {
            ñòàòóñ = "îòêëîíåí";
            ïîìåòêè = ïðè÷èíà;
            System.out.println("Çàêàç îòêëîí¸í: " + this);
        }

        public void äîáàâèòüÏîìåòêó(String ïîìåòêà) {
            if (!ïîìåòêè.isEmpty()) ïîìåòêè += "; ";
            ïîìåòêè += ïîìåòêà;
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
<<<<<<< HEAD
            if (!(o instanceof Заказ)) return false;
            Заказ заказ = (Заказ) o;
            return срокДней == заказ.срокДней &&
                    Double.compare(заказ.сумма, сумма) == 0 &&
                    Objects.equals(клиент, заказ.клиент) &&
                    Objects.equals(автомобиль, заказ.автомобиль) &&
                    Objects.equals(статус, заказ.статус) &&
                    Objects.equals(пометки, заказ.пометки);
=======
            if (!(o instanceof Çàêàç)) return false;
            Çàêàç çàêàç = (Çàêàç) o;
            return ñðîêÄíåé == çàêàç.ñðîêÄíåé &&
                   Double.compare(çàêàç.ñóììà, ñóììà) == 0 &&
                   Objects.equals(êëèåíò, çàêàç.êëèåíò) &&
                   Objects.equals(àâòîìîáèëü, çàêàç.àâòîìîáèëü) &&
                   Objects.equals(ñòàòóñ, çàêàç.ñòàòóñ) &&
                   Objects.equals(ïîìåòêè, çàêàç.ïîìåòêè);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public int hashCode() {
<<<<<<< HEAD
            return Objects.hash(клиент, автомобиль, срокДней, сумма, статус, пометки);
=======
            return Objects.hash(êëèåíò, àâòîìîáèëü, ñðîêÄíåé, ñóììà, ñòàòóñ, ïîìåòêè);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public String toString() {
<<<<<<< HEAD
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
=======
            return "Çàêàç{êëèåíò=" + êëèåíò + ", àâòîìîáèëü=" + àâòîìîáèëü +
                   ", ñðîê=" + ñðîêÄíåé + " äíåé, ñóììà=" + ñóììà +
                   ", ñòàòóñ='" + ñòàòóñ + "', ïîìåòêè='" + ïîìåòêè + "'}";
        }
    }

    // Êëàññ Àäìèíèñòðàòîð
    static class Àäìèíèñòðàòîð {
        private String èìÿ;

        public Àäìèíèñòðàòîð(String èìÿ) {
            if (èìÿ == null || èìÿ.isEmpty()) throw new IllegalArgumentException("Èìÿ àäìèíèñòðàòîðà íå ìîæåò áûòü ïóñòûì");
            this.èìÿ = èìÿ;
        }

        public void ïîäòâåðäèòüÇàêàç(Çàêàç çàêàç) {
            çàêàç.ïîäòâåðäèòü();
        }

        public void îòêëîíèòüÇàêàç(Çàêàç çàêàç, String ïðè÷èíà) {
            çàêàç.îòêëîíèòü(ïðè÷èíà);
        }

        public void çàôèêñèðîâàòüÏîâðåæäåíèå(Çàêàç çàêàç, String ïîâðåæäåíèå) {
            çàêàç.äîáàâèòüÏîìåòêó("Ïîâðåæäåíèå: " + ïîâðåæäåíèå);
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
        }

        @Override
        public String toString() {
<<<<<<< HEAD
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
=======
            return "Àäìèíèñòðàòîð{" + èìÿ + "}";
        }
    }

    // Îñíîâíîé ìåòîä
    public static void main(String[] args) {
        try {
            Àâòîìîáèëü àâòî1 = new Àâòîìîáèëü("Toyota", "Camry", "À123ÂÑ");
            Êëèåíò êëèåíò1 = new Êëèåíò("Èâàíîâ È.È.", "1234 567890");

            Çàêàç çàêàç1 = new Çàêàç(êëèåíò1, àâòî1, 5);
            çàêàç1.ðàññ÷èòàòüÑóììó(3000); // 3000 ó.å. çà äåíü

            Àäìèíèñòðàòîð àäìèí = new Àäìèíèñòðàòîð("Ïåòðîâ Ï.Ï.");

            // Ïîäòâåðæäàåì çàêàç
            àäìèí.ïîäòâåðäèòüÇàêàç(çàêàç1);

            // Äîáàâëÿåì ïîâðåæäåíèå
            àäìèí.çàôèêñèðîâàòüÏîâðåæäåíèå(çàêàç1, "öàðàïèíà íà äâåðè");

            System.out.println("Ôèíàëüíûé çàêàç: " + çàêàç1);

            // Ïðèìåð îòêëîí¸ííîãî çàêàçà
            Çàêàç çàêàç2 = new Çàêàç(new Êëèåíò("Ñèäîðîâ Ñ.Ñ.", "9876 543210"),
                                     new Àâòîìîáèëü("BMW", "X5", "B456CD"), 3);
            çàêàç2.ðàññ÷èòàòüÑóììó(5000);
            àäìèí.îòêëîíèòüÇàêàç(çàêàç2, "Íå õâàòàåò äîêóìåíòîâ");

        } catch (IllegalArgumentException e) {
            System.out.println("Îøèáêà: " + e.getMessage());
        }
    }
}
>>>>>>> 3a86768161b340c01f45c2473572f862776e0f50
