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
import java.util.HashMap;
class Solution {
    public ListNode doubleIt(ListNode head) {
        ListNode headPointer = head;
        ListNode tailPointer = head.next;
        HashMap<ListNode, ListNode> prev = new HashMap<>();
        prev.put(head, null); // only the first node will have null as its parent
        while (tailPointer != null) {
            prev.put(tailPointer, headPointer);
            tailPointer = tailPointer.next;
            headPointer = headPointer.next;
        }

        int carryOver = 0;
        while (true) {
            int newVal = headPointer.val * 2 + carryOver;
            if (prev.get(headPointer) == null) {
                if (newVal < 10) {
                    headPointer.val = newVal;
                } else {
                    headPointer.val = newVal%10;
                    carryOver = newVal - headPointer.val;
                    carryOver /= 10;
                    ListNode newHead = new ListNode(carryOver);
                    newHead.next = headPointer;
                    head = newHead;
                }
                break;
            } else {
                if (newVal < 10) {
                    headPointer.val = newVal;
                    carryOver = 0;
                } else {
                    headPointer.val = newVal%10;
                    carryOver = newVal - headPointer.val;
                    carryOver /= 10;
                }
                headPointer = prev.get(headPointer);
            }
        }
        return head;
    }
}