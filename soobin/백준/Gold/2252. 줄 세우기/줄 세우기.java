import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int node = sc.nextInt();
        int edge = sc.nextInt();

        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= node; i++) { 
            a.add(new ArrayList<>());
        }

        int[] in = new int[node + 1];
        for (int i = 0; i < edge; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            a.get(S).add(E);
            in[E]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= node; i++) {
            if (in[i] == 0) q.offer(i); 
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");
            for (int next : a.get(now)) {
                in[next]--;
                if (in[next] == 0) q.offer(next);
            }
        }
    }
}
