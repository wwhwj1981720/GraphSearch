package search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import unicom.Queen;
import unicom.Queen;
/*
 * 八皇后问题的解决
 * 用队列实现 图的 广度优先
 * 
 * */
public class BroadSearch {
	Queen queen;
	//初始化
	Queue<Queen> open=new LinkedList<Queen>();
	Set<Queen> close=new HashSet<Queen>();
	
	public  BroadSearch(Queen q)
	{
		queen=q;
	}
	//四个方向的操作
//	public void operatorQueenTwo(Queen q)
//	{
//		char [][]aup={{'a','a','a'},{'a','a','a'},{'a','a','a'}};
//		aup=q.pushUp();
//		Queen qup=new Queen(aup);
//		if(qup.getA()!=null) this.open.add(qup);
//		char [][]adown={{'a','a','a'},{'a','a','a'},{'a','a','a'}};
//		adown=q.pushDown();
//		Queen qdown=new Queen(adown);
//		if(qdown.getA()!=null) this.open.add(qdown);
//		
//	}
	public void operatorQueen(Queen q)
	{

		Queen qup=new Queen(q);
		if(qup.pushUp()) 
		{
			qup.setParent(q);
			if(qup.judgeParent()) this.open.add(qup);
		}
		Queen qdown=new Queen(q);
		if(qdown.pushDown())
		{ 
			qdown.setParent(q);
			if(qdown.judgeParent()) this.open.add(qdown);
		}
		Queen qleft=new Queen(q);
		if(qleft.pushLeft())
		{ 
			qleft.setParent(q);
			if(qleft.judgeParent()) this.open.add(qleft);
		}
		Queen qright=new Queen(q);
		if(qright.pushRight())
		{ 
			qright.setParent(q);
			if(qright.judgeParent()) this.open.add(qright);
		}
		
		
	}
	public void Search(Queen result)
	{
		open.add(queen);
		int size=0;
		size=open.size();
		while(size!=0)
		{
			Queen queennow=open.poll();
			close.add(queennow);
			//找到 结果
			if(queennow.equalsTo(result)) 
			{
				queennow.printGraph();
				queennow.printParentPath();
				break;
			}
			operatorQueen(queennow);
			
		}
	}
	
	public static void main(String args[])
	{
		//开始的棋局状态
		char startchess[][]={{'1','2','3'},{'4','@','5'},{'6','7','8'}};
		//结束的棋局状态
		char resultchess[][]={{'8','3','5'},{'4','2','@'},{'6','1','7'}};
		//queen.printGraph(chess);
		Queen result=new Queen(resultchess);
		result.printGraph();
		Queen qstart=new Queen(startchess);
		
		BroadSearch bs=new BroadSearch(qstart);
		bs.Search(result);
		
	}

}
