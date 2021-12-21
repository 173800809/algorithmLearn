//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;

//import net.sf.json.JSONObject;

//import net.sf.json.JSONObject;
//import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用JSONObject需要引用的jar包
 *  1.json-lib-0.8.jar
 *  2.commons-beanutils-core-1.8.3
 *  3.commons-lang-2.6
 *  4.commons-logging-1.2
 *  5.ezmorph-1.0.6
 *
 *
 *  BigDecimal bigDecimal = new BigDecimal("0.000000");
 *  bigDecimal.equals(BigDecimal.ZERO)为false
 *  注：equals不但值的大小要相等，保留位数也要相等
 */
public class Test01ForOffer {
    public static void main(String[] args) {
//        System.out.println(checkTimeFormat(""));
//        String  a = "";
//        try {
//            a = URLDecoder.decode("2020-07-14T22:25:26", "UTF-8");
//        }catch(Exception e){
//
//        }
//
//        String b = StringUtils.remove("sdfgjajgha","jaj");
//        System.out.println(urlTimeToFormalTime("2020-07-14T22%3A25%3A26"));

//        Pattern urlMatcherPattern = Pattern.compile("\\d+-\\d+-\\d+\\w+:\\w+:\\w+");

//        Pattern urlMatcherPattern = Pattern.compile(".+1?");
//        Matcher urlMatcher = urlMatcherPattern.matcher("2020-09-08T09:08:09");
//        System.out.println(urlMatcher.matches());
//
//        String t1 = "2020-07-14T22:25:26";
//        t1.replace("T", " ");
//        LocalDateTime localDateTime = new LocalDateTime();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dtf.format(LocalDateTime.parse(t1)));
//
//        Date date = new Date(0);
//        date.getTime();
//
//        int[] num = {2, 3, 4, 2, 5, 4, 9, 3, 8, 5};
//        int[] num1 = {1, 2, 2, 3, 3, 3, 3, 3, 5};
//        int[] num2 = {0};
//
////        ArrayList list = maxInWindow(num, 3);
//        ArrayList<ArrayList<Integer>> list = findContinueSequence(100);
//        int d = 5;
//        d &= -d;
//        System.out.println(getUglyNumber(9));

//        ArrayList<String> t = method("a bc d eag f");
//
//        for(String str : t){
//            System.out.println(str);
//        }
        int[] nums = {12,21,32,16,65,75};
        int[] nums1 = new int[1];
        int[] nums2 = new int[1];
        int[][] numMany = {{1,10,3,8},{12,2,9,6},{5,7,4,11},{3,7,16,5}};

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(3);

        ListNode listNode2 = new ListNode(5);
        listNode2.next = new ListNode(6);
        listNode2.next.next = new ListNode(7);

        ListNode listNode3 = new ListNode(51);
        listNode3.next = listNode2;
        listNode1.next.next.next = listNode2;

        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        TreeNode p = new TreeNode(5);
        node.left.left.right = p;

        TreeNode t11 = lowestCommonAncestor1(node, p, new TreeNode(11));
//        JSONObject jsonResult = JSONObject.fromObject(t11);
//        System.out.println(jsonResult);
    }


    //面试题：打印杨辉三角
    public static void Yanghui(int n) {
        //边界条件判断
        if(n<=0)return;
        //      1             第一行
        //   1   1           第二行
        //---->第n行有n个数  , 多一个0，作为标记位
        int a[] = new int[n+1];
        a[0] = 1;//初始化第一个为1;
        //定义两个指针，将数组看成一个循环数组
        int rear = a.length-1;
        int font = 0;
        int i = 1;
        while(i <= n) {
            //打印第n行杨辉三角
            printArray(a , rear , font, n-i);
            //  0       4      6      4       1         0
            // rear                          font
            //当a[font]==1  && a[font+1]=0 就不向前进行累加了
            while(a[font]!=1||a[(font+1)%a.length]!=0) {
                a[font] = a[font]+a[(font+1)%a.length];//a[font] = a[font]+a[font+1];
                font = (font+1)%a.length;//font++;
            }
            //  1       4      6      4       1         0
            // rear                          font
            a[rear] = 1;
            //	  1       4      6      4       1         0
            //                                  font     rear
            rear = (rear+a.length-1)%a.length;
            //	  1       4      6      4       1         0
            //   font                                    rear
            font = (rear+1)%a.length;
            n--;
        }
    }
    public static void printArray(int[] a , int rear , int font, int i) {
        while(a[font]!=0) {
            while(i-- > 0)
                System.out.print(" ");
            //当碰到0时，打印结束，否则向前打印
            System.out.print(a[font]+" ");
            font=(font+1)%a.length;
        }
        System.out.println();
    }

    //leetCode89题
    public static List<Integer> gerayCode(int n){
        ArrayList<Integer> nums = new ArrayList<>();

        nums.add(0);
        if(n == 0)//为0时
            return nums;

        nums.add(1);
        if(n == 1)//为1时
            return nums;

        int cnt = 1;
        while(cnt < n){
            for(int i = nums.size() - 1; i >= 0; i--)
                nums.add((int)Math.pow(2, cnt) + nums.get(i));
            cnt++;
        }

        return nums;
    }

    //题目见桌面个人信息文档(分别存储从左至右和从右至左的两个数组)
    public static int findMinArea(int[] H){
        if(H == null || H.length < 1)
            return 0;
        else if(H.length == 1)
            return H[0];
        int[] lMaxNums = new int[H.length];
        int[] rMaxNums = new int[H.length];

        lMaxNums[0] = H[0];
        rMaxNums[H.length - 1] = H[H.length - 1];

        for(int i = 1; i < H.length; i++){
            if(H[i] > lMaxNums[i - 1])
                lMaxNums[i] = H[i];
            else
                lMaxNums[i] = lMaxNums[i - 1];
        }

        int out = rMaxNums[H.length - 1] + lMaxNums[H.length - 2] * (H.length - 1);

        for(int i = H.length - 2; i > 0; i--){
            if(H[i] > rMaxNums[i + 1])
                rMaxNums[i] = H[i];
            else
                rMaxNums[i] = rMaxNums[i + 1];

            out = Math.min(out, rMaxNums[i] * (H.length - i) + lMaxNums[i - 1] * i);

        }
        return out;
    }

    //携程笔试题
    private static ArrayList<String> list = new ArrayList<>();
    private static ArrayList<String> method(String str) {
        String[] tStr = str.split(" ");

        list.add(tStr[0]);

        if (tStr.length < 2) {
            return list;
        } else {
            circleMethod(tStr, 1);
        }

        for (int i = 0; i < list.size(); i++) {
            boolean flag = false;
            HashSet<Character> set = new HashSet<>();
            String tmpStr = list.get(i);
            for (int j = 0; j < tmpStr.length(); j++) {
                if (set.contains(tmpStr.charAt(j))) {
                    flag = true;
                }
                set.add(tmpStr.charAt(j));
            }
            if (flag) {
                list.set(i, tmpStr + "--circular dependency");
            }
        }
        return list;
    }
    private static void circleMethod(String[] tStr, int len) {

        if (tStr.length > len && tStr[len].length() > 0) {
            ArrayList<String> temp = new ArrayList<>();
            int n = 1;
            if (list.size() != 0)
                n = list.size();

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < tStr[len].length(); i++) {
                    temp.add(list.get(j) + tStr[len].charAt(i));
                }
            }
            list = temp;

            circleMethod(tStr, ++len);
        }
        return;
    }

    //60 n个骰子的点数
    // 60.1（使用动态规划算法，前一个状态与后一个状态的固定规律）(Map.Entry<Integer, Double> t = new AbstractMap.SimpleEntry<>(i, dp[n][i] / total))
    private static List<Map.Entry<Integer, Double>> diceSum(int n) {
        int pointNum = n * 6;
        long[][] dp = new long[n + 1][pointNum + 1];//浪费很多空间，是否可以优化？？？
        //填充的索引，包左不包右，所以填的是1、7、1
        Arrays.fill(dp[1], 1, 7,1);//填充初始值，第一次1-6点数都是只出现一次
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= pointNum; j++) {
                for (int k = 1; k <= 6 && k <= j; k++) {//同时满足，不超过：目标点数和骰子点数<小于等于6>
                    //dp[i][j]的初始值为0，是通过k的变化动态规划累加
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        List<Map.Entry<Integer, Double>> list = new ArrayList<>();
        double total = Math.pow(6, n);
        for (int i = n; i <= pointNum; i++) {
            list.add(new AbstractMap.SimpleEntry<>(i, dp[n][i] / total));
        }
        return list;
    }
    // 60.2 优化60.1
    public static List<Map.Entry<Integer, Double>> dicesSum(int n) {
        int pointNum = n * 6;
        long[][] dp = new long[2][pointNum + 1];
        //填充的索引，包左不包右，所以填的是1、7、1
        Arrays.fill(dp[0], 1, 7, 1);
        int flag = 1;
        for (int i = 2; i <= n; i++, flag = 1 - flag) {
            for (int j = 1; j <= pointNum; j++) {
                dp[flag][j] = 0;
            }
            for (int j = i; j <= pointNum; j++) {
                for (int k = 1; k < j && k <= 6; k++) {
                    dp[flag][j] += dp[1 - flag][j - k];
                }
            }
        }
        List<Map.Entry<Integer, Double>> list = new ArrayList<>();
        double total = Math.pow(6, n);
        for (int i = n; i <= pointNum; i++) {
            list.add(new AbstractMap.SimpleEntry<>(i, dp[1 - flag][i] / total));
        }
        return list;
    }

    //59滑动窗口的最大值(精简的代码)
    public static ArrayList<Integer> maxInWindow(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null || num.length < size || size < 1) {
            return ret;
        }
        //使用LinkedList实现双端队列，Last:是索引值最大，First:是索引值最小
        LinkedList<Integer> listDeque = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            //把队列中比新的元素(num[i])小的都要移除，保留等于的数，因为i一定会添加到队列中
            // 使用while是因为把窗口中所有比新元素小的值都删除
            while (!listDeque.isEmpty() && num[i] > num[listDeque.getLast()]) {
                listDeque.removeLast();
            }
            //因为此语句写在删除尾结点的后面，保证listDeque里面一定是有值的
            listDeque.addLast(i);
            //保证滑动窗口的头(First)最少要在第一个元素开始
            if (i >= size - 1) {
                //如果队列中索引值最小的值，已经不再滑动窗口里了，要移除
                //下面while语句改用改为if吗？
                while (i - listDeque.getFirst() >= size) {
                    listDeque.removeFirst();
                }
                // 添加最小索引值到结果中，因为这个值正好覆盖到第一个滑动窗口的最大值
                // 如下图所示:10个数，滑动窗口为3，双端队列的范围
                // 1 2 3 4 5 6 7 8 9 10
                //    [双端队列可能索引值 ]
                ret.add(num[listDeque.getFirst()]);
            }
        }
        return ret;
    }
    //非精简的代码（废弃）
