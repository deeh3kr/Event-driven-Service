package jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;

public class TestJasypt {
    public static void main(String[] args) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setPassword("password!!");
        standardPBEStringEncryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        standardPBEStringEncryptor.setIvGenerator(new RandomIvGenerator());

        String result = standardPBEStringEncryptor.encrypt("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");
        System.out.println(result);
        System.out.println(standardPBEStringEncryptor.decrypt(result));

        String result1 = standardPBEStringEncryptor.encrypt("SpringCloudPwd!");
        System.out.println(result1);
        System.out.println(standardPBEStringEncryptor.decrypt(result1));
    }
}
