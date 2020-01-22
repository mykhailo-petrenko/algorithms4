package searching;

import searching.st.ST;
import searching.st.STLinkedList;

public class STLinkedListClient extends STClient {
    protected ST<String, Integer> getST() {
        return new STLinkedList<>();
    }

    public static void main(String[] args) {
        STClient client = new STLinkedListClient();

        client.run();
    }
}
