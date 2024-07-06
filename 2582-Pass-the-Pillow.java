class Solution {
    public int passThePillow(int n, int time) {
        int passesToReachEndAndReturn = 2 * (n - 1);
        int passesToReachEitherEnd = n - 1;
        if (time <= passesToReachEndAndReturn) {
            if (time <= passesToReachEitherEnd) {
                return time + 1;
            } else {
                int extra = time - passesToReachEitherEnd;
                return n - extra;
            }
        } else {
            time %= passesToReachEndAndReturn;
            if (time <= passesToReachEitherEnd) {
                return time + 1;
            } else {
                int extra = time - passesToReachEitherEnd;
                return n - extra;
            }
        }
    }
}