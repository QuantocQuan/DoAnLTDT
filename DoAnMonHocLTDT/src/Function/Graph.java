package Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

	/**
	 * Load graph data from a .txt file, symbolized by edge friction
	 * 
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
				mtk[dong][i] = Integer.parseInt(temp[i]);

			}
			dong++;
		}
	}

	/**
	 * Show matrix
	 * 
	 * @return matrix
	 */
	public String printMatrix() {
		String str = " ";
		for (int i = 0; i < mtk.length; i++) {
			str += "    " + i;
		}
		str += "\n";
		for (int i = 0; i < mtk.length; i++) {
			str += i + "    ";
			for (int j = 0; j < mtk.length; j++) {

				str += mtk[i][j] + "    ";
			}
			str += "\n";
		}
		return str;
	}

	/**
	 * Add edges to graph i is the first vertex, j is the last vertex of the edge to
	 * be added
	 * 
	 * @param i vertex i
	 * @param j vertex j add edges connect i and j
	 */
	public void addEdges(int i, int j, int value) {
		if (i != j) { // kh duoc add khuyen
			if (mtk[i][j] == 0) { // kh dc add canh song song
				if (i < soDinh && j < soDinh) {
					mtk[i][j] = value;
				}
			}
		}
	}

	/**
	 * remove edges to graph i is the first vertex, j is the last vertex of the edge
	 * to be deleted
	 * 
	 * @param i vertex i
	 * @param j vertex j
	 */
	public void removeEdges(int i, int j) {
		if (i < soDinh && j < soDinh && mtk[i][j] != 0) { // i and j must belong to the set of vertices of the
			mtk[i][j] = 0;
		}

	}

	/**
	 * trùng đỉnh use DFS to scanning the graph
	 * 
	 * @param startVertex
	 * @return
	 */
	public String DFSLinkkeList(int startVertex) {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
		LinkedList<Edge> listEdge = new LinkedList<>();
		// Khởi tạo kết quả là một StringBuffer và danh sách đỉnh sẽ duyệt
		StringBuffer result = new StringBuffer();
		LinkedList<Integer> lk = new LinkedList<>();
		lk.add(startVertex);

		// Bắt đầu duyệt đồ thị
		while (!lk.isEmpty()) {
			// Lấy đỉnh cuối cùng trong danh sách đỉnh sẽ duyệt
			int startCurrent = lk.getLast();
			lk.removeLast();

			// Nếu đỉnh chưa được duyệt thì đánh dấu và thêm vào kết quả
			if (visited[startCurrent] == false) {
				visited[startCurrent] = true;
				result = result.append(startCurrent + " ");
			}

			// Tìm các cạnh kề với đỉnh hiện tại và chưa được duyệt
			listEdge = new LinkedList<>();
			for (int i = 0; i < soDinh; i++) {
				if (mtk[startCurrent][i] != 0 && visited[i] == false) {
					listEdge.add(new Edge(startCurrent, i, mtk[startCurrent][i]));
				}
			}

			// Sắp xếp danh sách cạnh theo thứ tự giảm dần của trọng số
			Collections.sort(listEdge);

			// Thêm các đỉnh kề vào danh sách đỉnh sẽ duyệt tiếp theo
			for (int i = listEdge.size() - 1; i >= 0; i--) {
				lk.add(listEdge.get(i).dest);
			}
		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}

	
	public String DFSLinkkeList() {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
		LinkedList<Edge> listEdge = new LinkedList<>();
		// Khởi tạo kết quả là một StringBuffer và danh sách đỉnh sẽ duyệt
		StringBuffer result = new StringBuffer();
		LinkedList<Integer> lk = new LinkedList<>();
		int startVertex = findMin();
		lk.add(startVertex);

		// Bắt đầu duyệt đồ thị
		while (!lk.isEmpty()) {
			// Lấy đỉnh cuối cùng trong danh sách đỉnh sẽ duyệt
			int startCurrent = lk.getLast();
			lk.removeLast();

			// Nếu đỉnh chưa được duyệt thì đánh dấu và thêm vào kết quả
			if (visited[startCurrent] == false) {
				visited[startCurrent] = true;
				result = result.append(startCurrent + " ");
			}

			// Tìm các cạnh kề với đỉnh hiện tại và chưa được duyệt
			listEdge = new LinkedList<>();
			for (int i = 0; i < soDinh; i++) {
				if (mtk[startCurrent][i] != 0 && visited[i] == false) {
					listEdge.add(new Edge(startCurrent, i, mtk[startCurrent][i]));
				}
			}

			// Sắp xếp danh sách cạnh theo thứ tự giảm dần của trọng số
			Collections.sort(listEdge);

			// Thêm các đỉnh kề vào danh sách đỉnh sẽ duyệt tiếp theo
			for (int i = listEdge.size() - 1; i >= 0; i--) {
				lk.add(listEdge.get(i).dest);
			}
		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}
	/**
	 * trùng đỉnh use BFS to scanning the graph
	 * 
	 * @param startVertex
	 * @return
	 */
	public String BFSLinkedlist(int startVertex) {
		for (int i = 0; i < this.soDinh; i++) {
			visited[i] = false;
		}
		LinkedList<Edge> listEdge = new LinkedList<>();
		// Khởi tạo kết quả là một StringBuffer và danh sách đỉnh sẽ duyệt
		StringBuffer result = new StringBuffer();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startVertex);

		// Bắt đầu duyệt đồ thị
		while (!queue.isEmpty()) {
			// Lấy đỉnh cuối cùng trong danh sách đỉnh sẽ duyệt
			int startVex = queue.poll();

			// Nếu đỉnh chưa được duyệt thì đánh dấu và thêm vào kết quả
			if (visited[startVex] == false) {
				visited[startVex] = true;
				result = result.append(startVex + " ");
			}

			// Tìm các cạnh kề với đỉnh hiện tại và chưa được duyệt
			listEdge = new LinkedList<>();
			for (int i = 0; i < this.soDinh; i++) {
				if (mtk[startVex][i] != 0 && visited[i] == false) {
					listEdge.add(new Edge(startVex, i, mtk[startVex][i]));
				}
			}

			// Sắp xếp danh sách cạnh theo thứ tự giảm dần của trọng số
			Collections.sort(listEdge);

			// Thêm các đỉnh kề vào danh sách đỉnh sẽ duyệt tiếp theo
			for (int i = 0; i < listEdge.size(); i++) {
				queue.add(listEdge.get(i).dest);
			}

		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}

	public String BFSLinkedlist() {
		for (int i = 0; i < this.soDinh; i++) {
			visited[i] = false;
		}
		LinkedList<Edge> listEdge = new LinkedList<>();
		// Khởi tạo kết quả là một StringBuffer và danh sách đỉnh sẽ duyệt
		StringBuffer result = new StringBuffer();
		Queue<Integer> queue = new LinkedList<>();
		int startVertex = findMin();
		queue.add(startVertex);

		// Bắt đầu duyệt đồ thị
		while (!queue.isEmpty()) {
			// Lấy đỉnh cuối cùng trong danh sách đỉnh sẽ duyệt
			int startVex = queue.poll();

			// Nếu đỉnh chưa được duyệt thì đánh dấu và thêm vào kết quả
			if (visited[startVex] == false) {
				visited[startVex] = true;
				result = result.append(startVex + " ");
			}

			// Tìm các cạnh kề với đỉnh hiện tại và chưa được duyệt
			listEdge = new LinkedList<>();
			for (int i = 0; i < this.soDinh; i++) {
				if (mtk[startVex][i] != 0 && visited[i] == false) {
					listEdge.add(new Edge(startVex, i, mtk[startVex][i]));
				}
			}

			// Sắp xếp danh sách cạnh theo thứ tự giảm dần của trọng số
			Collections.sort(listEdge);

			// Thêm các đỉnh kề vào danh sách đỉnh sẽ duyệt tiếp theo
			for (int i = 0; i < listEdge.size(); i++) {
				queue.add(listEdge.get(i).dest);
			}

		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}

//	trả về đỉnh nhỏ nhất của cạnh nhỏ nhất trong ma trận 
	public int findMin() {
		ArrayList<Edge> array = new ArrayList<>();
		for (int i = 0; i < this.mtk.length; i++) {
			for (int j = 0; j < mtk.length; j++) {
				int edgeLength = this.mtk[i][j];
				if (edgeLength != 0)
					array.add(new Edge(i, j, this.mtk[i][j]));
			}
		}
		Collections.sort(array);
		System.out.println(array);
		return array.get(0).src ;

	}

	// TH1 nua bac ngoai cua dinh
	public int degreeOutV(int v) {
		int degree = 0;
		for (int i = 0; i < mtk.length; i++) {
			if (mtk[v][i] != 0) {
				degree++;
			}
		}
		return degree;
	}

	// TH2 nua bac trong cua dinh
	public int degreeInV(int v) {
		int degree = 0;
		for (int i = 0; i < this.mtk.length; i++) {
			if (mtk[i][v] != 0) {
				degree++;
			}

		}
		return degree;
	}

	public int DFSInt(int[][] mtk, int v) {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
		int count = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			int start = stack.pop();
			if (visited[start] == false) {
				visited[start] = true;
				count++;

			}
			for (int i = soDinh - 1; i >= 0; i--) {
				if (mtk[start][i] != 0 && visited[i] == false) {
					stack.push(i);
				}
			}
		}
		return count;

	}

	/*
	 * - kiem tra do thi lien thong yeu ý tưởng : chuyển đổi từ đồ thị có hướng hiện
	 * tại thành đồ thị vô hướng, nếu đồ thị vô hướng mới chuyển đổi liên thông(dùng
	 * - thuật toán dfs duyệt qua được tất cả các đỉnh)thì đồ thị có hướng ban đầu
	 * liên thông yếu.
	 */
	public boolean checkConnectWeekly() {
		int[][] copy = new int[soDinh][soDinh];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy.length; j++) {
				copy[i][j] = mtk[i][j];
			}
		}
		boolean re;
		int count = 0;

		revereseGraph(copy);
		// chuyen do thi co huong hien tai thanh do thi vo huong
		// printMatrix();
		count = DFSInt(copy, 0);
		// check xem do thi vo huong duoc chuyen tu do thi co huong co lien thong khong
		// neu so dinh duoc duyet het thi do thi lien thong, nguoc lai thi khong lien
		// thong
		if (count == soDinh) {
			re = true;
		} else {
			re = false;
		}

		return re;
	}

	public void revereseGraph(int[][] copy) {
		int copy1[][] = this.mtk;
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				if (copy1[i][j] != 0) {
					copy[i][j] = copy1[i][j];
					copy[j][i] = copy1[i][j];
				}
			}
		}
	}

	// kiểm tra đồ thị liên thông mạnh
	// ý tưởng: sử dụng dfs duyệt qua tất cả các đỉnh của đồ thị, nếu duyệt được hết
	// tất cả các đỉnh thì đồ thị liên thông mạnh.
	public boolean checkConnectStrongly() {
		boolean flag = true;
		int count = 0;
		count = DFSInt(this.mtk, 0);
		for (int i = 0; i < soDinh; i++) {
			int degreeIn = degreeInV(i);
			int degreeOut = degreeOutV(i);
			if (degreeIn < 1 || degreeOut < 1) {
				flag = false;
			}
		}
		if (count == soDinh && flag == true) {
			return true;
		} else {
			return false;
		}
	}

	public String checkConnect() {
		String re = "";
		if (checkConnectStrongly()) {
			re = "Đồ thị liên thông mạnh";
		} else if (checkConnectWeekly()) {
			re = "Đồ thị liên thông yếu";
		} else {
			re = "Đồ thị không liên thông";
		}
		return re;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Graph un1 = new Graph(
				"C:\\Users\\ASUS\\OneDrive - st.hcmuaf.edu.vn\\MyCode\\Java\\DoAnLTDT\\DoAnLTDT\\DoAnLTDT\\DoAnMonHocLTDT\\MatrantrongsoDTCH.txt");
		System.out.println(un1.printMatrix());
		System.out.println(un1.BFSLinkedlist(4));
		System.out.println(un1.DFSLinkkeList(4));
		System.out.println(un1.BFSLinkedlist());
		System.out.println(un1.DFSLinkkeList());
		// System.out.println(un1.checkConnect());
//		un1.addEdges(2, 0, 10);
//		System.out.println(un1.printMatrix());
		// System.out.println(un1.checkConnectWeekly());
		// un1.removeEdges(0, 2);
		// System.out.println(un1.printMatrix());
	}

}
