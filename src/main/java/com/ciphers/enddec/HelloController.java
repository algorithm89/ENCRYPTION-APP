package com.ciphers.enddec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    final String secretkey = "!!!!@@@$$%&&&*@3NCRyPT!0n!@#$$%!!!@@@##@$TTTTTSAS!@#";

    private static SecretKeySpec secretKey;
    private static byte[] key;


    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


public static String encrypt(final String strToEncrypt, final String secret) {
    try {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder()
                .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    } catch (Exception e) {
        System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
}


public static String decrypt(final String strToDecrypt, final String secret) {
    try {
        setKey(secret);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder()
                .decode(strToDecrypt)));
    } catch (Exception e) {
        System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
}
    @FXML
    private  TextField textField;

    @FXML
    private  TextField textField2;

    @FXML
    private TextArea textArea;

    @FXML
    private TextArea textArea2;

    @FXML
    private ProgressBar progressBar;

    String originalString;
    String encstring;

    BigDecimal progress = new BigDecimal(String.format("%.2f",0.0));

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        progressBar.setStyle("-fx-accent: green");

    }


    @FXML
    void ResetProgress()
    {
        progress = new BigDecimal(String.format("%.2f",progress.doubleValue()-1));
        progressBar.setProgress(progress.doubleValue());

    }

    @FXML
    void ENC(ActionEvent event)
    {
        try {



            progress = new BigDecimal(String.format("%.2f",progress.doubleValue()+1));
            progressBar.setProgress(progress.doubleValue());


            originalString = textField.getText();
            String encryptedString = HelloController.encrypt(originalString, secretkey) ;
            System.out.println(encryptedString);
            textArea.setText(encryptedString);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    @FXML
    void DEC(ActionEvent event)
    {

        try {
            encstring = textField2.getText();
            String decryptedString = HelloController.decrypt(encstring,secretkey);
            textArea2.setText(decryptedString);
        }catch (Exception e){
            System.out.println(e);
        }


    }

}