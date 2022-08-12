import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer =
            new StringTokenizer(bufferedReader.readLine());

        int numN = Integer.parseInt(stringTokenizer.nextToken());
        int quizN = Integer.parseInt(stringTokenizer.nextToken());

        int[] arr = new int[numN + 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= numN; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
        }

        for (int q = 0; q < quizN; q++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());

            System.out.println(arr[j] - arr[i-1]);
        }
    }
}