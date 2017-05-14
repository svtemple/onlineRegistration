package com.dronamraju.svtemple.util;

import com.microsoft.sqlserver.jdbc.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by mdronamr on 3/10/17.
 */

public class Util {

    private static Log log = LogFactory.getLog(Util.class);

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static Matcher matcher;

    public static boolean isValidDate(Date dateToValidate){
        //log.info("Calendar.getInstance().getTime(): " + Calendar.getInstance().getTime());
        if (dateToValidate.before(Calendar.getInstance().getTime())) {
            return false;
        }
        return true;
    }

    public static String newPassword() {
        String newPassword = UUID.randomUUID().toString();
        //log.info("newPassword: " + newPassword);
        return newPassword;
    }

    public static boolean isValidEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return (StringUtils.isNumeric(phoneNumber)  && phoneNumber.length() == 10);
    }

    public static boolean isValidZip(String zip) {
        return (StringUtils.isNumeric(zip) && zip.length() == 5) ;
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }


}