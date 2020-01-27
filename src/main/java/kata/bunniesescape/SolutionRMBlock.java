package kata.bunniesescape;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 * Prepare the Bunnies' Escape
 * ===========================
 *
 * You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny prisoners,
 * but once they're free of the prison blocks,
 * the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible.
 * Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies.
 * Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies.
 * Unfortunately (again), you can't just remove all obstacles between the bunnies and the escape pods
 *  - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station and to avoid arousing Commander Lambda's suspicions.
 *
 * You have maps of parts of the space station,
 * each starting at a prison exit and ending at the door to an escape pod.
 * The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls.
 * The door out of the prison is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1).
 *
 * Write a function solution(map) that generates the length of the shortest path from the prison door to the escape pod,
 * where you are allowed to remove one wall as part of your remodeling plans.
 * The path length is the total number of nodes you pass through, counting both the entrance and exit nodes.
 * The starting and ending positions are always passable (0).
 * The map will always be solvable, though you may or may not need to remove a wall.
 * The height and width of the map can be from 2 to 20.
 * Moves can only be made in cardinal directions; no diagonal moves are allowed.
 */
public class SolutionRMBlock {
    public static class Point implements Comparable<Point> {
        public int x;
        public int y;

        @Override
        public int compareTo(Point o) {
            return (this.x * this.y) - (o.x * o.y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] directions = new int[][]{
        {1, 0}, {0, 1}, {0, -1}, {-1, 0}
    };

    public static int solution(int[][] map) {
        int xMax = map.length;
        int yMax = map[0].length;

        int[][] distances = new int[xMax][yMax];

        LinkedList<Point> toVisit = new LinkedList<>();
        Set<Point> walls = new HashSet<>();

        toVisit.add(new Point(0, 0));
        distances[0][0] = 1;

        while (!toVisit.isEmpty()) {
            Point p = toVisit.pop();
            int distance = distances[p.x][p.y];
            int nextDistance = distance + 1;

            for (int[] direction: directions) {
                Point nextP = add(p, direction);

                // Out of bounds
                if (!inBounds(nextP, xMax, yMax)) {
                    continue;
                }

                // Is wall
                if (map[nextP.x][nextP.y] == 1) {
                    walls.add(nextP);
                    continue;
                }

                // Not visited yet or more optimal distance
                if (distances[nextP.x][nextP.y] == 0 || nextDistance < distances[nextP.x][nextP.y]) {
                    distances[nextP.x][nextP.y] = nextDistance;
                    toVisit.add(nextP);
                }
            }
        }

        int distance = 0;

        for (Point w : walls) {
            int min = 0;
            int max = 0;

            for (int[] direction: directions) {
                Point p = add(w, direction);

                // Out of bounds
                if (!inBounds(p, xMax, yMax)) {
                    continue;
                }

                int d = distances[p.x][p.y];

                if (d == 0) {
                    continue;
                }

                if (min == 0 || min > d) {
                    min = d;
                }

                if (max == 0 || max < d) {
                    max = d;
                }
            }

            int newDistance = max - min - 2;

            if (newDistance > distance) {
                distance = newDistance;
            }
        }

        return distances[xMax - 1][yMax - 1] - distance;
    }

    public static Point add(Point p, int[] v) {
        return new Point(p.x + v[0], p.y + v[1]);
    }

    public static boolean inBounds(Point p, int xMax, int yMax) {
        return (p.x >= 0 && p.x< xMax & p.y >= 0 && p.y < yMax);
    }

    public static void printDistances(int[][] distances) {
        for (int[] row : distances) {
            for (int d : row) {
                System.out.printf("%3d", d);
            }
            System.out.println();
        }
    }

    public static void printWalls(Iterable<Point> walls) {
        for (Point p : walls) {
            System.out.printf("[%d,%d] ", p.x, p.y);
        }
    }
}
