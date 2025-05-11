class Solution {
    public int countDays(int days, int[][] meetings) {
        Comparator<int[]> c = (a, b) -> Integer.compare(a[0], b[0]);
        Queue<int[]> pq = new PriorityQueue<>(c);

        for (int[] interval : meetings) {
            pq.add(interval);
        }

        if (pq.isEmpty()) {
            return days;
        }

        List<int[]> ranges = new LinkedList<>();
        int[] prevRange = pq.poll();
        while (!pq.isEmpty()) {
            int[] meeting = pq.poll();
            int currMeetingStart = meeting[0];
            int currMeetingEnd = meeting[1];

            int prevRangeEnd = prevRange[1];

            if (currMeetingStart > prevRangeEnd) {
                ranges.add(prevRange);
                prevRange = meeting;
            } else {
                int end = Math.max(currMeetingEnd, prevRangeEnd);
                prevRange[1] = end;
            }
        }
        ranges.add(prevRange);
        
        int totalDaysWithMeetings = 0;
        for (int[] range : ranges) {
            int numDays = range[1] - range[0] + 1;
            totalDaysWithMeetings += numDays;
        }
        int daysWithoutMeetings = days - totalDaysWithMeetings;
        return daysWithoutMeetings;
    }
}