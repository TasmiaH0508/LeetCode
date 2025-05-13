class Solution {
    /**
     * If 0, it can only appear as at most 2 trailing digits.
     * If even, it can appear at most 3 times.
     * If odd, it can appear at most 2 times.
     */
    Map<Integer, Integer> freq = new HashMap<>();

    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> evenNumbers = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            evenNumbers.add(i * 2);
        }
        for (int i = 0; i < 10; i++) {
            freq.put(i, 0);
        }
        for (int digit : digits) {
            int f = freq.get(digit);
            f++;
            freq.put(digit, f);
        }
        for (int f = 0; f < 10; f++) {
            if (freq.get(f) == 0) {
                evenNumbers.remove(f);
                freq.remove(f);
            }
        }

        List<Integer> res = new LinkedList<>();
        for (int leadingDigit : freq.keySet()) {
            if (leadingDigit == 0) {
                continue;
            }
            for (int firstTrailingDigit : freq.keySet()) {
                List<int[]> ll = new LinkedList<>();
                for (int evenLastDigit : evenNumbers) {
                    int[] num = new int[3];
                    num[0] = leadingDigit;
                    num[1] = firstTrailingDigit;
                    num[2] = evenLastDigit;
                    ll.add(num);
                }
                for (int[] num : ll) {
                    if (isValid(num)) {
                        int numVal = 100 * num[0] + 10 * num[1] + num[2];
                        res.add(numVal);
                    }
                }
            }
        }

        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }
    
    private boolean isValid(int[] numArr) {
        Map<Integer, Integer> fMap = new HashMap<>();
        for (int num : numArr) {
            if (!fMap.containsKey(num)) {
                fMap.put(num, 1);
            } else {
                int f = fMap.get(num);
                f++;
                fMap.put(num, f);
            }
        }
        
        for (int i : fMap.keySet()) {
            int maxTimesNumCanAppear = this.freq.getOrDefault(i, 0);
            if (fMap.get(i) > maxTimesNumCanAppear) {
                return false;
            }
        }
        return true;
    }
}