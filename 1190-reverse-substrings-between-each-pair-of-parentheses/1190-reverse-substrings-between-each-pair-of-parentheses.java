class Solution {
    public String reverseParentheses(String s) {
        char[] arr = s.toCharArray();
        Stack<Integer> openBrac = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '(') {
                openBrac.push(i);
                arr[i] = '0';
            } else if (c == ')') {
                int lastOpenBracAt = openBrac.pop();
                reverse(arr, lastOpenBracAt + 1, i - 1);
                arr[i] = '0';
            }
        }
        StringBuilder res = new StringBuilder();
        for (Character c : arr) {
            if (c != '0') {
                res.append(c);
            }
        }
        return res.toString();
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            if (arr[start] == '0' && arr[end] == '0') {
                start++;
                end--;
            } else if (arr[start] == '0') {
                start++;
            } else if (arr[end] == '0') {
                end--;
            } else {
                char temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }
    }
}