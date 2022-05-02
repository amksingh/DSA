
// { Driver Code Starts
import java.io.*;
import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

class GFG {
    static Node buildTree(String str) {
        // Corner Case
        if (str.length() == 0 || str.equals('N'))
            return null;
        String[] s = str.split(" ");

        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        // Starting from the second element
        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            // Get and remove the front of the queue
            Node currNode = q.remove();

            // Get the current node's value from the string
            String currVal = s[i];

            // If the left child is not null
            if (!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.left);
            }

            // For the right child
            i++;
            if (i >= s.length)
                break;
            currVal = s[i];

            // If the right child is not null
            if (!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                q.add(currNode.right);
            }

            i++;
        }

        return root;
    }

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t > 0) {
            String s = br.readLine();
            Node root = buildTree(s);

            Solution T = new Solution();

            ArrayList<Integer> res = T.boundary(root);
            for (Integer num : res)
                System.out.print(num + " ");
            System.out.println();
            t--;
        }
    }
}
// } Driver Code Ends

// User function Template for Java

// class Node
// {
// int data;
// Node left, right;

// public Node(int d)
// {
// data = d;
// left = right = null;
// }
// }

class Solution {
    ArrayList<Integer> boundary(Node node) {

        ArrayList<Node> a = new ArrayList<Node>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (node == null)
            return ans;
        if (!leafNode(node))
            a.add(node);
        leftBoundary(node.left, a);

        leaf(node, a);

        for (int i = 0; i < a.size(); i++)
            ans.add(a.get(i).data);
        ArrayList<Node> aa = new ArrayList<Node>();
        rightBoundary(node.right, aa);
        Collections.reverse(aa);

        for (int i = 0; i < aa.size(); i++)
            ans.add(aa.get(i).data);

        return ans;

    }

    boolean leafNode(Node node) {
        if (node.left == null && node.right == null)
            return true;
        else
            return false;
    }

    void leftBoundary(Node node, ArrayList<Node> a) {
        if (node != null && !leafNode(node)) {
            a.add(node);
            if (node.left != null)
                leftBoundary(node.left, a);
            else
                leftBoundary(node.right, a);
        } else
            return;
    }

    void leaf(Node node, ArrayList<Node> a) {
        if (node == null)
            return;
        leaf(node.left, a);
        if (leafNode(node))
            a.add(node);
        leaf(node.right, a);

    }

    void rightBoundary(Node node, ArrayList<Node> a) {
        if (node != null && !leafNode(node)) {
            a.add(node);
            if (node.right != null)
                rightBoundary(node.right, a);
            else
                rightBoundary(node.left, a);

        } else
            return;

    }
}
