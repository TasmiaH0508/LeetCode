class Solution {
    public boolean areAlmostEqual(String s1, String s2) {

        Map<Character, Integer> charToFreqS1 = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Character c = s1.charAt(i);
            if (charToFreqS1.containsKey(c)) {
                Integer freq = charToFreqS1.get(c);
                freq++;
                charToFreqS1.put(c, freq);
            } else {
                charToFreqS1.put(c, 1);
            }
        }

        Map<Character, Integer> charToFreqS2 = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            Character c = s2.charAt(i);
            if (charToFreqS2.containsKey(c)) {
                Integer freq = charToFreqS2.get(c);
                freq++;
                charToFreqS2.put(c, freq);
            } else {
                charToFreqS2.put(c, 1);
            }
        }

        System.out.println(charToFreqS2.entrySet());
        System.out.println(charToFreqS1.entrySet());

        for (Character c : charToFreqS1.keySet()) {
            if (charToFreqS2.containsKey(c)) {
                Integer f1 = charToFreqS1.get(c);
                Integer f2 = charToFreqS2.get(c);
                if (!f1.equals(f2)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        int numMismatch = 0;
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                numMismatch++;
            }
        }

        return numMismatch == 2 || numMismatch == 0;
    }
}