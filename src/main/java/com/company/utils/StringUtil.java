package com.company.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc

/**
 * The Class StringUtil.
 */
public class StringUtil {

    /**
     * The Constant sdf.
     */
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The Constant pattern.
     */
    private final static Pattern pattern = Pattern.compile("^\\w+$");

    /**
     * Checks if is not empty.
     *
     * @param obj the obj
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj != null && !"".equals(obj.toString().trim())) {
            return true;
        }
        return false;
    }

    /**
     * Gets the m5d string.
     *
     * @param str the str
     * @return the m5d string
     */
    public static String getM5dString(String str) {
        if (StringUtil.isNotEmpty(str)) {
            Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
            md5PasswordEncoder.setEncodeHashAsBase64(true);
            return md5PasswordEncoder.encodePassword(str, "");
        }
        return "";
    }

    /**
     * Gets the date string.
     *
     * @param date the date
     * @return the date string
     */
    public static String getDateString(Date date) {
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    /**
     * Gets the string date.
     *
     * @param str the str
     * @return the string date
     */
    public static Date getStringDate(String str) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                return sdf.parse(str);
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * Test.
     *
     * @param s the s
     * @return true, if successful
     */
    public static boolean test(String s) {
        if (StringUtil.isNotEmpty(s)) {
            Matcher matcher = StringUtil.pattern.matcher(s);
            return matcher.find();
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(getM5dString("12345678"));
    }


}
