package training.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 */
public class FirstUniqueCharacter {

    /**
     * Better to use  String methods: .charAt() and .size - to not convert string to char[].
     * @param s String
     * @return index
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        char[] chars = s.toCharArray();
        char symbol = 'N';

        for (int i = chars.length - 1; i >= 0; i--) {
            int c = 0;
            if (dict.containsKey(chars[i])) {
                c = dict.get(chars[i]);
            } else {
                dict.put(chars[i], 0);
            }

            dict.put(chars[i], ++c);

            if (c == 1) {
                symbol = chars[i];
            } else if (symbol == chars[i]) {
                symbol = 'N';
            }
        }

        return s.indexOf(symbol);
    }

    public static void main(String[] args) {
        FirstUniqueCharacter f = new FirstUniqueCharacter();

        System.out.println(f.firstUniqChar("helloo"));
    }
}
