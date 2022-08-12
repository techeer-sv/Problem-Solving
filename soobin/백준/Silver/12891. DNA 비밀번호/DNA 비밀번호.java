import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int checkArr[] = new int[4];
    static int currArr[] = new int[4];
    static int check = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int result = 0;

        char A[] = new char[N];
        A = bf.readLine().toCharArray();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) check++;
        }

        for (int i = 0; i < L; i++) {
            Add(A[i]);
        }
        if (check == 4) result++;

        for (int i = L; i < N; i++) {
            int j = i - L; // 이게 맞아 ?
            Add(A[i]);
            Remove(A[j]);
            if (check == 4) result++;
        }

        System.out.println(result);
        bf.close();
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                currArr[0]++;
                if (currArr[0] == checkArr[0]) check++;
                break;
            case 'C':
                currArr[1]++;
                if (currArr[1] == checkArr[1]) check++;
                break;
            case 'G':
                currArr[2]++;
                if (currArr[2] == checkArr[2]) check++;
                break;
            case 'T':
                currArr[3]++;
                if (currArr[3] == checkArr[3]) check++;
                break;
            }
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (currArr[0] == checkArr[0]) check--;
                currArr[0]--;
                break;
            case 'C':
                if (currArr[1] == checkArr[1]) check--;
                currArr[1]--;
                break;
            case 'G':
                if (currArr[2] == checkArr[2]) check--;
                currArr[2]--;
                break;
            case 'T':
                if (currArr[3] == checkArr[3]) check--;
                currArr[3]--;
                break;
        }
    }
}
