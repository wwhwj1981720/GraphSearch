package unicom;

public class scoreOperator {
	int score;
	String name;
	public scoreOperator(int score, String name) {
		super();
		this.score = score;
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public scoreOperator(scoreOperator data) {
		super();
		this.name=data.name;
		this.score=data.score;
		
	}
	
	
}
