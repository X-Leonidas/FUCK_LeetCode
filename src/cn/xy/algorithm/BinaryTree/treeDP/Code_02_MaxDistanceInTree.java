package cn.xy.algorithm.BinaryTree.treeDP;

/**
 * @author XiangYu
 * @create2021-03-29-20:59
 *
 *  二叉树中，一个节点可以往上走和往下走，那么从节点A总能走到节点B
 *  节点A走到节点B的距离为：A走到B最短路径上的节点个数
 *  求一颗二叉树上的最远距离
 *
 *  树形DP
 *  思路：与Code_04_BiggestSubBSTInTree相同思路
 *        分为三种情况：1. 最远距离在左树上
 *                    2. 最远距离在右树上
 *                    3. 最远距离经过头节点从左子树到右子树上
 *
 *
 *   收集信息：
 *       1.最大距离
 *       2.深度
 */
public class Code_02_MaxDistanceInTree {
    /**
     * 需要返回的信息
     */
    public static class ReturnType{
        public int maxDistance;
        public int h;

        public ReturnType(int m, int h) {
            this.maxDistance = m;;
            this.h = h;
        }
    }

    /**
     *
     * @param head
     * @return
     */
    public static ReturnType process(Node head) {
        if(head == null) {
            return new ReturnType(0,0);
        }
        ReturnType leftReturnType = process(head.left);
        ReturnType rightReturnType = process(head.right);
        //三种可能性
        int includeHeadDistance = leftReturnType.h + 1 + rightReturnType.h;
        int p1 = leftReturnType.maxDistance;
        int p2 = rightReturnType.maxDistance;


        int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
        //高度
        int hitself  = Math.max(leftReturnType.h, leftReturnType.h) + 1;
        return new ReturnType(resultDistance, hitself);
    }








    public static int maxDistance(Node head) {
        int[] record = new int[1];
        return posOrder(head, record);
    }


    public static int posOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            return 0;
        }
        int lMax = posOrder(head.left, record);
        int maxfromLeft = record[0];
        int rMax = posOrder(head.right, record);
        int maxFromRight = record[0];
        int curNodeMax = maxfromLeft + maxFromRight + 1;
        record[0] = Math.max(maxfromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(maxDistance(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(maxDistance(head2));

    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

}
