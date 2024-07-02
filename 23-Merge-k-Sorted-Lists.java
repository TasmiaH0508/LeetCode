/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Comparator<Integer[]> c = new Comparator<>(){
            @Override
            public int compare(Integer[] a, Integer[] b) {
                return Integer.compare(a[0], b[0]);
            }
        };
        Queue<Integer[]> pq = new PriorityQueue<>(c);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(new Integer[]{lists[i].val, i});
                lists[i] = lists[i].next;
            }
        }
        ListNode res = new ListNode(0);
        ListNode head = res;
        while (!pq.isEmpty()) {
            Integer[] min = pq.poll();
            res.next = new ListNode(min[0]);
            res = res.next;
            int index = min[1];
            if (lists[index] != null) {
                pq.add(new Integer[]{lists[index].val, index});
                lists[index] = lists[index].next;
            } else {
                int j = index + 1;
                if (j >= lists.length) {
                    j = 0;
                }
                while (j != index) {
                    if (lists[j] != null) {
                        pq.add(new Integer[]{lists[j].val, j});
                        lists[j] = lists[j].next;
                        break;
                    } else {
                        j++;
                        if (j >= lists.length) {
                            j = 0;
                        }
                    }
                }
                if (j == index) {
                    break;
                }
            }
        }
        while (!pq.isEmpty()) {
            Integer[] curr = pq.poll();
            res.next = new ListNode(curr[0]);
            res = res.next;
        }
        return head.next;
    }
}