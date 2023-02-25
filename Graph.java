package btthtuan1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public abstract class Graph {
	protected int soDinh;
	protected int[][] mtk;
	protected String path;
	protected boolean[] visited;
	protected int[] result;
	protected int index;

	public Graph(String path) throws NumberFormatException, IOException {
		loadGraph(path);
		visited = new boolean[soDinh];
		result = new int[soDinh];
		index = 0;

	}

	// cau1 tai du lieu ma tran ke
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

	// cau2 in ma tran ke
	public void printMatrix() {
		for (int i = 0; i < mtk.length; i++) {
			for (int j = 0; j < mtk[0].length; j++) {
				System.out.print(mtk[i][j] + " ");
			}
			System.out.println();
		}
	}

	// cau3 check don do thi (duong cheo chinh = 0)
	public boolean checkValid() {
		int[] temp = new int[mtk.length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (i == j) {
					temp[i] = mtk[i][j];
				}
			}
		}
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != 0) {
				return false;
			}

		}
		return true;
	}

	// cau4 kiem tra co phai do thi vo huong khong
	public boolean checkUngraph() {
		for (int i = 0; i < mtk.length; i++) {
			for (int j = 0; j < mtk.length; j++) {
				if (mtk[i][j] != mtk[j][i]) {
					return false;
				}
			}
		}
		return true;
	}

	// cau5 them canh
	public abstract void addEdges(int i, int j);

	// cau6 xoa canh
	public abstract void removeEdges(int i, int j);

	// cau7_1 so dinh cua do thi
	public int numVertexs() {
		return soDinh;
	}

	// cau8 so canh cua do thi
	public abstract int numEdges();

	// cau7_2 bac cua dinh
	public abstract int deg(int V);

	// cau9 bac cua do thi
	public abstract int sumDeg();

	// duyet cac dinh theo chieu sau
	public abstract void DFS(int v);

	// cau11_1 duyet do thi theo chieu sau(su dung stack) khong co tham so
	public abstract int[] DFSStack();

	// cau11_2 duyet do thi theo chieu sau(su dung stack) co tham so
	public abstract int[] DFSStack(int v);

	// duyet cac dinh theo chieu sau(su dung de quy) co tham so truyen vao
	public abstract int[] DFSRecursive(int v);

	// duyet cac dinh theo chieu sau(su dung de quy)khong co tham so truyen vao
	public abstract int[] DFSRecursive();

	// duyet cac dinh theo chieu rong
	public abstract void BFS(int v);

	// cau12_1 duyet do thi theo chieu rong(su dung queue)khong co tham so
	public abstract int[] BFSQueue();

	// cau12_2 duyet do thi theo chieu rong(su dung queue) co tham so
	public abstract int[] BFSQueue(int v);

}
