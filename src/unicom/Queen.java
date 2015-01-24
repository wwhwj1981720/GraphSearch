package unicom;

import java.util.Arrays;
/**
 * @param a 存储棋盘
 * @param parent 存储搜索的路径
 * @param 棋盘 行的大小
 * @param 棋盘列的大小
 *   
 */

public class Queen {

	char a[][] = { { '1', '2', '3' }, { '4', '@', '5' }, { '6', '7', '8' } };
	Queen parent = null;
	int n = 0;
	int m = 0;
	public Queen getParent() {
		return parent;
	}

	public void setParent(Queen parent) {
		this.parent = parent;
	}

	public Queen() {
		this.parent = null;

	}

	public Queen(char[][] b) {
		this.a = b;
		this.m = this.countM();
		this.n = this.countN();

	}

	public Queen(Queen p) {
		char[][] b = p.a;
		int i = 0;
		for (char[] k : b) {
			int j = 0;
			for (char c : k) {
				if (i == 0)
					this.m++;
				this.a[i][j] = c;
				j++;
			}
			i++;
		}
		this.n = i;
	}

	public int countN() {
		int N = 0;
		for (char[] b : a) {
			// int j=0;

			this.n++;
		}
		N = this.n;
		return N;
	}

	public int countM() {
		// char
		int M = 0;
		for (char b : a[0]) {
			// int j=0;

			this.m++;
		}
		M = m;
		return M;
	}

	// 向上操作i-1,j
	// 向上操作i-1,j
	public boolean pushUp() {
		Pos pnow = new Pos();
		pnow = findPos(a);
		int i = pnow.i;
		Pos pafter = new Pos();
		pafter.i = i - 1;
		pafter.j = pnow.j;
		if (boxMoveJudge(pafter)) {

			exchange(pnow, pafter);
			return true;
		}
		return false;
	}

	// 向下操作i+1,j
	public boolean pushDown() {
		Pos pnow = new Pos();
		pnow = findPos(a);
		int i = pnow.i;
		Pos pafter = new Pos();
		pafter.i = i + 1;
		pafter.j = pnow.j;
		if (boxMoveJudge(pafter)) {

			exchange(pnow, pafter);
			return true;
		}
		return false;
	}

	// 向左操作i,j-1
	public boolean pushLeft() {
		Pos pnow = new Pos();
		pnow = findPos(a);
		int j = pnow.j;
		Pos pafter = new Pos();
		pafter.i = pnow.i;
		pafter.j = j - 1;
		if (boxMoveJudge(pafter)) {

			exchange(pnow, pafter);
			return true;
		}
		return false;
	}

	// 向右操作i,j+1
	public boolean pushRight() {
		Pos pnow = new Pos();
		pnow = findPos(a);
		int j = pnow.j;
		Pos pafter = new Pos();
		pafter.i = pnow.i;
		pafter.j = j + 1;
		if (boxMoveJudge(pafter)) {

			exchange(pnow, pafter);
			return true;
		}
		return false;
	}

	public boolean boxMoveJudge(Pos p) {
		boolean nb = (p.i >= 0 && p.i < n);
		boolean mb = (p.j >= 0 && p.j < m);
		// boolean empty=(a[p.i][p.j]=='0');
		if (nb && mb)
			return true;
		return false;
	}

	// 交换 pnow 与 pafter这两个位置的值 推箱子操作
	public void exchange(Pos pnow, Pos pafter) {
		// char [][]anew

		char pnowvalue = a[pnow.i][pnow.j];
		char paftervalue = a[pafter.i][pafter.j];
		a[pnow.i][pnow.j] = paftervalue;
		a[pafter.i][pafter.j] = pnowvalue;

		// return anew;
	}

	// 找到当前空格的位置
	public Pos findPos(char a[][]) {
		int i = 0;

		Pos p = new Pos();
		for (char[] b : a) {
			int j = 0;
			for (char c : b) {

				if (c == '@') {
					p.i = i;
					p.j = j;
				}
				j++;
			}
			i++;
		}
		return p;
	}

	public char[][] getA() {
		return a;
	}

	public void setA(char[][] a) {
		this.a = a;
	}

	public void printGraph() {
		int i = 0;
		int j = 0;
		for (char[] b : a) {
			for (char c : b) {

				System.out.print(c);
				j++;
			}
			i++;
			System.out.println("");
		}
	}

	public boolean judgeParent() {
		Queen father = this.parent;
		boolean flag = true;
		while (father != null) {
			if (this.equalsTo(father)) {
				flag = false;
				break;
			}
			father = father.parent;
		}
		return flag;
	}

	public void printParentPath() {
		System.out.println("--------------------");
		this.printGraph();
		Queen father = this.parent;
		// boolean flag=true;
		while (father != null) {
			System.out.println("-^-");
			father.printGraph();

			father = father.parent;
		}

	}

	public boolean equalsTo(Queen result) {
		char[][] c = result.a;
		int i = 0;
		for (char[] b : a) {
			if (Arrays.equals(c[i], b))
				i++;
		}
		if (i == 3)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Queen result=new Queen();
		// char resulta[][]={{'1','2','3'},{'4','5','@'},{'6','7','8'}};
		// result.a=resulta;
		// Queen queen=new Queen();
		// char [][]a={{'1','2','3'},{'4','@','5'},{'6','7','8'}};
		// //char chess[][]=queen.getA();
		// queen.a=a;
		// boolean flag=queen.equalsTo(result);
		char ch[][] = { { '1', '2', '3' }, { '4', '5', '@' }, { '6', '7', '8' } };
		Queen qu = new Queen(ch);
		Queen qus = new Queen(qu);

		char chess[][] = { { '1', '2', '3' }, { '4', '5', '@' },
				{ '6', '7', '8' } };
		Queen queen = new Queen();
		queen.printGraph();
		queen.pushRight();
		queen.printGraph();
		queen.printGraph();
		queen.pushLeft();
		queen.printGraph();
		queen.printGraph();
		queen.pushDown();
		queen.printGraph();
		queen.printGraph();
		queen.pushUp();
		queen.printGraph();

	}

}
