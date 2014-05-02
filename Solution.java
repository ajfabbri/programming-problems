// you can also use imports, for example:
import java.math.*;
import java.util.*;

class Solution {
    // Each disc covers interval [i-A[i], i+A[i]] 
    private static class Interval implements Comparable<Interval> {
        int start;
        int end;
        public int compareTo(Interval b) {
            return (this.start - b.start);
        }
        public Interval(int a, int b) {
            start = a;
            end = b;
        }
    }
    
    /**
     * Find index of where 'val' fits in the list
     * intervals[a], ..., intervals[b], sorted by interval.start. 
     * Start search at intervals[cur].
     */
    private static int findStartVal(List<Interval> intervals, int val,
            int a, int b, int cur)
    {
        Interval i = intervals.get(cur);
        if (val >= i.start) {
            if (cur == b || val < intervals.get(cur+1).start) {
                return cur;
            } else {
                return findStartVal(intervals, val, cur+1, b, (cur+1+b)/2);
            }
        } else {
            return findStartVal(intervals, val, a, cur-1, (a+cur-1)/2);
        }
    }
    
    public int solution(int[] A) {
        // write your code in Java SE 7
        ArrayList<Interval> intervals = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            // could do sorted insert, or use a sorted list
            intervals.add(new Interval(i-A[i], i+A[i]));
        }
        
        Collections.sort(intervals);
        
        int intersectCount = 0;
        int numIntervals = intervals.size();
        
        for (int j = 0; j < numIntervals; j++) {
            Interval interval = intervals.get(j);
            
            int intervalIndex = findStartVal(intervals, interval.end,
                    j, numIntervals-1, (j+numIntervals-1)/2);
            
            intersectCount += intervalIndex - j;
            if (intersectCount > 10_000_000) {
                return -1;
            }
            
        }
        
        return intersectCount;
    }
}

