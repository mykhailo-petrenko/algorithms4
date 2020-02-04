package sorting.priorityqueue;

import edu.princeton.cs.introcs.In;

/**
 * Merges together several sorted input streams into one sorted output stream
 * `IndexPriorityQueueMin` demo client
 */
public class MultiwayStreamSorting {

    public static void main(String[] args) {
        int streamsCount = args.length;

        if (streamsCount == 0) {
            System.out.printf("Please specify at list one filename");
            return;
        }

        In[] streams = new In[args.length];

        for (int i = 0; i < args.length; i++) {
            streams[i] = new In(args[i]);
        }

        IndexPriorityQueueMin<String> queue = new IndexPriorityQueueMin<>(streamsCount);

        for (int i = 0; i < streamsCount; i++) {
            if (streams[i].isEmpty()) {
                continue;
            }

            queue.insert(i, streams[i].readString());
        }


        while (!queue.isEmpty()) {
            String item = queue.peekKey();

            // Next sorted item processing
            System.out.print(" " + item);

            int index = queue.dequeue();

            if (streams[index].isEmpty()) {
                continue;
            }

            queue.insert(index, streams[index].readString());
        }
    }

}
