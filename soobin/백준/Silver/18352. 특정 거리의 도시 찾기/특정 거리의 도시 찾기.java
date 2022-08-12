import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int country, road, len, start;
    static ArrayList<Integer>[] A;
    static int[] visited;

    // 방향 있음
    public static void main(String[] args) {

        // 데이터 전처리
        Scanner sc = new Scanner(System.in);
        country = sc.nextInt();
        road = sc.nextInt();
        len = sc.nextInt();
        start = sc.nextInt();

        visited = new int[country+1];
        for (int i = 1; i <= country; i++) {
            visited[i] = -1;
        }

        A = new ArrayList[country + 1];
        for (int i = 1; i <= country; i++) {
            A[i] = new ArrayList<Integer>();
        }

        // DFS 느낌 온
        for (int i = 0; i < road; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
        }

        BFS(start);

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i <= country; i++) {
            if (visited[i] == len) {
                answer.add(i);
            }
        }

        if (answer.isEmpty()) {
            System.out.println("-1");
        }
        else {
            Collections.sort(answer);
            for (int temp : answer) {
                System.out.println(temp);
            }
        }
    }

    public static void BFS(int node) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(node);
        visited[node]++;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : A[now]) {
                if(visited[i] == -1) {
                    visited[i] = visited[now] + 1;
                    q.add(i);
                }
            }
        }
    }
}
