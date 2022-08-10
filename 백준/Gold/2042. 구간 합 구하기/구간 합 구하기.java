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
        int change = Integer.parseInt(st.nextToken());
        int cal = Integer.parseInt(st.nextToken());

        int height = 0;
        int length = num;

        while (length != 0) {
            length /= 2;
            height++;
        }

        int treeSize = (int) Math.pow(2, height + 1);
        int leftNodeStartIndex = treeSize / 2 - 1;
        tree = new long[treeSize + 1];

        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + num; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1);

        for (int i = 0; i < change + cal; i++) {
            st = new StringTokenizer(br.readLine());
            long type = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            if (type == 1) { // 변경
                changeVal(leftNodeStartIndex + s, e);
            } else if (type == 2) {
                s = s + leftNodeStartIndex;
                e = e + leftNodeStartIndex;
                System.out.println(getSum(s, (int) e));
            } else {
                return;
            }
        }

        br.close();
    }

    private static long getSum(int s, int e) {
        long partSum = 0;
        while (s <= e) {
            if (s % 2 == 1) {
                partSum = partSum + tree[s];
                s++;
            }
            if (e % 2 == 0) {
                partSum = partSum + tree[e];
                e--;
            }

            s = s / 2;
            e = e / 2;
        }
        return partSum;
    }

    private static void changeVal(int index, long val){
        long diff = val - tree[index];
        while (index > 0) {
            tree[index] = tree[index] + diff;
            index = index / 2;
        }
    }

    private static void setTree(int i) {
        while ( i != 1) {
            tree[i / 2] += tree[i];
            i--;
        }
    }
}
