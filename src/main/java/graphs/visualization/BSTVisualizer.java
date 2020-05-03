package graphs.visualization;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class BSTVisualizer<N> {
    private int WIDTH = 1280;
    private int HEIGHT = 960;
    private int INDENT_VERTICAL = 10;
    private int INTERVAL_VERTICAL = 60;
    private int LABEL_OFFSET_X = 0;
    private int LABEL_OFFSET_Y = -15;

    public static class VNode<N> {
        String label;
        N left;
        N right;

        Color color;

        int level;
        int position;

        int parentX;
        int parentY;

        public VNode() {
            setDefaultProperties();
        }

        public VNode(String label, N left, N right) {
            setDefaultProperties();

            this.label = label;
            this.left = left;
            this.right = right;
        }

        public VNode(String label, N left, N right, Color color) {
            setDefaultProperties();

            this.label = label;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        VNode(String label, N left, N right, int level, int position, int parentX, int parentY) {
            setDefaultProperties();

            this.label = label;
            this.left = left;
            this.right = right;
            this.level = level;
            this.position = position;
            this.parentX = parentX;
            this.parentY = parentY;
        }

        VNode<N> setColor(Color color) {
            this.color = color;

            return this;
        }

        VNode<N> setPositions(int level, int position, int parentX, int parentY) {
            this.level = level;
            this.position = position;
            this.parentX = parentX;
            this.parentY = parentY;

            return this;
        }

        private void setDefaultProperties() {
            this.label = null;
            this.left = null;
            this.right = null;
            this.level = 0;
            this.position = 0;
            this.parentX = -1;
            this.parentY = 1;

            this.color = Color.BLACK;
        }
    }
    public interface NodeTransformer<N> {
        VNode<N> fromDomain(N node);
    }

    private NodeTransformer<N> transformer;

    public BSTVisualizer(NodeTransformer<N> transformer) {
        this.transformer = transformer;
        init();
    }

    public void draw(N node) {
        StdDraw.clear();

        if (node == null) {
            System.out.println("The node is NULL :o(");
            return;
        }

        VNode<N> root = transformer.fromDomain(node);

        bfsVisualize(root);
    }

    private void bfsVisualize(VNode<N> root) {
        Queue<VNode<N>> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            VNode<N> point = q.poll();

            int level = point.level;
            int position = point.position;

            int width = WIDTH / (int) Math.pow(2, level);
            int shift = - (width / 2);
            int y = (level * INTERVAL_VERTICAL) + INDENT_VERTICAL;
            int x = width * (position) - shift;

            StdDraw.setPenColor(point.color);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(x, y);

            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(Color.DARK_GRAY);
            StdDraw.text(x + LABEL_OFFSET_X, y + LABEL_OFFSET_Y, point.label);

            if (point.parentX != -1) {
                StdDraw.setPenColor(point.color);
                StdDraw.setPenRadius(0.001);
                StdDraw.line(x, y, point.parentX, point.parentY);
            }

            level += 1;
            position *= 2;

            if (point.left != null) {
                q.add(transformer.fromDomain(point.left).setPositions(level, position, x, y));
            }

            if (point.right != null) {
                q.add(transformer.fromDomain(point.right).setPositions(level, position + 1, x, y));
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
}
