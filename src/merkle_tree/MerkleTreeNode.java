package merkle_tree;

import java.security.MessageDigest;

public class MerkleTreeNode extends AbstractNode{
    /**
     * 缺省构造函数
     *
     * @param md
     */
    public MerkleTreeNode(MessageDigest md) {
        super(md);
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

    public void addChildNode(AbstractNode leftChild, AbstractNode rightChild){
        setLeftChild(leftChild);
        setRightChild(rightChild);
        leftChild.setParent(this);
        rightChild.setParent(this);
        leftChild.setSibling(rightChild);
        rightChild.setSibling(leftChild);
        leftChild.setLocation(0);
        rightChild.setLocation(1);
        md.update(leftChild.getDigest());
        setDigest(md.digest(rightChild.getDigest()));
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

    public void modifyDigest(){
        md.update(leftChild.getDigest());
        setDigest(md.digest(rightChild.getDigest()));
        if (parent != null)
            parent.modifyDigest();
    }
}
