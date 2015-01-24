package search;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import unicom.Box;
import unicom.Box;
import unicom.Pos;
import unicom.scoreOperator;
/*
 * 撞球游戏找结果
 *   @param Box 是  棋盘
 *   @param Stack  用栈存储每一步的 棋盘状态  一实现 深度搜索
 * 
 * */
public class ChessBroadSearch {
		Box box;
		Queue<Box> open=new LinkedList<Box>();
		Stack<Box> openstack=new Stack<Box>();
		Set<Box> close=new HashSet<Box>();
		int deepcount=0;
		public ChessBroadSearch (Box q)
		{
			box=q;
		}
		
		//第一步放旗子
		public void firstStep()
		{
			Box bi=new Box(box);
			//Box bi=this.box;
			int N=bi.getN();
			int M=bi.getM();
			for(int i=0;i<N;i++)
			{
				for(int j=M-1;j>=0;j--)
				{
					char a[][]=bi.getA();
					if(a[i][j]!='*')  
					{
						Box bifirstson=new Box(bi);
						deepcount++;
						bifirstson.setParent(bi);
						bifirstson.layChess(i,j);
						Pos pf=new Pos();
						pf.setI(i);
						pf.setJ(j);
						bifirstson.setPchess(pf);
						openstack.add(bifirstson);//
					}
				}
			}
		}
		public void deepSearch(Box result) throws IOException
		{
			int size=0;
			firstStep();
			size=openstack.size();
			while(size!=0)
			{
				Box Boxnow=openstack.pop();
				close.add(Boxnow);
				if(Boxnow.equalsTo(result)) 
				{
					//Boxnow.printGraph();
					//Boxnow.printParentPath();
					Boxnow.writeFileParentPath();
					break;
				}
				//squenceoperatorBox(Boxnow);
				estimateoperatorBox(Boxnow);
				size=openstack.size();
				
			}
		}
		public void operatorUp(Box q)
		{
			Box qup=new Box(q);
			if(qup.rollUp()) 
			{
				qup.setParent(q);
				if(qup.judgeParent()) 
				{
					this.openstack.add(qup);
					deepcount++;
				}
			}
		}
		public void operatorDown(Box q)
		{

			Box qdown=new Box(q);
			if(qdown.rollDown()) 
			{
				qdown.setParent(q);
				if(qdown.judgeParent()) 
				{
					this.openstack.add(qdown);
					deepcount++;
				}
			}
		}
		public void operatorLeft(Box q)
		{
			Box qleft=new Box(q);
			if(qleft.rollLeft()) 
			{
				qleft.setParent(q);
				if(qleft.judgeParent()) 
				{
					this.openstack.add(qleft);
					deepcount++;
				}
			}
		}
		public void operatorRight(Box q)
		{
			Box qright=new Box(q);
			if(qright.rollRight()) 
			{
				qright.setParent(q);
				if(qright.judgeParent()) 
				{
					this.openstack.add(qright);
					deepcount++;
				}
			}
		}
		public void estimateoperatorBox(Box q)
		{
			scoreOperator[]order=q.elimateValue();
			for(int i=0;i<order.length;i++)
			{
				if(order[i].getName().equals("up")) operatorUp(q);
				else if(order[i].getName().equals("down")) operatorDown(q);
				else if(order[i].getName().equals("left")) operatorLeft(q);
				else if(order[i].getName().equals("right")) operatorRight(q);
			}
			



		}
		public void squenceoperatorBox(Box q)
		{
			 	operatorUp(q);
				 operatorDown(q);
				 operatorLeft(q);
				 operatorRight(q);
			}
			



		

		public static void main(String args[]) throws IOException
		{
			char startchess[][]={{'0','0','0','0','0','*'},
					{'0','0','0','0','0','*'},
					{'0','0','*','0','0','0'},
					{'0','0','0','0','*','0'},
					{'0','0','0','0','0','0'}};
								/*{{'*','0','0'},
								{'0','0','0'},
								{'0','0','*'}};
							
//								{
 */
 
//								{'0','0','0','*','0','0'},
//								{'0','*','0','*','0','0'},
//								{'0','0','0','0','0','0'},
//								{'0','0','0','0','*','0'},
//								{'0','*','0','0','*','0'},
//								{'0','0','0','0','0','0'}};
							
			
		
			Box qstart=new Box(startchess);
			Box result=qstart.makeResult();
			result.printGraph();
			//Box qstart=new Box();
			//bsqstart
			ChessBroadSearch bs=new ChessBroadSearch(qstart);
			bs.deepSearch(result); 
			System.out.println("结束     深度优先搜索次数： "+bs.deepcount);
			
		}

	}



