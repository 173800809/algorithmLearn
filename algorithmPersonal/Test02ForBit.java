/**
 * 位运算：八王后问题的网址：https://www.cxyxiaowu.com/8990.html
 */


import java.util.HashMap;
import java.util.Map;

/**
 * (~:二进制数取反，0变1，1变0)
 * (^:异或符合，两个数为0或1的时候值才为1)
 *
 * 1.判断整数的奇偶性
     *  if((x & 1) == 0){
     *      偶数：二进制的最低位为0
     *  }else{
     *      奇数
     *  }
 *
 * 2.判断第n位(n从0开始)是否为1（由上面方法改进来）
     * if(x & (1 << n)){
     *     第n位为1
     * }else{
     *     第n位不为1
     * }
 *
 * 3.将第n位(n从0开始)设置为1
     * y = x | (1 << n)
 *
 * 4.将第n位(n从0开始)设置为0
     * y = x & ~(1 << n)
 *
 * 5.将第n位(n从0开始)的值取反
     * y = x ^ (1 << n)
 *
 * 6.将最右边的1设为0
     * y = x & (x - 1)
 * 7.将最右边的第一个1保留，其余位置零
 *     diff &= -diff
 *
 * 7.java中自带的位方法
     * int Integer.bitCount():统计二进制中1的数量
     * int Integer.highestOneBit():获得最高位
     * String toBinaryString(int i):转换为二进制表示的字符串
 */
public class Test02ForBit {
    public static void main(String[] args) {
        Integer i = new Integer(79);
        System.out.println(i.byteValue());

//        reverseBits
    }

    //1.统计两个数的二进制表示有多少位不同（两个十进制数转为二进制后进行比较）
    //1.1从低位统计到高位（进制低的为低位）
    public static int hammingDistance(int x, int y){
        int z = x ^ y;//得出不同位至1，最后得出的十进制数字
        int cnt = 0;
        while(z != 0){//从低位统计到高位
            if((z & 1) == 1)//统计最低位是否为1
                cnt ++;
            z = z >> 1;//右移，整体向右移，高位置0
        }
        return cnt;
    }
    //1.2从高位统计到低位
    public static int hammingDistance1(int x, int y){
        int z = x ^ y;
        int cnt = 0;
        while(z != 0){
            z &= (z - 1);
            cnt++;
        }
        return cnt;
    }
    //1.3使用java自带方法
    public static int hammingDistance2(int x, int y){
        return Integer.bitCount(x ^ y);
    }

    //2.数组中唯一"一个"不重复的元素（其他元素都是出现两次）
    public static int singleNumber(int[] nums){
        int ret = 0;
        for(int n : nums)
            ret = ret ^ n;//因为其他的数字都是出现两次，所以两次异或之后就为0
        return ret;
    }

    //3.找出数组中缺失的那个数（数字长度为n，数组元素在0-n之间<没有重复元素>，所以一定"有且只有一个"缺失元素）
    public static int missingNumber(int[] nums){
        int ret = 0;
        for(int i = 0; i < nums.length; i++)//最多使用n个循环，因为nums中只有n个数
            ret = ret ^ i ^ nums[i];
        return ret ^ nums.length;//最后与最后一个数进行异或比较
    }

    //4.数组中不重复的"两个"元素（其他的数都是出现两次）
    public static int[] singleNumber1(int[] nums){
        int diff = 0;
        for(int num : nums)
            diff ^= num;
        diff &= -diff;//得到最右一位（两个不同元素的最右差异位）
        int[] ret = new int[2];
        for(int num : nums){
            if((num & diff) == 0)//因为其他元素都是出现两次，所以在此两者判断条件中的异或结果都是0
                ret[0] ^= num;
            else
                ret[1] ^= num;
        }
        return ret;
    }

