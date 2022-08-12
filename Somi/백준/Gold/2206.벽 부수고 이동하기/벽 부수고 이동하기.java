import java.io.*;
import java.util.*;

public class Main {
    
    private static int N, M;
    private static int[][] map;
    private static int[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    static class Point{
        int x, y, distance;
        int drill;

        public Point(int x, int y, int distance, int drill) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.drill = drill;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        int ans = bfs(0, 0);
        bw.write(ans + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1, 0));
        visited[y][x] = 0;

        while(!q.isEmpty()) {
            Point point = q.poll();
            if(point.x == M-1 && point.y == N-1)
                return point.distance;

            for(int i=0; i<4; i++){
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if(nx >= 0 && nx<M && ny>=0 && ny<N) {
                    if(visited[ny][nx] > point.drill) {
                        if(map[ny][nx] == 0) {
                            q.add(new Point(nx, ny, point.distance + 1, point.drill));
                            visited[ny][nx] = point.drill;
                        } else{
                            if(point.drill == 0){
                                q.add(new Point(nx, ny, point.distance +1, point.drill + 1));
                                visited[ny][nx] = point.drill + 1;
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }
    
}
