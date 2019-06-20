package ex8.main;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class IsingModel {

	// Check if a board constellation is valid
	public static boolean isValid(boolean[] board, int n) {
		// Iterate over all positions on the board
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				// If a position is occupied: check for its neighbors
				if (board[x * n + y] == true) {
					// If the up-field exists, check if occupied
					if (x != 0) {
						if (board[(x - 1) * n + y] == true) {
							return false;
						}
					}
					// If the down-field exists, check if occupied
					if (x != n - 1) {
						if (board[(x + 1) * n + y] == true) {
							return false;
						}
					}
					// If the left-field exists, check if occupied
					if (y != 0) {
						if (board[x * n + (y - 1)] == true) {
							return false;
						}
					}
					// If the right-field exists, check if occupied
					if (y != n - 1) {
						if (board[x * n + (y + 1)] == true) {
							return false;
						}
					}
				}
			}
		}
		// The board does not contain any neighboring stones and is therefore valid
		return true;
	}

	/**
	 * Computes the possible constellations using fix number of stones using
	 * backtracking.
	 * 
	 * @param stoneCount - number of stones to place.
	 * @param n          - board size.
	 * @param startX     - position to place the current stone.
	 * @param board      - the board.
	 * @return the number of possible constellations with given number of stones.
	 */
	public static int countPossibilities(int stoneCount, int n, int startX, boolean[] board) {
		if (n <= 0) {
			return 0;
		}
		if (stoneCount == 0) {
			// all stones have been placed
			if (isValid(board, n)) {
				// solution found
				return 1;
			}
			// no solution
			return 0;
		}

		if (startX >= board.length) {
			// last stone does not fit in
			// stones left
			// no solution
			return 0;
		}

		int possibilities = countPossibilities(stoneCount, n, startX + 1, board);

		// place stone
		board[startX] = true;

		// place next stone
		possibilities += countPossibilities(stoneCount - 1, n, startX + 1, board);

		// reset stone
		board[startX] = false;

		return possibilities;
	}

	/**
	 * Computes the number of valid possible constellations for a board of size n x
	 * n.
	 * 
	 * @param n - board size.
	 * @return number of possible constellations.
	 */
	public static int possibleConstellations(int n) {
		boolean[] board = new boolean[n * n];

		// max stones per row
		int possibleStoneCount = n % 2 == 0 ? n / 2 : n / 2 + 1;
		// max stones
		possibleStoneCount *= n;

		int posCons = 0;
		for (int i = 0; i <= possibleStoneCount; i++) {
			posCons += countPossibilities(i, n, 0, board);
		}
		return posCons;
	}

	public static void main(String[] args) {
		boolean[] example1 = { false, false, false, false, true, true, true, false, false };
		boolean[] example2 = { true, false, true, false, true, false, true, false, false };

		System.out.println(isValid(example1, 3));
		System.out.println(isValid(example2, 3));

		int n = 6; // Size of the board
		// results: 0 => 0; 1 => 2; 2 => 7; 3 => 63; 4 => 1234; 5 => 55447

		System.out.println(
				"Valid constellations for a board of size " + n + " x " + n + ": " + possibleConstellations(n));
	}
}