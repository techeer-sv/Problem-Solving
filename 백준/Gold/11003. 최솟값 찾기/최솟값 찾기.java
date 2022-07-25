import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // i 는 뭔데 시이이이이발. 문제 개그지같이 내 시발
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> mydeque = new LinkedList<>();

        for (int i = 0; i < num; i++) {
            int now = Integer.parseInt(st.nextToken());
            // 새로운 값이 들어올 때마다 정렬하는 대신
            // 현재 수보다 큰 값을 덱에서 제거해 시간 복잡도를 줄인다.

            while (!mydeque.isEmpty() && mydeque.getLast().value > now) {
                mydeque.removeLast();
            }

            mydeque.addLast(new Node(now, i));

            if (mydeque.getFirst().index <= i - len) {
                mydeque.removeFirst();
            }

            bw.write(mydeque.getFirst().value + " ");
        }
        
        bw.flush();
        bw.close();
    }

    static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
