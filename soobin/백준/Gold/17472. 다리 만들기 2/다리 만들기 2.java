import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] parent;
    static int[][] map;
    static int row, col;
    static int sNum;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;
    static PriorityQueue<bEdge> q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sNum = 1;
        sumlist = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] != 0 && visited[i][j] != true) {
                    BFS(i, j);
                    sNum++;
                    sumlist.add(mlist);
                }
            }
        }
        q = new PriorityQueue<>();

        for (int i = 0; i < sumlist.size(); i++) {
            ArrayList<int[]> now = sumlist.get(i);
            for (int j = 0; j < now.size(); j++) {
                int r = now.get(j)[0];
                int c = now.get(j)[1];
                int now_S = map[r][c];
                for (int d = 0; d < 4; d++) {
                    int tempR = dr[d];
                    int tempC = dc[d];
                    int blength = 0;
                    while (r + tempR >= 0 && r + tempR < row
                        && c + tempC >= 0 && c + tempC < col) {
                        if (map[r + tempR][c + tempC] == now_S) break;
                        else if (map[r + tempR][c + tempC] != 0) {
                            if (blength > 1) {
                                q.add(new bEdge(now_S, map[r + tempR][c + tempC], blength));
                            }
                            break;
                        } else blength++;

                        if (tempR < 0) tempR--;
                        else if (tempR > 0) tempR++;
                        else if (tempC < 0) tempC--;
                        else if (tempC > 0) tempC++;
                    }
                }
            }
        }

        parent = new int[sNum];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int useEdge = 0;
        int result = 0;
        while (!q.isEmpty()) {
            bEdge now = q.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result = result + now.c;
                useEdge++;
            }
        }
        if (useEdge == sNum -2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        if (a == parent[a])
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    public static void BFS(int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        mlist = new ArrayList<>();
        int[] start = {i, j};
        q.add(start);
        mlist.add(start);
        visited[i][j] = true;
        map[i][j] = sNum;

        while (!q.isEmpty()) {
            int now[] = q.poll();
            int r = now[0];
            int c = now[1];
            for (int d = 0; d < 4; d++) {
                int tempR = dr[d];
                int tempC = dc[d];
                while (r + tempR >= 0
                    && r + tempR < row
                    && c + tempC >= 0
                    && c + tempC < col) {

                    if (visited[r + tempR][c + tempC] == false
                        && map[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, q);
                    } else break;

                    if (tempR < 0) tempR--;
                    else if (tempR > 0) tempR++;
                    else if (tempC < 0) tempC--;
                    else if (tempC > 0) tempC++;
                }
            }
        }
    }

    private static void addNode(int i , int j, Queue<int[]> q) {
        map[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = {i, j};
        mlist.add(temp);
        q.add(temp);
    }
}

class bEdge implements Comparable<bEdge> {
    int s, e, c;

    bEdge(int s, int e, int c) {
        this.s = s;
        this.e = e;
        this.c = c;
    }

    @Override
    public int compareTo(bEdge o) {
        return this.c - o.c;
    }
}