package leecode_Debug.BTree.LevelBianLi;

import leecode_Debug.BTree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 打印二叉树各个层的均值
 * */
public class LevelAVG_637 {
    public List<Double> averageOfLevels(TreeNode root){
        LinkedList<Double> res = new LinkedList<>();
        if (root==null)
            return res;
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            double sum = 0;
            for (int i=0;i<size;i++){
                TreeNode node = que.poll();
                sum+=node.val;
                if (node.left!=null)
                    que.add(node.left);
                if (node.right!=null)
                    que.add(node.right);
            }
            res.add(sum/size);
        }
        return res;
    }
}
