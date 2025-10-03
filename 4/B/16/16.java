import java.util.Objects;

// �������� �����
public class Main {

    // ����� ����������
    static class ���������� {
        private String �����;
        private String ������;
        private String �����;

        public ����������(String �����, String ������, String �����) {
            if (����� == null || �����.isEmpty()) throw new IllegalArgumentException("����� �� ����� ���� ������");
            if (������ == null || ������.isEmpty()) throw new IllegalArgumentException("������ �� ����� ���� ������");
            if (����� == null || �����.isEmpty()) throw new IllegalArgumentException("����� �� ����� ���� ������");
            this.����� = �����;
            this.������ = ������;
            this.����� = �����;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ����������)) return false;
            ���������� that = (����������) o;
            return �����.equals(that.�����) && ������.equals(that.������) && �����.equals(that.�����);
        }

        @Override
        public int hashCode() {
            return Objects.hash(�����, ������, �����);
        }

        @Override
        public String toString() {
            return ����� + " " + ������ + " (" + ����� + ")";
        }
    }

    // ����� ������
    static class ������ {
        private String ���;
        private String �������;

        public ������(String ���, String �������) {
            if (��� == null || ���.isEmpty()) throw new IllegalArgumentException("��� �� ����� ���� ������");
            if (������� == null || �������.isEmpty()) throw new IllegalArgumentException("���������� ������ �����������");
            this.��� = ���;
            this.������� = �������;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ������)) return false;
            ������ ������ = (������) o;
            return ���.equals(������.���) && �������.equals(������.�������);
        }

        @Override
        public int hashCode() {
            return Objects.hash(���, �������);
        }

        @Override
        public String toString() {
            return ��� + " (�������: " + ������� + ")";
        }
    }

    // ����� �����
    static class ����� {
        private ������ ������;
        private ���������� ����������;
        private int ��������;
        private double �����;
        private String ������; // "������", "�����������", "��������"
        private String �������; // ��������, �����������

        public �����(������ ������, ���������� ����������, int ��������) {
            if (�������� <= 0) throw new IllegalArgumentException("���� ������ ������ ���� �������������");
            this.������ = ������;
            this.���������� = ����������;
            this.�������� = ��������;
            this.������ = "������";
            this.����� = 0;
            this.������� = "";
        }

        // ��������� ����� ������
        public void ���������������(double ����������) {
            ����� = ���������� * ��������;
        }

        public void �����������() {
            ������ = "�����������";
            System.out.println("����� �����������: " + this);
        }

        public void ���������(String �������) {
            ������ = "��������";
            ������� = �������;
            System.out.println("����� �������: " + this);
        }

        public void ���������������(String �������) {
            if (!�������.isEmpty()) ������� += "; ";
            ������� += �������;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof �����)) return false;
            ����� ����� = (�����) o;
            return �������� == �����.�������� &&
                   Double.compare(�����.�����, �����) == 0 &&
                   Objects.equals(������, �����.������) &&
                   Objects.equals(����������, �����.����������) &&
                   Objects.equals(������, �����.������) &&
                   Objects.equals(�������, �����.�������);
        }

        @Override
        public int hashCode() {
            return Objects.hash(������, ����������, ��������, �����, ������, �������);
        }

        @Override
        public String toString() {
            return "�����{������=" + ������ + ", ����������=" + ���������� +
                   ", ����=" + �������� + " ����, �����=" + ����� +
                   ", ������='" + ������ + "', �������='" + ������� + "'}";
        }
    }

    // ����� �������������
    static class ������������� {
        private String ���;

        public �������������(String ���) {
            if (��� == null || ���.isEmpty()) throw new IllegalArgumentException("��� �������������� �� ����� ���� ������");
            this.��� = ���;
        }

        public void ����������������(����� �����) {
            �����.�����������();
        }

        public void ��������������(����� �����, String �������) {
            �����.���������(�������);
        }

        public void ������������������������(����� �����, String �����������) {
            �����.���������������("�����������: " + �����������);
        }

        @Override
        public String toString() {
            return "�������������{" + ��� + "}";
        }
    }

    // �������� �����
    public static void main(String[] args) {
        try {
            ���������� ����1 = new ����������("Toyota", "Camry", "�123��");
            ������ ������1 = new ������("������ �.�.", "1234 567890");

            ����� �����1 = new �����(������1, ����1, 5);
            �����1.���������������(3000); // 3000 �.�. �� ����

            ������������� ����� = new �������������("������ �.�.");

            // ������������ �����
            �����.����������������(�����1);

            // ��������� �����������
            �����.������������������������(�����1, "�������� �� �����");

            System.out.println("��������� �����: " + �����1);

            // ������ ����������� ������
            ����� �����2 = new �����(new ������("������� �.�.", "9876 543210"),
                                     new ����������("BMW", "X5", "B456CD"), 3);
            �����2.���������������(5000);
            �����.��������������(�����2, "�� ������� ����������");

        } catch (IllegalArgumentException e) {
            System.out.println("������: " + e.getMessage());
        }
    }
}
