/**
 * @Author lee1.li
 * @Date 9:58 上午 2021/6/4
 **/

/**
 * dp:
 * 1. 概念：后一个状态依赖前一个状态，是维持一个链式状态
 */
public class LC_10 {
    public static void main(String[] args) {
        System.out.println(isMatch2("aaaaa", "a*") == true);
        System.out.println(isMatch2("aa", "a*") == true);
        System.out.println(isMatch2("ab", ".*") == true);
        System.out.println(isMatch2("aab", "c*a*b") == true);
        System.out.println(isMatch2("mississippi", "mis*is*p*.") == false);
        System.out.println(isMatch2("aaa", "ab*a"));
    }

    /**
     * 首行是匹配空S的，所以#*是依赖前值
     * <p>
     * 1 0 0 0
     * 0 0 0 0
     * 0 0 0 0
     * 0 0 0 0
     * 0 0 # 0
     * 0 0 0 0
     */
    public static boolean isMatch2(String s, String p) {
        // 考虑边界情况(其中一个为null,允许空""不允许Null)
        if (s == null || p == null)
            return false;
        int m = s.length();
        int n = p.length();
        // M[i][j]的意思就是，s.subString(0,i)和p.subString(0,j)是否可以匹配。如果可以匹配则为true
        boolean[][] M = new boolean[m + 1][n + 1];
        M[0][0] = true;
        // 如果s是空串的情况，p必须是#*#*#*#*这种类型
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && M[0][j - 2]) {
                M[0][j] = true;
            }
        }
        // 遍历方式是以s为主
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                // curS和curP已使用。（所以取i-1和j-1的前置状态）
                if (curS == curP || curP == '.') {
                    M[i][j] = M[i - 1][j - 1];
                } else if (curP == '*') {// 如果当前curP是'*'
                    char preP = p.charAt(j - 2);
                    // curS一定不匹配preP+curP,则不使用#*
                    if (preP != '.' && preP != curS) {
                        M[i][j] = M[i][j - 2];
                    } else {// curS可以匹配preP+curP
                        // M[i][j - 2]，i;j不使用#*，例如s="abcd",p=".*.*.*.*"
                        // M[i - 1][j - 2],i使用#*,但(i-1)不使用#*
                        // M[i - 1][j],i使用#*,(i-1)也使用#*
                        M[i][j] = M[i][j - 2] || M[i - 1][j - 2] || M[i - 1][j];
                    }
                }
            }
        }
        return M[m][n];
    }

    public boolean isMatch1(String s, String p) {
        // corner case
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();

        // M[i][j] represents if the 1st i characters in s can match the 1st j characters in p
        boolean[][] M = new boolean[m + 1][n + 1];

        // initialization:
        // 1. M[0][0] = true, since empty string matches empty pattern
        M[0][0] = true;

        // 2. M[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string

        // 3. M[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex :P,
        // and for this case we need to check manually:
        // as we can see, the length of pattern should be even && the character at the even position should be *,
        // thus for odd length, M[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2.
        // and notice that the length of repeat sub-pattern #* is only 2, we can just make use of M[0][j - 2] rather than scanning j length each time
        // for checking if it matches #*#*#*#*.
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && M[0][j - 2]) {
                M[0][j] = true;
            }
        }

        // Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), M[i][j] = M[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', M[i][j] = M[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. M[i][j] = M[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then M[i][j] = M[i][j - 2]
        //      2.2 if counted as one, then M[i][j] = M[i - 1][j - 2]
        //      2.3 if counted as multiple, then M[i][j] = M[i - 1][j]

        // recap:
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i - 1][j - 1]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i][j - 2]
        // M[i][j] = M[i - 1][j - 2]
        // M[i][j] = M[i - 1][j]
        // Observation: from above, we can see to get M[i][j], we need to know previous elements in M, i.e. we need to compute them first.
        // which determines i goes from 1 to m - 1, j goes from 1 to n + 1
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    M[i][j] = M[i - 1][j - 1];
                } else if (curP == '*') {
                    char preCurP = p.charAt(j - 2);
                    if (preCurP != '.' && preCurP != curS) {
                        M[i][j] = M[i][j - 2];
                    } else {
                        M[i][j] = (M[i][j - 2] || M[i - 1][j - 2] || M[i - 1][j]);
                    }
                }
            }
        }

        return M[m][n];
    }
}
