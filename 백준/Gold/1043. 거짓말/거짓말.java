import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int[] parent;
    public static int[] avoidInfo;
    public static ArrayList<Integer>[] partyInfo;
    public static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int peopleTotal = sc.nextInt();
        int partyNum = sc.nextInt();
        int avoidNum = sc.nextInt();
        result = 0;

        avoidInfo = new int[avoidNum];
        for (int i = 0; i < avoidNum; i++) {
            avoidInfo[i] = sc.nextInt();
        }

        partyInfo = new ArrayList[partyNum];
        for (int i = 0; i < partyNum; i++) {
            partyInfo[i] = new ArrayList<Integer>();
            int participate = sc.nextInt();
            for (int j = 0; j < participate; j++) {
                partyInfo[i].add(sc.nextInt());
            }
        }

        parent = new int[peopleTotal + 1];
        for (int i = 0; i <= peopleTotal; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < partyNum; i++) {
            int firstPeople = partyInfo[i].get(0);
            for (int j = 1; j < partyInfo[i].size(); j++) {
                union(firstPeople, partyInfo[i].get(j));
            }
        }

        for (int i = 0; i < partyNum; i++) {
            boolean isPossible = true;
            int cur = partyInfo[i].get(0);
            for (int j = 0; j < avoidInfo.length; j++) {
                if (find(cur) == find(avoidInfo[j])) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible)
                result++;
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

    // sss
    public static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }
}