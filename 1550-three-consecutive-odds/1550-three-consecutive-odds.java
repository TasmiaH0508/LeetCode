class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            int[] window = new int[3];
            for (int j = 0; j < window.length; j++) {
                int k = i + j;
                window[j] = arr[k];
            }
            if (checkOdd(window)) {
                    return true;
            }
        }
        return false;
    }

    public boolean checkOdd(int[] window) {
        boolean isAllOdd = true;
        for (int i : window) {
            if (i%2 == 0) {
                return false;
            }
        }
        return isAllOdd;
    }
}