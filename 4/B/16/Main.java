import java.util.Objects;

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
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Àâòîìîáèëü)) return false;
            Àâòîìîáèëü that = (Àâòîìîáèëü) o;
            return ìàðêà.equals(that.ìàðêà) && ìîäåëü.equals(that.ìîäåëü) && íîìåð.equals(that.íîìåð);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ìàðêà, ìîäåëü, íîìåð);
        }

        @Override
        public String toString() {
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
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Êëèåíò)) return false;
            Êëèåíò êëèåíò = (Êëèåíò) o;
            return ôèî.equals(êëèåíò.ôèî) && ïàñïîðò.equals(êëèåíò.ïàñïîðò);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ôèî, ïàñïîðò);
        }

        @Override
        public String toString() {
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
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Çàêàç)) return false;
            Çàêàç çàêàç = (Çàêàç) o;
            return ñðîêÄíåé == çàêàç.ñðîêÄíåé &&
                   Double.compare(çàêàç.ñóììà, ñóììà) == 0 &&
                   Objects.equals(êëèåíò, çàêàç.êëèåíò) &&
                   Objects.equals(àâòîìîáèëü, çàêàç.àâòîìîáèëü) &&
                   Objects.equals(ñòàòóñ, çàêàç.ñòàòóñ) &&
                   Objects.equals(ïîìåòêè, çàêàç.ïîìåòêè);
        }

        @Override
        public int hashCode() {
            return Objects.hash(êëèåíò, àâòîìîáèëü, ñðîêÄíåé, ñóììà, ñòàòóñ, ïîìåòêè);
        }

        @Override
        public String toString() {
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
        }

        @Override
        public String toString() {
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