    //5翻转一个数的比特位(二进制下首尾交换)
    //5.1常规方法
    public static int reverseBits(int n){
        int ret = 0;
        for(int i = 0; i < 32; i++){
            ret <<= 1;
            ret |= (n & 1);
            n >>>= 1;
        }
        return ret;
    }
    //5.2优化方法
    private static Map<Byte, Integer> cache = new HashMap<>();
    public static int reverseBits1(int n){
        int ret = 0;
        for(int i = 0; i < 4; i++){
            ret <<= 8;
            ret |= reverseByte((byte)(n & 0b11111111));
            n >>= 8;
        }
        return ret;
    }
    private static int reverseByte(byte b){
        if(cache.containsKey(b))
            return cache.get(b);
        int ret = 0;
        byte t = b;
        for(int i = 0; i < 8; i++){
            ret <<= 1;
            ret |= t & 1;
            t >>= 1;
        }
        cache.put(b, ret);
        return ret;
    }

    //6.不用额外变量交换两个整数（使用异或的特性）
    public static void swapTwoNumbers(int a, int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    //7.判断一个数是不是2的n次方
    //7.1使用java自带方法（2的n次方数，的二进制表示下只有一个1）
    public static boolean isPowerOfTwo(int n){
        return n > 0 && Integer.bitCount(n) == 1;
    }
    //7.2利用1000 & 0111 == 0（即：n & (n - 1) == 0）这种性质
    public static boolean isPowerOfTwo1(int n){
        return n > 0 && (n & (n - 1)) == 0;
    }

    //8判断一个数是不是4的n次方
    //8.1使用"有且只有一个"奇数位为1
    public static boolean isPowerOfFour(int num){
        return num > 0 && (num & (num - 1)) == 0 && (num & 0b01010101010101010101010101010101) != 0;
    }
    //8.2使用正则表达式进行匹配
    public static boolean isPowerOfFour1(int num){
        return Integer.toString(num, 4).matches("10*");
    }

    //9.判断一个数的二进制表示是否"不会"出现连续的0和1
    public static boolean hasAlternatingBits(int n){
        int a = (n ^ (n >> 1));//如果满足true，则啊a的值为1111111
        return (a & (a + 1)) == 0;//判断a的二进制数是否全部为1
    }

    //10.求一个数的补码（不考虑二进制表示中的首0部分）
    //10.1求掩码
    public static int findComplement(int num){
        if(num == 0)
            return 1;
        int mask = 1 << 30;
        while((num & mask) == 0)
            mask >>= 1;
        mask = (mask << 1) - 1;
        return num ^ mask;
    }
    //10.1使用java自带方法
    public static int findComplement1(int num){
        if(num == 0)
            return 1;
        int mask = Integer.highestOneBit(num);
        mask = (mask << 1) - 1;
        return num ^ mask;
    }
    //10.2把10000000扩展成11111111
    public static int findComplement2(int num){
        int mask = num;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        mask |= mask >> 16;
        return (mask ^ num);
    }

    //11.实现整数的加法(坐进，加上进位)
    public static int getSum(int a, int b){
        return b == 0 ? a : getSum((a ^ b), (a & b) << 1);
    }

    //12.字符串数组最大乘积（两个字符串长度乘积最大值<这两个字符串不能含有相同字符>）
    public static int maxProduct1(String[] words){
        int n = words.length;
        String s1 = "14fv";

        int[] val = new int[n];
        for(int i = 0; i < n; i++)
            for(char c : words[i].toCharArray())
                val[i] |= 1 << (c - 'a');

        int ret = 0;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                if((val[i] & val[j]) == 0)
                    ret = Math.max(ret, words[i].length() * words[j].length());
        return ret;
    }

    //13.统计从0-n每个数的二进制表示1的个数(使用动态规划)
    //对于数字 6(110)，它可以看成是 4(100) 再加一个 2(10)，因此 dp[i] = dp[i&(i-1)] + 1;
    public static int[] countBits(int num){
        int[] ret = new int[num + 1];
        for(int i = 1; i <= num; i++)
            ret[i] = ret[i & (i - 1)] + 1;
        return ret;
    }
}

