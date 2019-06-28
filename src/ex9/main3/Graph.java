package ex9.main3;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class Graph {

	/**
	 * The adjacency matrix.
	 */
	private double[][] mat;

	/**
	 * Constructor.
	 * 
	 * @param nNodes - Number of nodes within the graph.
	 */
	public Graph(int nNodes) {
		mat = new double[nNodes][nNodes];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = -1;
			}
		}
	}

	/**
	 * The weight of the connection between the two vertices.
	 * 
	 * @param vertexIndex1 - Index of the first vertex.
	 * @param vertexIndex2 - Index of the second vertex.
	 * @return The weight of the connection between the two vertices.
	 */
	public double getEdge(int vertexIndex1, int vertexIndex2) {
		return mat[vertexIndex1][vertexIndex2];
	}

	/**
	 * Sets a new edge with given distance between from the first to the second
	 * vertex.
	 * 
	 * @param distance     - Distance between the vertices.
	 * @param vertexIndex1 - Index of the first vertex.
	 * @param vertexIndex2 - Index of the second vertex.
	 */
	public void setEdge(double distance, int vertexIndex1, int vertexIndex2) {
		mat[vertexIndex1][vertexIndex2] = distance;
	}

	/**
	 * Sets a new bidirectional edge with given distance between two vertices.
	 * 
	 * @param distance     - Distance between the vertices.
	 * @param vertexIndex1 - Index of the first vertex.
	 * @param vertexIndex2 - Index of the second vertex.
	 */
	public void setEdgeBidirectional(double distance, int vertexIndex1, int vertexIndex2) {
		setEdge(distance, vertexIndex1, vertexIndex2);
		setEdge(distance, vertexIndex2, vertexIndex1);
	}

	/**
	 * Computes the shortest distance between two vertices using the Dijkstra
	 * algorithm.
	 * 
	 * @param source - Vertex to start from.
	 * @param target - Target vertex.
	 * @return The shortest distance from source to target.
	 */
	public double shortestDistanceBetween(int source, int target) {
		// i)
		double[] distanceFromSource = new double[mat.length];
		for (int i = 0; i < distanceFromSource.length; i++) {
			if (i == source) {
				distanceFromSource[i] = 0;
				continue;
			}
			distanceFromSource[i] = Double.MAX_VALUE;
		}
		// ii)
		PriorityQueue queue = new PriorityQueue();
		queue.enqueue(source, distanceFromSource[source]);
		boolean targetReached = false;

		int currentVert;
		double currentDist;
		// v)
		while (!queue.isEmpty()) {
			// iii)
			currentVert = queue.getNode();
			currentDist = queue.getDistance();
			queue.pop();

			if (currentVert != target) {
				// check all connections
				for (int j = 0; j < mat[currentVert].length; j++) {
					if (mat[currentVert][j] >= 0) {
						// connection found
						if (currentDist + mat[currentVert][j] < distanceFromSource[j]) {
							// connection via current vertex is shorter
							distanceFromSource[j] = distanceFromSource[currentVert] + mat[currentVert][j];
							if (queue.contains(j)) {
								queue.update(j, distanceFromSource[j]);
							} else {
								queue.enqueue(j, distanceFromSource[j]);
							}
						}
					}
				}
				// iv)
				queue.blockInFuture(currentVert);
			} else {
				// current ist tartet
				targetReached = true;
			}
		}
		// vi)
		return targetReached ? distanceFromSource[target] : -1;
	}
}