import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int buildingNum = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= buildingNum; i++) {
            a.add(new ArrayList<>());
        }

        int[] in = new int[buildingNum + 1];
        int[] time = new int[buildingNum + 1];
        for (int i = 1; i <= buildingNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int preTemp = Integer.parseInt(st.nextToken());
                if (preTemp == -1)
                    break;
                a.get(preTemp).add(i);
                in[i]++;
            }
        }

        // 위상 정렬
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= buildingNum; i++) {
            if (in[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[buildingNum + 1];
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : a.get(now)) {
                in[next]--;
                result[next] = Math.max(result[next], result[now] + time[now]);
                if (in[next] == 0){
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= buildingNum; i++) {
            System.out.println(result[i] + time[i]);
        }
    }
}
