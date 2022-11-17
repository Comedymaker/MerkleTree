package merkle_tree;

import java.util.ArrayList;
import java.util.List;

public class Authentication {
    public static AuthPack authPackage(MerkleTree merkleTree, AbstractNode node){
        AuthPack authPack = new AuthPack();
        AbstractNode tempNode = node;
        while (tempNode.getParent() != null){
            authPack.locList.add(tempNode.getSibling().getLocation());
            authPack.authList.add(tempNode.getSibling().getDigest());
//            authList.add(tempNode.getParent().getDigest());
            tempNode = tempNode.getParent();
        }
//        authList.remove(authList.size() - 1);
        return authPack;
    }
}
