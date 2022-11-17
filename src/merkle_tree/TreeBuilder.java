package merkle_tree;
import prove.Prove;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TreeBuilder {
    public static void main(String[] args) {
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("SHA");
        }
        catch (NoSuchAlgorithmException e)
        {
            // Should never happen, we specified SHA, a valid algorithm
            assert false;
        }
        MerkleTreeBuilder merkleTreeBuilder = new MerkleTreeBuilder(md);
        MerkleTree merkleTree = merkleTreeBuilder.buildMerkleTree();
        merkleTree.addLeaf("hust");
        merkleTree.addLeaf("dogxu");
        merkleTree.addLeaf("hasaki");
        merkleTree.addLeaf("aaa");
        System.out.println(AbstractNode.fromBytesToHex(Prove.hashOfRoot("aaa", Authentication.authPackage(merkleTree, merkleTree.usedLeafList.get(3)), md)));
        System.out.println(AbstractNode.fromBytesToHex(merkleTree.root.getDigest()));
        System.out.println("End");
    }
}
