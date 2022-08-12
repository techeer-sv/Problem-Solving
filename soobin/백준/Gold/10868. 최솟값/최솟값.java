import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int pair = Integer.parseInt(st.nextToken());

        int height = 0;
        int length = num;
        while (length != 0) {
            length /= 2;
            height++;
        }

        int treeSize = (int) Math.pow(2, height + 1);
        int leftNodeStartIndex = treeSize / 2 - 1;

        // 트리 초기화하기
        tree = new long[treeSize + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.MAX_VALUE;
        }

        // 데이터 입력받기
        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + num; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1);

        for (int i = 0; i < pair; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            s = s + leftNodeStartIndex;
            e = e + leftNodeStartIndex;
            System.out.println(getMin(s, e));
        }
    }

    private static long getMin(int s, int e) {
        long min = Long.MAX_VALUE;
        while (s <= e) {
            if (s % 2 == 1) {
                min = Math.min(min, tree[s]);
                s++;
            }
            s = s / 2;
            if (e % 2 == 0) {
                min = Math.min(min, tree[e]);
                e--;
            }
            e = e / 2;
        }
        return min;
    }

    private static void setTree(int i) {
        while (i != 1) {
            if (tree[i / 2] > tree[i]) {
                tree[i / 2] = tree[i];
            }
            i--;
        }
    }
}
