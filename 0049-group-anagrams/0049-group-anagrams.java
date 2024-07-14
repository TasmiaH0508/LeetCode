class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        String[] stringsInSortedFormat = new String[strs.length];
        for (int i = 0; i < strs.length; i++) {
            stringsInSortedFormat[i] = copyStringAndSort(strs[i]);
        }
        Map<String, List<String>> anagrams = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (anagrams.containsKey(stringsInSortedFormat[i])) {
                List<String> ll = anagrams.get(stringsInSortedFormat[i]);
                ll.add(strs[i]);
            } else {
                List<String> ll = new LinkedList<>();
                ll.add(strs[i]);
                anagrams.put(stringsInSortedFormat[i], ll);
            }
        }
        List<List<String>> res = new LinkedList<>();
        for (String s : anagrams.keySet()) {
            res.add(anagrams.get(s));
        }
        return res;
    }

    private String copyStringAndSort(String s) {
        StringBuilder res = new StringBuilder();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        for (char c : arr) {
            res.append(c);
        }
        return res.toString();
    }
}