import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int node = Integer.parseInt(br.readLine()); // 도시
        int edge = Integer.parseInt(br.readLine()); // 도로

        // 출발 도시의 번호
        // 도착 도시 번호
        // 도로를 지나는 데 걸리는 시간

        ArrayList<ArrayList<dNode>> a = new ArrayList<>();
        ArrayList<ArrayList<dNode>> reverse = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i <= node; i++) {
            a.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        // 진입 차수 배열 : 자기 자신을 가리키는 에지의 개수
        int[] in = new int[node + 1];
        for (int i = 0; i < edge; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); 
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            a.get(start).add(new dNode(end, time));
            reverse.get(end).add(new dNode(start, time));
            in[end]++;
        }

        // 지도를 그리는 사람들이 출발하는 출발 도시와 도착 도시
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startDosi = Integer.parseInt(st.nextToken());
        int endDosi = Integer.parseInt(st.nextToken());

        // 위상 정렬 
        Queue<Integer> q = new LinkedList<>();
        q.offer(startDosi);
        int[] result = new int[node + 1];
        while (!q.isEmpty()) {
            int now = q.poll();
            for (dNode next : a.get(now)) {
                in[next.targetNode]--;
                result[next.targetNode] =
                    Math.max(result[next.targetNode],
                        result[now] + next.value);
                if (in[next.targetNode] == 0) {
                    q.offer(next.targetNode);
                }
            }
        }

        // 위상 정렬 reverse
        int count = 0;
        boolean visited[] = new boolean[node + 1];
        q = new LinkedList<>();
        q.offer(endDosi);
        visited[endDosi] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (dNode next : reverse.get(now)) {
                if (result[next.targetNode] + next.value == result[now]) {
                    count++;
                    if (visited[next.targetNode] == false) {
                        visited[next.targetNode] = true;
                        q.offer(next.targetNode);
                    }
                }
            }
        }
        System.out.println(result[endDosi]);
        System.out.println(count);
    }
}

class dNode {
    int targetNode;
    int value;

    dNode(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }
}