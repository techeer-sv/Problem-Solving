import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int node, veryStart, veryEnd, edge;

    static long[] earning;
    static long[] distance;

    static mEdge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        veryStart = Integer.parseInt(st.nextToken());
        veryEnd = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        edgeList = new mEdge[edge];
        earning = new long[node]; // 각 도시에서 벌 수 있는 돈
        distance = new long[node];
        Arrays.fill(distance, Long.MIN_VALUE);

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edgeList[i] = new mEdge(s, e, c);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < node; i++) {
            earning[i] = Long.parseLong(st.nextToken());
        }

        // 변형된 벨만 포드
        distance[veryStart] = earning[veryStart];

        for (int i = 0; i <= node + 100; i++) {
            for (int j = 0; j < edge; j++) {
                int start = edgeList[j].start;
                int end = edgeList[j].end;
                int cost = edgeList[j].cost;

                // 출발 노드가 방문하지 않은 노드 skip
                if (distance[start] == Long.MIN_VALUE) continue;

                // 출발 노드가 양수 사이클에 연결되 노드라면 동료 노드도 연결 노드로 업데이트
                else if (distance[start] == Long.MAX_VALUE)
                    distance[end] = Long.MAX_VALUE;

                // 더 많은 돈을 벌 수 있는 새로운 경로 발견됐을 때 새로운 경로값으로 업데이트
                else if (distance[end] < distance[start] + earning[end] - cost) {
                    distance[end] = distance[start] + earning[end] -cost;
                    if (i >= node -1) distance[end] = Long.MAX_VALUE;
                }
            }
        }

        // 도착 불가능
        if (distance[veryEnd] == Long.MIN_VALUE) System.out.println("gg");

        // 양수 사이클 연결돼 무한대 돈을 벌수 있을 때
        else if (distance[veryEnd] == Long.MAX_VALUE) System.out.println("Gee");

        // 이외
        else System.out.println(distance[veryEnd]);
    }
}

class mEdge {
    int start, end, cost;
    public mEdge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}