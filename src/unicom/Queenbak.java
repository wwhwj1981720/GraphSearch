package unicom;

import java.util.Arrays;

public class Queenbak {

	/**
	 * @param args
	 */
	char a[][]={{'1','2','3'},{'4','@','5'},{'6','7','8'}};
	public Queenbak()
	{
		
			
	}
	//向上操作i-1,j
	public char[][] pushUp(char a[][])
	{
		Pos pnow=new Pos();
		pnow=findPos(a);
		int i=pnow.i;
		if((i-1)>=0&&(i-1)<=2) 
		{
			Pos pafter=new Pos();
			pafter.i=i-1;
			pafter.j=pnow.j;
			return exchange(a,pnow,pafter);
		}
		return null;
	}
	//向下操作i+1,j
		public char[][] pushDown(char a[][])
		{
			Pos pnow=new Pos();
			pnow=findPos(a);
			int i=pnow.i;
			if((i+1)>=0&&(i+1)<=2) 
			{
				Pos pafter=new Pos();
				pafter.i=i+1;
				pafter.j=pnow.j;
				return exchange(a,pnow,pafter);
			}
			return null;
		}
		//向左操作i,j-1
				public char[][] pushLeft(char a[][])
				{
					Pos pnow=new Pos();
					pnow=findPos(a);
					int j=pnow.j;
					if((j-1)>=0&&(j-1)<=2) 
					{
						Pos pafter=new Pos();
						pafter.i=pnow.i;
						pafter.j=j-1;
						return exchange(a,pnow,pafter);
					}
					return null;
				}
				//向右操作i,j+1
				public char[][] pushRight(char a[][])
				{
					Pos pnow=new Pos();
					pnow=findPos(a);
					int j=pnow.j;
					if((j+1)>=0&&(j+1)<=2) 
					{
						Pos pafter=new Pos();
						pafter.i=pnow.i;
						pafter.j=j+1;
						return exchange(a,pnow,pafter);
					}
					return null;
				}	
	//交换 pnow 与 pafter这两个位置的值
	public char[][] exchange(char a[][],Pos pnow,Pos pafter )
	{
		char pnowvalue=a[pnow.i][pnow.j];
		char paftervalue=a[pafter.i][pafter.j];
		a[pnow.i][pnow.j]=paftervalue;
		a[pafter.i][pafter.j]=pnowvalue;
		return a;
	}
	//找到当前空格的位置
	public Pos findPos(char a[][])
	{
		int i=0;
		
		Pos p=new Pos();
		for(char []b:a)
		{
			int j=0;
			for(char c:b)
			{
				
				if(c=='@') 
				{
					p.i=i;
					p.j=j;
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
	public void printGraph(char a[][])
	{
		int i=0;
		int j=0;
		for(char []b:a)
		{
			for(char c:b)
			{
				
				System.out.print(c);
				j++;
			}
			i++;
			System.out.println("");
		}
	}
	public boolean equalsTo(Queenbak result)
	{
		char [][]c=result.a;
		int i=0;
		for(char []b:a)
		{
			if(Arrays.equals(b,c[i])) i++;
		}
		if(i==3) return true;
		else return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queenbak result=new Queenbak();
		char resulta[][]={{'1','2','3'},{'4','5','@'},{'6','7','8'}};
		result.a=resulta;
		Queenbak queen=new Queenbak();
		char [][]a={{'1','2','3'},{'4','@','5'},{'6','7','8'}};
		//char chess[][]=queen.getA();
		queen.a=a;
		boolean flag=queen.equalsTo(result);
		char chess[][]={{'1','2','3'},{'4','5','@'},{'6','7','8'}};
		queen.printGraph(chess);
		chess=queen.pushRight(chess);
		
		queen.printGraph(chess);
		chess=queen.pushLeft(chess);
		
		queen.printGraph(chess);
		chess=queen.pushDown(chess);
		
		queen.printGraph(chess);
		chess=queen.pushUp(chess);
		queen.printGraph(chess);
		
		

	}

}