//    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
//        ArrayList<Integer> ret = new ArrayList<>();
//        if (num == null) {
//            return ret;
//        }
//        if (num.length < size || size < 1) {
//            return ret;
//        }
//
//        LinkedList<Integer> indexDeque = new LinkedList<>();
//        for (int i = 0; i < size - 1; i++) {
//            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
//                indexDeque.removeLast();
//            }
//            indexDeque.addLast(i);
//        }
//
//        for (int i = size - 1; i < num.length; i++) {
//            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
//                indexDeque.removeLast();
//            }
//            indexDeque.addLast(i);
//            if (i - indexDeque.getFirst() + 1 > size) {
//                indexDeque.removeFirst();
//            }
//            ret.add(num[indexDeque.getFirst()]);
//        }
//        return ret;
//    }

    //58.2左旋转字符串(使用2次部分旋转+1次全部旋转)
    //背景：汇编语言中有一种移位指令叫做循环左移（ROL）
    //实例：原字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”
    //注意类的toString：除了重写toString方法，不然只会打印类名
    public static String LeftRotateString(String str, int n) {
        if (n >= str.length()) {
            return str;
        }

        char[] chars = str.toCharArray();

        reverse(chars, 0, n - 1);//前n个字母翻转
        reverse(chars, n, str.length() - 1);//n字母后翻转
        reverse(chars, 0, str.length() - 1);//全部翻转

        return new String(chars);
    }
    private static void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;//记得自增或自减
            j--;
        }
    }

    //58.1翻转字符串中的单词（使用多次部分旋转<次数由空格划分的字符串决定>+1次全部旋转）
    //示例：输入："the sky is blue"，输出："blue is sky the"
    public static String reverseSentence(String str) {
        char[] chars = str.toCharArray();

        int i = 0, j = 0;

        while (j <= str.length()) {
            if (j == str.length() || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(chars, 0, str.length() - 1);
        return new String(chars);
    }

    //57.2和为一个数的连续数字集合（start--和end++适用于解决连续数字问题）
    // 优先大数(start)和小数(end)只能加。这样覆盖范围才是全部
    //因为题目要求是连续数字集合，一个数字不算是连续
    public static ArrayList<ArrayList<Integer>> findContinueSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1, end = 2, curSum = 3;//start和end是头尾指针，curSum是在指针范围内的数字和
        while (end < sum) {//尾指针要小于最终值（因为题目要求是连续数字集合，一个数字不算是连续）
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                ret.add(list);
                end++;
                curSum += end;//上面几步的目的是：让end指针向后一个位置（不可以省略，如果省略则无法完成遍历工作）
            }
        }
        return ret;
    }

    //57.1和为N的两个数字（排序数组）（相同的数字取乘积最小的）
    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        int i = 0, j = array.length - 1;

        while (i < j) {
            int temp = array[i] + array[j];
            if (temp == sum) {
                //list.add(Arrays.asList(array[i], array[j]));这样写是错的（因为add只能添加：单个元素 或 ArrayList对象。而Arrays.asList返回的不是真正的ArrayList）
                //直接返回结果
                return new ArrayList<>(Arrays.asList(array[i], array[j]));
            } else if (temp < sum) {
                i++;
            } else {
                j--;
            }
        }
        //如果没有结果则返回一个空对象
        return new ArrayList<>();
    }

    //56数组中只出现一次的两个数字（输入中的其他数字"必须"出现两次）
    public static void findNumsAppearOnce(int[] nums, int[] num1, int[] num2) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;//diff最终保存的是只出现1次的2个数字的异或（因为出现两次的数字异或为0）
        }

        diff &= -diff;//（用以区分只出现1次的2个数字）把二进制最右边第一个为1的数保存下来（因为负数是：正数二进制每位取反，之后加一）

        for (int num : nums) {
            if ((num & diff) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }

    //55.2判断是否是平衡二叉树（左右子树<所有的左右子树>高度差都不超过1）
    public static boolean IsBalancedSolution(TreeNode root) {
        // 使用两个方法，因为返回boolean和int两种值
        // 就是因为需要复用getDepth方法的返回树的高度和是否是平衡二叉树
        return getDepth(root) != -1;
    }
    public static int getDepth(TreeNode root) {//无需自顶向下，只需自下向上
        if (root == null) {
            return 0;//叶子为空则，为0
        }
        int left = getDepth(root.left);
        // -1就是标识不是平衡二叉树（存在一个子树的高度差大于1）
        // 所以只要有-1就可以透传到最外层
        if (left == -1) {
            return -1;
        }
        int right = getDepth(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(right - left) > 1 ? -1 : 1 + Math.max(right, left);
    }

    //55.1二叉树的深度
    public static int treeDepth(TreeNode root) {
        //代码有的时候需要精简
        //需要合理理解默认值和递归条件，如下面代码所示
        return root == null ? 0 : 1 + Math.max(treeDepth(root.right), treeDepth(root.left));
    }

    //54二叉查找树的第K个节点（使用递归方法）（二叉查找树是中序遍历有序）
    private static TreeNode ret;//不能使用循环来解决此类问题，因为这个是广度优先算法问题（使用循环的话，则不能记录之前走过的路）
    //    private static int cnt = 0;
    public static TreeNode findKthNode(TreeNode root, int k) {
        inOrder(root, k);
        return ret;
    }
    public static void inOrder(TreeNode root, int k) {
        if (root == null || cnt >= k) {
            return;
        }
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) {
            ret = root;
        }
        inOrder(root.right, k);
    }
    //（使用栈方法：非递归）
    private static TreeNode KthNode(TreeNode root, int k) {
        if (root == null || k == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        int cnt = 0;
        do {//一定要先进入这个循环里面，为了完成对stack设置初始值
            //只要有根节点，都需要放入栈中(因为先计数左节点，再计数根节点)
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                //从stack里面取出来的都是根节点(就算是最后一层左节点，也认为是根节点)
                root = stack.pop();
                cnt++;
                if (cnt == k) {
                    return root;
                }
                root = root.right;
            }
        } while (root != null || !stack.isEmpty());//判断条件就是root和stack里面的都要走完（因为二叉树的最长长度，只有走完才能知道）
        return null;
    }

    //53数字在"排序<递增>"数组中出现的次数（是整数）
    public static int GetNumberOfK(int[] nums, int k) {
        int first = binarySearch(nums, k);//头指针
        int last = binarySearch(nums, k + 1);//尾指针（不包括尾指针）
        //如果头指针 = 数组长度，或者头指针位置数 != k，则返回0
        return (first == nums.length || nums[first] != k) ? 0 : last - first;
    }
    //找到一个数在数组中的最低索引
    private static int binarySearch(int[] nums, int k) {
        /**
         * h使用的最长长度, 目的是让k是最后一个数时，Last要在索引之外
         */

        int l = 0, h = nums.length;
        //最后l等于h，因为这个是循环退出条件，不会出现l大于h的情况
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= k) {
                h = m;//只要m位置的数"大于等于"(因为数字可能是多个，所以需要等于)
            } else {
                l = m + 1;//如果m位置比较小，则l只比m大一
            }
        }
        return l;
    }

    //52两个链表中的第一个公共节点(两个链表分别为a+c和b+c。因为a+c+b=b+c+a, 两个链表走一样的步数之后，就到公共节点)
    public static ListNode getFirstNode(ListNode node1, ListNode node2) {
        ListNode l1 = node1, l2 = node2;
        //通过类的引用直接比较是否相同
        while (l1 != l2) {
            l1 = l1 == null ? node2 : l1.next;
            l2 = l2 == null ? node1 : l2.next;
        }
        return l1;
    }

    //51数组中的逆序对（通过归并排序的过程中，对调的次数获取对应的逆序对）（数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对<两个数>。题目是求逆序对的总数<如3，1>）
    private static long cnt = 0;
    private static int[] tmp;
    public static int inversePairs(int[] nums) {
        /**
         * 不能使用 tmp = nums进行赋值，不然改变tmp也会改变nums
         *
         * 原因：java对象和数据传的是引用的地址，实际在内存中在一个位置
         */
        tmp = new int[nums.length];//辅助数组必须使用，用于存储排序好的数组
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt % 1000000007);//防止数字溢出，即long型转int型（因为1000000007是10位整数<8个零>里面最小的质数）
    }
    private static void mergeSort(int[] nums, int l, int h) {
        if (h - l < 1) {//数组中最少有两个数字（当子序列中只有一个元素的时候结束递归）
            return;
        }
        int m = l + (h - l) / 2;//划分子序列
        mergeSort(nums, l, m);//对左侧子序列进行递归排序
        mergeSort(nums, m + 1, h);//对右侧子序列进行递归排序
        merge(nums, l, m, h);//两个排序好的子序列合并为一个子序列
    }
    private static void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;//i、j是左右检测指针，k是存放指针（这个指针很重要）
        while (i <= m || j <= h) {
            if (i > m) {//如果左半部分添加完了，则直接添加右半部分
                tmp[k++] = nums[j++];
            } else if (j > h) {//如果右半部分添加完了，则直接添加左半部分
                tmp[k++] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
                //使用m - i + 1的原因是，mid之前的的数组是有序的，而tmp[k++] = nums[j++]会把数组位置调换，所以需要记录mid之前(包括mid本身)的所有比nums[j]大的个数
                //注意不能使用cnt++，因为m之前的数没有被记录下来
                //因为左右数组是有序数组，所以"i至m"的所有数字都比"j"的数字大
                cnt += m - i + 1;
            }
        }
        //复制回原数组
        for (k = l; k <= h; k++) {
            nums[k] = tmp[k];
        }
    }
    //51补（和本题无关）：归并排序算法（和上面的思路是一样的，不过不够简洁）
    public void mergeSortMethod(int[] a, int start, int end) {
        //注意这里使用的是if,本方法中没有循环，只有子方法中有循环
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSortMethod(a, start, mid);//对左侧子序列进行递归排序
            mergeSortMethod(a, mid + 1, end);//对右侧子序列进行递归排序
            mergeMethod(a, start, mid, end);//合并
        }
    }
    public void mergeMethod(int[] a, int left, int mid, int right) {//两路归并算法，两个排好序的子序列合并为一个子序列
        int[] tmp = new int[a.length];//辅助数组
        //所有的值都是从入参中left、mid、right得到的
        //p1是左指针(包括mid)、p2是右指针(不包括mid)，k是存放指针(一直顺序自增)
        int p1 = left, p2 = mid + 1, k = left;
        //精简代码(下面循环的次数是right - left + 1)
//        while (p1 <= mid || p2 <= right) {
//            if (p1 > mid) {//如果左半部分添加完了，则直接添加右半部分
//                tmp[k++] = a[p2++];
//            } else if (p2 > right) {//如果右半部分添加完了，则直接添加左半部分
//                tmp[k++] = a[p1++];
//            } else if (a[p1] <= a[p2]) {
//                tmp[k++] = a[p1++];
//            } else {
//                tmp[k++] = a[p2++];
//            }
//        }
        //下面代码可以被替换
        {
            while (p1 <= mid && p2 <= right) {
                if (a[p1] <= a[p2])
                    tmp[k++] = a[p1++];
                else
                    tmp[k++] = a[p2++];
            }
            while (p1 <= mid) tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
            while (p2 <= right) tmp[k++] = a[p2++];//同上
        }

        //复制回原素组，注意只用复制部分元素，起止是left和right
        for (int i = left; i <= right; i++)
            a[i] = tmp[i];
    }

    //BitSet（可以解决大量数字中重复问题）(1.存储长度是64<因为用long类型，所以默认有8*8=64bit存储>  2. 存储的值是Boolean类型， 3.set重复的数字没有影响)（boolean正常占1字节<8 bit>,在转换为int类型后则占4字节）
    //BitMap和BitSet差不多，而且BitMap使用过程中方法是不常见的
    //50第一个只出现一次的字符位置(ASCII码中的字符用一个字节表示，一共可以有256个字符，现在使用的是128个字符)（已经从HashMap的O(n)空间复杂度，改为O(1)了）
    public static int firstNotRepeatingChar(String str) {
        int[] cnts = new int[256];//ASSIC码就是256个字符（因为是正常是128）
        for (int i = 0; i < str.length(); i++) {
            cnts[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (cnts[str.charAt(i)] == 1)
                return i;
        }
        return -1;
    }
    //改进空间复杂度（从int的<4字节>改为1字节）
    public static int findNotRepeatingChar2(String str) {
        //BitSet的默认值是false；
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(256);
        //使用bs1和bs2模拟只出现一次(bs1为true，bs2为false)，和出现两次及两次以上(bs1为true，bs2为true)
        for (int i = 0; i < str.length(); i++) {
            if (!bs1.get(str.charAt(i)) && !bs2.get(str.charAt(i))) {
                bs1.set(str.charAt(i));
            }
            else if (bs1.get(str.charAt(i)) && !bs2.get(str.charAt(i))) {
                bs2.set(str.charAt(i));
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (bs1.get(str.charAt(i)) && !bs2.get(str.charAt(i)))
                return i;
        }
        return -1;
    }

    //49丑数（下方是求从1开始，第N个丑数是什么）（因子只包含1、2、3和5的数称为丑数）
    public static int getUglyNumber(int N) {
//        if (N <= 6)//可有可无，下方代码已经覆盖此部分了
//            return N;
        /**
         * i2,i3,i5是2，3，5可以乘数组的最小值
         * 最一开始是乘以1(就是本身)
         * 之后依次乘比1大的最小值
         */
        // 乘以2、3、5分别乘以dp数组里面的"最小值"（每次要自增1）
        // i2、i3、i5就是使用动态规划的核心
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[N];
        //需要设置初始值
        dp[0] = 1;
        //动态规划需要注意：i是从1开始的，因为dp[0]的值不能被覆盖掉
        for (int i = 1; i < N; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            //赋值给新的dp
            /**
             * 只能使用if，不能使用else if
             * 因为2*3 和3*2的值是一致的
             */
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) {
                i2++;
            }
            if (dp[i] == next3) {
                i3++;
            }
            if (dp[i] == next5) {
                i5++;
            }
        }
        return dp[N - 1];
    }
    //49.1 找零钱的最少硬币数(输入的是：不同数值的硬币、需要找的零钱数)
    public static int minCoins(int[] coins, int amount) {
        //检验入参
        if(amount == 0 || coins == null)
            return 0;
        int[] dp = new int[amount + 1];
        //对入参做数据准备
        Arrays.sort(coins);
        Arrays.fill(dp, -1);
        //动态规划使用的是：amount，下题使用的是coins为动态规划
        for(int i = coins[0]; i <= amount; i++){
            int temp = Integer.MAX_VALUE;
            for(int coin : coins){
                //使用动态规划
                if(i - coin >= 0 && dp[i - coin] != -1)
                    temp = Math.min(temp, 1 + dp[i - coin]);
                //只需一个硬币的特例（需要覆盖上面的temp值）
                //只能用if,不能用else if
                if(i == coin)
                    temp = 1;
            }
            dp[i] = temp == Integer.MAX_VALUE ? -1 : temp;
        }
        return dp[amount];
    }
    //49.2 找零钱的硬币数组合
    public static int coinsCompose(int amount, int[] coins) {
        if (amount == 0 || coins == null)
            return 0;
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        //需要设置初始值
        dp[0] = 1;
        //动态规划是：amount先是有小的coin组成，之后由大的coin组成
        //动态规划是coins,不能使用amount动态规划！！！(所以下面for位置不能更改)
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    //48最长不含重复字符的子字符串（小写的26个英文字母）
    public static int longestSubStringWithoutDuplication(String str){
        //临时滑动窗口的长度，maxLen只能和curLen进行比较
        int curLen = 0;
        //最长滑动窗口的长度
        int maxLen = 0;
        //preIndexs里面存储的就是a-z里面26个字母中的每个字母的"上一个同样字母出现的索引是多少"
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);//填充不能为0（因为0也是索引的位置）
        for(int curI = 0; curI < str.length(); curI++){
            int c = str.charAt(curI) - 'a';
            //这里记录字符的上一次出现的索引位置（每次循环都更新）
            int preI = preIndexs[c];
            //当出现 有两个重复字符 并且两个字符的间距不比curLen大，就记录：1.此次间距；2.上次临时长度和最大长度的最大值
            //下面if保证了curLen里面不会统计重复字符(如果有重复字符，则会使用下面方法)
            if(preI != -1 && curI - preI <= curLen){//是小于等于
                //统计curI(不包括curI)之前字符的"最终长度"
                maxLen = Math.max(maxLen, curLen);
                //统计preI(不包括preI)之后字符的"临时长度"
                curLen = curI - preI;
            }else {
                //上面保证了curLen不会有重复字符，所以curLen可以放心自增
                curLen++;
            }
            preIndexs[c] = curI;
        }
        //为了解决最长长度的curI没有重复字符
        maxLen = Math.max(maxLen, curLen);//需要最后判断一次
        return maxLen;
    }

    //47礼物的最大价值(就是棋盘上放礼物。因为是从左上角开始，到右下角结束。所以应该使用动态规划，而不是使用深度优先搜索)
    // 就和数数到100，一次最多3个数字，最少1个数字。前后是相关的，比如下图
    // 到1，只能通过2和3。2就是dp[n],3就是dp[n-1]
    // 0 0 0 0 0
    // 0 0 0 0 0
    // 0 0 0 0 2
    // 0 0 0 3 1
    public static int getMost(int[][] values){
        if(values == null || values.length == 0 || values[0].length == 0)//需要判断存储数字的三层结构为空，则为空
            return 0;
        int n = values[0].length;
        int[] dp = new int[n];//这样做可以节省空间（实际可以使用二维数组去保存最大值，但现在一维数组就可以了）
        //遍历顺序是从上往下（上方一行遍历完，再到下方）
        for(int[] value : values){
            //最左边从上到下累加
            dp[0] += value[0];
            // i值需要从1开始，不然就会有溢出的风险
            for(int i = 1; i < n; i++)
                //dp[i]是当前点的上方点最大值（因为dp[i]还是保存上次的最大值），dp[i - 1]是当前点的左方点的最大值
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
        }
        return dp[n - 1];
    }

    //46把数字翻译成字符串有多少种可能性(动态规划:找到条件把——>dp[i - 2]和dp[i - 1]的和赋值给dp[i])
    //题目解释(解释2101输出是1)：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
    //有个前提是输入的s里面一定是数字
    //注 ：
    // 1. 如果动态规划是依赖dp[i - 2]和dp[i - 1]。则需要设置初始值
    // 2. 动态规划的长度有的时候要多一位比如 dp = new int[n+1]
    public static int numDecodings(String s){
        if(s == null || s.length() == 0)
            return 0;
        int n = s.length();
        //真正记录的下标是从1开始
        int[] dp = new int[n + 1];
        dp[0] = 1;//所有动态规划的初始值
        dp[1] = s.charAt(0) == '0' ? 0 : 1;//真正开始记录数字的下标
        for(int i = 2; i <= n; i++){
            int one = Integer.valueOf(s.substring(i - 1, i));//一位数
            int two = Integer.valueOf(s.substring(i - 2, i));//两位数
            //(为了解决"2101"输出是1，因为题目要求不允许0单独存在，必须实现如下分割2 10 1)
            //如果one为0，则dp[i]则为默认值0！！(为了解决2101在其实)
            //如果one不为0，则先把dp[i - 1]赋值给dp[i]
            if(one != 0)
                dp[i] = dp[i - 1];
            if(s.charAt(i - 2) != '0' && two <= 26)//如果两位数：十位不为0、数值小于等于26，则把dp[i - 2]和dp[i - 1]的和赋值给dp[i]
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    //45把数组排成最小的数(使用java自带方法，包括jdk8。主要是比较s1+s2与s2+s1)
    //Arrays.sort(待排序数组, 排序条件)（排序条件:(s1, s2) -> (s1 + s2).compareTo(s2 + s1))
    public static String printMinNumber(int[] numbers) {
        if(numbers == null || numbers.length == 0)
            return "";
        int n = numbers.length;
        String[] nums = new String[n];
        for(int i = 0; i < n; i++)
            nums[i] = numbers[i] + "";
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));//(s1, s2) -> (s1 + s2).compareTo(s2 + s1)就是排序条件
        String ret = "";
        for(String str : nums)
            ret += str;
        return ret;
    }

    //44数字序列中的某一位数字（数字以 0123456789101112131415...自然数顺序，求这个字符串的第 index<从0开始> 位。）
    // 把数据截取为同一位数值，比如数只有个位或十位或百位的数
    public static int getDigitAtIndex(int index){
        if(index < 0)
            return -1;
        int place = 1;//1 表示个位，2 表示十位
        while(true){// index在每次循环中都被改变
            long amount = getAmountOfPlace(place);//当前位数下有多少"数字"，个位10个<特例>、十位90个、百位900个
            long totalAmount = amount * place;//当前位数下有多少个"纯数字"，十位90*2<2就是两位数>=180，百位900*3<3就是三位数>= 2700
            if(index < totalAmount)//如果index在当前数字个数下，则可以找到此数字
                return getDigitAtIndex(index, place);
            index -= totalAmount;//每次index都要减去上个位数的数字个数
            place++;
        }
    }
    //place为数的数字组成的字符串长度，10，90，900，...
    private static long getAmountOfPlace(int place){
        if(place == 1)
            return 10;
        return (long)Math.pow(10, place - 1) * 9;
    }
    //place位数的起始数字，0(个位)，10(十位)，100(百位)，...
    private static int getBeginNumberOfPlace(int place){
        if(place == 1)
            return 0;
        return (int)Math.pow(10, place - 1);
    }
    //在place位数组成的字符串中，第index个数（index = 15, place = 2）
    private static int getDigitAtIndex(int index, int place){
        long beginNumber = getBeginNumberOfPlace(place);// 得到初始数字（10）
        long shiftNumber = index / place;// index属于第几个"完整"数字（7）
        int count = index % place;// index在"完整"数字的第几位（1）

        /**
         * 技巧:通过string去数字的第n位数
         */
        String number = (beginNumber + shiftNumber) + "";// 得到index所属的"完整"数字(17)
        return number.charAt(count) - '0';// 获得在index的值(1)
    }

    //43从1到n整数中1出现的次数(从牛客网上找的答案)
    public static int numberOf1Between1AndN(int n){
        if(n <= 0)
            return 0;
        int count = 0;
        // 输入为1321
        // 得到的值
        // 个位的1，left = 132(0-131)，right = 1(1)
        // 十位的1，left = 130(0-12)，right = 10(0-9)
        // 百位的1，left = 100(0)，right = 100(0-99)
        // 千位的1，left = 0，right = 322(0-321)
        for(int i = 1; i <= n; i *= 10) {
            // 划分的位数
            long diviver = i * 10;
            // 位数的左边从0开始，左边的最大数的低一位
            long left = (n / diviver) * i;
            // 位数的左边的最大数，只包括右面的数
            long right = Math.min(Math.max(n % diviver - i + 1, 0), i);

            count += left + right;
        }
        return count;
    }

    //42连续子数组的最大和(数组中有负数)
    public static int findGreatestSumOfSubArray(int[] nums){
        if (nums == null || nums.length == 0)
            return 0;
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int val : nums){
            // 是否取前sum的值，只有前sum大于0的时候
            sum = sum <= 0 ? val : sum + val;//只要和是正数，就要保留给之后用（所以不用担心正确结果是从中间开始的）
            // 需要记录之前的最大值和现在的和进行比较
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }

    //41.2字符流中第一个不重复的字符(解题方法是使用栈的原理，下面方法不全<原题就是这样>)
    // 验证方法：对输入的字符串，先Insert，再firstAppearingOnece
//    public static String getNoDuplication(String str) {
//        if(str == null || str.length() < 1){
//            return null;
//        }
//        String result = "";
//        for (int i = 0; i < str.length(); i++) {
//            Insert(str.charAt(i));
//            result = result + firstAppearingOnce();
//        }
//        return result;
//    }
    private static int[] cnts = new int[256];
    private static Queue<Character> queue = new LinkedList<>();
    public static void Insert(char ch){
        cnts[ch]++;
        queue.add(ch);
        // 需要使用while，因为要把栈中所有的重复字符都要删除
        /**
         * 使用栈做移除操作的时候注意使用while方法
         */
        while(!queue.isEmpty() && cnts[queue.peek()] > 1)
            queue.poll();
    }
    public static char firstAppearingOnce(){
        return queue.isEmpty() ? '#' : queue.peek();
    }

    //41.1数据流中的中位数（下面方法不全<原题就是这样>）
    /**
     * 重写Comparator，
     * "默认" 从小到大，底层是(o1, o2) -> o1 - o2
     * "重写" 从大到小，重写是(o1, o2) -> o2 - o1
     */
    private static PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);//大顶堆，存储左半边元素
    private static PriorityQueue<Integer> right = new PriorityQueue<>();//小顶堆，存储右半边元素，并且右边元素都大于左半边
    private static int N = 0;//当前数据流读入的元素个数
    public static void insert(Integer val){
        //插入要保证两个堆存于平衡状态
        if(N % 2 == 0){
            //N为偶数的情况下插入到右半边。
            //因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大
            //因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边
            left.add(val);
            right.add(left.poll());
        }else{
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }
    public static Double getMedian(){
        if(N % 2 == 0)
            return (left.peek() + right.peek()) / 2.0;
        else
            return (double)right.peek();
    }

    //40最小的K个数(快速选择)（复杂度：O(N) +O(1)，只有当允许修改数组元素时才可以使用）
    // 这k个数不用顺序输出(它们可以是无序的，只要是最小的k个数就行)，所以使用快速排序
    public static ArrayList<Integer> getLastNumbersSolution(int[] nums, int k){
        ArrayList<Integer> ret = new ArrayList<>();
        if(k > nums.length || k <= 0)
            return ret;
        findKthSmallest(nums, k - 1);
        //findKthSmallest会改变数组，使得前k个数都是最小的k个数
        for(int i = 0; i < k; i++)
            ret.add(nums[i]);
        return ret;
    }
    public static void findKthSmallest(int[] nums, int k){
        int l = 0, h = nums.length - 1;
        while(l < h){
            // j就是标杆的位置，包括j本身
            int j = partition(nums, l, h);
            if(j == k)
                break;
            if(j > k)
                h = j - 1;
            else
                l = j + 1;
        }
    }
    private static int partition(int[] nums, int l, int h){
        int p = nums[l];
        int i = l, j = h + 1;
        while(true){
            while(i != h && nums[++i] < p);
            while(j != l && nums[--j] > p);
            if(i >= j)
                break;
            // 找到和标杆数字nums[l]顺序不同的数，交换他们的顺序
            swap(nums, i, j);
        }
        // 最后一次把标杆数字nums[l]和最后一个没有换位置的数置换
        swap(nums, l, j);
        return j;
    }
    private static void  swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    //40最"小"的K个数（使用"大"顶堆，因为最大数在顶端，增删效率高）（时间复杂度O(NlogK) + O(K)）
    public static ArrayList<Integer> getLeastNumbersSolution(int[] nums, int k){
        if(k > nums.length || k <= 0)
            return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int num : nums){
            maxHeap.add(num);
            if(maxHeap.size() > k)
                maxHeap.poll();
        }
        // PriorityQueue可以直接注入到ArrayList，因为他们都是继承Queue
        return new ArrayList<>(maxHeap);
    }

    //39数组中出现次数超过一半的数字
    public static int moreThanHalfNumSolution(int[] nums){
        int majority = nums[0];
        // 找到可能是最大数的数字（错误的一定会被错过，正确会留下来）
        for(int i = 1, cnt = 1; i < nums.length; i++){
            cnt = nums[i] == majority ? cnt + 1: cnt - 1;
            // 确认是错了，就换一个
            if(cnt == 0){
                majority = nums[i];
                cnt = 1;
            }
        }
        int cnt = 0;
        // 判断是否是有最大数
        for (int val : nums)
            if (val == majority)
                cnt++;
        return cnt > nums.length / 2 ? majority : 0;
    }

    //38字符串的排序（例如输入字符串 abc，所能排列出来的所有字符串 abc, acb, bac, bca, cab, cba）
    // 每个字符都需要使用
    private static ArrayList<String> ret38 = new ArrayList<>();
    public static ArrayList<String> permutation(String str){
        if(str.length() == 0)
            return ret38;
        // 是否选择的标识
        char[] chars = str.toCharArray();
        // 必须要排序，让重复字符在一起，不然下面递归有问题
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuilder());
        return ret38;
    }
    private static void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s){
        // 跳出递归的关键点
        if(s.length() == chars.length){
            ret38.add(s.toString());
            return;
        }
        for(int i = 0; i < chars.length; i++){
            if(hasUsed[i])
                continue;
            //因为不可能：同一个字符，之前(i)和之后(i - 1)的那个都没有被使用过
            //原因是这个字符被之前使用了。同样字符交换位置后是一样的
            if(i != 0 && chars[i] == chars[i - 1] && !hasUsed[i - 1])
                continue;
            // 进入下层循环的时候，要把该字符置为已使用过
            hasUsed[i] = true;
            s.append(chars[i]);
            backtracking(chars, hasUsed, s);
            // 删除的是临时字符串的"最后一位"
            s.deleteCharAt(s.length() - 1);
            // 结束本次循环的时候，要把该字符置为未使用过
            hasUsed[i] = false;
        }
    }

    //37序列化二叉树（使用前序遍历）
    public static String serialize(TreeNode root){
        if(root == null)
            return "#";
        return root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }
    // 反序列化二叉树

    private static String deserializeStr;
    public static TreeNode deserialize(String str){
        deserializeStr = str;
        return deserialize();
    }
    private static TreeNode deserialize(){
        if(deserializeStr.length() == 0)
            return null;
        // indexOf是第一次出现该字符的索引位置（如果不存在则返回-1，-1表示字符串中只有一个字符）
        // index的值只能是1或-1
        int index = deserializeStr.indexOf(" ");
        // 只有一个字符的时候，把该字符直接返回。不止一个字符的时候，把该字符串中第一个字符返回
        String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
        // substring(subtractCount),返回的是字符串中除掉前面subtractCount字符。
        // 例如"unhappy".substring(2) returns "happy"
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
        if(node.equals("#"))
            return null;
        int val = Integer.valueOf(node);
        TreeNode t = new TreeNode(val);
        t.left = deserialize();
        t.right = deserialize();
        return t;
    }

    // 36二叉搜索树与双向链表
    // 题意： (当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继)，函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
    // 二叉搜索树是中序排序
    private static TreeNode pre = null;
    private static TreeNode head = null;
    public static TreeNode convert(TreeNode root){
        inOrder(root);
        return head;
    }
    private static void inOrder(TreeNode node){
        if(node == null)
            return;
        /**
         * 中序遍历的左节点
         */
        inOrder(node.left);

        /**
         * 中序遍历的根节点(核心处理逻辑)
         *
         * 1.置当前节点的左指针
         * 2.置前一个节点的右指针
         * 3.把当前节点的值赋给 前一个节点
         *
         * 补充：初始化头节点
         */
        // 1.置当前节点的左指针
        node.left = pre;
        // 2.置前一个节点的右指针
        if(pre != null) {
            pre.right = node;
        }
        // 3.把当前节点的值赋给 前一个节点
        pre = node;
        // 补充：初始化头节点
        if(head == null) {// 初始化头节点
            head = node;
        }

        /**
         * 中序遍历的右节点
         */
        inOrder(node.right);
    }

    //35复杂链表的复制(输入一个复杂链表，返回结果)
    // 例：
    // 输入：1 -> 2 -> 3
    // 过程：1 -> 复制1 -> 2 -> 复制2 —> 3 -> 复制3
    // 结果：1 -> 2 -> 3
    //      复制1 -> 复制2 -> 复制3
    public static RandomListNode clone(RandomListNode pHead){
        if(pHead == null)
            return null;
        /**
         * 第一步：
         *   插入新节点，构建next节点
         * 结果(复制节点没有random节点)：
         *   1 -> 复制1 -> 2 -> 复制2 —> 3 -> 复制3
         */
        RandomListNode cur = pHead;
        while(cur != null){
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        /**
         * 第二步：
         *   建立random链接，构建random节点
         * 结果(复制节点有正确的random节点)：
         *   1 -> 复制1 -> 2 -> 复制2 —> 3 -> 复制3
         */
        cur = pHead;
        while(cur != null){
            RandomListNode clone = cur.next;
            if(cur.random != null)
                clone.random = cur.random.next;
            cur = clone.next;
        }
        /**
         * 第三步：
         *   拆分，把两个链表分开(就是next指针指向next.next)
         * 结果：
         *   1 -> 2 -> 3
         *   复制1 -> 复制2 -> 复制3
         */
        cur = pHead;
        RandomListNode pCloneHead = pHead.next;
        while(cur.next != null){
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pCloneHead;
    }

    //34二叉树中和为某一值的路径（从根节点开始<一定包括根节点>，到叶子节点<一定包括叶子节点>结束）
    // 题意：
    //  1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
    //  2.叶子节点是指没有子节点的节点
    //  3.路径只能从父节点到子节点，不能从子节点到父节点
    private static ArrayList<ArrayList<Integer>> ret1 = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target){
        backtracking1(root, target, new ArrayList<>());
        return ret1;
    }
    private static void backtracking1(TreeNode node, int target, ArrayList<Integer> path){
        if(node == null)
            return;
        path.add(node.val);
        target -= node.val;
        if(target == 0 && node.left == null && node.right == null){
            /**
             * 使用add方法的时候必须添加new ArrayList<>(path)
             *
             * 原因：因为path只用一个内存内置，会被刷新为空的
             */
            ret1.add(new ArrayList<>(path));
        }else{
            backtracking1(node.left, target, path);
            backtracking1(node.right, target, path);
        }
        // 移除最后一个元素
        path.remove(path.size() - 1);
    }

    //33二叉搜索树的后续遍历序列
    // （判断一个数组是不是二叉搜索树的后序遍历结果<因为二叉搜索树只有中序遍历是有序的>）
    public static boolean verifySequenceOfBST(int[] sequence){
        if(sequence == null || sequence.length == 0)
            return false;
        return verify(sequence, 0, sequence.length - 1);
    }
    private static boolean verify(int[] sequence, int first, int last){
        // 当只有两个数据的时候，就不用关心两个数大小了
        // 因为数据可能是：根左子树，根右子树
        if(last - first <= 1)
            return true;
        // 获得当前循环的根节点
        int rootVal = sequence[last];
        int cutIndex = first;
        // 到当前rootVal为根节点的 左子树节点(first至cutIndex)
        // cutIndex是右子树的第一个数
        while(cutIndex < last && sequence[cutIndex] <= rootVal)
            cutIndex++;
        // 如果在右子树有比根节点小的，则返回false(结果会透传到最外层)
        for(int i = cutIndex; i < last; i++)
            if(sequence[i] < rootVal)
                return false;
        return verify(sequence, first, cutIndex - 1) && verify(sequence, cutIndex, last - 1);
    }

    //32.3按之字形顺序打印二叉树(之字型顺序：就是不同层头尾相接的顺序)
    // 使用队列的特性，存储不同的
    public static  ArrayList<ArrayList<Integer>> print(TreeNode pRoot){
        ArrayList<ArrayList<Integer>> ret22 = new ArrayList<>();
        Queue<TreeNode>  queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while(!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            // 需要记录本层的节点数
            int cnt = queue.size();
            while(cnt-- > 0){
                TreeNode node = queue.poll();
                // 因为叶子节点的左右子树是null，所以node节点可能为null
                if(node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if(reverse)
                Collections.reverse(list);
            reverse = !reverse;
            if(list.size() != 0)
                ret22.add(list);
        }
        return ret22;
    }
    //32.2把二叉树打印成多行（从上往下）
    public static ArrayList<ArrayList<Integer>> print32(TreeNode pRoot){
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while(!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while(cnt-- > 0){
                TreeNode node = queue.poll();
                if(node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            if(list.size() != 0)
                ret.add(list);
        }
        return ret;
    }
    //32.1从上往下打印二叉树（从上往下，同层从左至右打印）
    public static ArrayList<Integer> printFromTopToBottom(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> ret = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            while(cnt-- > 0){
                TreeNode t = queue.poll();
                if(t == null)
                    continue;
                ret.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return ret;
    }

    //31栈的压入、弹出序列（有两个整数序列，判断前一个序列是压入，判断后一个序列是否是压出？）
    public static boolean isPopOrder(int[] pushSequence, int[] popSequence){
        int n = pushSequence.length;
        //就是用一个队列去压入stack，另一个队列按顺序去压出，最后看stack是否为空
        Stack<Integer> stack = new Stack<>();
        for(int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++){
            stack.push(pushSequence[pushIndex]);
            /**
             * 关键点：尽快压出(按照弹出序列)
             */
            while(popIndex < n && !stack.isEmpty() && stack.peek() == popSequence[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();//如果为空表示就是另一个序列的压出
    }

    //30包含min函数的栈（自定义栈可以找到最小元素）
    private static Stack<Integer> dataStack = new Stack<>();
    private static Stack<Integer> minStack30 = new Stack<>();
    public static void push(int node){
        dataStack.push(node);
        //每次都压入一个最小栈
        minStack30.push(minStack30.isEmpty() ? node : Math.min(minStack30.peek(), node));
    }
    public static void pop(){
        dataStack.pop();
        //压出栈的时候不需要判断
        minStack30.pop();
    }
    public static int top(){
        return dataStack.peek();
    }
    public static int min(){
        return minStack30.peek();
    }

    //29顺时针打印矩阵
    public static ArrayList<Integer> printMatrix(int[][] matrix){
        ArrayList<Integer> ret = new ArrayList<>();
        // row是行，column是列
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while(r1 <= r2 && c1 <= c2){
            // 打印最上一行
            /**
             * 不用担心下面图形
             * X X X
             * X O X
             * X X X
             * 因为O只会进入第一个for循环(下面的for循环)
             */
            for(int i = c1; i <= c2; i++)
                ret.add(matrix[r1][i]);
            // 打印右侧一列
            for(int i = r1 + 1; i <= r2; i++)
                ret.add(matrix[i][c2]);
            // 打印最下一行(需要判断行)
            if(r1 != r2)
                for(int i = c2 - 1; i >= c1; i--)
                    ret.add(matrix[r2][i]);
            // 打印左侧一行(需要判断列)
            if(c1 != c2)
                for(int i = r2 - 1; i > r1; i--)
                    ret.add(matrix[i][c1]);
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ret;
    }

    //28对称的二叉树
    public static boolean isSymmetrical(TreeNode pRoot){
        if(pRoot == null)
            return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }
    public static boolean isSymmetrical(TreeNode t1, TreeNode t2){
        // 下面方法不能合并，因为需要一个方法返回true
        if(t1 == null && t2 == null)
            return true;
        if(t1 == null || t2 == null)
            return false;
        // 判断值
        if(t1.val != t2.val)
            return false;
        // 按照对称设置入参（两边对，中间对）
        return isSymmetrical(t1.left, t2.right) && isSymmetrical(t1.right, t2.left);
    }

    //27二叉树的镜像
    //每个子树都需要进行交换，父节点需要先交换
    public static void mirror(TreeNode root){
        if(root == null)
            return;
        // 父节点交换
        swap(root);
        // 左右子树交换
        mirror(root.left);
        mirror(root.right);
    }
    private static void swap(TreeNode root){
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }

    //26树的子结构（判断一个树是否是另一个树的子结构）
    // (可以是根节点相同，或中间相同，或叶子节点相同)
    // root1是父树(大的树)，root2是子树(小的树)
    public static boolean hasSubTree(TreeNode root1, TreeNode root2){
        // 子树和父树有一个为null，则为false。（之后方法就容忍子树的枝子节点为null）
        if(root1 == null || root2 == null)
            return false;
        return isSubTreeWithRoot(root1, root2) || hasSubTree(root1.left, root2) || hasSubTree(root1.right, root2);
    }
    // 判断root2是否和root1 从"根节点"开始相同（父树不用到子节点结束）
    // 特例：
    //  如果root2为null，则为true。认为也是从根节点相同
    private static boolean isSubTreeWithRoot(TreeNode root1, TreeNode root2){
        if(root2 == null)
            return true;
        if(root1 == null)
            return false;
        if(root1.val != root2.val)
            return false;
        return isSubTreeWithRoot(root1.left, root2.left) && isSubTreeWithRoot(root1.right, root2.right);
    }

    //25合并两个排序的链表（升序排序，从小到大）
    /**
     * 本方法不需要额外空间,但输入的链表被改变了
     */
    public static ListNode mergeSortList(ListNode list1, ListNode list2){
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        /**
         * 通过赋值XXX.next 来继续判断之后的值
         */
        // 这个方法已经选择了list1第一个值
        if(list1.val <= list2.val){
            list1.next = mergeSortList(list1.next, list2);
            return list1;
        }else{// 这个方法已经选择了list2第一个值
            list2.next = mergeSortList(list1, list2.next);
            return list2;
        }
    }

    //24反转链表（递归）
    public static ListNode reverseList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode next = head.next;//暂存第二个指针之后的指针
        head.next = null;//第一个指针的next为空 （node1 ——> nodes 变为 node1 ——> null）
        ListNode newHead = reverseList(next);
        next.next = head;//第二个指针的next是第一个指针(node1 ——> node2 ——> nodes 变为 node1 <—— node2 <—— nodes)
        return newHead;
    }
    //24反转链表（迭代：头插法）
    public static ListNode reverseList2(ListNode head){
        ListNode newList = new ListNode(-1);
        while(head != null){
            ListNode next = head.next;//暂存head指针之后的链表
            head.next = newList.next;//head的next为新指针头的next
            newList.next = head;//新指针的next的为head
            head = next;//之后把暂存的next赋值给head
        }
        return newList.next;
    }

    //23链表中环的入口节点（不能使用额外的空间）
    public ListNode entryNodeOfLoop(ListNode pHead){
        if(pHead == null || pHead.next == null)
            return null;
        ListNode slow = pHead, fast = pHead;
        do{
            fast = fast.next.next;
            slow = slow.next;
        }while(slow != fast);//找到相遇的点，相遇的点不是入口（但是这个点和重回点）
        fast = pHead;
        while(slow != fast){//从两个不同的点出发，到入口点
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    //22链表中倒数第k个节点
    public static ListNode findKthToTail(ListNode head, int k){
        if(head  == null)
            return null;
        ListNode p1 = head;
        while(p1 != null && k-- > 0)//让p1指针走到第k个节点,p1走的长度为k（总长度为sumLength）
            p1 = p1.next;
        if(k > 0)
            return null;
        ListNode p2 = head;
        while(p1 != null){
            p1 = p1.next;//p1走到终点，则走了sumLength - k长度
            p2 = p2.next;//p2则走了sumLength - (sumLength - k) = k
        }
        return p2;
    }

    //21调整数组顺序使奇数位于偶数前面（但是奇数和偶数的相对顺序不变，这个和《剑指offer》的书上不相同的）
    //21.创建一个新的数组，时间复杂度为O(N)，空间复杂度为O(N)
    public static void reOrderArray(int[] nums){
        int oddCnt = 0;//奇数个数
        for(int x : nums)
            if(!isEven(x))
                oddCnt++;
        int[] copy = nums.clone();
        int i = 0, j = oddCnt;//i指针是用来存放奇数，j指针是用来存放偶数
        for(int num : copy){
            if(num % 2 == 1)
                nums[i++] = num;
            else
                nums[j++] = num;//因为j++的值不会变化，所以oddCnt正好是存储第一个偶数
        }
    }
    private static boolean isEven(int x){
        return x % 2 == 0;
    }
    //21.冒泡思想，时间复杂度为O(N^2),空间复杂度为O(1)（从右至左每次都将当前偶数上浮到当前的最右边）
    public static void reOrderArray1(int[] nums){
        int N = nums.length;
        for(int i = N - 1; i > 0; i--)
            for(int j = 0; j < i; j++)
                if((nums[j] % 2 == 0) && (nums[j + 1] % 2 != 0)){//当j指针是奇数且j+1指针为偶数时，则交换两个数字
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
    }

    //20表示数值的字符串（看字符串是不是一个数值，包括-、+、E、e）
    //20.使用正则表达式进行匹配（[]:字符集合、():分组、?:重复0-1次、+:重复1—n次、*:重复0-n次、.:任意字符）
    public static boolean isNumeric(char[] str){
        if(str == null || str.length == 0)
            return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)");
    }
    //20.使用自己写的代码
    private static int index = 0;
    public static boolean isMumeric1(char[] str){
        if(str.length < 1)
            return false;
        boolean flag = scanInteger(str);
        if(index < str.length && str[index] == '.'){
            index++;
            flag = scanUnsignedInteger(str) || flag;
        }
        if(index < str.length && (str[index] == 'E' || str[index] == 'e')){
            index++;
            flag = flag && scanInteger(str);
        }
        return flag && index == str.length;
    }
    private static boolean scanInteger(char[] str){
        if(index < str.length && (str[index] == '+' || str[index] == '-'))
            index++;
        return scanUnsignedInteger(str);
    }
    private static boolean scanUnsignedInteger(char[] str){
        int start = index;
        while(index < str.length && str[index] >= '0' && str[index] <= '9')
            index++;
        return start < index;//是否存在整数
    }

    //19正则表达式匹配（用函数来匹配包括：'.'和'*'的正则表达式。'.'表示任意一个字符，'*'表示它前面的字符可以出现任意次数<包括0次>）
    public static boolean match(char[] str, char[] pattern){
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++)
            if(pattern[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if(str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if(pattern[j - 1] == '*')
                    if(pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.'){
                        dp[i][j] |= dp[i][j - 1];//a*:一个a
                        dp[i][j] |= dp[i - 1][j];//a*:多个a
                        dp[i][j] |= dp[i][j - 2];//a*:空
                    }else
                        dp[i][j] = dp[i][j - 2];
        return dp[m][n];
    }

    //18.2删除链表中的重复节点(重复节点就是：val的值是相同的)
    public static ListNode deleteDuplication(ListNode pHead){
        if(pHead == null || pHead.next == null)
            return pHead;
        ListNode next = pHead.next;
        if(pHead.val == next.val){//去除重复的节点
            while(next != null && pHead.val == next.val)
                next = next.next;
            return deleteDuplication(next);
        }else{//把不重复的节点放在头指针后面
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    //对"链表的指针"和"新建一个链表"要区分！！！！！！！！！！！
    //18.1在O(1)时间内删除链表节点（处理把链表全部复制一遍，不然对一个节点的复制，作用只是有个指针！！！！！！！！！）（1：不是尾节点，2：是尾结点）
    public static ListNode deleteNode(ListNode head, ListNode tobeDelete){
        if(head == null || tobeDelete == null)
            return null;
        if(tobeDelete.next != null){//要删除的节点中间和开头节点
            ListNode next = tobeDelete.next;
            tobeDelete.val = next.val;
            tobeDelete.next = next.next;
        }else{
            if(head == tobeDelete)//原链表只有一个节点
                head = null;
            else{//有
                ListNode cur = head;//cur只是一个指针，链表一直只有一个！！
                while(cur.next != tobeDelete)
                    cur = cur.next;
                cur.next = null;
            }
        }
        return head;
    }

    //17打印从1到最大的n位数（比如输入3，则打印1，2，3至999）
    public static void print1ToMaxOfNDigits(int n){
        if(n <= 0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }
    private static void print1ToMaxOfNDigits(char[] number, int digit){
        if(digit == number.length){//当digit == number.length时候，刚刚超过数组的下表值
            printNumber(number);//总共只存储一个数字
            return;
        }
        for(int i = 0; i < 10; i++){//n为几，则进入几次递归循环，总循环次数为10^n
            number[digit] = (char)(i + '0');//为对应的位数设置值
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }
    private static void printNumber(char[] number){//把number中的字符串打印出来
        int index = 0;
        while(index < number.length && number[index] == '0')//前面是0的不要打印，直接index++
            index++;
        while(index < number.length)//循环结束则是打印一个数字
            System.out.print(number[index++]);
        System.out.println();
    }

    //16数值的整数次方(使用递归的方法找到规律：因为X^n = (x * x)^(n/2) 或 = x * (x * x)^(n/2))
    public static double power(double base, int exponent){
        if(exponent == 0)//为递归使用
            return 1;
        if(exponent == 1)//为递归使用
            return base;
        boolean isNegative = false;
        if(exponent < 0){
            exponent = - exponent;//需要先把负的幂值转为正的
            isNegative = true;//记录是否是负的幂
        }
        double pow = power(base * base, exponent / 2);
        if(exponent % 2 != 0)//如果幂为奇数，则需要再乘一个底数
            pow = pow * base;//对应的就是：x * (x * x)^(n / 2)
        return isNegative ? 1 / pow : pow;//对正负幂做出判断
    }

    //15二进制中1的个数
    //15.1使用自己写的方法
    public static int numberOf1(int n){
        int cnt = 0;
        while(n != 0){
            cnt++;
            n &= (n - 1);//这个方法可以把二进制数最右边的1置为0
        }
        return cnt;
    }
    //15.2使用Integer自带的方法
    public static int numberOf11(int n){
        return Integer.bitCount(n);
    }

    //14剪绳子（把一根绳子剪成多段，使得每段长度的乘积最大）（尽量多剪成长度为3的绳子，且不允许有长度为1的绳子）
    //14.1数学思路解法（剪尽量多长度为3的绳子）
    public static int integerBreak(int n){
        if(n < 2)
            return 0;
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;
        int timesOf3 = n / 3;
        if(n - timesOf3 * 3 == 1)//如果最后剩下的长度为1
            timesOf3--;
        int timesOf2 = (n - timesOf3 * 3) / 2;//余数可能是0(timesOf3正好)、1(timesOf3余2)、2（timesOf3余1）
        return (int)(Math.pow(3, timesOf3)) * (int)(Math.pow(2, timesOf2));
    }
    //14.2动态规划(从长度为2开始，一直到目标长度)
    public static int integerBreak1(int n){
        int[] dp = new int[n + 1];
        dp[1] = 1;//设置初始值
        for(int i = 2; i <= n; i++)//从2开始，因为长度为1进入此判断没有意义
            for(int j = 1; j < i; j++)//第一刀剪的长度范围为(1-i)
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));//判断：当前dp[i]、总共剪一刀j * (i - j)、剪多刀dp[j] * (i - j)
        return dp[n];
    }

    //13机器人的运动范围（使用DFS）（本题DFS特例是使用回溯，需要保存局部状态。普通的DFS不需要保存局部状态）（深度优先搜索DFS，广度优先搜索BFS）（<25，37>的值为3+5+3+7=18）
    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//机器人的运动方向（这个运动方向很有必要，因为可能存在之前的路是不能通过，但最后的路可以通过）
    private static int cnt1 = 0;
    private static int rows;//把入参记录为全局变量
    private static int cols;//把入参记录为全局变量
    private static int threshold;//把入参记录为全局变量
    private static int[][] digitSum;
    public static int movingCount(int thresholdTemp, int row, int col){
        rows = row;
        cols = col;
        threshold = thresholdTemp;
        //初始数组，让数组中记录:行坐标和列坐标的数位之和
        initDigitSum();

        boolean[][] marked = new boolean[row][col];//标记是否走过
        //开始深度搜索
        dfs(marked, 0, 0);

        return cnt1;
    }
    private static void initDigitSum(){
        int[] digitSumOne = new int[Math.max(rows, cols)];//行坐标或列坐标的数位之和
        for(int i = 0; i < digitSumOne.length; i++){
            int n = i;
            while(n > 0){
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        digitSum = new int[rows][cols];
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
                digitSum[i][j] = digitSumOne[i] + digitSumOne[j];//行坐标和列坐标的数位之和
    }
    private static void dfs(boolean[][] marked, int r, int c){
        if(r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c])//走过的、行列指针越界的都直接返回
            return;
        marked[r][c] = true;
        if(digitSum[r][c] > threshold)//如果不符合小于阈值，则直接返回
            return;
        cnt++;//记得要加
        for(int[] n : next)//以当前点出发，向4个方向出发
            dfs(marked, r + n[0], c + n[1]);
    }

    //12矩阵中的路径（回溯法）（判断在一个矩阵中是 否存在一条包含某字符串所有字符的路径，不能走回头路）
    private static final int[][] next1 = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static int rows1;
    private static int cols1;
    public static boolean hasPath(char[] array, int rows, int cols, char[] str){
        if(rows == 0 || cols == 0)
            return false;
        rows1 = rows;
        cols1 = cols;
        boolean[][] marked = new boolean[rows][cols];//用于记录是否走过
        char[][] matrix = buildMatrix(array);//把一维数组设置改为二维数组
        for(int i = 0; i < rows; i++)//此循环条件是设置开始位置
            for(int j = 0; j < cols; j++)
                if(backtracking2(matrix, str, marked, 0, i, j))
                    return true;
        return false;
    }
    private static boolean backtracking2(char[][] matrix, char[] str, boolean[][] marked, int pathLen, int r, int c){//pathLen:已经匹配的长度，r、c为当前位置
        if(pathLen == str.length)
            return true;
        if(r < 0 || r >= rows1 || c < 0 || c >= cols1 || matrix[r][c] != str[pathLen] || marked[r][c])//判断当前的不符合条件
            return false;
        marked[r][c] = true;//将当前位置置为走过
        for(int[] n : next)//走4个方向
            if(backtracking2(matrix, str, marked, pathLen + 1, r + n[0], c + n[1]))
                return true;
        marked[r][c] = false;//把当前位置重置为未走过
        return false;//返回false
    }
    private static char[][] buildMatrix(char[] array){
        char[][] matrix = new char[rows1][cols1];
        for(int r = 0, idx = 0; r < rows1; r++)
            for(int c = 0; c < cols1; c++)
                matrix[r][c] = array[idx++];
        return matrix;
    }

    //11旋转数组的最小数字（排序好的数组之后，头尾相接之后切分。这样形成旋转数组）
    //11.1数组中无重复数字(下面方法不完全准确)
    public static int minNumberInRotateArray(int[] nums){
        if(nums.length == 0)
            return 0;
        int l = 0, h = nums.length - 1;
        while(l < h) {//使用二分查找去找最小的数
            int m = l + (h - l) / 2;
            if(nums[m] <= nums[h])//当h不大于m时，则重置h的值
                h = m;
            else//当m大于h时，表示最小值在比m值大的索引位置
                l = m + 1;//位置为m + 1
        }
        return nums[l];//l为最小值的位置
    }
    //11.2数组中有重复数字（下面方法适用于所有类型的输入）（原因是无法判断：nums[l] == nums[m] == nums[h]时，该走哪个区间）
    public static int minNumberInRotateArray1(int[] nums){
        if(nums.length == 0)
            return 0;
        int l = 0, h = nums.length - 1;
        while(l < h) {
            int m = l + (h - l) / 2;
            if(nums[l] == nums[m] && nums[m] == nums[h])//这种情况只能使用笨办法进行查找
                return minNumber(nums, l, h);
            else if(nums[m] <= nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }
    private static int minNumber(int[] nums, int l, int h){
        for(int i = l; i < h; i++)//按正常顺序找到最小值
            if(nums[i] > nums[i + 1])
                return nums[i + 1];
        return nums[l];//如果值都是一样的，则取第一个
    }

    //10.4变态跳台阶(青蛙一次可以跳1级台阶、2级台阶...n级台阶)
    //10.4.2动态规划
    public static int jumpFloorII(int target){
        int[] dp = new int[target];
        Arrays.fill(dp, 1);//每个数组的初始值都要设置为1
        for(int i = 1; i < target; i++)
            for(int j = 0; j < i; j++)
                dp[i] += dp[j];
        return dp[target - 1];
    }
    //10.4.1数学推导（规律是等比数列）
    public static int jumpFloorII1(int target){
        return (int)Math.pow(2, target - 1);
    }
    //10.3跳台阶（一次可以跳1级或2级台阶）（使用数学规律：f(n) = f(n - 1) + f(n - 2)）
    public static int jumpFloor(int n){
        if(n <= 2)
            return n;
        int pre2 = 1, pre1 = 2;
        int result = 0;
        for(int i = 2; i < n; i++){
            result = pre2 + pre1;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }
    //10.2矩形覆盖（使用2*1的矩形去覆盖2*n的矩形，总共有多少种可能）（还是有数学规律：f(n) = f(n - 1) + f(n - 2)）（斐波那契方法）
    public static int rectCover(int n){
        if(n <= 2)
            return n;
        int pre2 = 1, pre1 = 2;
        int result = 0;
        for(int i = 3; i <= n; i++){
            result = pre2 + pre1;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }
    //10.1斐波那契数列（代码类似上面代码）

    //9用两个栈实现队列（从先进后出，改为先进先出）
    private static Stack<Integer> in = new Stack<>();
    private static Stack<Integer> out = new Stack<>();
    public static void push1(int node){
        in.push(node);
    }
    public static int pop1() throws Exception{
        if(out.isEmpty())//如果out队列为空，则把in里面的数全部"取出放到"out里面
            while(!in.isEmpty())
                out.push(in.pop());
        if (out.isEmpty())
            throw new Exception("queue is empty");
        return out.pop();//所有的数都要是通过out出去
    }

    //8二叉树的下一个节点（中序遍历的下一个节点）(TreeLinkNode是包含父节点的)
    public static TreeLinkNode getNext(TreeLinkNode pNode){
        if(pNode.right != null){//如果输入节点的右节点存在，则下一个节点就是"右节点"的"最左节点"
            TreeLinkNode node = pNode.right;
            while(node.left != null)
                node = node.left;
            return node;
        }else{
            while(pNode.next != null){//如果输入节点的右节点不存在，则下一个节点就是"父节点"的"其中之一"
                TreeLinkNode parent = pNode.next;
                if(parent.left == pNode)//如果父节点的"左子树"是输入节点，则此就是下一个节点
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }

    //7重建二叉树（根据二叉树的前序遍历和中序遍历的结果，重建该二叉树）（补：遍历结果中不含重复的数字）
    private static Map<Integer, Integer> indexForInOrders = new HashMap<>();//缓存中序遍历数组每个值对应的索引
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in){
        for(int i = 0; i < in.length; i++)
            indexForInOrders.put(in[i], i);
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }
    private static TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL){
        if(preL > preR)
            return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);//中序遍历的位置
        int leftTreeSize = inIndex - inL;//左子树长度
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

    //6从尾到头打印链表（就是把链表的头尾反转后放入list中）
    //6.1使用递归（是否可以改变此递归的处理方式？？？）
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode){
        ArrayList<Integer> ret = new ArrayList<>();
        if(listNode != null){
            ret.addAll(printListFromTailToHead(listNode.next));//为什么使用此方法？
            ret.add(listNode.val);
        }
        return ret;
    }
    //6.2使用头插法
    public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode){
        //头插法构建逆序链表(把老的链表从头至尾依次，放在新的头指针的后面)
        ListNode head = new ListNode(-1);
        while(listNode != null){
            ListNode memo = listNode.next;//临时存放老的链表next的数
            listNode.next = head.next;//用新的头指针next的数，覆盖老的链表next的数
            head.next = listNode;//把新的头指针next的数，置为新的链表
            listNode = memo;//把暂存的老链表next，重新赋值给老链表
        }
        //构建ArrayList
        ArrayList<Integer> ret = new ArrayList<>();
        head = head.next;
        while(head != null){
            ret.add(head.val);
            head = head.next;
        }
        return ret;
    }
    //6.3使用栈
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode){
        Stack<Integer> stack = new Stack<>();
        while(listNode != null){
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        while(!stack.isEmpty())
            ret.add(stack.pop());
        return ret;
    }

    //5替换空格（把字符串中的"空格"替换为"%20"）
    public static String replaceSpace(StringBuffer str){
        int P1 = str.length() - 1;
        for(int i = 0; i <= P1; i++)
            if(str.charAt(i) == ' ')
                str.append("   ");
        int P2 = str.length() - 1;//P2是尾指针，使用头指针也可以
        while(P1 >= 0 && P2 > P1){
            char c = str.charAt(P1--);
            if(c == ' '){
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            }else{
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }

    //4二维数组中的查找（二维数组是从左至右、从上至下递增）
    public static boolean find(int target, int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1;//从右上角开始
        while(r <= rows - 1 && c >= 0){
            if(target == matrix[r][c])
                return true;
            else if(target > matrix[r][c])
                r++;
            else
                c--;
        }
        return false;
    }

    //3数组中的重复数字（找出任意一个重复的数字，可能还有多个重复的数字）(时间复杂度为O(N),空间复杂度为O(1))
    public static boolean duplicate(int[] nums, int  length, int[] duplication){
        if(nums == null || length <= 0)
            return false;
        for(int i = 0; i < length; i++){
            while(nums[i] != i){//判断条件是：将值为i的元素调整到第i个位置上
                if(nums[i] == nums[nums[i]]){//如果元素，和此索引为此元素的数相同，则这个就是重复数字（不用担心此元素的索引和值相同，因为上面while判断条件已经把此种情况排除掉了）
                    duplication[0] = nums[i];//duplication用于存放重复的"一个"数字
                    return true;
                }
                swap(nums, i, nums[i]);//交互数字（此方法在上面的代码中出现过）
            }
        }
        return false;
    }

    //61扑克牌顺子（总共5张牌，牌面为0的是癞子，结果判断五张牌是否可以组成顺子）
    public static boolean isContinuous(int[] nums){
        if(nums.length < 5)
            return false;
        Arrays.sort(nums);
        //统计癞子数量
        int cnt = 0;
        for(int num : nums)
            if(num == 0)
                cnt++;
        //使用癞子去补全不连续的顺子
        for(int i = cnt; i < nums.length - 1; i++){
            if(nums[i + 1] == nums[i])
                return false;
            cnt -= nums[i + 1] - nums[i] - 1;
        }
        return cnt >= 0;
    }

    //62圆圈中最后剩下的数(索引从0开始，到 n-1 结束)（约瑟夫环问题：长度为n的解可以看成长度为"n-1"的解再加上报数的长度m，因为是圆圈，所以最后需要对n取余）（返回的是索引，从0开始。n是圆圈长度，m是报数长度）
    //62.1数学思路（使用动态规划方法：f(n, m) = (f(n - 1, m) + m) % n）
    public int lastRemainingSolution(int n, int m){
        if(n == 0)//特殊输入处理
            return -1;
        if(n == 1)//递归返回条件
            return 0;
        return (lastRemainingSolution(n - 1, m) + m) % n;
    }
    //62.2常规解法（删除数组）
    public int lastRemainingSolution1(int n, int m){
        if(m == 0 || n == 0)
            return -1;
        ArrayList<Integer> data = new ArrayList<>();
        for(int i = 0; i < n; i++)
            data.add(i);
        int index = -1;
        while(data.size() > 1){
            // index+m 就是新报数中的索引
            // 如果这个值小于数组长度，则新的index就是index+m本身。
            // 如果这个值大于数组长度，则新的index就是(index+m)%size
            index = (index + m ) % data.size();
            data.remove(index);
            //因为删除了一个数，所以index需要自减一
            index--;
        }
        return data.get(0);
    }
    //62.3常规解法（不删除数组）（符合正常人思维）
    public static int findLastNumber(int n, int m){
        if(n < 1 || m < 1)
            return -1;
        int[] array = new int[n];
        //i为报数的位置，step为有效报数人数的累计，count为记录移除（n-1）个数
        int i = -1, step = 0, count = n;
        while(count > 0){//跳出循环时将最后一个元素也设置为-1
            //是上一个报数的下一个
            i++;
            //模拟环，如果报数的i超过队列长度，则重置为0
            if(i >= n)
                i = 0;
            //跳过被删除的对象，不计入step
            if(array[i] == -1)
                continue;
            //累计有效报数人数
            step++;
            //找到被删除的索引
            if(step == m){
                array[i] = -1;
                step = 0;
                count--;
            }
        }
        return i;//返回跳出循环时的i，即最后一个被设置为-1的元素
    }

    //63股票的最大利润
    //63.1（一次买入，一次卖出）
    public static int maxProfit(int[] prices){
        if(prices == null || prices.length == 0)
            return 0;
        int minPrice = prices[0];//记录最低的股价
        int maxProfit = 0;//记录最高利润
        for(int i = 1; i < prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
    //63.2 多次卖出(买卖可以在同一天)(只要后一天大于前一天都算是收益)
    public static int maxProfit1(int[] prices) {
        if (prices.length == 0)
            return 0;
        int profit=0;
        for (int i=0; i<prices.length-1; i++)
            if (prices[i+1]-prices[i] > 0)
                profit = profit + prices[i+1]-prices[i];
        return profit;
    }
    //63.3 最多交易两次（目的是找到两个不重合区间的利润和为最大值）（最多买两次，卖两次。而且在第二次买之前，必须要卖掉自己手里的股票）
    //63.3-1 注：会有交易一次的时候，所以在循环遍历的时候要考虑这种情况
    //时间复杂度是O(n ^ 2)。空间复杂度是O(1)
    public static int maxProfit2_1 (int[] prices){
        if(prices.length == 0)
            return 0;
        int out = 0;
        int firstSell;
        int secondSell = prices.length - 1;
        //优化第二次卖出的时机
        while(secondSell > 0 && prices[secondSell - 1] >= prices[secondSell])
            secondSell--;
        for(firstSell = 1;firstSell < prices.length; firstSell++) {
            //优化第一次卖出的时机
            while(firstSell < prices.length - 1 && prices[firstSell + 1] > prices[firstSell])
                firstSell++;
            //注意临界值的问题！！
            int l = tempMax(prices, 0, firstSell);
            //注意临界值的问题！！
            int r = tempMax(prices, firstSell+1, secondSell);
            out = Math.max(out, l + r);
        }
        return out;
    }
    private static int tempMax(int[] n, int x, int y){
        int out = 0;
        //提前退出循环
        if(x >= y)
            return out;
        int min = n[0];
        //注意临界值的问题！！
        for(int i = x + 1; i <= y; i++){
            min = Math.min(n[i], min);
            out = Math.max(out, n[i] - min);
        }
        return out;
    }
    /**
     * 动态规划的思路：
     *      状态定义：f(x, y) -------- 第x笔交易在第y天能取得的最大利润
     *      状态转移：
     *      （1）当x == 1时
     *         a：我们可以选择在第y天不卖出也不买入，
     *          a-1：如果此时y == 0，即第0天，那么f(1, 0) = 0，即取得的最大利润为0。
     *          a-2：如果此时y > 0，那么此时我们的第x笔交易在第y天能取得的最大利润是f(1, y - 1)，因为我们在第y天既不买入也不卖出，取得的最大利润自然和第x笔交易在第y - 1天能取得的最大利润相同。
     *         b：我们可以选择在第y天卖出，
     *          b-1：如果此时y == 0，显然，我们不可能在第0天卖出，这种情况不予讨论。
     *          b-2：如果此时y > 0，f(x, y) = max(prices[y] - prices[b])，其中0 <= b < y，代表我们在第b天买入。
     *         综上，对于x == 1的情况，当y == 0时，f(1, 0) = 0；当y > 0时，f(1, y) = max(f(1, y - 1), prices[y] - prices[b])，0 <= b < y。
     *      （2）当x == 2时
     *         a：我们可以选择在第y天不卖出也不买入，
     *          a-1：如果此时y == 0，即第0天，那么f(x, y) = 0，即取得的最大利润为0。
     *          a-2：如果此时y > 0，那么此时我们的第x笔交易在第y天能取得的最大利润是f(2, y - 1)，因为我们在第y天既不买入也不卖出，取得的最大利润自然和第x笔交易在第y - 1天能取得的最大利润相同。
     *         b：我们可以选择在第y天卖出，
     *          b-1：如果此时y == 0，显然，我们不可能在第0天卖出，这种情况不予讨论。
     *          b-2：如果此时y > 0，f(x, y) = max(prices[y] - prices[b] + f(1, b - 1))，其中0 <= b < y，代表我们在第b天买入。
     *         综上，对于x == 2的情况，当y == 0时，f(2, 0) = 0；当y > 0时，f(2, y) = max(f(2, y - 1), prices[y] - prices[b] + f(1, b - 1))，0 <= b < y。
     */
    //63.3-2 动态规划
    //时间复杂度是O(kn ^ 2)，其中k为交易次数，n为prices数组的大小。空间复杂度是O(kn)
    public static int maxProfit2_2(int[] prices){
        int out = 0;
        if(prices.length == 0)
            return out;
        int[][] dp = new int[2][prices.length];
        for(int k = 0; k < 2; k++){
            dp[k][0] = 0;
            int min = prices[0];
            for(int i = 1; i < prices.length; i++) {
                for(int j = 1; j < i; j++){
                    if(k == 0) {
                        min = Math.min(min, prices[j]);
                    }else{
                        //在这里加上了第一次交易的收益dp[k - 1][j - 1]
                        //通过下面的判断，第一次交易的收益一定会加到第二次收益里面的
                        min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
                    }
                }
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);

            }
        }
        out = dp[1][prices.length - 1];
        return out;
    }
    //63.3-3 动态规划改进1
    //上种解题方法中：我们对第一笔交易和第二笔交易进行了分别计算，因为第1笔交易的前一笔交易不存在，会产生数组越界问题。
    //我们可以在第一笔交易前增加一笔虚拟的第0笔交易，其f(0, y)均为0，这样就避免了对第一笔交易和第二笔交易的讨论。
    //时间复杂度和空间复杂度均是O(kn)，其中k为交易次数，n为prices数组的大小。
    public static int maxProfit2_3(int[] prices) {
        int result = 0;
        if (0 == prices.length)
            return result;
        int[][] dp = new int[3][prices.length];
        for (int k = 1; k <= 2; k++) {
            dp[k][0] = 0;
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
                min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
            }
        }
        return dp[2][prices.length - 1];
    }
    //63.3-4 动态规划改进1的另一种形式(交换内外层循环的位置)
    //时间复杂度和空间复杂度均是O(kn)，其中k为交易次数，n为prices数组的大小。
    public static int maxProfit2_4(int[] prices) {
        int result = 0;
        if(0 == prices.length)
            return result;
        int[][] dp = new int[3][prices.length];
        int[] min = new int[3];
        //初始值在外层设置
        for(int i = 1; i < 3; i++)
            min[i] = prices[0];
        for(int i = 1; i < prices.length; i++){
            for(int k = 1; k <= 2; k++){
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min[k]);
                min[k] = Math.min(min[k], prices[i] - dp[k - 1][i - 1]);
            }
        }
        return dp[2][prices.length - 1];
    }
    //63.3-5 动态规划改进2(在上面解题中，第i列的变量只依赖于第i - 1列的变量，所以压缩该维度)
    //时间复杂度是O(kn)，其中k为交易次数，n为prices数组的大小。空间复杂度是O(k)。
    public static int maxProfit2_5(int[] prices) {
        int result = 0;
        if(0 == prices.length)
            return result;
        int[] dp = new int[3];
        int[] min = new int[3];
        for(int i = 1; i < 3; i++){
            min[i] = prices[0];
        }
        for(int i = 1; i < prices.length; i++){
            for(int k = 1; k <= 2; k++){
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
            }
        }
        return dp[2];
    }
    //63.3-6 动态规划改进3
    //时间复杂度是O(n)，n为prices数组的大小。空间复杂度是O(1)。
    public static int maxProfit2_6(int[] prices) {
        int buy1 = Integer.MAX_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MAX_VALUE;
        int sell2 = 0;
        for(int i = 0; i < prices.length; i++){
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy1 = Math.min(buy1, prices[i]);
            sell2 = Math.max(sell2, prices[i] - buy2);
            buy2 = Math.min(buy2, prices[i] - sell1);
        }
        return sell2;
    }

    //64求1+2+3+...+n(不能使用基本的逻辑判断方法,如：乘除法、for、while、if、else、switch、case、A?B:C)
    public static int sumSolution(int n){
        int sum = n;
        boolean b = (n > 0) && ((sum += sumSolution(n - 1)) > 0);//使用&&短路的特性
        return sum;
    }

    //65不用加减乘除做加法（a ^ b表示没有考虑进位情况下两数的和， （a & b）<< 1就是进位：当此值为0时则结束递归）
    public static int add(int a, int b){
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

    //66构建乘积数组（不能使用除法）（一个数组，除了本索引位置的数，其他位置的数乘积要存放在此索引位置）
    //  给定一个数组A[0, 1, …, n-1]，请构建一个数组B[0, 1, …, n-1]，其中B中的元素B[i] =A[0]×A[1]×… ×A[i-1]×A[i+1]×…×A[n-1]（注意：里面没有A[i]）。不能使用除法。
    public static int[] multiply(int[] A){
        int n = A.length;
        int[] B = new int[n];
        for(int i = 0, t = 1; i < n; t *= A[i], i++)//因为第三3分号比之前快一步，记录索引为i的"前面"索引元素乘积，赋值给B[i]
            B[i] = t;
        for(int i = n - 1, t = 1; i >= 0; t *= A[i], i--)//因为第三3分号比之前快一步，记录索引为i的"后面"索引元素乘积，与B[i]原有数字相乘
            B[i] *= t;
        return B;
    }

    //67把字符串转换成整数（不是合法数字，就返回0）
    public static int strToInt(String str){
        if(str == null || str.length() == 0)
            return 0;
        boolean isNegative = str.charAt(0) == '-';//记录负数符号
        int ret = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(i == 0 && (c == '+' || c == '-'))//只有第一个位数为负号，才可以继续下面的循环
                continue;
            if(c < '0' || c > '9')//非数字，则直接返回0
                return 0;
            ret = ret * 10 + (c - '0');//记得要先乘以10
        }
        return isNegative ? -ret : ret;
    }

    //68树中两个节点的最低公共祖先
    //68.1二叉查找树（中序遍历，有序的树）
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null)
            return root;
        if(root.val > p.val && root.val > q.val)//根节点同时比p和q要大，表示需要找根节点的左子树
            return lowestCommonAncestor(root.left, p, q);
        if(root.val < p.val && root.val < q.val)//根节点同时比p和q要小，表示需要找根节点的右子树
            return lowestCommonAncestor(root.right, p, q);
        return root;//如果根节点大于等于一个节点，并小于等于另一个节点，则此根节点为"最低公共祖先"
    }
    //68.2普通二叉树（非有序树）
    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root.val == p.val || root.val == q.val)//找到树中q、p两个节点，如果没找到则会返回null；
            return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        //如果left为null，则返回right
        //如果right为null，则返回left
        //如果两个不是null，则返回root
        return left == null ? right : right == null ? left : root;
    }

}