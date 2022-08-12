import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int[] parent;
    static PriorityQueue<pEdge> pq;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int node = sc.nextInt();
        int edge = sc.nextInt();
        pq = new PriorityQueue<>();
        parent = new int[node + 1];

        for (int i = 0; i < node; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edge; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            pq.add(new pEdge(s, e, v));
        }

        int useEdge = 0;
        int result = 0;
        while (useEdge < node - 1) {
            pEdge now = pq.poll();
            if (find(now.start) != find(now.end)) {
                union(now.start, now.end);
                result = result + now.cost;
                useEdge++;
            }
        }
        System.out.println(result);
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
}

class pEdge implements Comparable<pEdge>{
    int start, end, cost;

    public pEdge (int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(pEdge o) {
        return this.cost - o.cost;
    }
}
