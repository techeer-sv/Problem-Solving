import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int node, edge; // country, bus

    static ArrayList<Node>[] list; // 인접 리스트로 그래프 표현하기
    static int[] dist; //최단거리 배열 distance
    static boolean[] visit; // 사용 노드인지 확인 visited

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        node = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());

        dist = new int[node + 1];
        visit = new boolean[node + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 거리 배열을 충분히 큰 수로 초기화

        list = new ArrayList[node + 1]; //ArrayList<>[]아님 주의
        for (int i = 0; i <= node; i++) { //또 채워줌 0
            list[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, price));
        }

        st = new StringTokenizer(br.readLine());
        int startCountry = Integer.parseInt(st.nextToken());
        int endCountry = Integer.parseInt(st.nextToken());

        bw.write(dijkstra(startCountry, endCountry) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node nowNode = pq.poll();
            int now = nowNode.targetNode;
            if (!visit[now]) {
                visit[now] = true;
                for (Node n : list[now]) {
                    if (!visit[n.targetNode] &&
                            dist[n.targetNode] > dist[now] + n.value) {
                        dist[n.targetNode] = dist[now] + n.value;
                        pq.add(new Node(n.targetNode, dist[n.targetNode]));
                    }
                }
            }
        }
        return dist[end];
    }
}

class Node implements Comparable<Node> {
    int targetNode;
    int value;

    Node(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return value - o.value;
    }
}
