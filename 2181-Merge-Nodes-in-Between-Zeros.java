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
    public ListNode mergeNodes(ListNode head) {
        int sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while (head != null) {
            if (head.val == 0) {
                head = head.next;
                if (sum != 0) {
                    res.next = new ListNode(sum);
                    res = res.next;
                }
                sum = 0;
            } else {
                sum += head.val;
                head = head.next;
            }
        }
        return dummy.next;
    }
}