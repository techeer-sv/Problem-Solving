import java.util.Scanner;

public class Main {
    static int city;
    static int road;
    public static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        city = sc.nextInt();
        road = sc.nextInt();

        int[][] map = new int[city + 1][city + 1];

        for (int i = 1; i <= city; i++) {
            for (int j = 1; j<= city; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int[] route = new int[road + 1];
        for (int i = 1; i <= road; i++) {
            route[i] = sc.nextInt();
        }

        parent = new int[city + 1];
        for (int i = 1; i <= city; i++) {
            parent[i] = i;
        }

        for (int i =1; i <= city; i++) {
            for (int j = 1; j <= city; j++) {
                if (map[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        // 여행 계획 도시들이 1개의 대표 도시로 연결되어 있는지 확
        int index = find(route[1]);
        for ( int i =2; i < route.length; i++) {
            if (index != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
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
        else {
            return parent[a] = find(parent[a]);
        }
    }
}
