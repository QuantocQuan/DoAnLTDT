package Model;

public class Edge implements Comparable<Edge> {
	int src;
	int dest;
	int weight;
	public Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
	
		public String toString() {
			return "("+ src + "-" + dest + ")" + ":" +weight;
		}

	
	

}
