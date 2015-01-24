package unicom;


import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;

import output.CFile;
import sorttree.BitSortTree;

//推箱子
public class Box {

	char a[][] = null;	
					/*{{'0','0','0','0','0','*'},
					{'0','0','0','0','0','*'},
					{'0','0','*','0','0','0'},
					{'0','0','0','0','*','0'},
					{'0','0','0','0','0','0'}};*/
					//棋盘初始化
	Box parent = null;// 父棋盘
	Pos pchess = null;// 当前棋子的位置
	int n = 0;// 行值
	int m = 0;// 列植
	
	public Box getParent() {
		return parent;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public void setParent(Box parent) {
		this.parent = parent;
	}
	//体会不同构造函数下的使用
	public Box() {
		this.parent = null;
		this.pchess = new Pos();
		char [][]start={{'*','0','0','0'},
						{'0','0','0','0'},
						{'0','0','0','*'}};
		this.a=start;

	}

	public Box(char[][] b) {
		this.a = b;
		this.pchess = new Pos();

	}

	//
	public Box(Box p) {
		char[][] b = p.a;
		int N = 0;
		int M = 0;
		N = p.countN();
		M = p.countM();
		this.a=new char[N][M];
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

		this.pchess = new Pos();
		this.pchess.setI(p.getPchess().i);
		this.pchess.setJ(p.getPchess().j);

	}
	//根据初始状态自动生成结果棋盘
	public Box makeResult()
	{
		Box end=new Box();
		char[][] b = this.a;
		int N = 0;
		int M = 0;
		N = this.countN();
		M = this.countM();
		end.a=new char[N][M];
		int i = 0;
		for (char[] k : b) {
			int j = 0;
			for (char c : k) {
				if (i == 0)
					end.m++;
				if(c=='0') c='@';
				end.a[i][j] = c;
				j++;
			}
			i++;
		}
		end.n = i;
		
//		end.pchess = new Pos();
//		this.pchess.setI(p.getPchess().i);
//		this.pchess.setJ(p.getPchess().j);
		
		return end;
	}
	public Pos getPchess() {
		return pchess;
	}

	public void setPchess(Pos pchess) {
		this.pchess = pchess;
	}

	// 向上操作i-1,j

	// 找到当前空格的位置
	public int countN() {
		//int N = 0;
		this.n=0;
		for (char[] b : a) {
			// int j=0;

			this.n++;
		}
		return this.n;
		//return N;
	}

	public int countM() {
		// char
		//int M = 0;
		this.m=0;
		for (char b : a[0]) {
			// int j=0;

			this.m++;
		}
		return this.m;
		//return M;
	}

	

	public char[][] getA() {
		return a;
	}

	public void setA(char[][] a) {
		this.a = a;
	}

	

	// 遍历所有的父节点判断是否有相同
	public boolean judgeParent() {
		Box father = this.parent;
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
	//输出棋盘当前下棋位置
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
	public void writeFileGraph(CFile f) {
		int i = 0;
		int j = 0;
		for (char[] b : a) {
			for (char c : b) {

				
				f.inputRow(Character.toString(c));
				j++;
			}
			i++;
			
			f.inputRow("\r\n");
		}
	}
	//控制台输出方式
	public void printParentPath() {
		System.out.println("--------------------");
		
		this.printGraph();
		Box father = this.parent;
		System.out.println("下棋位置:"+this.pchess.i+" "+this.pchess.j);
		while (father != null) {
			System.out.println("-^-");
			
			father.printGraph();
			System.out.println("下棋位置:"+father.pchess.i+" "+father.pchess.j);
			father = father.parent;
		}

	}
	//文件输出方式
	public void writeFileParentPath() throws IOException {
		System.out.println("--------------------");
		CFile file=new CFile(); 
		file.makefile("e:\\SMSCDR_20130901\\"+"coil"+ ".txt");
		this.printGraph();
		writeFileGraph(file);
		Box father = this.parent;
		System.out.println("下棋位置:"+this.pchess.i+" "+this.pchess.j);
		file.inputRow("下棋位置:"+this.pchess.i+" "+this.pchess.j+"\r\n");
		while (father != null) {
			System.out.println("-^-");
			file.inputRow("-^-"+"\r\n");
			father.printGraph();
			father.writeFileGraph(file);
			System.out.println("下棋位置:"+father.pchess.i+" "+father.pchess.j);
			file.inputRow("下棋位置:"+father.pchess.i+" "+father.pchess.j+"\r\n");
			father = father.parent;
		}

	}
	public boolean equalsTo(Box result) {
		char[][] c = result.a;
		int i = 0;
		for (char[] b : a) {
			if (Arrays.equals(c[i], b))
				i++;
		}
		if (i == this.n)
			return true;
		else
			return false;
	}

	// true 表示有障碍物，false 表示可以移动

	// zai 棋盘上放置一个棋子
	public void layChess(int i, int j) {
		this.a[i][j] = '@';

	}

	// public boolean isOrNotFirst
	public boolean rollUp() {
		Pos pnow = new Pos();
		pnow = pchess;
		Pos pafter = new Pos();
		pafter.i = pnow.i - 1;
		pafter.j = pnow.j;
		if (borderJudge(pafter)) {

			dectRollUp(pafter);
			return true;
		}
		return false;
	}

	// 向下操作i+1,j
	public boolean rollDown() {
		Pos pnow = new Pos();
		pnow = pchess;
		int i = pnow.i;
		Pos pafter = new Pos();
		pafter.i = i + 1;
		pafter.j = pnow.j;
		if (borderJudge(pafter)) {

			dectRollDown(pafter);
			;
			return true;
		}
		return false;
	}

	// 向左操作i,j-1
	public boolean rollLeft() {
		Pos pnow = new Pos();
		pnow = pchess;
		int j = pnow.j;
		Pos pafter = new Pos();
		pafter.i = pnow.i;
		pafter.j = j - 1;
		if (borderJudge(pafter)) {

			dectRollLeft(pafter);
			return true;
		}
		return false;
	}

	// 向右操作i,j+1
	public boolean rollRight() {
		Pos pnow = new Pos();
		pnow = pchess;
		int j = pnow.j;
		Pos pafter = new Pos();
		pafter.i = pnow.i;
		pafter.j = j + 1;
		if (borderJudge(pafter)) {

			dectRollRight(pafter);
			return true;
		}
		return false;
	}

	public boolean borderJudge(Pos p) {
		boolean nb = (p.i >= 0 && p.i < n);
		boolean mb = (p.j >= 0 && p.j < m);

		if (nb && mb) {
			boolean empty = (a[p.i][p.j] == '0');
			if (empty)
				return true;
		}
		return false;
	}

	public void dectRollUp(Pos pafter) {
		// 确定 roll位置 p
		int count = 0;
		for (int i = pafter.i; i >= 0; i--) {
			if (a[i][pafter.j] == '0') {
				layChess(i, pafter.j);
				count++;

			} else
				break;
		}
		pchess.i = pafter.i - (count - 1);
		pchess.j = pafter.j;
		// return anew;
	}

	public void dectRollDown(Pos pafter) {
		// 确定 roll位置 p
		int count = 0;
		for (int i = pafter.i; i < n; i++) {
			if (a[i][pafter.j] == '0') {
				layChess(i, pafter.j);
				count++;

			} else
				break;
		}
		pchess.i = pafter.i + count - 1;
		pchess.j = pafter.j;
		// return anew;
	}

	public void dectRollLeft(Pos pafter) {
		// 确定 roll位置 p
		int count = 0;
		for (int j = pafter.j; j >= 0; j--) {
			if (a[pafter.i][j] == '0') {
				layChess(pafter.i, j);
				count++;

			} else
				break;
		}
		pchess.i = pafter.i;
		pchess.j = pafter.j - (count - 1);
		// return anew;
	}

	public void dectRollRight(Pos pafter) {
		// 确定 roll位置 p
		int count = 0;
		for (int j = pafter.j; j < m; j++) {
			if (a[pafter.i][j] == '0') {
				layChess(pafter.i, j);
				count++;

			} else
				break;
		}
		pchess.i = pafter.i;
		pchess.j = pafter.j + (count - 1);
		// return anew;
	}
	//四个方向评估 每个方向可能就给该方向加一分
	public int fourDirectionScores(Pos pnow)
	{	
		Pos pDirection[]=new Pos[4];
		int j = pnow.j;
		Pos up = new Pos();
		up.i = pnow.i-1;
		up.j = j;
		pDirection[0]=up;
		Pos down = new Pos();
		down.i = pnow.i+1;
		down.j = j;
		pDirection[1]=down;
		
		Pos left = new Pos();
		left.i = pnow.i;
		left.j = j-1;
		pDirection[2]=left;
		Pos right = new Pos();
		right.i = pnow.i;
		right.j = j + 1;
		pDirection[3]=right;
		int score=0;
		for(Pos p:pDirection)
		{
			score+=markPos(p);
		}
		return score;
		
	}
	public scoreOperator [] elimateValue()
	{
		int []score=new int [4];
		scoreOperator ps[]=new scoreOperator[4];
		Pos pnow = new Pos();
		pnow = pchess;
		int j = pnow.j;
		
		Pos up = new Pos();
		up.i = pnow.i-1;
		up.j = j;
		score[0]=fourDirectionScores(up);
		ps[0]=new scoreOperator(score[0],"up");
		Pos down = new Pos();
		down.i = pnow.i+1;
		down.j = j;
		
		score[1]=fourDirectionScores(down);
		ps[1]=new scoreOperator(score[1],"down");
		Pos left = new Pos();
		left.i = pnow.i;
		left.j = j-1;
		
		score[2]=fourDirectionScores(left);
		ps[2]=new scoreOperator(score[2],"left");
		Pos right = new Pos();
		right.i = pnow.i;
		right.j = j + 1;
		
		score[3]=fourDirectionScores(right);
		ps[3]=new scoreOperator(score[3],"right");
		//this.orderTheArray(ps);
		//return ps;
		return orderSortTree(ps);
	}
	//快排序
	public void orderTheArray(scoreOperator [] a)
	{
		int len=a.length;
		scoreOperator m=new scoreOperator(0,"m");;
		for(int i=len-1;i>0;i--)
		{
			for(int j=0;j<i;j++)
			{
				if(a[j].score>=a[j+1].score)
				{
					
					m=a[j];
					a[j]=a[j+1];
					a[j+1]=m;
				}
			}
		}
	}
	//二叉排序树
	public scoreOperator[] orderSortTree(scoreOperator [] a)
	{
		BitSortTree bst=new BitSortTree();
		bst.createSortTree(a);
		bst.middleTravleTree(bst.getRoot());
		scoreOperator[]sort=new scoreOperator[4];
		bst.getSort().toArray(sort);
		return sort;
	}
	
	//某点打分
	public int markPos(Pos p)
	{
		int mark=0;
		boolean nb = (p.i >= 0 && p.i < n);
		boolean mb = (p.j >= 0 && p.j < m);
		
		if(nb&&mb)
		{ 
			boolean empty = (a[p.i][p.j] == '0');
			if(empty) mark++;
		}
		return mark;
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Box result=new Box();
		// char resulta[][]={{'1','2','3'},{'4','5','@'},{'6','7','8'}};
		// result.a=resulta;
		// Box Box=new Box();
		// char [][]a={{'1','2','3'},{'4','@','5'},{'6','7','8'}};
		// //char chess[][]=Box.getA();
		// Box.a=a;
		// boolean flag=Box.equalsTo(result);
		char ch[][] = { { '1', '2', '3' }, { '4', '5', '@' }, { '6', '7', '*' } };
		Box qu = new Box(ch);
		Box qus = new Box(qu);
		// qus.countN();
		// qus.countM();
		System.out.println(qus.n + "    " + qus.m);
		char chess[][] = { { '1', '2', '3' }, { '4', '5', '@' },
				{ '6', '7', '*' } };
		Box Box = new Box();
		Box.printGraph();
	

	}

}
