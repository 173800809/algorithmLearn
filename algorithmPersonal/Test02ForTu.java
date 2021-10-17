import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author: lee1.li
 * 1.二分图：如果可以用两个颜色对图中的节点进行着色，并且保证相邻的节点颜色不同，那么这个图就是二分图
 * 参考网址：
 *      https://cloud.tencent.com/developer/article/1382422
 *      https://blog.csdn.net/qq_26822029/article/details/90382581
 *      https://www.jianshu.com/p/84a59257b0b7
 *      https://d3gt.com/unit.html
 * 2.基础知识：
 *    2.1 二分图
 * 2.判断二分图的方法：匈牙利算法、HK(Hopcroft-Karp)算法、二分图多重匹配
 */

public class Test02ForTu {
    public static void main(String[] args) {

    }

    //1.判断是否为二分图
    //示例：
    //输入：[[1,3], [0,2], [1,3], [0,2]]
    //图为：
    // 0----1
    // |    |
    // |    |
    // 3----2
    //解释：
    // [1,3]->0节点和1、3节点相连
    // [0,2]->1节点和0、2节点相连
    // [1,3]->2节点和1、3节点相连
    // [0,2]->3节点和0、2节点相连
    //答案：是二分图，结果是：{0, 2} and {1, 3}
    public static boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        for (int i = 0; i < graph.length; i++)//处理图不是联通的情况
            if (colors[i] == -1 && !isBipartite(i, 0, colors, graph))
                return false;
        return true;
    }

    private static boolean isBipartite(int curNode, int curColor, int[] colors, int[][] graph) {
        if (colors[curNode] != -1)
            return colors[curNode] == curColor;
        colors[curNode] = curColor;
        for (int nextNode : graph[curNode])
            if (!isBipartite(nextNode, 1 - curColor, colors, graph))
                return false;
        return true;
    }

    //2.非拓扑排序-课程安排的合法性（一个课程可能会先修课程，判断给定的先修课程规定是否合法）
    //不需要使用拓扑排序，只需要检测有向图是否存在环即可
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++)
            graphic[i] = new ArrayList<>();
        for (int[] pre : prerequisites)
            graphic[pre[0]].add(pre[1]);
        boolean[] globalMarked = new boolean[numCourses];
        boolean[] localMarked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++)
            if (hasCycle(globalMarked, localMarked, graphic, i))
                return false;
        return true;
    }

    private static boolean hasCycle(boolean[] globalMarked, boolean[] localMarked, List<Integer>[] graphic, int curNode) {
        if (localMarked[curNode])
            return true;
        if (globalMarked[curNode])
            return false;
        globalMarked[curNode] = true;
        localMarked[curNode] = true;
        for (int nextNode : graphic[curNode])
            if (hasCycle(globalMarked, localMarked, graphic, nextNode))
                return true;
        localMarked[curNode] = false;
        return false;
    }

    //拓扑排序-课程安排顺序（使用DFS实现拓扑排序，是用栈存储"后序遍历结果"，这个栈的"逆序结果"就是拓扑排序结果）
    //证明？？？？？：对于任何先序关系：v->w,后序遍历结果可以保证w先进入栈中，因此栈的逆序结果中v会在w之前
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++)
            graphic[i] = new ArrayList<>();
        for (int[] pre : prerequisites)
            graphic[pre[0]].add(pre[1]);
        Stack<Integer> postOrder = new Stack<>();
        boolean[] globalMarked = new boolean[numCourses];
        boolean[] localMarked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++)
            if (hasCycle(globalMarked, localMarked, graphic, i, postOrder))
                return new int[0];
        int[] orders = new int[numCourses];
        for (int i = numCourses - 1; i >= 0; i--)
            orders[i] = postOrder.pop();
        return orders;
    }

    private static boolean hasCycle(boolean[] globalMarked, boolean[] localMarked, List<Integer>[] graphic, int curNode, Stack<Integer> postOrder) {
        if (localMarked[curNode])
            return true;
        if (globalMarked[curNode])
            return false;
        globalMarked[curNode] = true;
        localMarked[curNode] = true;
        for (int nextNode : graphic[curNode])
            if (hasCycle(globalMarked, localMarked, graphic, nextNode, postOrder))
                return true;
        localMarked[curNode] = false;
        postOrder.push(curNode);
        return false;
    }

    //3.并查集-冗余连接（并查集可以动态连通两个点，并且可以发出快速地判断两个点是否连通）
    //题目描述：有一系列的边连成的图，找出一条边，移除它之后该图能够成为一棵树
    public static int[] findRedundantConnection(int[][] edges){
        int N = edges.length;
        UF uf = new UF(N);
        for(int[] e : edges){
            int u = e[0], v = e[1];
            if(uf.connect(u, v))
                return e;
            uf.union(u, v);
        }
        return new int[]{-1, -1};
    }
    private static class UF{
        private int[] id;
        UF(int N){
            id = new int[N + 1];
            for(int i = 0; i < id.length; i++)
                id[i] = i;
        }
        void union(int u, int v){
            int uID = find(u);
            int vID = find(v);
            if(uID == vID)
                return;
            for(int i = 0; i < id.length; i++)
                if(id[i] == uID)
                    id[i] = vID;
        }
        int find(int p) {
            return id[p];
        }
        boolean connect(int u, int v) {
            return find(u) == find(v);
        }
    }

}
