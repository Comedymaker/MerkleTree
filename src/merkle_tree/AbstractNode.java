package merkle_tree;

import java.security.MessageDigest;

public abstract class AbstractNode {
    /**
     * 存储摘要
     */
    protected byte[] digest;

    /**
     * 孩子节点
     */
    protected AbstractNode leftChild;
    protected AbstractNode rightChild;

    /**
     * 双亲节点
     */
    protected AbstractNode parent;

    /**
     * 兄弟节点
     */
    protected AbstractNode sibling;

    /**
     *MessageDigest类
     */
    protected MessageDigest md;

    /**
     *  记录结点是左子还是右子,左子为0，右子为1
     */
    protected int location;

    /**
     * 缺省构造函数
     */
    public AbstractNode(MessageDigest md){
        this.md = md;
    }

    public abstract byte[] getDigest();

    public abstract AbstractNode getLeftChild();

    public abstract AbstractNode getRightChild();

    public abstract void setDigest(byte[] digest);

    public abstract void setLeftChild(AbstractNode leftChild);

    public abstract void setRightChild(AbstractNode rightChild);

    public abstract String toString();

    public abstract AbstractNode getParent();

    public abstract AbstractNode getSibling();

    public abstract void setParent(AbstractNode parent);

    public abstract void setSibling(AbstractNode sibling);

    public abstract void modifyDigest();

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public static String fromBytesToHex(byte[] resultBytes) {
        StringBuilder builder = new StringBuilder();
        for (byte resultByte : resultBytes) {
            if (Integer.toHexString(0xFF & resultByte).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultByte));
            } else {
                builder.append(Integer.toHexString(0xFF & resultByte));
            }
        }
        return builder.toString();
    }
}
