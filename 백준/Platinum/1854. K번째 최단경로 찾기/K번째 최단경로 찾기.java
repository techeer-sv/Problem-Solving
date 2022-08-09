import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 100000000;
    public static void main(String[] args) throws IOException {
        int node, edge, k;
        int[][] w = new int[1001][1001];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer>[] distQueue = new PriorityQueue[node + 1];
        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };
        for (int i = 0; i < node + 1; i++) { // node + 1
            distQueue[i] = new PriorityQueue<Integer>(k, cp);
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            w[a][b] = c;
        }

        PriorityQueue<tNode> pq = new PriorityQueue<>();
        pq.add(new tNode(1, 0));
        distQueue[1].add(0);
        while (!pq.isEmpty()) {
            tNode u = pq.poll();
            for (int adjNode = 1; adjNode <= node; adjNode++) {
                if (w[u.node][adjNode] != 0) {
                  
                    if (distQueue[adjNode].size() < k) {
                        distQueue[adjNode].add(u.cost + w[u.node][adjNode]);
                        pq.add(new tNode(adjNode, u.cost + w[u.node][adjNode]));
                    }

                    else if (distQueue[adjNode].peek() > u.cost + w[u.node][adjNode]) {
                        distQueue[adjNode].poll();
                        distQueue[adjNode].add(u.cost + w[u.node][adjNode]);
                        pq.add(new tNode(adjNode, u.cost + w[u.node][adjNode]));
                    }
                }
            }
        }

        for (int i = 1; i <= node; i++) {
            if (distQueue[i].size() == k) {
                bw.write(distQueue[i].peek() + "\n");
            } else {
                bw.write(-1 + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class tNode implements Comparable<tNode> {
    int node;
    int cost;

    tNode(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(tNode o) {
        return this.cost < o.cost ? -1 : 1;
    }
}
