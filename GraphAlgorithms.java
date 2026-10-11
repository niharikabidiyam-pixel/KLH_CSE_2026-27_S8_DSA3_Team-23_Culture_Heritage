
import java.util.*;

public class GraphAlgorithms {
    static int V;
    static ArrayList<ArrayList<Integer>> graph =
            new ArrayList<>();

    // Breadth First Search
    static void BFS(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        System.out.println();
    }

    // Depth First Search
    static void DFSUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                DFSUtil(next, visited);
            }
        }
    }

    static void DFS(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS: ");
        DFSUtil(start, visited);
        System.out.println();
    }

    // Dijkstra's Shortest Path
    static void dijkstra(int[][] weight, int start) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        for (int count = 0; count < V; count++) {
            int u = -1;

            for (int i = 0; i < V; i++) {
                if (!visited[i] &&
                    (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            if (u == -1 || dist[u] == Integer.MAX_VALUE)
                break;

            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && weight[u][v] > 0 &&
                    dist[u] <= Integer.MAX_VALUE - weight[u][v] &&
                    dist[u] + weight[u][v] < dist[v]) {
                    dist[v] = dist[u] + weight[u][v];
                }
            }
        }

        System.out.println("Shortest distances from " + start + ":");
        for (int i = 0; i < V; i++) {
            System.out.println(start + " to " + i + " = " +
                (dist[i] == Integer.MAX_VALUE ? "Unreachable" : dist[i]));
        }
    }

    // Prim's Minimum Spanning Tree
    static void prim(int[][] weight) {
        int[] key = new int[V];
        int[] parent = new int[V];
        boolean[] mst = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[0] = 0;

        int total = 0;

        for (int count = 0; count < V; count++) {
            int u = -1;

            for (int i = 0; i < V; i++) {
                if (!mst[i] &&
                    (u == -1 || key[i] < key[u])) {
                    u = i;
                }
            }

            if (u == -1 || key[u] == Integer.MAX_VALUE) {
                System.out.println("Graph is disconnected; no spanning tree.");
                return;
            }

            mst[u] = true;

            if (parent[u] != -1) {
                System.out.println(parent[u] + " - " + u +
                    " : " + key[u]);
                total += key[u];
            }

            for (int v = 0; v < V; v++) {
                if (!mst[v] && weight[u][v] > 0 &&
                    weight[u][v] < key[v]) {
                    key[v] = weight[u][v];
                    parent[v] = u;
                }
            }
        }

        System.out.println("Total MST weight: " + total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();

        if (V <= 0) {
            System.out.println("Number of vertices must be positive.");
            sc.close();
            return;
        }

        int[][] weight = new int[V][V];

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges as: source destination weight");
        System.out.println("Use vertices numbered from 0 to " + (V - 1));

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (u < 0 || u >= V || v < 0 || v >= V || w <= 0) {
                System.out.println("Invalid edge. Use valid vertices and positive weights.");
                sc.close();
                return;
            }

            graph.get(u).add(v);
            graph.get(v).add(u);

            weight[u][v] = w;
            weight[v][u] = w;
        }

        System.out.print("Enter starting vertex: ");
        int start = sc.nextInt();

        if (start < 0 || start >= V) {
            System.out.println("Invalid starting vertex.");
            sc.close();
            return;
        }

        BFS(start);
        DFS(start);
        dijkstra(weight, start);

        System.out.println("Prim's MST edges:");
        prim(weight);

        sc.close();
    }
}