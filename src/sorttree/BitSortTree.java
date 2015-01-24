package sorttree;

import java.util.LinkedList;
import java.util.Queue;

import unicom.scoreOperator;



public class BitSortTree {
	BitSortNode root;
	Queue <scoreOperator> sort=new LinkedList<scoreOperator>();
	//BitSortNode head;
	public BitSortTree(BitSortNode root) {
		super();
		this.root = root;
	}
	
		
	
	public Queue<scoreOperator> getSort() {
		return sort;
	}



	public void setSort(Queue<scoreOperator> sort) {
		this.sort = sort;
	}



	public BitSortTree() {
		super();
		this.root=null;
	}
	//node 当前插入点 
	public void insertOneNode(BitSortNode node,scoreOperator score)
	{
		if(root==null) 
		{
			BitSortNode newone=new BitSortNode(score, null, null);
			root=newone;
			//root=node;
			//return node;
		}
		else if((score.getScore())>(node.data.getScore())) 
		{
			if(node.rt!=null) insertOneNode(node.rt,score);
			else 
			{
				BitSortNode newone=new BitSortNode(score, null, null);
				node.rt=newone;
			}
		}
		else 
		{
			if(node.lt!=null) insertOneNode(node.lt,score);
			else 
			{
				BitSortNode newone=new BitSortNode(score, null, null);
				node.lt=newone;
			}
		}
		
	}
	public void createSortTree(scoreOperator []data)
	{
		int len=data.length;
		
		for(int i=0; i<len ;i++)
		insertOneNode(root,data[i]);
	}
	public void middleTravleTree(BitSortNode  node)
	{
		if(node!=null) 
		{
			 middleTravleTree(node.lt);
			
			
				//System.out.println(node.data.getScore()+" "+node.data.getName());
				sort.add(node.data);
				
			
			 middleTravleTree(node.rt);
			
		}
	}
	public BitSortNode getRoot() {
		return root;
	}



	public void setRoot(BitSortNode root) {
		this.root = root;
	} 
	public void SortNode()
	{
		for(scoreOperator b:sort){
		System.out.println(b.getScore());
		}
	}
	
	public static void main(String args[])
	{
		scoreOperator []data={new scoreOperator(3,"up"),new scoreOperator(2,"down"),new scoreOperator(1,"left"),new scoreOperator(4,"right"),new scoreOperator(0,"h")};
		BitSortTree bst=new BitSortTree();
		BitSortNode start=new BitSortNode(data[3],null,null);
		bst.createSortTree(data);
		bst.middleTravleTree(bst.getRoot());
		//bst.insertOneNode(start, data[0]);
		bst.SortNode();
	}



	
}


