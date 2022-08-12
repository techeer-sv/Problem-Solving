import java.util.Scanner;

public class Main {

    // 문자열
    // 검색 문자열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int word = sc.nextInt();
        int search = sc.nextInt();

        wNode root = new wNode();

        while (word > 0) {
            String text = sc.next();
            wNode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null) {
                    now.next[c - 'a'] = new wNode();
                }

                now = now.next[c - 'a'];

                // 마지막 chart이면
                if (i == text.length() - 1)
                    now.isEnd = true;
            }
            word--;
        }
        int count = 0;
        while (search > 0) {
            String text = sc.next();
            wNode now = root;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (now.next[c - 'a'] == null)
                    break;
                now = now.next[c - 'a'];
                if (i == text.length() - 1 && now.isEnd)
                    count++;
            }
            search--;
        }
        System.out.println(count);
    }
}

class wNode {

    wNode[] next = new wNode[26];
    boolean isEnd;
}