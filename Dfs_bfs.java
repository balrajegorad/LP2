import java.util.*;

public class Dfs_bfs {

    // Depth-First Search
    static void dfs(ArrayList<ArrayList<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stk = new Stack<>();

        stk.push(start);
        visited[start] = true;
        System.out.print("DFS Traversal: ");
        while (!stk.isEmpty()) {
            int node = stk.pop();
            System.out.print(node + " ");
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    stk.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        System.out.println();
    }

    // Breadth-First Search
    static void bfs(ArrayList<ArrayList<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;
        System.out.print("BFS Traversal: ");
        while (!que.isEmpty()) {
            int node = que.poll();
            System.out.print(node + " ");
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    que.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes and edges: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the edges: ");
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // If it's a directed graph, remove this line
        }

        System.out.print("Enter the starting node for DFS and BFS: ");
        int startNode = scanner.nextInt();

        dfs(graph, startNode);
        bfs(graph, startNode);

        scanner.close();
    }
}


//output
// Enter the number of nodes and edges: 5 6
// Enter the edges:
// 0 1
// 0 2
// 1 3
// 1 4
// 2 3
// 3 4
// Enter the starting node for DFS and BFS: 0
// DFS Traversal: 0 2 3 4 1
// BFS Traversal: 0 1 2 3 4

