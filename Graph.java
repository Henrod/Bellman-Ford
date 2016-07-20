import java.util.*;

class Graph {
	private static final int INF = Integer.MAX_VALUE;
	private int V;
	private Vertex[] vertices;
	private HashMap<Vertex, List<Vertex>> adj;
	private HashMap<Vertex, HashMap<Vertex, Integer>> w;


	public Graph(Vertex[] vertices, HashMap<Vertex,List<Vertex>> adj, 
	  HashMap<Vertex,HashMap<Vertex, Integer>> w) {
	  	this.V = vertices.length;
		this.vertices = vertices;
		this.adj = adj;
		this.w = w;
	}

	private void relax(Vertex u, Vertex v) {
		int weight = w(u, v);
		if (u.distance != INF && v.distance > u.distance + weight) {
			v.distance = u.distance + weight;
			v.parent = u;
		}
	}

	public boolean BellmanFord(int s) {
		initialize(vertices[s - 1]);
		List<Edge> edges = edges();
		for (int i = 0; i < V - 1; i++)
			for (Edge e : edges)
				relax(e.u, e.v);

		for (Edge e : edges) 
			if (e.v.distance > e.u.distance + w(e.u, e.v)) {
				return false;
			}

		return true;
	}

	private class Edge {Vertex u; Vertex v;}
	private List<Edge> edges() {
		List<Edge> list = new LinkedList<>();
		for (Vertex src : w.keySet()) {
			for (Vertex dst : w.get(src).keySet()) {
				Edge e = new Edge();
				e.u = src;
				e.v = dst;
				list.add(e);
			}
		}
		return list;
	}

	private void initialize(Vertex s) {
		for (Vertex v : vertices) {
			v.distance = INF;
			v.parent = null;
		}

		s.distance = 0;

	}

	private int w(Vertex u, Vertex v) {
		return w.get(u).get(v);
	}

	public String toString(Vertex vertex) {
		StringBuffer sb = new StringBuffer();
		for (Vertex v = vertex; v != null; v = v.parent) {
			sb.append(v.index + " <- ");
		}
		return sb.toString();
	}
}
