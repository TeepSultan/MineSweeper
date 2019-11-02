package MineSweeper;
import java.util.Random;

public class Board {

	Cell[][] board;
	boolean hasUncoveredMine = false;

	int numberOfMines;
	int numberOfFlags;

	public Board(int width, int height) {
		board = new Cell[height][width];
		numberOfMines = (int) ((height * width) * .2);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[y][x] = new Cell();
			}
		}
		placeMines();
	}

	private void placeMines() {
		Random rand = new Random();
		for (int i = numberOfMines; i > 0;) {
			int yC = rand.nextInt(board.length);
			int xC = rand.nextInt(board[0].length);
			if (!board[yC][xC].hasMine()) {
				board[yC][xC].setHasMine(true);
				i--;
			}

		}
	}

	private void minesAround(int x, int y) {
		int mines = 0;
		Cell currentCell = board[y][x];
		if (currentCell.hasFlag() && currentCell.isCovered()) {

		} else {
			if (currentCell.hasMine()) {

			} else {
				if (y > 0 && board[y - 1][x].hasMine()) {
					mines = mines + 1;
				}
				if ((y < board.length - 1) && board[y + 1][x].hasMine()) {
					mines = mines + 1;
				}
				if (x > 0 && board[y][x - 1].hasMine()) {
					mines = mines + 1;
				}
				if ((x < board[y].length - 1) && board[y][x + 1].hasMine()) {
					mines = mines + 1;
				}
				if ((y < board.length - 1) && (x < board[y].length - 1)
						&& board[y + 1][x + 1].hasMine()) {
					mines = mines + 1;
				}
				if ((y < board.length - 1) && (x > 0)
						&& board[y + 1][x - 1].hasMine()) {
					mines = mines + 1;
				}
				if ((y > 0) && (x < board[y].length - 1)
						&& board[y - 1][x + 1].hasMine()) {
					mines = mines + 1;
				}
				if ((y > 0) && (x > 0) && board[y - 1][x - 1].hasMine()) {
					mines = mines + 1;
				}
			}
		}
		currentCell.setNumberMinesAroundCell(mines);
		// how to check if current cell is at top row or far right column
	}

	public void flagCell(int x, int y) {
		if (areCoordinatesWithinBounds(x, y)) {
			Cell c = board[y][x];
			if (c.isCovered()) {
				if (c.hasFlag()) {
					System.out.println("Cells is already Flagged");
				} else {
					c.setHasFlag(true);
					numberOfFlags++;
				}
			} else {
				System.out.println("Cant flag an uncovered cell");
			}
		}
	}

	public void unFlagCell(int x, int y) {
		// if c.isCovered needs to be checked before the
		if (y > -1 && y < board.length) {
			if (x > -1 && x < board[0].length) {
				Cell c = board[y][x];
				if (c.isCovered()) {
					if (c.hasFlag()) {
						c.setHasFlag(false);
						numberOfFlags--;
					} else {
						System.out.println("No flag exists at cell");
					}
					// ends the c.isCovered() == true
				} else {
					System.out.println("No flag exists at cell");
				}
			}
		}

	}

	private void uncoverAllMines() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (board[y][x].hasMine()) {
					if (board[y][x].hasFlag()) {
						board[y][x].setHasFlag(false);
						board[y][x].setIsCovered(false);
					} else {
						board[y][x].setIsCovered(false);
					}
				} // outer if
			} // for loop
		}// outer for loop
	}

	public void uncoverCell(int x, int y) {
		if (areCoordinatesWithinBounds(x, y)) {
			Cell c = board[y][x];
			if (c.isCovered()) {
				if (c.hasFlag()) {
					System.out.println("Must unflag cell first");
				} else {
					if (c.hasMine()) {
						c.setIsCovered(false);
						uncoverAllMines();
						hasUncoveredMine = true;
					} else {
						minesAround(x, y);
						c.setIsCovered(false);
					}
				}
			} else {
				System.out.println("Cell is already uncovered");
			}
		}
	}

	public void printBoard() {
		System.out.println("-------------------");
		for (int y = this.board.length - 1; y >= 0; y--) {
			for (int x = 0; x < this.board[y].length; x++) {
				Cell c = board[y][x];
				if (c.isCovered()) {
					if (c.hasFlag()) {
						System.out.print(" F ");
					} else {
						System.out.print(" . ");
					}
				} else {
					if (c.hasMine()) {
						System.out.print(" M ");
					} else {
						System.out.print(" " + c.getNumberMinesAroundCell()
								+ " ");
					}
				}
			} // x for loop
			System.out.println();
		} // y for loop
		System.out.println("Mines remaining:---> " + remainingMines() + " <---");
	}

	// unused method
	// private void setMine(int x, int y) {
	// Cell c = board[y][x];
	// c.setHasMine(true);
	// }

	private int getNumberCellsUncovered() {
		int cellsUncovered = 0;
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (!board[y][x].isCovered()) {
					cellsUncovered = cellsUncovered + 1;
				}
			}
		}
		return cellsUncovered;
	}

	private int getNumberOfMines() {
		return this.numberOfMines;
	}

	private int totalCells() {
		return board[0].length * board.length;
	}

	public int remainingCellsToWin() {
		return (totalCells() - getNumberCellsUncovered() - getNumberOfMines());
	}

	public boolean hasDied() {
		return hasUncoveredMine;
	}
	
	public boolean hasWon() {
		if (remainingCellsToWin() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean areCoordinatesWithinBounds(int x, int y) {
		boolean b = true;
		if (!(x > -1 && x < board[0].length)) {
			System.out.println("X Coordinate out of bounds: " + x);
			b = false;
		}
		if (!(y > -1 && y < board.length)) {
			System.out.println("Y Coordinate out of bounds: " + y);
			b = false;
		}
		return b;
	}
	
	public int remainingMines() {
		return (numberOfMines - numberOfFlags);
	}
}

