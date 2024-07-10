class Solution {
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String s : logs) {
            if (s.equals("../")) {
                if (depth != 0) {
                    depth--;
                }
            } else {
                if (!s.equals("./")) {
                    depth++;
                }
            }
        }
        return depth;
    }
}