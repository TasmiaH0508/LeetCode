class Solution {

    Stack<Integer> s = new Stack<>();
    HashMap<Integer, Integer> outgoingArrowsOf = new HashMap<>();
    HashMap<Integer, LinkedList<Integer>> parentOf = new HashMap<>();
    HashSet<Integer> noOutgoingArrows = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // initialise all to no outgoing arrows
        int p = 0;
        while (p < numCourses) {
            noOutgoingArrows.add(p);
            p++;
        }
        // initialise outgoingArrowsOf and parentOf hashmaps. 
        // at the same time, removing the relevant elems from hashset
        for (int i = 0; i < prerequisites.length; i++) {
            int outgoing = prerequisites[i][0];
            int incoming = prerequisites[i][1];
            if (noOutgoingArrows.contains(outgoing)) {
                noOutgoingArrows.remove(outgoing);
                outgoingArrowsOf.put(outgoing, 1);
            } else {
                if (outgoingArrowsOf.containsKey(outgoing)) {
                    Integer f = outgoingArrowsOf.get(outgoing);
                    f++;
                    outgoingArrowsOf.put(outgoing, f);
                }
            }
            if (parentOf.containsKey(incoming)) {
                LinkedList<Integer> ll = parentOf.get(incoming);
                ll.add(outgoing);
            } else {
                LinkedList<Integer> ll = new LinkedList<>();
                ll.add(outgoing);
                parentOf.put(incoming, ll);
            }
        }

        // retrieve elems from hashset and put them in stack
        int pp = 0;
        while (pp < numCourses) {
            if (noOutgoingArrows.contains(pp)) {
                s.push(pp);
                noOutgoingArrows.remove(pp);
            }
            pp++;
        }

        // if stack empty, return false. Else, proceed
        if (s.empty()) {
            return false;
        } else {
            int count = 0;
            while (!s.empty()) {
                Integer curr = s.pop();
                count++;
                if (parentOf.containsKey(curr)) {
                    LinkedList<Integer> ll = parentOf.get(curr);
                    for (Integer i : ll) {
                        if (outgoingArrowsOf.containsKey(i)) {
                            Integer f = outgoingArrowsOf.get(i);
                            f--;
                            if (f == 0) {
                                s.push(i);
                                outgoingArrowsOf.remove(i);
                            } else {
                                outgoingArrowsOf.put(i, f);
                            }
                        }
                    }
                }
            }
            return count == numCourses;
        }
    }

}