package Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
	public int soDinh;
	public String[][] mtk;
	public String path;
	boolean visited[]; 
	// use path file to load matrix and vertex from file .txt  
	public Graph(String pathFile) throws NumberFormatException, IOException {
		loadGraph(pathFile);
		visited = new boolean[soDinh];
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
		this.mtk = new String[soDinh][soDinh];
		int dong = 0;
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(" ");
			for (int i = 0; i < temp.length; i++) {
				mtk[dong][i] = temp[i];
				
			}
			dong++;
		}
	}
	/**
	 * Show matrix
	 * @return matrix
	 */
	public String printMatrix() {
		String str = "  ";
		for (int i = 0; i < mtk.length; i++) {
			str += "  " + i  ;
		}
		str += "\n";
		for (int i = 0; i < mtk.length; i++) {
			str += i + "   ";
			for (int j = 0; j < mtk.length; j++) {
				str +=  mtk[i][j] + "       ";
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
	public void addEdges(int i, int j, String value) {

		if (i != j) {
			if (mtk[i][j].equals("0")  || mtk[i][j].equals("-∞")|| mtk[i][j].equals("+∞")) {
				if (i < soDinh && j < soDinh) { // i and j must belong to the set of vertices of the graph
					mtk[i][j] = value ;
				}
			}
		}

	}

	/**
	 *remove edges to graph
	 *i is the first vertex, j is the last vertex of the edge to be deleted
	 * @param i vertex i
	 * @param j vertex j
	 */
	public void removeEdges(int i , int j) {
			if (i < soDinh && j < soDinh && condition(mtk, i, j)) { // i and j must belong to the set of vertices of the graph and the edge to be deleted must exist	
					mtk[i][j] = "0";
			}

	}
	public boolean condition(String[][] matrix, int i, int j) {
		if(matrix[i][j].equals("0") || matrix[i][j].equals("-∞") ||matrix[i][j].equals("∞") ) {
			return false;
		}
		return true;
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
	 *trùng đỉnh
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
						if (condition(mtk, startCurrent, i) && visited[i] == false) {
							lk.add(i);
						}
					}
				}
			
				return  result.toString();
		
		}
		
	/**
	 * trùng đỉnh
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
				if (condition(mtk, startCurrent, i) && visited[i] == false) {
					lk.add(i);

				}
			}

		}
		return result.toString();
	}
	// TH1 nua bac ngoai cua dinh
		public int degreeOutV(int v) {
			int degree = 0;
			for (int i = 0; i < mtk.length; i++) {
				degree += Integer.parseInt(mtk[v][i]);
			}
			return degree;
		}

		// TH2 nua bac trong cua dinh
		public int degreeInV(int v) {
			int degree = 0;
			for (int i = 0; i < this.mtk.length; i++) {
				degree += Integer.parseInt(mtk[v][i]);
			}
			return degree;
		}
		public int DFSInt(String[][] matrix,int v) {
			for (int i = 0; i < soDinh; i++) {
				visited[i] = false;
			}
			int count = 0;
			Stack<Integer> stack = new Stack<>();
			stack.push(v);
			while(!stack.isEmpty()) {
				int start = stack.pop();
				if(visited[start]== false) {
					visited[start] = true;
					count++;
				
				}
				for (int i = soDinh-1; i >= 0; i--) {
					if(condition(matrix, start,i) && visited[i] == false) {
						stack.push(i);
					}
				}
			}
			return count;
			
		}
	//kiem tra do thi lien thong yeu
		//ý tưởng : chuyển đổi từ đồ thị có hướng hiện tại thành đồ thị vô hướng, nếu đồ thị vô hướng mới chuyển đổi liên thông(dùng thuật toán dfs duyệt qua được tất cả các đỉnh)thì đồ thị có hướng ban đầu liên thông yếu.
		public boolean checkConnectWeekly() {
			String [][]copy = new String[soDinh][soDinh];
		    for (int i = 0; i < copy.length; i++) {
				for (int j = 0; j < copy.length; j++) {
					copy[i][j] = mtk[i][j];
				}
			}
		boolean re ;
		int count = 0;	

	    	 revereseGraph(copy); //chuyen do thi co huong hien tai thanh do thi vo huong
	    	// printMatrix();
	    	count = DFSInt(copy,0);//check xem do thi vo huong duoc chuyen tu do thi co huong co lien thong khong
	    	 if(count == soDinh) { //neu so dinh duoc duyet het thi do thi lien thong, nguoc lai thi khong lien thong
	    		 re = true;
	    	 }else {
	    		 re = false;
	    	 }
	    	 
				return re;
		}
		public void revereseGraph(String[][] copy) {
			String copy1[][] = this.mtk;
			for (int i = 0; i < copy.length; i++) {
				for (int j = 0; j < copy[0].length; j++) {
					if(condition(copy1, i, j) ) {
						copy[i][j] =  copy1[i][j];
						copy[j][i] = copy1[i][j];
					}
				}
			}	
		}
		//kiểm tra đồ thị liên thông mạnh
		//ý tưởng: sử dụng dfs duyệt qua tất cả các đỉnh của đồ thị, nếu duyệt được hết tất cả các đỉnh thì đồ thị liên thông mạnh.
		public boolean checkConnectStrongly() {
			boolean flag = true;
			int count = 0;
			count = DFSInt(this.mtk,0);
			for (int i = 0; i < soDinh; i++) {
				 int degreeIn = degreeInV(i);
				 int degreeOut = degreeOutV(i);
				 if(degreeIn < 1 || degreeOut < 1) {
					 flag = false;
				 }
			}
			if(count == soDinh && flag == true) {
				return true;
			}else {
				return false;
			}
		}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph un1 = new Graph("E:\\CodeLTDT\\src\\DoThiCoHuong.txt");
		System.out.println(un1.printMatrix());
     	System.out.println(un1.DFSLinkkeList(0));
	//	un1.removeEdges(0, 2);
	// System.out.println(un1.printMatrix());
	}
	
}
