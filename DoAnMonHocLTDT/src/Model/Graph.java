package Model;

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
		String str = "   ";
		for (int i = 0; i < mtk.length; i++) {
			str += "     " + i;
		}
		str += "\n";
		for (int i = 0; i < mtk.length; i++) {
			str += i + "      ";
			for (int j = 0; j < mtk.length; j++) {
				if (mtk[i][j] == 0) {
					str += "0";
				} else {
					str += mtk[i][j];
				}
				int count;
				if (mtk[i][j] != 0)
					count = 2 * this.computeHowManyNumber(this.mtk[i][j]) - 1;
				else
					count = 2;

				while (count < 6) {
					str += " ";
					count++;
				}
			}
			str += "\n";
		}
		return str;
	}

	private int computeHowManyNumber(int number) {
		int count = 0;
		if (number == 0)
			return 1;
		while (number >= 1) {
			number = number / 10;
			count++;
		}
		return count;
	}

	// thêm cạnh(i,j) và trọng số cạnh đó vào đồ thị, nếu cạnh(i,j) không phải là
	// khuyên và không phải cạnh song song thì thêm
	public void addEdges(int i, int j, int value) {
		if (i != j) {
			if (mtk[i][j] == 0) {
				if (i < soDinh && j < soDinh) {
					mtk[i][j] = value;
				}
			}
		}
	}

	// Xóa cạnh (i,j) của đồ thị, nếu i, j thuộc các đỉnh của đồ thị và cạnh (i,j)
	// tồn tại trong đồ thị thì xóa
	public void removeEdges(int i, int j) {
		if (i < soDinh && j < soDinh && mtk[i][j] != 0) {
			mtk[i][j] = 0;
		}

	}

	//// Duyệt theo chiều sâu, sử dụng danh sách liên kết(Stack), trùng đỉnh, có
	//// tham số truyền vào là đỉnh bắt đầu duyệt
	public String DFSLinkkeList(int startVertex) {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
		LinkedList<Edge> listEdge = new LinkedList<>();
		// Khởi tạo kết quả là một StringBuffer và danh sách đỉnh sẽ duyệt
		StringBuffer result = new StringBuffer();
		Stack<Integer> stack = new Stack<>();
		stack.add(startVertex);

		// Bắt đầu duyệt đồ thị
		while (!stack.isEmpty()) {
			// Lấy đỉnh cuối cùng trong danh sách đỉnh sẽ duyệt
			int startCurrent = stack.pop();

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
				stack.add(listEdge.get(i).dest);
			}
		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}

	// Duyệt theo chiều sâu, sử dụng danh sách liên kết(Stack), trùng đỉnh, đỉnh bắt
	// đầu duyệt là đỉnh của cạnh có trọng số nhỏ nhất
	public String DFSLinkkeList() {
		for (int i = 0; i < soDinh; i++) {
			visited[i] = false;
		}
		LinkedList<Edge> listEdge = new LinkedList<>();
		// Khởi tạo kết quả là một StringBuffer và danh sách đỉnh sẽ duyệt
		StringBuffer result = new StringBuffer();

		int startVertex = findMin();
		Stack<Integer> stack = new Stack<>();
		stack.add(startVertex);

		// Bắt đầu duyệt đồ thị
		while (!stack.isEmpty()) {
			// Lấy đỉnh cuối cùng trong danh sách đỉnh sẽ duyệt
			int startCurrent = stack.pop();

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
				stack.add(listEdge.get(i).dest);
			}
		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}

	// Duyệt theo chiều rộng, sử dụng danh sách liên kết(Queue), trùng đỉnh, có tham
	// số truyền vào là đỉnh bắt đầu duyệt
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
			System.out.println(listEdge);

			// Thêm các đỉnh kề vào danh sách đỉnh sẽ duyệt tiếp theo
			for (int i = 0; i < listEdge.size(); i++) {
				queue.add(listEdge.get(i).dest);
			}

		}

		// Trả về kết quả dưới dạng chuỗi
		return result.toString();
	}

	// Duyệt theo chiều rộng, sử dụng danh sách liên kết(Queue), trùng đỉnh, đỉnh
	// bắt đầu duyệt là đỉnh của cạnh có trọng số nhỏ nhất
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

//	trả về đỉnh nhỏ nhất của cạnh có trọng số nhỏ nhất trong ma trận 
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
		return array.get(0).src;

	}

	// nửa bậc ngoài của đỉnh
	public int degreeOutV(int v) {
		int degree = 0;
		for (int i = 0; i < mtk.length; i++) {
			if (mtk[v][i] != 0) {
				degree++;
			}
		}
		return degree;
	}

	// nửa bậc trong của đỉnh
	public int degreeInV(int v) {
		int degree = 0;
		for (int i = 0; i < this.mtk.length; i++) {
			if (mtk[i][v] != 0) {
				degree++;
			}

		}
		return degree;
	}

	// dùng DFS để duyệt qua tất cả các đỉnh của đồ thị, sau đó gán số đỉnh đã duyệt
	// được vào biến count
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
	 * Kiểm tra đồ thị liên thông yếu: chuyển đổi từ đồ thị có hướng hiện tại thành
	 * đồ thị vô hướng, nếu đồ thị vô hướng mới chuyển đổi liên thông(dùng thuật
	 * toán dfs duyệt qua được tất cả các đỉnh)thì đồ thị có hướng ban đầu liên
	 * thông yếu.
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
		count = DFSInt(copy, 0);
		if (count == soDinh) {
			re = true;
		} else {
			re = false;
		}

		return re;
	}

	// phương thức chuyển đồ thị có hướng thành vô hướng
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
	// ý tưởng: sử dụng dfs duyệt qua tất cả các đỉnh của đồ thị (1), nếu mọi đỉnh
	// trong đồ thị đều có bậc của đỉnh ra và đỉnh vào > 1 (2) nếu thỏa 2 điều kiều
	// kiên (1) và (2) thì đồ thị liên thông mạnh
	public boolean checkConnectStrongly() {
		boolean flag = true;
		int count = 0;
		count = DFSInt(this.mtk, 0);
		for (int i = 0; i < soDinh; i++) {
			int degreeIn = degreeInV(i);
			int degreeOut = degreeOutV(i);
			if (degreeIn < 1 || degreeOut < 1) {
				flag = false;
				break;
			}
		}
		if (count == soDinh && flag == true) {
			return true;
		} else {
			return false;
		}
	}

	// kiểm tra tính liên thông của đồ thị : Nếu đồ thị trọng số, có hướng liên
	// thông mạnh thì dừng, ngược lại thì sẽ kiểm tra liên thông yếu, nếu là liên
	// thông yếu thì dừng, ngược lại thì là đồ thị không liên thông
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
				"C:\\Users\\ASUS\\OneDrive - st.hcmuaf.edu.vn\\MyCode\\Java\\LTDT\\DoAnLTDT\\DoAnMonHocLTDT\\DoThiLienThongYeu.txt");
		String a = un1.printMatrix();
		System.out.println(a);
		// System.out.println(un1.printMatrix());
//		System.out.println(un1.BFSLinkedlist(0));
		// System.out.println(un1.DFSLinkkeList(0));
//		System.out.println(un1.BFSLinkedlist());
//		System.out.println(un1.DFSLinkkeList());
		// System.out.println(un1.checkConnect());
//		un1.addEdges(2, 0, 10);
//		System.out.println(un1.printMatrix());
		// System.out.println(un1.checkConnectWeekly());
		// un1.removeEdges(0, 2);
		// System.out.println(un1.printMatrix());
	}

}
