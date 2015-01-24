package sorttree;

import unicom.scoreOperator;


public class BitSortNode {
	scoreOperator data;
	BitSortNode lt;
	BitSortNode rt;
	//深拷贝
	public BitSortNode(scoreOperator score, BitSortNode lt, BitSortNode rt) {
		super();
		this.data = new scoreOperator(score);
		this.lt = lt;
		this.rt = rt;
	}
	//浅拷贝
	
	/*public BitSortNode(scoreOperator score, BitSortNode lt, BitSortNode rt) {
		super();
		this.data = score;
		this.lt = lt;
		this.rt = rt;
	}
*/	
	
	public BitSortNode() {
		super();
	}

		

}
