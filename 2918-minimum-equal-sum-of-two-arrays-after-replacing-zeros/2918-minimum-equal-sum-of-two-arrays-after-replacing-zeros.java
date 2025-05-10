class Solution {
    public static long minSum(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        int numZeroes1 = 0;
        int numZeroes2 = 0;
        for (int num : nums1) {
            sum1 += num;
            if (num == 0) {
                numZeroes1++;
            }
        }
        for (int num : nums2) {
            sum2 += num;
            if (num == 0) {
                numZeroes2++;
            }
        }

        if (sum1 == sum2) {
            if ((numZeroes1 != 0 && numZeroes2 == 0) || (numZeroes1 == 0 && numZeroes2 != 0)) {
                return -1;
            } else {
                return sum1 + Math.max(numZeroes1, numZeroes2);
            }
        } else {
            if (sum1 > sum2) {
                // Make up the difference first
                int diff = sum1 - sum2;
                int numZeroesNotCoveredInNums2 = diff - numZeroes2; // if negative, sum zeroes in nums2 not covered.
                // Check for additional zeroes in nums1
                if (numZeroes2 == 0) {
                    return -1;
                } else if (numZeroes1 == 0) {
                    if (numZeroesNotCoveredInNums2 < 0) {
                        return -1;
                    } else {
                        return sum1;
                    }
                } else {
                    if (numZeroesNotCoveredInNums2 >= 0) {
                        // all the 0s can be covered already
                        return sum1 + numZeroes1;
                    } else {
                        int numZeroesInNums2ThatNeedToBeCovered = Math.abs(numZeroesNotCoveredInNums2);
                        return sum1 + Math.max(numZeroesInNums2ThatNeedToBeCovered, numZeroes1);
                    }
                }
            } else {
                return minSum(nums2, nums1);
            }
        }
    }
}