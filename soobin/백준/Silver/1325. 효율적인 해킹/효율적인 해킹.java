import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int node, edge;
    static boolean visited[];
    static int answer[];
    static ArrayList<Integer> A[]; // 얘네가 그 ppp

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(
            new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        A = new ArrayList[node + 1];
        answer = new int[node + 1];

        for (int i = 1; i <= node; i++) // ppp 그
            A[i] = new ArrayList<>();

        for (int i = 0; i < edge; i++) { // 신뢰 관계이기 때문에 방향 중요
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E);
        }

        for (int i = 1; i <= node; i++) {
            visited = new boolean[node + 1];
            BFS(i);
        }

        int max = 0;
        for (int i = 1; i <= node; i++) {
            max = Math.max(max, answer[i]);
        }
        for (int i = 1; i <= node; i++) {
            if (answer[i] == max) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS(int index) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(index);
        visited[index] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : A[now]) {
                if (visited[i] == false) {
                    visited[i] = true;
                    answer[i]++;
                    q.add(i);
                }
            }
        }
    }
}
