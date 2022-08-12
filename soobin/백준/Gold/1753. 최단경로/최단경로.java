import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int node, edge, start;

    public static int distance[];
    public static boolean visited[];

    public static ArrayList<Edge> list[];
    public static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine()); 

        distance = new int[node + 1];
        visited = new boolean[node + 1];

        list = new ArrayList[node + 1];
        for (int i = 1; i <= node; i++) {
            list[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i <= node; i++) {
            distance[i] = Integer.MAX_VALUE; // 정수의 최댓값 출력
        }

        // u : 에서
        // v  : 로 가는
        // w : 가중치
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
        }

        pq.add(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int CtoV = current.vertex;
            if (visited[CtoV]) continue;
            visited[CtoV] = true;

            for (int i = 0; i < list[CtoV].size(); i++) {
                Edge temp = list[CtoV].get(i);
                int next = temp.vertex;
                int value = temp.value;

                if (distance[next] > distance[CtoV] + value) {
                    distance[next] = value + distance[CtoV];
                    pq.add(new Edge(next, distance[next]));
                }
            }

        }
        for (int i = 1; i <= node; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println("INF");
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int vertex, value;

    Edge(int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }

    public int compareTo(Edge e) {
        if (this.value > e.value) return 1;
        else return -1;
    }
}
