package Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Graph {
	public int soDinh;
	public int[][] mtk;
	public String path;
	boolean visited[]; 
	// use path file to load matrix and vertex from file .txt  
	public Graph(String pathFile) throws NumberFormatException, IOException {
		loadGraph(pathFile);
		visited = new boolean[soDinh];
	}
	
	public Graph(int[][] matrix) {
		
	}
	
	public Graph(int soDinh) {
		
	}
	
	public Graph(int soDinh , int[][] matrix) {
		
	}
	/**
	 * Load graph data from a .txt file, symbolized by edge friction
	 * @param pathFile
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void loadGraph(String pathFile) throws NumberFormatException, IOException {
		File file = new File(pathFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		this.soDinh = Integer.valueOf(br.readLine());
		this.mtk = new int[soDinh][soDinh];
		int dong = 0;
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(" ");
			for (int i = 0; i < temp.length; i++) {
				mtk[dong][i] = Integer.valueOf(temp[i]);
			}
			dong++;
		}
	}
	/**
	 * Show matrix
	 * @return matrix
	 */
	public String printMatrix() {
//			StringBuilder str = new StringBuilder();
//			for (int i = 0; i < soDinh; i++) {
//				for (int j = 0; j < soDinh; j++) {
//					str.append(mtk[i][j]).append(" ");
//				}
//				str.append("\n");
//			}
//				return str.toString();
		String str = "  ";
		for (int i = 0; i < mtk.length; i++) {
			str += "   " + i ;
		}
		str += "\n";
		for (int i = 0; i < mtk.length; i++) {
			str += i + "   ";
			for (int j = 0; j < mtk.length; j++) {
				str +=  mtk[i][j] + "   ";
			}
			str += "\n";
		}
		return str;
	}
	/**
	 * Add edges to graph
	 * i is the first vertex, j is the last vertex of the edge to be added
	 * @param i vertex i
	 * @param j vertex j
	 * add edges connect i and j
	 */
	public void addEdges(int i , int j) {
		try {
			if (i < soDinh && j < soDinh) { // i and j must belong to the set of vertices of the graph
				if (i == j) { //In case of adding tips, i and j coincide
					mtk[i][j] += 1;
				} else {
					mtk[i][j] += 1;
				}
			} 
		} catch (Exception e) {
			System.out.println("Vui long nhap lai");
		}
	}
	/**
	 *remove edges to graph
	 *i is the first vertex, j is the last vertex of the edge to be deleted
	 * @param i vertex i
	 * @param j vertex j
	 */
	public void removeEdges(int i , int j) {
		try {
			if (i < soDinh && j < soDinh && mtk[i][j] > 0) { // i and j must belong to the set of vertices of the graph and the edge to be deleted must exist
				if (i == j) { //In case of removing tips, i and j coincide
					mtk[i][j] -= 1;
				} else {
					mtk[i][j] -= 1;
				}
			} 
		} catch (Exception e) {
			System.out.println("Vui long nhap lai");
		}
	
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
	 *tr??ng ?????nh
	 * use DFS to scanning the graph
	 * @param startVertex
	 * @return
	 */
	public String DFSLinkkeList(int startVertex) {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
			StringBuffer result = new StringBuffer();
				LinkedList<Integer> lk = new LinkedList<>();
				lk.add(startVertex);
				while(!lk.isEmpty()) {
					int startCurrent = lk.getLast();
					lk.removeLast();
					if (visited[startCurrent] == false) {
						visited[startCurrent] = true;
					result =result.append(startCurrent + " ");
					}
					for (int i = soDinh - 1; i >= 0; i--) {
						if (mtk[startCurrent][i] != 0 && visited[i] == false) {
							lk.add(i);
						}
					}
				}
			
				return  result.toString();
		
		}
		
	/**
	 * tr??ng ?????nh
	 * use BFS to scanning the graph
	 * @param startVertex
	 * @return
	 */
	public String BFSLinkedlist(int startVertex) {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
		StringBuffer result = new StringBuffer();
		LinkedList<Integer> lk = new LinkedList<>();
		lk.add(startVertex);
		while (!lk.isEmpty()) {
			int startCurrent = lk.getFirst();
			lk.removeFirst();
			if (visited[startCurrent] == false) {
				visited[startCurrent] = true;
				result = result.append(startCurrent + " ");
			}
			for (int i = 0; i < soDinh; i++) {
				if (mtk[startCurrent][i] != 0 && visited[i] == false) {
					lk.add(i);

				}
			}

		}
		return result.toString();
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
