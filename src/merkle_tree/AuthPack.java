package merkle_tree;

import java.util.ArrayList;
import java.util.List;

public class AuthPack {
    /**
     * 保存验证路径需要用到的所有结点值
     */
    public List<byte[]> authList;

    /**
     * 记录每个结点是左子还是右子
     */
    public List<Integer> locList;

    public AuthPack(){
        authList = new ArrayList<>();
        locList = new ArrayList<>();
    }
}
