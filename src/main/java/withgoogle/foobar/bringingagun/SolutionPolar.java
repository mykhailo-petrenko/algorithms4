package withgoogle.foobar.bringingagun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SolutionPolar {
    public static void main(String[] args) {
//        System.out.println( solution(new int[]{3,2}, new int[]{1,1}, new int[]{2,1}, 4) );
        System.out.println( solution(new int[] {4, 4}, new int[] {1, 1}, new int[] {3, 3}, 10) );
    }

    public static int PRECISION = 10000000;

    public static int angle(int x, int y) {
        return (int)(Math.atan2(y, x) * PRECISION);
    }

    public static int magnitude2(int x, int y) {
        return (x * x) + (y * y);
    }

    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {
//        LaserDraw debug = new LaserDraw(dimensions, distance);

        int R2 = distance * distance;

        double width = dimensions[0];
        double height = dimensions[1];

        int NX_MAX = (int)Math.ceil(distance / width);
        int NY_MAX = (int)Math.ceil(distance / height);

        Map<Integer, Integer> myReflections = new HashMap<>();
        Set<Integer> guardDirections = new HashSet<>();
        List<int[]> guardReflectionsRaw = new ArrayList<>();

        int direction, magnitude, prevMagnitude;

        for (int xn = -NX_MAX; xn <= NX_MAX + 1; xn++) {
            int xMe, yMe;
            int xGuard, yGuard;

            if (xn % 2 == 0) {
                xMe = xn * dimensions[0] - 2 * your_position[0];
                xGuard = xn * dimensions[0] - guard_position[0] - your_position[0];
            } else {
                xMe = (xn - 1) * dimensions[0];
                xGuard = (xn - 1) * dimensions[0] + guard_position[0] - your_position[0];
            }


            for (int yn = -NY_MAX; yn <= NY_MAX + 1; yn++) {
                if (yn % 2 == 0) {
                    yMe = yn * dimensions[1] - 2 * your_position[1];
                    yGuard = yn * dimensions[1] - guard_position[1] - your_position[1];
                } else {
                    yMe = (yn - 1) * dimensions[1] ;
                    yGuard = (yn - 1) * dimensions[1] + guard_position[1] - your_position[1];
                }

                if (xn != 1 || yn != 1) {
                    magnitude = magnitude2(xMe, yMe);
                    if (magnitude <= R2) {
                        direction = angle(xMe, yMe);

//                        debug.me(xMe, yMe);

                        prevMagnitude = myReflections.getOrDefault(direction, Integer.MAX_VALUE);

                        if (magnitude < prevMagnitude) {
                            myReflections.put(direction, magnitude);
                        }
                    }
                }


                magnitude = magnitude2(xGuard, yGuard);
                if (magnitude <= R2) {
//                    debug.guard(xGuard, yGuard);
                    guardReflectionsRaw.add(new int[]{xGuard, yGuard, magnitude});
                }
            }
        }

        for (int[] guard: guardReflectionsRaw) {
            direction = angle(guard[0], guard[1]);

            if (guardDirections.contains(direction)) {
                continue;
            }

            magnitude = myReflections.getOrDefault(direction, 0);

            if (magnitude > 0 && guard[2] > magnitude) {
                continue;
            }

//            debug.ray(guard[0], guard[1]);

            guardDirections.add(direction);
        }

        return guardDirections.size();
    }
}
