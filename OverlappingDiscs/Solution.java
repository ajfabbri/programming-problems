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

    private class TestCase {
    	int[] discRadii;
	int numOverlapPairs;
    }

    private void Test() {

    	TestCase[] cases = new TestCase[] {
		new TestCase() {{
			discRadii = new int[] {0,0,0,1,0,0,1,2,0,0,0};
			numOverlapPairs = 7;
		}},
		new TestCase() {{
			discRadii = new int[] {2,0,0,1,0,0,1,2,0,0,0};
			numOverlapPairs = 10;
		}},
		new TestCase() {{
			discRadii = new int[] {10,0,0,0,0,0,0,0,0,0,0};
			numOverlapPairs = 10;
		}},
		new TestCase() {{
			discRadii = new int[] {5,5,5};
			numOverlapPairs = 3;
		}},
		new TestCase() {{
			discRadii = new int[] {10,0,0,0,0,0,0,0,0,0,0,
				5,5,5};
			numOverlapPairs = 13 + 3 + 4 + 5 + 3;
		}},
		new TestCase() // Last one is big one, make programmatically below
	};

	// make big test case
	int[] subBig = {   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	final int multiplier = 100_000_000;
	final int subSize = subBig.length;
	final int bigSize = subSize * multiplier;
	int bigInput[] = new int[bigSize];
	for (int i = 0; i<multiplier; i++) {
		for (int j = 0; j < subSize; j++) {
			bigInput[i*subSize + j] = subBig[j];
		}
	}

	cases[cases.length-1].discRadii = bigInput;
	cases[cases.length-1].numOverlapPairs = 20 * multiplier;

    	Solution s = new Solution();
	int i = 0;
    	for (TestCase test : cases) {
	    System.out.printf("Test %d: ", i++);
	    int result = s.solution(test.discRadii);
	    if (result != test.numOverlapPairs) {
		    System.out.printf("FAILED (expect %d got %d)\n",
			    test.numOverlapPairs, result);
	    } else {
		    System.out.println("SUCCESS");
	    }
	}

    }

    public static void main(String args[]) {
	
    	new Solution().Test();
    }
}
/*vim tabstop=8 expandtab shiftwidth=4 softtabstop=4 */

