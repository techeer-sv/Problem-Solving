import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int node;
    static boolean[] visited;
    static ArrayList<Integer> tree[];
    static int answer[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        node = sc.nextInt();

        visited = new boolean[node + 1];
        tree = new ArrayList[node + 1];
        answer = new int[node + 1];

        // 트리 - 인접 리스트 초기화
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < node; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            tree[n1].add(n2);
            tree[n2].add(n1);
        }
        DFS(1);
        for (int i = 2; i <= node; i++) {
            System.out.println(answer[i]);
        }
    }

    static void DFS(int number) {
        visited[number] = true;
        for (int i : tree[number]) {
            if (!visited[i]) {
                answer[i] = number;
                DFS(i);
            }
        }
    }
}
