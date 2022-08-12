import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw =
        new BufferedWriter(new OutputStreamWriter(System.out));

    static int node, edge;
    static int distance[][];

    public static void main(String[] args) throws IOException {
        StringTokenizer st =
            new StringTokenizer(br.readLine());
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        distance = new int[node + 1][node + 1];

        for (int i = 1; i <= node; i++) {
            for (int j = 1; j <= node; j++) {
                if (i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = 10000001;
            }
        }

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            distance[s][e] = 1;
            distance[e][s] = 1;
        }

        // floyd warshall
        for (int k = 1; k <= node; k++) {
            for (int i = 1; i <= node; i++) {
                for (int j = 1; j <= node; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }

        int Min = Integer.MAX_VALUE;
        int Answer = -1;
        for (int i = 1; i <= node; i++) {
            int temp = 0;
            for (int j = 1; j <= node; j++) {
                temp = temp + distance[i][j];
            }
            if (Min > temp) {
                Min = temp;
                Answer = i;
            }
        }
        System.out.println(Answer);
    }
}
