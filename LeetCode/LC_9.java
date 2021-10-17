/**
 * @Author lee1.li
 * @Date 9:20 上午 2021/6/4
 **/
public class LC_9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(101));
    }

    public static boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int t = x;
        int x1 = 0;
        while (t > 0) {
            x1 = 10 * x1 + t%10;
            t /= 10;
        }
        return x == x1;
    }
}
