import java.util.Arrays;

/**
 * 认识复杂度和简单的排序算法(排序算法输出都是从小到大)
 *      常用O(读作big O)来表示：在表达式中，不要低阶项，也不要高阶系数
 *      1.1 选择排序、冒泡排序、插入排序：
 *      1.2 对数器的方法：
 *      1.3 位运算：
 */

/**
 * 排序算法总结：
 *  排序方法    时间复杂度(平均) 时间复杂度(最坏) 时间复杂度(最好) 空间复杂度 稳定性
 *
 *  插入排序        O(n2)       O(n2)           O(n)        O(1)     稳定(和冒泡排序不一样，一次遍历之后不会有最大值或最小值出现在两端)
 *  冒泡排序        O(n2)       O(n2)           O(n)        O(1)     稳定(实现的过程中需要满足只有两个相邻数交换)
 *  归并排序        O(nlog2n)   O(nlog2n)       O(nlog2n)   O(n)     稳定
 *
 *  希尔排序        O(n1.3)     O(n2)           O(n)        O(1)     不稳定
 *  选择排序        O(n2)       O(n2)           O(n2)       O(1)     不稳定(因为有横跨的交换)
 *  堆排序          O(nlog2N)   O(nlog2N)       O(nlog2n)   O(1)     不稳定
 *  快速排序        O(nlog2n)   O(n2)           O(nlog2n)   O(nlog2N) 不稳定
 *
 *  计数排序        O(n + k)    O(n + k)        O(n + k)    O(n + k) 稳定
 *  桶排序          O(n+k)     O(n2)           O(n)        O(n + k)  稳定
 *  基数排序        O(n*k)      O(n*k)          O(n*k)      O(n+k)   稳定
 *
 */

/**
 * 算法的稳定性：
 *      不稳定：因为有横跨的交换(和相邻交换不同的交换)，所以会把原来的顺序换掉
 *      举例：
 *          比如：A-80,B-80,C-70这三个卷子从小到大排序
 *              第一步会把C和A做交换变成：CBA
 *              之后就不需要交换了，所以排序完是CBA
 *              但是稳定排序是CAB
 */
public class Test01ForZou1Base01 {
    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 100;
        boolean success = true;
        for(int i = 0; i < testTime; i++){
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            insertSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1, arr2)){
                success = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(success ? "Nice！" : "No");
    }

    /**
     * 1.1 A 选择排序:从低位开始有序
     */
    public static void selectionSort(int[] arr) {
        if(arr == null || arr.length < 2)
            return;
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            simpleSwap(arr, i, minIndex);
        }
    }

    /**
     * 1.1 B 冒泡排序:从高位开始有序(非稳定性算法：这个实现中有横跨的交换)
     */
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        for(int i = arr.length - 1; i >= 0; i--){
            for(int j = i - 1; j >= 0; j--){
                if(arr[i] < arr[j])
                    bitSwap(arr, i, j);
            }
        }
    }
    /**
     * 1.1 B 冒泡排序改进1：如果全部有序则跳出循环
     */
    public static void bubbleSortOptimize1(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        for(int i = 0; i < arr.length - 1; i++){
            boolean preBreak = true;
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j + 1]) {
                    bitSwap(arr, j, j + 1);
                    preBreak = false;
                }
            }
            if(preBreak)
                break;;
        }
    }
    /**
     * 1.1 B 冒泡排序改进2：找到局部有序的索引(最优化)
     */
    public static void bubbleSortOptimize2(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        int sortBorder = arr.length - 1;
        for(int i = 0; i < arr.length; i++){
            boolean preBreak = true;
            int temp = 0;
            for(int j = 0; j < sortBorder; j++){
                if(arr[j] > arr[j+1]){
                    bitSwap(arr, j, j + 1);
                    preBreak = false;
                    //记录已有序数组的最低索引
                    temp = j;
                }
            }
            sortBorder = temp;
            if(preBreak)
                break;
        }
    }

    /**
     * 1.1 C 插入排序:和冒泡排序不一样，一次遍历之后不会有最大值或最小值出现在两端
     */
    public static void insertSort(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        for(int i = 1; i < arr.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[j] > arr[j + 1])
                    bitSwap(arr, j, j + 1);
            }
        }
    }

    /**
     * 1.1 不能使用位运算进行交换，因为i和j可能相同(如果使用位运算，数组中的arr[i]的值会被洗为0)
     */
    public static void simpleSwap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 1.1 可以使用位运算，因为i和j不会相同
     */
    public static void bitSwap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 1.2 对数器：自测代码是否正确
     */
    public static void comparator ( int[] arr){
        Arrays.sort(arr);
    }
    public static int[] generateRandomArray ( int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //下面两个随机数的相减是为了产生负数
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
    public static int[] copyArray ( int[] arr){
        if (arr == null)
            return null;
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    public static boolean isEqual ( int[] arr1, int[] arr2){
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
            return false;
        if (arr1 == null && arr2 == null)
            return true;
        if (arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
    public static void printArray ( int[] arr){
        if (arr == null)
            return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 1.3 位运算
     */
//    public static void

























}
