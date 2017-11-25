import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static void solvePuzzle(int gridRows, int gridColumns, char[][] grid, MyHashTable<String> H, int maxlength,
			String enhance) {
		// puzzle solve starts
		int wordCount = 0;
		for (int i = 0; i < gridRows; i++) {
			// check puzzle from left to right
			for (int j = 0; j < gridColumns; j++) {
				String gridData = "";
				for (int k = j; k < gridColumns; k++) {
					gridData = gridData.concat("" + grid[i][k]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}
			// check puzzle from right to left
			for (int j = gridColumns - 1; j >= 0; j--) {
				String gridData = "";
				for (int k = j; k >= 0; k--) {
					gridData = gridData.concat("" + grid[i][k]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}
		}

		for (int j = 0; j < gridColumns; j++) {
			// check puzzle from top to bottom
			for (int i = 0; i < gridRows; i++) {
				String gridData = "";
				for (int k = i; k < gridRows; k++) {
					gridData = gridData.concat("" + grid[k][j]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}
			// check puzzle from bottom to top
			for (int i = gridRows - 1; i >= 0; i--) {
				String gridData = "";
				for (int k = i; k >= 0; k--) {
					gridData = gridData.concat("" + grid[k][j]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}
		}

		for (int i = 0; i < gridRows; i++) {
			// check puzzle for diagonal top to bottom-left to right
			for (int j = 0; j < gridColumns; j++) {
				String gridData = "";
				for (int k = i, l = j; k < gridRows & l < gridColumns; l++, k++) {
					gridData = gridData.concat("" + grid[k][l]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}

			// check puzzle for diagonal top to bottom-right to left
			for (int j = gridColumns - 1; j >= 0; j--) {
				String gridData = "";
				for (int k = i, l = j; k < gridRows & l >= 0; l--, k++) {
					gridData = gridData.concat("" + grid[k][l]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}
		}

		for (int i = gridRows - 1; i >= 0; i--) {
			// check puzzle for diagonal bottom to top-left to right
			for (int j = 0; j < gridColumns; j++) {
				String gridData = "";
				for (int k = i, l = j; k >= 0 & l < gridColumns; l++, k--) {
					gridData = gridData.concat("" + grid[k][l]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}

			// check puzzle for diagonal bottom to top-right to left
			for (int j = gridColumns - 1; j >= 0; j--) {
				String gridData = "";
				for (int k = i, l = j; k >= 0 & l >= 0; l--, k--) {
					gridData = gridData.concat("" + grid[k][l]);
					if (gridData.length() > maxlength)
						break;
					if (H.contains(gridData)) {
						if (H.isWord(gridData)) {
							System.out.println(gridData);
							wordCount++;
						}
					} else if (enhance.equals("Y")) {
						break;
					}
				}
			}
		}
		// puzzle solve Ends
		System.out.println("Number of words Found: " + wordCount);

	}

	public static void main(String[] args) {
		char grid[][] = new char[2000][2000];
		int maxlength = 0;
		int gridRows, gridColumns;
		String fileName = "dictionary.txt";

		MyHashTable<String> H = new MyHashTable<>();
		Scanner in = new Scanner(System.in);
		String enhance;

		while (true) {
			System.out.print("Do you want to apply the enhancement? (Y/N): ");
			enhance = in.nextLine();
			if (enhance.equals("Y") || enhance.equals("N")) {
				break;
			} else
				System.out.println("Please enter a valid character Y or N");
		}

		try {

			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;

			if (enhance.equals("Y")) {
				while ((line = bufferReader.readLine()) != null) {
					for (int i = 0; i < line.length(); i++) {
						if (i == line.length() - 1) {
							H.insert(line.substring(0, i + 1), true);
						} else
							H.insert(line.substring(0, i + 1), false);
					}
					if (maxlength < line.length()) {
						maxlength = line.length();
					}
				}
			} else {
				while ((line = bufferReader.readLine()) != null) {
					for (int i = 0; i < line.length(); i++) {
						H.insert(line, true);
						if (maxlength < line.length()) {
							maxlength = line.length();
						}
					}
				}
			}
			bufferReader.close();
		} catch (IOException e) {
			System.err.println("Cannot open " + fileName);
			System.exit(0);
		}
		while (true) {
			System.out.print("Please Enter the number of Rows of the grid : ");
			gridRows = in.nextInt();
			System.out.print("Please Enter the number of Columns of the grid: ");
			gridColumns = in.nextInt();
			if (gridRows <= 0 || gridColumns <= 0) {
				System.out.println("Not valid values, Please enter again ");
			} else
				break;
		}
		in.close();

		// create a grid of random letters
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				Random r = new Random();
				grid[i][j] = (char) (r.nextInt(26) + 'a');
			}
		}

		System.out.println("\nGrid generated :");

		// display the grid
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.print("\n");
		}

		System.out.println("\nWords found : ");

		long startTime = System.currentTimeMillis();// start time

		solvePuzzle(gridRows, gridColumns, grid, H, maxlength, enhance);

		long endTime = System.currentTimeMillis(); // end time
		System.out.println("Elapsed time: " + (endTime - startTime));
	}
}