
/**
 * Solution for codility GenomicRangeQuery
 * @author Aaron Fabbri -- 50 minutes, O(n+m) time, O(n) space
 */

// you can also use imports, for example:
// import java.math.*;
class Solution {
    private static final int A = 1, C = 2, G = 3, T = 4;

    private void precompute(String S,
        int[] nextA, int[] nextC, int[] nextG) {

        int lastA = Integer.MAX_VALUE, lastC = Integer.MAX_VALUE,
            lastG = Integer.MAX_VALUE;
        for (int i = S.length() -1; i >= 0; i--) {
            char x = S.charAt(i);
            switch (x) {
                case 'A' :
                    lastA = i;
                    break;
                case 'C' :
                    lastC = i;
                    break;
                case 'G' :
                    lastG = i;
                    break;
                case 'T' :
                    break;
                default:
                    throw new RuntimeException("Invalid input");
            }
            nextA[i] = lastA;
            nextC[i] = lastC;
            nextG[i] = lastG;
        }
    }

    public int[] solution(String S, int[] P, int[] Q) {
        final int N = S.length();
        int[] nextA = new int[N],
            nextC = new int[N], nextG = new int[N];

        precompute(S, nextA, nextC, nextG);

        int M = P.length;
        int[] results = new int[M];
        for (int i = 0; i < M; i++) {
            if (nextA[P[i]] <= Q[i]) {
                results[i] = A;
            } else if (nextC[P[i]] <= Q[i]) {
                results[i] = C;
            } else if (nextG[P[i]] <= Q[i]) {
                results[i] = G;
            } else {
                results[i] = T;
            }
        }
        return results;
    }
}
/*vim tabstop=8 expandtab shiftwidth=4 softtabstop=4 */

