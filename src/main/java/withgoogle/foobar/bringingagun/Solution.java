package withgoogle.foobar.bringingagun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Bringing a Gun to a Guard Fight
 * ===============================
 *
 * Uh-oh - you've been cornered by one of Commander Lambdas elite guards! Fortunately,
 * you grabbed a beam weapon from an abandoned guard post while you were running through the station,
 * so you have a chance to fight your way out.
 * But the beam weapon is potentially dangerous to you as well as to the elite guard:
 * its beams reflect off walls, meaning you'll have to be very careful where you shoot to avoid bouncing a shot toward yourself!
 *
 * Luckily, the beams can only travel a certain maximum distance before becoming too weak to cause damage.
 * You also know that if a beam hits a corner, it will bounce back in exactly the same direction.
 * And of course, if the beam hits either you or the guard, it will stop immediately (albeit painfully).
 *
 * Write a function solution(dimensions, your_position, guard_position, distance)
 * that gives an array of 2 integers of the width and height of the room,
 * an array of 2 integers of your x and y coordinates in the room,
 * an array of 2 integers of the guard's x and y coordinates in the room,
 * and returns an integer of the number of distinct directions that you can fire to hit the elite guard,
 * given the maximum distance that the beam can travel.
 *
 * The room has integer dimensions [1 < x_dim <= 1250, 1 < y_dim <= 1250].
 * You and the elite guard are both positioned on the integer lattice at different distinct positions (x, y)
 * inside the room such that [0 < x < x_dim, 0 < y < y_dim].
 * Finally, the maximum distance that the beam can travel before becoming harmless will be given as an integer 1 < distance <= 10000.
 *
 * For example, if you and the elite guard were positioned in a room with dimensions [3, 2],
 * your_position [1, 1],
 * guard_position [2, 1],
 * and a maximum shot distance of 4,
 * you could shoot in seven different directions to hit the elite guard (given as vector bearings from your location):
 * [1, 0], [1, 2], [1, -2], [3, 2], [3, -2], [-3, 2], and [-3, -2].
 *
 * As specific examples, the shot at bearing [1, 0] is the straight line horizontal shot of distance 1,
 * the shot at bearing [-3, -2] bounces off the left wall and then the bottom wall before hitting the elite guard with a total shot distance of sqrt(13),
 * and the shot at bearing [1, 2] bounces off just the top wall before hitting the elite guard with a total shot distance of sqrt(5).
 *
 * -- Java cases --
 * Input:
 * Solution.solution([3,2], [1,1], [2,1], 4)
 * Output:
 *     7
 *
 * Input:
 * Solution.solution([300,275], [150,150], [185,100], 500)
 * Output:
 *     9
 */
public class Solution {

    public static class Direction {
        public double x;
        public double y;

        public Direction(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Direction)) return false;
            Direction direction = (Direction) o;
            return x == direction.x && y == direction.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static Direction unitVector(int x, int y, double magnitude) {
        return new Direction(x / magnitude, y / magnitude);
    }

    public static int magnitude2(int x, int y) {
        return (x * x) + (y * y);
    }

    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {
        int[] me = your_position;
        int guard[] = guard_position;
        int R = distance;
        int R2 = R * R;
        double W = dimensions[0];
        double H = dimensions[1];
        int NXmax = (int)Math.ceil(R / W);
        int NYmax = (int)Math.ceil(R / H);
        int Xmax = (int)(NXmax * W);
        int Xmin = -Xmax;
        int Ymax = (int)(NYmax * W);
        int Ymin = -Ymax;

        Map<Direction, Double> myReflections = new HashMap<>();
        Set<Direction> guardReflections = new HashSet<>();
        List<int[]> guardReflectionsRaw = new ArrayList<>();


        int xm, xg, ym, yg;
        int myMagnitude2;
        int guardMagnitude2;

        double myMagnitude;
        double guardMagnitude;

        for (int nx = -NXmax; nx <= NXmax; nx++) {
            if (nx % 2 != 0) {
                xm = (nx - 1) * (int) W;
                xg = (nx - 1) * (int) W + guard[0] - me[0];
            } else {
                xm = nx * (int) W- 2*me[0];
                xg = nx * (int) W - guard[0] - me[0];
            }
            for (int ny = -NYmax; ny <= NYmax; ny++) {
                if (ny % 2 != 0) {
                    ym = (ny - 1) * (int) H;
                    yg = (ny - 1) * (int) H + guard[1] - me[1];
                } else {
                    ym = ny * (int) H - 2*me[1];
                    yg = ny * (int) H - guard[1] - me[1];
                }

                if (!(nx == 1 && ny == 1)) {
                    myMagnitude2 = magnitude2(xm, ym);
                    if (myMagnitude2 <= R2) {
                        myMagnitude = Math.sqrt(myMagnitude2);
                        Direction myDirection = unitVector(xm, ym, myMagnitude);

                        if (myReflections.containsKey(myDirection)) {
                            if (myReflections.get(myDirection) > myMagnitude) {
                                myReflections.put(myDirection, myMagnitude);
                            }
                        } else {
                            myReflections.put(myDirection, myMagnitude);
                        }
                    }
                }

                guardMagnitude2 = magnitude2(xg, yg);
                if (guardMagnitude2 <= R2) {
                    guardReflectionsRaw.add(new int[]{xg, yg, guardMagnitude2});
                }
            }
        }

        for (int[] guardRaw: guardReflectionsRaw) {
            guardMagnitude = Math.sqrt(guardRaw[2]);
            Direction guardDirection = unitVector(guardRaw[0], guardRaw[1], guardMagnitude);

            if (guardReflections.contains(guardDirection)) {
                continue;
            }

            if (myReflections.containsKey(guardDirection)) {
                myMagnitude = myReflections.get(guardDirection);

                if (myMagnitude < guardMagnitude) {
                    continue;
                }
            }

            guardReflections.add(guardDirection);
        }

        return guardReflections.size();
    }
}
