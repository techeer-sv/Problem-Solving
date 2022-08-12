import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int node;
    static int edge;
    static int start;

    static ArrayList<Integer>[] A;

    static boolean visited[];
    static Queue<Integer> Q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        node = sc.nextInt();
        edge = sc.nextInt();
        start = sc.nextInt();
        
        A = new ArrayList[node+1];
        Q = new LinkedList<>();

        // 각 노드 배열 초기화하기
        for (int i = 1; i <= node; i++) {
            A[i] = new ArrayList<Integer>();
        }

        // 노드 연결된 값 추가
        for (int i = 0; i < edge; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
            A[E].add(S);
        }

        // 인접 리스트 정렬하기
        for (int i = 1; i <= node; i++) {
            Collections.sort(A[i]);
        }

        // DFS
        // 재귀 함수
        visited = new boolean[node + 1];
        DFS(start);

        System.out.print("\n");

        // BFS
        visited = new boolean[node + 1];
        Q.add(start);
        visited[start] = true;
        BFS(start);


    }

    // stack 과 재귀함수
    public static void DFS(int i) {

        if (visited[i]) { // 방문 함
            return;
        }
        else {
            visited[i] = true;
            System.out.print(i + " ");
        }

        for (int k : A[i]) {
            DFS(k);
        }
    }

    public static void BFS(int i) {


        for (int k : A[i]) {
            if (!visited[k]) { // 방문 아직 안했으면 출력하기
//                System.out.print(k + " ");
                Q.add(k);
                visited[k] = true;
            }
        }
        if (!Q.isEmpty()) {
            int now = Q.poll();
            System.out.print(now + " ");
            BFS(now);
        }
    }

}
