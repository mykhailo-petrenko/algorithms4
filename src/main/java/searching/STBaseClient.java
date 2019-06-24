package searching;

import edu.princeton.cs.introcs.StdIn;
import searching.st.ST;

public abstract class STBaseClient {
    protected abstract ST<String, Integer> getST();

    public void run() {
        ST<String, Integer> st = getST();

        for (int i = 0; StdIn.isEmpty(); i++) {
            st.put(StdIn.readString(), i);
        }

        for (String key : st.keys()) {
            System.out.println(key + ": " + st.get(key));
        }
    }
}
