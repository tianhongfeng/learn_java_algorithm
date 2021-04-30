
import java.util.*;
import java.util.stream.Collectors;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    TreeNode(int val, TreeNode left, TreeNode right, TreeNode next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}

public class Binary_Tree {

    TreeNode root;

    private TreeNode addNode(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.val) {
            current.left = addNode(current.left, value);
        } else if (value > current.val) {
            current.right = addNode(current.right, value);
        } else {
            return current;
        }
        return current;
    }
    public void addNode(int value) {
        root = addNode(root, value);
    }

    // 迭代中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ll = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        while(root != null || !st.empty()){
            while(root != null){
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            ll.add(root.val);
            root = root.right;
        }
        return ll;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ll = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int n = queue.size();
        while(n != 0){
            List<Integer> ls = new ArrayList<>();
            for(int i = 0; i < n; i++){
                root = queue.poll();
                ls.add(root.val);
                if(root.left != null) queue.add(root.left);
                if(root.right != null) queue.add(root.right);
            }
            n = queue.size();
            ll.add(ls);
        }
        return ll;
    }

    int max = 0;
    public int maxDepth(TreeNode root) {
        int n = 0;
        maxDepth_(root, n);
        return max;
    }
    public void maxDepth_(TreeNode root, int n){
        if(root == null) return;
        if(root.left == null && root.right == null) {
            max = Math.max(max, n + 1);
            return;
        }
        if(root.left != null) maxDepth_(root.left, n+1);
        if(root.right != null) maxDepth_(root.right, n+1);
    }
    /**
     * 镜像二叉树
      */
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    public boolean isSymmetric(TreeNode root) {
        pre(root);
        post(root);
        return list1.equals(list2);
    }

    public void pre(TreeNode root){
        if(root == null) return;
        list1.add(root.val);
        if(root.left != null) pre(root.left);
        if(root.right != null) pre(root.right);
    }

    public void post(TreeNode root){
        if(root == null) return;
        list2.add(root.val);
        if(root.right != null) post(root.right);
        if(root.left != null) post(root.left);
    }

    public boolean isSymmetric1(TreeNode root) {
        return mirror(root, root);
    }

    public boolean mirror(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 ==null) return true;
        // 一个为空，一个不为空
        if(root1 == null || root2 == null) return false;
        // 两个值相等
        if(root1.val != root2.val) return false;
        return mirror(root1.left, root2.right) && mirror(root1.right, root2.left);
    }

    public boolean isSymmetric2(TreeNode root){
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);
        q1.add(root);
        return mirror1(q1);
    }
    public boolean mirror1(Queue<TreeNode> q1){
        while (!q1.isEmpty()){
            TreeNode root1 = q1.poll();
            TreeNode root2 = q1.poll();
            if(root1 == null && root2 == null) continue;
            if(root1 == null || root2 == null) return false;
            if(root1.val != root2.val) return false;
            q1.add(root1.left);
            q1.add(root2.right);
            q1.add(root1.right);
            q1.add(root2.left);

        }
        return true;
    }

    /**
     * 给你二叉树的根节点root和一个表示目标和的整数targetSum,
     * 判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和targetSum 。
     */
    boolean flag = false;
    public void hasPathSum(TreeNode root, int targetSum) {
        int n = 0;
        pre(root, targetSum, n);
        System.out.println(flag);
    }
    public void pre(TreeNode root, int targetSum, int n){
        if(!flag){
            if(root == null) return;
            n+= root.val;
            if(root.left == null && root.right ==null) flag = (n == targetSum);
            if(root.left != null) pre(root.left, targetSum, n);
            if(root.right!= null) pre(root.right, targetSum, n);
        }
        return;
    }

    // TODO 递归
    public boolean hasPathSum1(TreeNode root, int targetSum){
        if(root == null) return false;
        if(root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum1(root.left, targetSum - root.val) || hasPathSum1(root.right, targetSum - root.val);

    }

    // TODO 用队列迭代 BFS
    public boolean hasPathSum2(TreeNode root, int targetSum){
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q1.add(root);
        q2.add(root.val);
        while(!q1.isEmpty()){
            TreeNode tempNode = q1.poll();
            Integer tempVale = q2.poll();
            if(tempNode == null) return false;
            if(tempNode.left == null && tempNode.right == null)
                if(targetSum == tempVale){
                    return true;
                }else {
                    continue;
                }

            if(tempNode.left != null){
                q1.add(tempNode.left);
                q2.add(tempNode.left.val + tempVale);
            }
            if(tempNode.right != null){
                q1.add(tempNode.right);
                q2.add(tempNode.right.val + tempVale);
            }
        }
        return false;
    }

    /**
     *根据一棵树的中序遍历与后序遍历构造二叉树。
     */
    int post_idx;
    int[] postorder;
    int[] inorder;
    Map<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // 如果这里没有节点构造二叉树了，就结束
        if (in_left > in_right) {
            return null;
        }

        // 选择 post_idx 位置的元素作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // 根据 root 所在位置分成左右两棵子树
        int index = idx_map.get(root_val);

        // 下标减一
        post_idx--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // 从后序遍历的最后一个元素开始
        post_idx = postorder.length - 1;

        // 建立（元素，下标）键值对的哈希表
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inorder.length - 1);
    }

    /**
     * 用到层序遍历
     * @param root
     * @return
     */
    public TreeNode connect(TreeNode root) {
        Queue<TreeNode> q1 = new LinkedList<>();
        if(root == null) return null;
        q1.add(root);
        while(!q1.isEmpty()){
            int n = q1.size();
            for(int i = 0; i < n; i++){
                TreeNode tmp = q1.poll();
                if(i != n-1) tmp.next = q1.peek();
                if(tmp.left != null) q1.add(tmp.left);
                if (tmp.right != null) q1.add(tmp.right);
            }
        }
        return root;
    }

    public TreeNode connect1(TreeNode root) {
        TreeNode pre = root;
        while (pre.left != null){
            TreeNode tmp = pre;
            while (tmp != null){
                tmp.left.next = tmp.right;
                if(tmp.next != null){
                    tmp.right.next = tmp.next.left;
                }
                tmp = tmp.next;
            }
            pre = pre.left;
        }
        return root;
    }

    public TreeNode connect2(TreeNode root) {
        if(root == null) return null;
        TreeNode cur = root;
        while(cur != null){
            TreeNode pre = new TreeNode(0);
            TreeNode tmp = pre;
            while(cur != null){
                if(cur.left != null){
                    tmp.next = cur.left;
                    tmp = tmp.next;
                }
                if(cur.right != null){
                    tmp.next = cur.right;
                    tmp = tmp.next;
                }
                cur = cur.next;
            }
            cur = pre.next;
        }
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null) return null; // 1.
        if(left == null) return right; // 3.
        if(right == null) return left; // 4.
        return root; // 2. if(left != null and right != null)
    }

    public boolean isValidBST(TreeNode root) {
        // 中序
        Stack<TreeNode> st = new Stack<>();
        int pre = -1;
        while (root != null || !st.isEmpty()){
            while (root != null){
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            if (root.val == -1) pre = root.val;
            if(root.val - pre < 0) return false;
            root = root.right;
        }

        // 后序
//        ArrayList<Integer> l1 = new ArrayList<>();
//        Stack<TreeNode> st = new Stack<>();
//        TreeNode pre = new TreeNode(-1);
//        while (root != null || !st.isEmpty()){
//            while (root != null){
//                st.push(root);
//                root = root.left;
//            }
//            root = st.peek();
//            if(root.right == null || root.right.val == pre.val){
//                pre = root;
//                st.pop();
//                l1.add(root.val);
//                root = null;
//            }else {
//                root =root.right;
//            }
//        }

        //前序
//        ArrayList<Integer> l1 = new ArrayList<>();
//        Stack<TreeNode> st = new Stack<>();
//        while (root != null || !st.isEmpty()){
//            while (root != null){
//                l1.add(root.val);
//                st.push(root);
//                root = root.left;
//            }
//            root = st.pop();
//            root = root.right;
//        }
        return true;
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q ==null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ll = new ArrayList<>();
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root);
        int n = q1.size();
        int flag = 0;
        while(n != 0){
            List<Integer> ls = new ArrayList<>();
            for(int i = 0; i < n; i++){
                TreeNode tmp = q1.poll();
                ls.add(tmp.val);
                if(tmp.left != null) q1.add(tmp.left);
                if(tmp.right != null) q1.add(tmp.right);
            }
            flag ++;
            if(flag % 2 != 0) {
                ll.add(ls);
            }else{
                Collections.reverse(ls);
                ll.add(ls);
            }
            n = q1.size();
        }
        return ll;
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) return null;
        List<List<Integer>> ll = new ArrayList<>();
        Queue<TreeNode> qq = new LinkedList<>();
        qq.add(root);
        int n = qq.size();
        while(n != 0){
            List<Integer> ls = new ArrayList<>();
            for(int i = 0; i < n; i++){
                TreeNode tmp = qq.poll();
                ls.add(tmp.val);
                if(tmp.left != null) qq.add(tmp.left);
                if(tmp.right != null) qq.add(tmp.right);
            }
            ll.add(ls);
            n = qq.size();
        }
        Collections.reverse(ll);
        return ll;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>>  ll = new ArrayList<>();
        return dfs(root, targetSum, new ArrayList<Integer>(), ll);
    }

    public List<List<Integer>> dfs(TreeNode root, int targetSum, List<Integer> ls, List<List<Integer>> ll){
        if(root == null) return ll;
        ls.add(root.val);
        if(root.left == null && root.right == null){
            if(targetSum == root.val) ll.add(new ArrayList<>(ls));
            ls.remove(ls.size() - 1);
            return ll;
        }
        dfs(root.left, targetSum - root.val, ls, ll);
        dfs(root.right, targetSum - root.val, ls, ll);
        ls.remove(ls.size() - 1);
        return ll;
    }

    int min = -1;
    public int minDepth1(TreeNode root) {
        if(root == null) return 0;
        int tmp = 1;
        dfsDepth(root, tmp);
        return min;
    }

    public void dfsDepth(TreeNode root, int tmp){
        if(root == null) return;
        if(root.left == null && root.right == null){
            if(min < tmp) min = tmp;
            return;
        }
        dfsDepth(root.left, tmp+1);
        dfsDepth(root.right, tmp+1);
        return;
    }

    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        int min = Integer.MAX_VALUE;
        if(root.left != null){
            min = Math.min(minDepth(root.left) + 1, min);
        }
        if(root.right != null){
            min = Math.min(minDepth(root.right) + 1, min);
        }
        return min + 1;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        return  binaryTreePathsdfs(root, new String(""), new ArrayList<>());
    }

    public List<String> binaryTreePathsdfs(TreeNode root, String ss, List<String> ll){
        if(root == null) return ll;
        if(root.left == null && root.right == null) {
            ss += "->" + root.val;
            ll.add(ss);
            return ll;
        }
        if(!ss.equals("")) ss += "->";
        binaryTreePathsdfs(root.left, ss + root.val, ll);
        binaryTreePathsdfs(root.right, ss + root.val, ll);
        return ll;
    }


    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node.val != target.val) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int l = countlevel(root.left);
        int r = countlevel(root.right);
        if(l == r){
            return (int)Math.pow(2, l) + conutNode(root.right) ;
        }else{
            return (int)Math.pow(2, Math.max(l, r) + 1) - (int)Math.pow(2, Math.max(l, r) - 1) - 1;
        }
    }

    public int countlevel(TreeNode root){
        int level = 0;
        while(root != null){
            level += 1;
            root = root.left;
        }
        return level;
    }

    public int conutNode(TreeNode root){
        if(root == null) return 0;
        return Math.max(conutNode(root.left), conutNode(root.right)) + 1;
    }


    public int kthSmallest(TreeNode root, int k) {
        int n = 1;
        int target = -1;
        if(root == null) return 0;
        List<Integer> ll = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        while(root != null || !st.isEmpty()){
            while(root != null){
                st.push(root);
                root = root.left;
            }
            root = st.pop();
            if(n == k) {
                target = root.val;
                break;
            }
            root = root.right;
            n++;
        }
        return target;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ll = new ArrayList<>();
        if(root == null) return ll;
        Stack<TreeNode> st = new Stack<>();
        while(root != null || !st.isEmpty()){
            while(root != null){
                ll.add(root.val);
                st.push(root);
                root = root.right;
            }
            root = st.pop();
            root = root.left;
        }
        return ll;
    }

    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head, null);
    }

    public TreeNode buildTree(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }
        ListNode mid = getMedian(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid.next, right);
        return root;
    }

    public ListNode getMedian(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    class BSTIterator {

        Stack<TreeNode> st;
        public BSTIterator(TreeNode root) {
            this.st = new Stack<>();
        }

        public void inorder(TreeNode root){
            while (root != null){
                this.st.push(root);
                root = root.left;
            }
        }

        public int next() {
            TreeNode root = this.st.pop();
            if(root.right != null) this.inorder(root.right);
            return root.val;
        }

        public boolean hasNext() {
            return this.st.isEmpty();
        }
    }

    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root){
        if(root == null) return 0;
        boolean isleft = false;
        return bfs(root, isleft);
    }

    public int bfs(TreeNode root, boolean isleft){
        if(root == null) return 0;
        bfs(root.left, true);
        if(root.left == null && root.right == null && isleft == true){
            sum += root.val;
        }
        bfs(root.right, false);
        return sum;
    }


    public int sumOfLeftLeaves1(TreeNode root){
        int sum = 0;
        if(root == null) return sum;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            // 判断左节点是否为空
            if(root.left != null){
                // 左节点是否为叶子节点，如果为叶子节点在计入总数，如果不为则放入队列中
                if(isLeafNode(root.left)) {
                    sum += root.left.val;
                }else{
                    queue.offer(root.left);
                }
            }
            // 判断右节点是否为空
            if(root.right != null){
                // 右节点是否为叶子节点，如果为否则加入队列
                if(!isLeafNode(root.right)) queue.offer(root.right);
            }
        }
        return sum;
    }

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    int maxlength = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return maxlength;
    }

    public int helper(TreeNode root){
        if(root == null) return 0;
        int l = helper(root.left);
        int r = helper(root.right);
        maxlength = Math.max(maxlength, l + r);
        return Math.max(l, r) + 1;
    }



    public static void main(String[] args){



        Binary_Tree tree = new Binary_Tree();
//        tree.buildTree(new int[] {9,3,15,20,7}, new int[] {9,15,7,20,3});
        tree.addNode(9);
        tree.addNode(5);
        tree.addNode(12);
        tree.addNode(1);
        tree.addNode(6);
        tree.addNode(11);
        System.out.println(tree.diameterOfBinaryTree(tree.root));

//        BSTIterator obj = tree.new BSTIterator(tree.root);
//        System.out.println(obj.next());    // 返回 3
//        System.out.println(obj.next());    // 返回 7
//        System.out.println(obj.hasNext()); // 返回 true
//        System.out.println(obj.next());    // 返回 9
//        System.out.println(obj.hasNext()); // 返回 true
//        System.out.println(obj.next());    // 返回 15
//        System.out.println(obj.hasNext()); // 返回 true
//        System.out.println(obj.next());    // 返回 20
//        System.out.println(obj.hasNext()); // 返回 false


//        tree.addNode(11);
//        System.out.println(tree.sortedArrayToBST(new int[] {-10,-3,0,5,9}));
//        tree.connect2(tree.root);
//        tree.lowestCommonAncestor(tree.root, new TreeNode(12), new TreeNode(15));
//        List<Integer> ll = tree.inorderTraversal(tree.root);
//        System.out.println(tree.hasPathSum2(tree.root, 12));
//        System.out.println(tree.isSymmetric2(tree.root));

    }
}
