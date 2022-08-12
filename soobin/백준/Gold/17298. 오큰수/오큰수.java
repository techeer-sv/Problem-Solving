import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        
        // 전처리 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[] A = new int[N];
        int[] answer = new int[N];

        String[] str = bf.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(str[i]);
        }
        
        // 로직
        Stack<Integer> s = new Stack<>();
        s.push(0); // 처음에는 스택이 항상 비어 있으므로 최초 값을 push해서 초기화해주어야 한다. 
        
        for (int i = 0; i < N; i++) {
            
            while (!s.isEmpty() && A[s.peek()] < A[i]) {
                answer[s.pop()] = A[i];
            }
            s.push(i);
        }
        
        while (!s.empty()) {
            answer[s.pop()] = -1;
        }
        
        // 출력

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(answer[i] + " ");
        }

        bw.write("\n");
        bw.flush();
    }
}
