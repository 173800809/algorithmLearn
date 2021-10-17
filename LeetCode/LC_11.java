/**
 * @Author lee1.li
 * @Date 2:20 下午 2021/6/21
 **/
public class LC_11 {
    public static void main(String[] args) {

        int[] t1 = {1, 8, 6, 2, 5, 4, 8, 25, 7};
        int[] t2 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] t3 = {1, 1};
        int[] t4 = {4, 3, 2, 1, 4};
        int[] t5 = {1, 2, 1};
        int[] t6 = {1, 2};
        System.out.println(test(t1) == 49);
        System.out.println(test(t2) == 49);
        System.out.println(test(t3) == 1);
        System.out.println(test(t4) == 16);
        System.out.println(test(t5) == 2);
        System.out.println(test(t6) == 1);
    }

    private static int test(int[] height) {
        int len = height.length;
        int result = 0;
        int h = 0;
        int l = 0;

        int[] lMax = new int[len];
        int[] rMax = new int[len];
        lMax[0] = 0;
        for (int i = 1; i < len; i++) {
            if (height[i - 1] > height[lMax[i - 1]]) {
                lMax[i] = i - 1;
            } else {
                lMax[i] = lMax[i - 1];
            }
        }
        l = len - 1 - lMax[len - 1];
        h = height[len - 1] > height[lMax[len - 1]] ? height[lMax[len - 1]] : height[len - 1];
        result = l * h;
        for (int i = len - 1; i >= 0; i--) {
            l = 0;
            int low = lMax[i];

            h = height[i];
            while (height[low] >= h) {
                l = i - low;
                if (low == lMax[low])
                    break;
                low = lMax[low];
            }
            result = Math.max(result, h * l);
        }
        return result;
    }
    /**
     * The O(n) solution with proof by contradiction doesn't look intuitive enough to me. Before moving on, read any example of the algorithm first if you don't know it yet.
     *
     * Here's another way to see what happens in a matrix representation:
     *
     * Draw a matrix where rows correspond to the position of the left line, and columns corresponds to the position of the right line.
     *
     * For example, say n=6. Element at (2,4) would corresponds to the case where the left line is at position 2 and the right line is at position 4. The value of the element is the volume for the case.
     *
     * In the figures below, x means we don't need to compute the volume for that case, because:
     *
     * on the diagonal, the two lines are overlapped;
     * the lower left triangle area of the matrix, the two lines are switched and the case is symmetric to the upper right area.
     * We start by computing the volume at (1,6), denoted by o. Now if the left line is shorter than the right line, then moving the right line towards left would only decrease the volume, so all the elements left to (1,6) on the first row have smaller volume. Therefore, we don't need to compute those cases (crossed by ---).
     *
     *   1 2 3 4 5 6
     * 1 x ------- o
     * 2 x x
     * 3 x x x
     * 4 x x x x
     * 5 x x x x x
     * 6 x x x x x x
     * So we can only move the left line towards right to 2 and compute (2,6). Now if the right line is shorter, all cases below (2,6) are eliminated.
     *
     *   1 2 3 4 5 6
     * 1 x ------- o
     * 2 x x       o
     * 3 x x x     |
     * 4 x x x x   |
     * 5 x x x x x |
     * 6 x x x x x x
     * And no matter how this o path goes, we end up only need to find the max value on this path, which contains n-1 cases.
     *
     *   1 2 3 4 5 6
     * 1 x ------- o
     * 2 x x - o o o
     * 3 x x x o | |
     * 4 x x x x | |
     * 5 x x x x x |
     * 6 x x x x x x
     * Hope this helps. I feel more comfortable seeing things this way.
     */
}
