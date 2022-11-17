package merkle_tree;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MerkleTreeBuilder {

    private List<AbstractNode> leafList;

    int predictUserNum = 4;

    protected MessageDigest md;

    MerkleTreeBuilder(MessageDigest md){
        this.md = md;
        this.leafList = new ArrayList<>();
    }

    MerkleTreeBuilder(int predictUserNum, MessageDigest md){
        this.predictUserNum = predictUserNum;
        this.md = md;
        this.leafList = new ArrayList<>();
    }

    public MerkleTree buildMerkleTree(){
        String tempString;
        List<AbstractNode> nodeQueue = new ArrayList<>();
        for (int i = 0; i < predictUserNum; i++) {
            tempString = getRandomString(10);
            Leaf leaf = new Leaf(md,getRandomString(20));
            leafList.add(leaf);
            nodeQueue.add(leaf);
        }
        AbstractNode leftChild, rightChild;
        MerkleTreeNode parent;
        while(nodeQueue.size() != 1){
            leftChild = nodeQueue.get(0);
            nodeQueue.remove(0);
            rightChild = nodeQueue.get(0);
            nodeQueue.remove(0);
            parent = new MerkleTreeNode(md);
            parent.addChildNode(leftChild, rightChild);
            nodeQueue.add(parent);
        }
        //接下来要根据叶子结点生成merkle树，通过优先队列解决
        return new MerkleTree(this.leafList, (MerkleTreeNode) (nodeQueue.get(0)), predictUserNum,this.md);
    }

    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
