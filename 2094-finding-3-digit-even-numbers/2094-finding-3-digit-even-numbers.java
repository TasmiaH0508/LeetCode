class Solution {
    /**
     * If 0, it can only appear as at most 2 trailing digits.
     * If even, it can appear at most 3 times.
     * If odd, it can appear at most 2 times.
     */
    Map<Integer, Integer> freq = new HashMap<>();

    public int[] findEvenNumbers(int[] digits) {
        Arrays.sort(digits);
        List<Integer> startingIndices = new LinkedList<>();
        Set<Integer> evenDigits = new HashSet<>();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] % 2 == 0) {
                evenDigits.add(digits[i]);
            }
            if (!freq.containsKey(digits[i])) {
                freq.put(digits[i], 1);
                startingIndices.add(i);
            } else {
                int f = freq.get(digits[i]);
                f++;
                freq.put(digits[i], f);
            }
        }
        
        List<Integer> res = new LinkedList<>();
        for (int i : startingIndices) {
            if (digits[i] == 0) {
                continue;
            }
            int startingDigit = digits[i];
            for (int j : startingIndices) {
                List<int[]> numbersThatCanBeFormed = new LinkedList<>();
                int firstTrailingDigit = digits[j];
                for (Integer evenDigit : evenDigits) {
                    int[] numberToForm = new int[3];
                    numberToForm[0] = startingDigit;
                    numberToForm[1] = firstTrailingDigit;
                    numberToForm[2] = evenDigit;
                    numbersThatCanBeFormed.add(numberToForm);
                }
                // check if the numbers is valid
                for (int[] numberThatCanBeFormed : numbersThatCanBeFormed) {
                    if (isValid(numberThatCanBeFormed)) {
                        int validNumFormed = 100 * numberThatCanBeFormed[0] 
                                + 10 * numberThatCanBeFormed[1] 
                                + numberThatCanBeFormed[2];
                        res.add(validNumFormed);
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