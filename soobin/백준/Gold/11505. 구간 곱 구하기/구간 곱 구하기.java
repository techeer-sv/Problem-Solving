import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;
    static int MOD;

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
        MOD = 1000000007;
        tree = new long[treeSize + 1];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = 1;
        }

        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + num; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1);
        for (int i = 0; i < change + cal; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());
            if (a == 1) {
                changeVal(leftNodeStartIndex + s, e);
            } else if (a == 2) {
                s = s + leftNodeStartIndex;
                e = e + leftNodeStartIndex;
                System.out.println(getMul(s, (int) e));
            } else {
                return;
            }
        }

        br.close();
    }

    private static long getMul(int s, int e) {
        long partMul = 1;
        while (s <= e) {
            if (s % 2 == 1) {
                partMul = partMul * tree[s] % MOD;
                s++;
            }
            if (e % 2 == 0) {
                partMul = partMul * tree[e] % MOD;
                e--;
            }
            s = s / 2;
            e = e / 2;
        }
        return partMul;
    }

    private static void changeVal(int index, long val) {
        tree[index] = val;
        while (index > 1) {
            index = index / 2;
            tree[index] = tree[index * 2] % MOD * tree[index * 2 + 1] % MOD;
        }
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] = tree[i / 2]*tree[i]%MOD;
            i--;
        }
    }
}
