package MineSweeper;
import java.util.Scanner;

public class MineSweeper {
	Board brd;

	public MineSweeper() {
		brd = new Board(5, 5);
	}

	public MineSweeper(int x, int y) {
		brd = new Board(x, y);
	}

	public static void main(String[] args) {

//		int width = Integer.parseInt(args[0]);
//		int height = Integer.parseInt(args[1]);
		int width = 10;
		int height = 10;
		MineSweeper m = new MineSweeper(width, height);

		m.startGame();
	}

	public void help() {
		System.out.println("Type 'Uncover x y' to uncover a cell coordinate.");
		System.out.println("Type 'Flag x y' to flag a cell coordinate.");
		System.out.println("Type 'Unflag x y' to unflag a cell coordinate.");
                System.out.println("Type 'Help' to get help prompt.");
		System.out.println("Type 'Exit' to exit game.");
	}

	public void win() {
		if (brd.remainingCellsToWin() == 0) {
			System.out.println("You have Won!!");
		}
	}

	public void startGame() {
		this.brd.printBoard();
		help();
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("Enter Command: ");
			String s = scan.nextLine();
			String[] input = s.split("\\s+");
			System.out.println(s);
			if (input.length == 3 || input.length == 1) {
				if (input[0].equalsIgnoreCase("uncover")) {
					int x = Integer.parseInt(input[1]);
					int y = Integer.parseInt(input[2]);
					brd.uncoverCell(x, y);
					brd.printBoard();
					if (brd.hasDied()) {
						System.out.println("You lose");
						break;
					}
					if (brd.hasWon()) {
						System.out.println("!!!You have Won!!!");
						break;
					}
				} else if (input[0].equalsIgnoreCase("flag")) {
					int x = Integer.parseInt(input[1]);
					int y = Integer.parseInt(input[2]);
					brd.flagCell(x, y);
					brd.printBoard();
				} else if (input[0].equalsIgnoreCase("unflag")) {
					int x = Integer.parseInt(input[1]);
					int y = Integer.parseInt(input[2]);
					brd.unFlagCell(x, y);
					brd.printBoard();
				} else if (input[0].equalsIgnoreCase("help")) {
					help();
				} else if (input[0].equalsIgnoreCase("exit")) {
					break;
				} else if (input[0].equalsIgnoreCase("play")) {
					MineSweeper m2 = new MineSweeper();
					m2.startGame();
				} else {
					System.out.println("Invalid Command");
					help();
				}
			} else {
				System.out.println("Invalid Command");
				help();
			}
		}
		System.out.println("Thanks for playing Minesweeper!");
		scan.close();
	}
	
}
