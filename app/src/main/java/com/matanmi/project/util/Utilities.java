package com.matanmi.project.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public static NumberFormat nf = null;
    public static SimpleDateFormat sdf = null;
    public static final String EMAIL_PATTERN = "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

    public void Utilities() {

    }

    public static String format(double dbl) {
        return nf.format(dbl);
    }

    public static String format(String dbl) {
        return nf.format(Double.parseDouble(dbl));
    }

    public static String formatNaira(double dbl) {
        return "₦".concat(nf.format(dbl));
    }

    public static String formatTimestamp(Object obj) {
        sdf = new SimpleDateFormat("E dd MMM yyyy hh:mm:ss");
        return sdf.format(obj);
    }

    public static boolean isNull(String value) {
        if (value == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotString(String value) {
        if (value instanceof String) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isEmpty(String value) {
        if (value.equals("") || value.equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isZeroLength(String value) {
        if (value.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotFormattedEmail(String value) {
        boolean formatted = false;
        if (isZeroLength(value.trim())) {
            formatted = true;
        } else {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(value.trim());
            if (!matcher.matches()) {
                formatted = true;
            }
        }
        return formatted;
    }

}