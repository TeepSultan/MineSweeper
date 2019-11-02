package MineSweeper;

public class Cell {
	private boolean hasMine;
	private boolean hasFlag;
	private boolean isCovered = true;
	private int numberMinesAround;
	
	
	public boolean hasMine() {
		return this.hasMine;
	}
	
	public void setHasMine(boolean mine) {
		this.hasMine = mine;
	}
	
	public boolean hasFlag() {
		return this.hasFlag;
	}
	
	public void setHasFlag(boolean flag) {
		this.hasFlag = flag;
	}
	
	public boolean isCovered() {
		return this.isCovered;
	}
	
	public void setIsCovered(boolean covered) {
		this.isCovered = covered;
	}
	
	public int getNumberMinesAroundCell(){
		return this.numberMinesAround;
	}
	
	public void setNumberMinesAroundCell(int mines) {
		this.numberMinesAround = mines;
	}
	
}
