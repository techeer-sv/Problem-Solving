import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 사이클의 존재 여부 확인
public class Main {

    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean visited[];

    static boolean isEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            String[] s = br.readLine().split(" ");
            int node = Integer.parseInt(s[0]);
            int edge = Integer.parseInt(s[1]);
            A = new ArrayList[node + 1];
            visited = new boolean[node + 1];
            check = new int[node + 1];
            isEven = true;
            for (int i = 1; i <= node; i++) {
                A[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < edge; i++) {
                s = br.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                A[start].add(end);
                A[end].add(start);
            }

            for (int i = 1; i <= node; i++) {
                if (isEven)
                    DFS(i);
                else
                    break;
            }

            check[1] = 0;
            if (isEven)
                System.out.println("YES");
            else {
                System.out.println("NO");
            }
        }
    }

    public static void DFS(int node) {
        visited[node] = true;
        for (int i : A[node]) {
            if (!visited[i]) {
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            }

            // 이미 방문한 노드가 현재 내 노드와 같은 집합이면 이분 그래프가 아닌ㅁ
            else if (check[node] == check[i]) {
                isEven = false;
            }
        }
    }
}
