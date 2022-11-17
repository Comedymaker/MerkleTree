package merkle_tree;

import java.security.MessageDigest;
import java.util.List;

public class Leaf extends AbstractNode{

    /**
     *判断是否为有效结点，若为1，则为有效叶子结点
     */
    private int value = 0;

    /**
     * 缺省构造函数
     *
     * @param md
     */

    public Leaf(MessageDigest md, String data) {
        super(md);
        setDigest(md.digest(data.getBytes()));
    }

    @Override
    public AbstractNode getParent() {
        return this.parent;
    }

    @Override
    public AbstractNode getSibling() {
        return this.sibling;
    }

    @Override
    public void setParent(AbstractNode parent) {
        this.parent = parent;
    }

    @Override
    public void setSibling(AbstractNode sibling) {
        this.sibling = sibling;
    }

    @Override
    public byte[] getDigest() {
        return this.digest;
    }

    @Override
    public AbstractNode getLeftChild() {
        return this.leftChild;
    }

    @Override
    public AbstractNode getRightChild() {
        return this.rightChild;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    @Override
    public void setLeftChild(AbstractNode leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public void setRightChild(AbstractNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Node digest: " + fromBytesToHex(getDigest()) + "\n");
        if (leftChild != null && rightChild != null){
            builder.append(leftChild.toString() + rightChild.toString());
        }
        return builder.toString();
    }

    /**
     * 修改叶子结点存储的值，同时修正整个merkle树
     * @param data
     */
    public void modifyData(String data){
        setDigest(md.digest(data.getBytes()));
        modifyDigest();
    }

    @Override
    public void modifyDigest() {
        parent.modifyDigest();
    }
}
