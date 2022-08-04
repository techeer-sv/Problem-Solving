import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    // 반복 : 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 붇는다
        // 쏟아 붇는 조건 : 한 물통이 비거나, 다른 한 물통이 가득 찰 떄까지
    // 조건 : 첫 번쨰 물통이 비었을 때,
    // 구해야 하는 값 : 세 번쨰 물통에 담겨있을 수 있는 모든 물의 양

    static int[] sender = {0, 0, 1, 1, 2, 2};
    static int[] receiver = {1, 2, 0, 2, 0, 1};
    static boolean visited[][];
    static boolean answer[];
    static int now[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        now = new int[3];
        now[0] = sc.nextInt();
        now[1] = sc.nextInt();
        now[2] = sc.nextInt();
        visited = new boolean[201][201];
        answer = new boolean[201];
        BFS();

        for (int i = 0; i < answer.length; i++) {
            if (answer[i]) {
                System.out.print(i + " ");
            }
        }

    }

    public static void BFS() {
        Queue<AB> q = new LinkedList<>();
        q.add(new AB(0, 0));
        visited[0][0] = true;
        answer[now[2]] = true;

        while (!q.isEmpty()) {
            AB p = q.poll();
            int A = p.A;
            int B = p.B;
            int C = now[2] - A - B;

            for (int k = 0; k < 6; k++) {
                // A -> B , A -> C, B -> A, B -> C, C -> A, C -> B
                int[] next = {A, B, C};
                next[receiver[k]] += next[sender[k]];
                next[sender[k]] = 0;
                if (next[receiver[k]] > now[receiver[k]]) {
                    // 초과하는 만큼 다시 이전 물통에 넣어 줌
                    next[sender[k]] = next[receiver[k]] - now[receiver[k]];
                    next[receiver[k]] = now[receiver[k]];
                }
                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    q.add(new AB(next[0], next[1]));
                    if (next[0] == 0) {
                        answer[next[2]] = true;
                    }
                }
            }
        }
    }
}

class AB {
    int A;
    int B;

    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}
