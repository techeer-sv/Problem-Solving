import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int node, edge;

    static long[] distance;
    static cEdge edges[];

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        distance = new long[node + 1];
        edges = new cEdge[edge + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[i] = new cEdge(start, end, time);
        }

        // bellman-ford-moore
        distance[1] = 0;
        for (int i = 1; i < node; i++) { // node보다 1개 적은 수만큼 반
            for (int j = 0; j < edge; j++) {
                cEdge now = edges[j]; //here
                if (distance[now.start] != Integer.MAX_VALUE
                        && distance[now.end] > distance[now.start] + now.time) {
                    distance[now.end] = distance[now.start] + now.time;
                }
            }
        }

        boolean mCycle = false;
        for (int i = 0; i < edge; i++) {
            cEdge now = edges[i];
            if (distance[now.start] != Integer.MAX_VALUE
                    && distance[now.end] > distance[now.start] + now.time) {
                mCycle = true;
            }
        }

        if (!mCycle) {
            for (int i = 2; i <= node; i++) {
                if (distance[i] == Integer.MAX_VALUE)
                    System.out.println("-1");
                else
                    System.out.println(distance[i]);
            }
        } else {
            System.out.println("-1");
        }
    }
}

class cEdge {
    int start, end, time;
    public cEdge(int start, int end, int time) {
         this.start = start;
         this.end = end;
         this.time = time;
    }
}
