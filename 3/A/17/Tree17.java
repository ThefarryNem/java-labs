import java.util.*;

class Tree17 {
    static class Node {
        int index;         // ������ �������
        List<Node> children;

        Node(int index) {
            this.index = index;
            this.children = new ArrayList<>();
        }
    }

    private Node root;

    // ����������� 1: ������ ������
    public Tree() {
        this.root = null;
    }

    // ����������� 2: ������ � ����� ������
    public Tree(int rootIndex) {
        this.root = new Node(rootIndex);
    }

    // ����������� 3: ������ �� ������� ��������� (������ ������� � ������ �����)
    public Tree(Map<Integer, List<Integer>> adjacency) {
        if (adjacency.isEmpty()) {
            root = null;
            return;
        }
        Map<Integer, Node> nodes = new HashMap<>();
        for (Integer key : adjacency.keySet()) {
            nodes.putIfAbsent(key, new Node(key));
            for (Integer child : adjacency.get(key)) {
                nodes.putIfAbsent(child, new Node(child));
            }
        }
        // �������, ��� ������ � ����������� ������
        int rootIndex = Collections.min(adjacency.keySet());
        root = nodes.get(rootIndex);

        // ������ �����
        for (Integer key : adjacency.keySet()) {
            Node parent = nodes.get(key);
            for (Integer child : adjacency.get(key)) {
                parent.children.add(nodes.get(child));
            }
        }
    }

    // ================= ������ ���������� =================

    public int countNodes() {
        return dfsCount(root);
    }

    private int dfsCount(Node node) {
        if (node == null) return 0;
        int count = 1;
        for (Node child : node.children) {
            count += dfsCount(child);
        }
        return count;
    }

    public int countOddIndices() {
        return dfsOdd(root);
    }

    private int dfsOdd(Node node) {
        if (node == null) return 0;
        int count = (node.index % 2 != 0) ? 1 : 0;
        for (Node child : node.children) {
            count += dfsOdd(child);
        }
        return count;
    }

    public int countEvenIndices() {
        return dfsEven(root);
    }

    private int dfsEven(Node node) {
        if (node == null) return 0;
        int count = (node.index % 2 == 0) ? 1 : 0;
        for (Node child : node.children) {
            count += dfsEven(child);
        }
        return count;
    }

    public int countGreaterThan(int threshold) {
        return dfsGreater(root, threshold);
    }

    private int dfsGreater(Node node, int threshold) {
        if (node == null) return 0;
        int count = (node.index > threshold) ? 1 : 0;
        for (Node child : node.children) {
            count += dfsGreater(child, threshold);
        }
        return count;
    }

    // ================= ����� ���������� ���������� =================

    public int distance(int a, int b) {
        // ���� �� ����� �� ������ �������
        List<Integer> pathA = new ArrayList<>();
        List<Integer> pathB = new ArrayList<>();

        if (!findPath(root, a, pathA) || !findPath(root, b, pathB)) {
            return -1; // ���� ���� �� ������ �� �������
        }

        // ���� ����� ������� (���������� ����� ������)
        int i = 0;
        while (i < pathA.size() && i < pathB.size() && pathA.get(i).equals(pathB.get(i))) {
            i++;
        }

        // ���������� = (����� ���� �� A - i) + (����� ���� �� B - i)
        return (pathA.size() - i) + (pathB.size() - i);
    }

    private boolean findPath(Node node, int target, List<Integer> path) {
        if (node == null) return false;
        path.add(node.index);
        if (node.index == target) return true;
        for (Node child : node.children) {
            if (findPath(child, target, path)) return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        // ������: ������ (1 -> {2,3}, 2 -> {4,5})
        Map<Integer, List<Integer>> adj = new HashMap<>();
        adj.put(1, Arrays.asList(2, 3));
        adj.put(2, Arrays.asList(4, 5));
        adj.put(3, Collections.emptyList());
        adj.put(4, Collections.emptyList());
        adj.put(5, Collections.emptyList());

        Tree tree = new Tree(adj);

        System.out.println("����� ����� ������: " + tree.countNodes());
        System.out.println("�������� �������: " + tree.countOddIndices());
        System.out.println("������ �������: " + tree.countEvenIndices());
        System.out.println("������� > 2: " + tree.countGreaterThan(2));
        System.out.println("���������� ����� 4 � 5: " + tree.distance(4, 5));
        System.out.println("���������� ����� 4 � 3: " + tree.distance(4, 3));
    }
}
