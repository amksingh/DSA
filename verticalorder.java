/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Tuple {
    TreeNode node;
    int x;
    int y;

    public Tuple(TreeNode _node, int _row, int _col) {
        node = _node;
        x = _row;
        y = _col;
    }
}

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        Queue<Tuple> q = new LinkedList<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        if (root == null)
            return (List) ans;
        q.offer(new Tuple(root, 0, 0));
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode node = tuple.node;
            int x = tuple.x;
            int y = tuple.y;
            if (!map.containsKey(x))
                map.put(x, new TreeMap<>());
            if (!map.get(x).containsKey(y))
                map.get(x).put(y, new PriorityQueue<>());
            map.get(x).get(y).offer(node.val);
            if (node.left != null)
                q.offer(new Tuple(node.left, x - 1, y + 1));
            if (node.right != null)
                q.offer(new Tuple(node.right, x + 1, y + 1));
        }
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            ans.add(new ArrayList<Integer>());
            for (PriorityQueue<Integer> nw : ys.values()) {
                while (!nw.isEmpty())
                    ans.get(ans.size() - 1).add(nw.poll());
            }
        }
        return (List) ans;

    }
}