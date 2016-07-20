class Vertex implements Comparable<Vertex> {
	int distance;
	Vertex parent;
	int index;

	public Vertex (int index) {
		this.index = index;
	}

	public int compareTo (Vertex v) {
		return this.distance - v.distance;
	}
}
