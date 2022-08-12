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

    static int node;
    static int edge;

    static int distance[][];

    public static void main(String[] args) throws IOException {
        node = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());
        distance = new int[node + 1][node + 1];

        for (int i = 1; i <= node; i++) {
            for (int j = 1; j <= node; j++) {
                if (i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = 10000001; // 충분히 큰 수로 저장
            }
        }

        for (int i = 0; i < edge; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (distance[s][e] > c) distance[s][e] = c;
        }

        // floyd-warshall
        for (int k = 1; k <= node; k++) {
            for (int i = 1; i <= node; i++) {
                for (int j = 1; j <= node; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }

        for (int i = 1; i <= node; i++) {
            for (int j = 1; j <= node; j++) {
                if (distance[i][j] == 10000001) {
                    System.out.print("0 ");
                } else {
                    System.out.print(distance[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
