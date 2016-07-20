import java.util.*;

class Main {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		Vertex[] vertices = new Vertex[N];

		for (int i = 0; i < N; i++)
			vertices[i] = new Vertex(i + 1);

		HashMap<Vertex, HashMap<Vertex, Integer>> w = new HashMap<>();
		HashMap<Vertex, List<Vertex>> adj = new HashMap<>();
		for (Vertex v : vertices) {
			adj.put(v, new LinkedList<Vertex>());
			w.put(v, new HashMap<Vertex,Integer>());
		}

		for (int i = 0; i < M; i++) {
			Vertex src = vertices[sc.nextInt() - 1];
			Vertex dst = vertices[sc.nextInt() - 1];
			int weight = sc.nextInt();

			adj.get(src).add(dst);
			w.get(src).put(dst, weight);
		}

		int s = sc.nextInt();
		Graph graph = new Graph (vertices, adj, w);
		boolean negative_weighted_cycle = graph.BellmanFord(s);
		if (!negative_weighted_cycle) {
			System.out.println("Negative weighted cycle.");
		}

		else for (Vertex v : vertices) {
			System.out.println(graph.toString(v));
		}
	}
}
