package Function;

public class Graph {
	protected int soDinh;
	protected int[][] mtk;
	protected String path;
	// use path file to load matrix and vertex from file .txt  
	public Graph(String pathFile) {
		
	}
	
	public Graph(int[][] matrix) {
		
	}
	
	public Graph(int soDinh) {
		
	}
	
	public Graph(int soDinh , int[][] matrix) {
		
	}
	
	public void loadGraph() {
		
	}
	// show matrix in JtextArea
	public String printMatrix() {
		return null;
	}
	/**
	 * @param i vertex i
	 * @param v vertex v
	 * add edges connect i and v
	 */
	public void addEdges(int i , int v) {
		
	}
	/**
	 * 
	 * @param i vertex i
	 * @param v vertex v
	 * remove edges connect i and v
	 */
	public void removeEdges(int i , int v) {
		
	}
	/**
	 *  determine simple graph
	 * @return true if this is simple graph
	 * @return false if this is not simple graph
	 */
	
	public boolean checkValid() {
		return false;
	}
	/**
	 * determine undirected graph
	 * @return true if this is directed graph
	 * @return false if not
	 */
	public boolean checkUnGraph() {
		return false;
	}
	/**
	 * use DFS to scanning the graph
	 * @param startVertex
	 * @return
	 */
	public String DFSLinkkeList(int startVertex) {
		return null ;
	}
	/**
	 * use BFS to scanning the graph
	 * @param startVertex
	 * @return
	 */
	public String BFSLinkedlist(int startVertex) {
		return null ;
	}
	/**
	 * 
	 * @param startVex
	 * @return true if this is connect graph
	 */
	public boolean checkConnectGraph(int startVex) {
		return false ;
	}
	/**
	 * 
	 * @param startVex
	 * @return show how many connect element and all vertex in a connect element
	 */
	public String findElementConnect(int startVex) {
		return null ;
	}
	
}
