import java.util.*;

public class Main {
    static boolean visited[];
    static ArrayList<Integer>[] A;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int nodeCount = scan.nextInt();
        int edgeCount = scan.nextInt();
        int startNode = scan.nextInt();
        
        A = new ArrayList[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            A[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < edgeCount; i++) {
            int node1 = scan.nextInt();
            int node2 = scan.nextInt();
            A[node1].add(node2);
            A[node2].add(node1);
        }
        
        for (int i = 1; i <= nodeCount; i++) {
            Collections.sort(A[i]);
        }
        
        visited = new boolean[nodeCount + 1];
        DFS(startNode);
        System.out.println();
        
        visited = new boolean[nodeCount + 1];
        BFS(startNode);
        System.out.println();
        
    }
    
    public static void DFS(int Node) {
        System.out.print(Node + " ");
        visited[Node] = true;
        
        for (int i : A[Node]) {
            if(!visited[i]) {
                DFS(i);
            }
        }
    }
    
    private static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        visited[Node] = true;
        
        while (!queue.isEmpty()) {
            int now_Node = queue.poll();
            System.out.print(now_Node + " ");
            for (int i : A[now_Node]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}