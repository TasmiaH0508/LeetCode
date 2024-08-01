class Solution {
    public int countSeniors(String[] details) {
        int count = 0;
        for (String d : details) {
            char f = d.charAt(11);
            int fVal = Character.getNumericValue(f);
            char s = d.charAt(12);
            int sVal = Character.getNumericValue(s);
            if (fVal >= 6) {
                if (fVal > 6) {
                    count++;
                } else {
                    if (sVal > 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}