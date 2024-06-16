import java.util.PriorityQueue;
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        Comparator<int[]> c = new Comparator<>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return Integer.compare(-a[1], -b[1]);
                } else {
                    int aNum = a[0];
                    int bNum = b[0];
                    return Integer.compare(-a[1] * bNum, -b[1] * aNum);
                }
            }
        };
        PriorityQueue<int[]> pq = new PriorityQueue<>(c);
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                int[] frac = new int[] {arr[i], arr[j]};
                pq.add(frac);
            }
        }
        int[] res = null;
        for (int i = 0; i < k; i++) {
            res = pq.poll();
        }
        return res;
    }
}
