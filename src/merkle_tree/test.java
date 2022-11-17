package merkle_tree;
import java.nio.charset.StandardCharsets;
import  java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import prove.Prove;

public class test {
    public static String fromBytesToHex(byte[] resultBytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultBytes[i]));
            } else {
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
        return builder.toString();
    }
    public static String toHexString(final byte[] array)
    {
        final StringBuilder str = new StringBuilder();

        str.append("[");

        boolean isFirst = true;
        for(int idx=0; idx<array.length; idx++)
        {
            final byte b = array[idx];

            if (isFirst)
            {
                //str.append(Integer.toHexString(i));
                isFirst = false;
            }
            else
            {
                //str.append("," + Integer.toHexString(i));
                str.append(",");
            }

            final int hiVal = (b & 0xF0) >> 4;
            final int loVal = b & 0x0F;
            str.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
            str.append((char) ('0' + (loVal + (loVal / 10 * 7))));
        }

        str.append("]");

        return(str.toString());
    }
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
//        MerkleTreeBuilder merkleTreeBuilder = new MerkleTreeBuilder(md);
//        MerkleTree merkleTree = merkleTreeBuilder.buildMerkleTree();
//        merkleTree.addLeaf("Xuyucheng is a dog");
//        merkleTree.addLeaf("dogxu");
//        System.out.println(toHexString(Prove.hashOfRoot("dogxu", Authentication.authPackage(merkleTree, merkleTree.usedLeafList.get(1)), md)));
//        System.out.println("End");
        byte[] temp1, temp2, temp;
        String string2 = "dogxu";
        String string1 = "Xuyucheng is a dog";
        temp1 = md.digest(string1.getBytes());
        temp2 = md.digest(string2.getBytes());
        md.update(temp1);
        temp = md.digest(temp2);
        md.update(temp1);
        md.update(temp2);
        temp = md.digest();
        System.out.println(AbstractNode.fromBytesToHex(temp));
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
