package merkle_tree;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    //merkle树叶子结点列表
    public List<AbstractNode> leafList;
    //已使用的叶子结点列表
    public List<AbstractNode> usedLeafList;

    protected MessageDigest md;

    MerkleTreeNode root;

    //存储结点数量
    public int leafNum = 0;

    public int maxNum;

    MerkleTree(){
        this.usedLeafList = new ArrayList<>();
    }

    MerkleTree(List<AbstractNode> leafList, MerkleTreeNode root, int maxNum, MessageDigest md){
        this.md = md;
        this.leafList = leafList;
        this.root = root;
        this.maxNum = maxNum;
        this.usedLeafList = new ArrayList<>();
    }

    /**
     * 用来给merkle树加入新叶子结点，同时更新merkle树相关结点的内容
     * @param data 新加入结点的数据（字符串形式）
     * @return 0表示加入失败，merkle树叶子结点已满
     */
    public int addLeaf(String data){
        if (this.maxNum == this.leafNum) return 0;
        Leaf leaf = (Leaf) leafList.get(this.leafNum);
        this.leafNum++;
        leaf.setValue(1);
        leaf.modifyData(data);
        usedLeafList.add(leaf);
        return 1;
    }

    /**
     * 删除merkle树中某个已使用的叶子结点
     * @param leaf
     * @return 0表示删除失败，merkle树中无已使用叶子结点
     */
    public int deleteLeaf(AbstractNode leaf){
        if (this.leafNum == 0) return 0;
        this.leafNum--;
        this.usedLeafList.remove(leaf);
        return 1;
    }
}
