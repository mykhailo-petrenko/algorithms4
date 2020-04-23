package graphs.visualization;

import edu.princeton.cs.introcs.StdDraw;
import searching.st.STBinarySearchTree;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class BSTVisualizer {
    private int WIDTH = 1024;
    private int HEIGHT = 800;
    private int INDENT_VERTICAL = 10;
    private int INTERVAL_VERTICAL = 60;
    private int LABEL_OFFSET_X = 0;
    private int LABEL_OFFSET_Y = -15;

    private class VNode {
        String label;
        VNode left;
        VNode right;

        Color color;

        int level;
        int position;

        int parentX;
        int parentY;

        VNode() {
            this.label = null;
            this.left = null;
            this.right = null;
            this.level = 0;
            this.position = 0;
            this.parentX = -1;
            this.parentY = 1;

            this.color = Color.BLACK;
        }

        VNode(String label, VNode left, VNode right, int level, int position, int parentX, int parentY) {
            this.label = label;
            this.left = left;
            this.right = right;
            this.level = level;
            this.position = position;
            this.parentX = parentX;
            this.parentY = parentY;
        }

        void setColor(Color color) {
            this.color = color;
        }
    }

    public BSTVisualizer() {
        init();
    }

    public void draw() {
        StdDraw.clear();

        // @TODO: Init VNode
        VNode root = new VNode();

        bfsVisualize(root);
    }

    private void bfsVisualize(VNode root) {
        Queue<VNode> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            VNode point = q.poll();

            int level = point.level;
            int position = point.position;

            int chunks = (int) Math.pow(2, level) + 1;
            int y = (level * INTERVAL_VERTICAL) + INDENT_VERTICAL;
            int x = (WIDTH / chunks) * (position + 1);

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x, y);

            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.text(x + LABEL_OFFSET_X, y + LABEL_OFFSET_Y, point.label);

            if (point.parentX != -1) {
                StdDraw.setPenColor(Color.GRAY);
                StdDraw.setPenRadius(0.001);
                StdDraw.line(x, y, point.parentX, point.parentY);
            }

            level += 1;
            position *= 2;

            if (point.left != null) {

                q.add(new VNode(node.left, level, position, x, y));
                //
            }

            if (point.right != null) {
                q.add(new VNode(node.right, level, position + 1, x, y));
            }
        }
    }

    public final void setSize(int width, int height) {
        WIDTH = width;
        HEIGHT = height;

        init();
    }

    public final void init() {
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(HEIGHT, 0);
    }

    public static void main(String[] args) {
        Integer[] init = new Integer[] {7, 2, 1, 5, 3, 6, 4, 21, 15, 38, 36, 55, 54, 37};

        BSTVisualizer visualizer = new BSTVisualizer();

        visualizer.draw();
    }
}
