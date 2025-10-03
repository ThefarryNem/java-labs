import java.util.Objects;

// �������� ����� ����������
public class Main {

    // ����� ����������� �������
    static class �� {
        private String ��������;
        private int ���������;
        private double �������;

        public ��(String ��������, int ���������, double �������) {
            if (�������� == null || ��������.isEmpty()) {
                throw new IllegalArgumentException("�������� �� �� ����� ���� ������");
            }
            if (��������� <= 0) {
                throw new IllegalArgumentException("��������� ������ ���� �������������");
            }
            if (������� <= 0) {
                throw new IllegalArgumentException("������� ������ ���� �������������");
            }
            this.�������� = ��������;
            this.��������� = ���������;
            this.������� = �������;
        }

        public String get��������() {
            return ��������;
        }

        public int get���������() {
            return ���������;
        }

        public double get�������() {
            return �������;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            �� �� = (��) obj;
            return ��������� == ��.��������� &&
                   Double.compare(��.�������, �������) == 0 &&
                   ��������.equals(��.��������);
        }

        @Override
        public int hashCode() {
            return Objects.hash(��������, ���������, �������);
        }

        @Override
        public String toString() {
            return "��{��������='" + �������� + "', ���������=" + ��������� + ", �������=" + ������� + " �?}";
        }
    }

    // ����� �������
    static class ������� {
        private String ��������;
        private int �����������������������;

        public �������(String ��������, int �����������������������) {
            if (�������� == null || ��������.isEmpty()) {
                throw new IllegalArgumentException("�������� ������� �� ����� ���� ������");
            }
            if (����������������������� <= 0) {
                throw new IllegalArgumentException("���������� ������������� ������ ���� �������������");
            }
            this.�������� = ��������;
            this.����������������������� = �����������������������;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ������� ������� = (�������) obj;
            return ����������������������� == �������.����������������������� &&
                    ��������.equals(�������.��������);
        }

        @Override
        public int hashCode() {
            return Objects.hash(��������, �����������������������);
        }

        @Override
        public String toString() {
            return "�������{��������='" + �������� + "', �����������������������=" + ����������������������� + "}";
        }
    }

    // ����� ��������������� ����
    static class ������������������� {
        private String ������������;

        public �������������������(String ������������) {
            if (������������ == null || ������������.isEmpty()) {
                throw new IllegalArgumentException("�������� ���� �� ����� ���� ������");
            }
            this.������������ = ������������;
        }

        // ����� ������������ ��
        public void ������������������(�� ��) {
            System.out.println("�� ����������������: " + ��);
        }

        // ����� ���������� ��������� �������������� (�������� �������)
        public double ���������������������������������(�� ��) {
            double ��������� = ��.get�������() * 50 + ��.get���������() * 1000;
            System.out.println("��������� ��������������: " + ��������� + " �.�.");
            return ���������;
        }

        // ����� ������ ������� �������������
        public ������� ��������������(String ��������, int ����������) {
            ������� ������� = new �������(��������, ����������);
            System.out.println("������� �������: " + �������);
            return �������;
        }

        // ����� ���������� ���� ���������
        public void �������������(double �����) {
            System.out.println("���� ��������� ���������: " + ����� + " �.�.");
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ������������������� ���� = (�������������������) obj;
            return ������������.equals(����.������������);
        }

        @Override
        public int hashCode() {
            return Objects.hash(������������);
        }

        @Override
        public String toString() {
            return "�������������������{������������='" + ������������ + "'}";
        }
    }

    // �������� �����
    public static void main(String[] args) {
        try {
            ������������������� ���� = new �������������������("�����������");

            �� �� = new ��("������������ ����� ���", 10, 1500.0);
            ����.������������������(��);

            double ��������� = ����.���������������������������������(��);
            ����.�������������(���������);

            ������� ������� = ����.��������������("��������������", 5);

            // �������� equals � hashCode
            ������� �������2 = new �������("��������������", 5);
            System.out.println("������� �����? " + �������.equals(�������2));
            System.out.println("HashCode ������ �������: " + �������.hashCode());
            System.out.println("HashCode ������ �������: " + �������2.hashCode());

        } catch (IllegalArgumentException e) {
            System.out.println("������: " + e.getMessage());
        }
    }
}
