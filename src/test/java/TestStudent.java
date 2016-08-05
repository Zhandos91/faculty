import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by admin on 6/28/2016.
 */
public class TestStudent {

    public static void main(String args[]) {
        String password = "MyPassword123";

        String salt = "3<u4~UkR6f1>e?8DS|2l2;eA`d*Zy#+04w\"K4WzcocUXaW\"#VmeeOv8#p&Zu3448ZWh=kbO5v]E45bP}2Xha])#r1L";
        System.out.println(DigestUtils.md5Hex(password));
        System.out.println(DigestUtils.md5Hex(password + salt));


    }
}
