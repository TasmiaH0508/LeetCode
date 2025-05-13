class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = 1_000_000_007;
        int[] freqMap = new int[26];

        for (char c : s.toCharArray()) {
            freqMap[c - 'a']++;
        }

        for (int i = 0; i < t; i++) {
            int[] newFreqMap = new int[26];
            newFreqMap[0] = freqMap[25];
            for (int j = 1; j < 26; j++) {
                newFreqMap[j] = freqMap[j - 1];
            }
            freqMap = newFreqMap;
            // prevents overflow error here
            freqMap[1] = (freqMap[1] + freqMap[0]) % MOD;
        }

        long sum = 0;
        for (int val : freqMap) {
            sum = (sum + val) % MOD;
        }

        return (int) sum;
    }
}