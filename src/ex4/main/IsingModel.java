package ex4.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class IsingModel {
	// pruefen ob eine Belegung des Schachbrettes gueltig ist
	public static boolean isValid(boolean[] board, int n) {

		// generate 2D-Array
		boolean[][] boardArray = new boolean[n][n];
		for (int i = 0; i < boardArray.length; i++) {
			for (int j = 0; j < boardArray.length; j++) {
				boardArray[i][j] = board[n * i + j];
			}
		}

		int i, j;
		for (i = 0; i < boardArray.length - 1; i++) {
			for (j = 0; j < boardArray.length - 1; j++) {
				if (boardArray[i][j] && (boardArray[i + 1][j] || boardArray[i][j + 1])) {
					return false;
				}
			}
			// check last column at row
			if (boardArray[i][j] && boardArray[i + 1][j]) {
				return false;
			}
		}
		// check last row
		for (j = 0; j < boardArray.length - 1; j++) {
			if (boardArray[i][j] && boardArray[i][j + 1]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		boolean[] example1 = { false, false, false, false, true, true, true, false, false };

		boolean[] example2 = { true, false, true, false, true, false, true, false, false };

		System.out.println(isValid(example1, 3));
		System.out.println(isValid(example2, 3));
	}
}
