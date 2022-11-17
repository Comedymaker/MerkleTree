package prove;

import java.security.MessageDigest;
import java.util.List;
import merkle_tree.AbstractNode;
import merkle_tree.AuthPack;

public class Prove {

    public static byte[] hashOfRoot(String data, AuthPack authPack, MessageDigest md){
        byte[] temp;
//        md.update(data.getBytes());
        temp = md.digest(data.getBytes());
//        for (byte[] digest:
//             authPack.authList) {
////            md.update(digest);
//
//            md.update(temp);
//            temp = md.digest(digest);
//            System.out.println(AbstractNode.fromBytesToHex(temp));
//        }
        for (int i = 0; i < authPack.authList.size(); i++) {
            if (authPack.locList.get(i) == 0){
                md.update(authPack.authList.get(i));
                temp = md.digest(temp);
            }
            else {
                md.update(temp);
                temp = md.digest(authPack.authList.get(i));
            }
        }
        return temp;
    }
}
