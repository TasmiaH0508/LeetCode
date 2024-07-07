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
    /**
    Method:
    1. Initialise the priority queue of integer arrays of size 2. The integer arrays 
    are as such: [val, index of list in lists array]. The priority queue should 
    poll integer arrays such that "val" is the smallest across all arrays in the 
    priority queue. 
    2. Poll an integer array(curr) from the priority queue. For curr:
    - Immediately add curr[0] to the listnode result. 
    - Check lists[curr[1]] to see if the list is null. If not null, 
    add to the priority queue in the manner stated above. 

    Naiive solution:
    1.Run through all k lists to find the smallest value. 
    2.Add the smallest value found to the front of the list. 
    3.Recurse until all the lists are empty. 
    Total runtime = O(nk), where n is the total number of elements across k lists. 

    Why is it important to track "index of list in lists array"?
    - We must make sure that what is being added to the listnode result is the smallest
    across all k lists(see naiive solution)
    - By removing an element from a particular list and adding back another element from 
    the same list, we are able to ensure that anytime an array,arr, is polled from 
    the priority queue, val = arr[0] is the smallest across all lists. 
    - In fact, the priority queue is implemented to help speed up step 1 of the naiive 
    solution. 
    */
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
            }
        }
        return head.next;
    }
}