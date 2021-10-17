/**
 * @author: lee1.li
 * @date: 2020/10/10 3:38 下午
 */
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;//指向父节点的指针

    TreeLinkNode(int val){
        this.val = val;
    }
}
