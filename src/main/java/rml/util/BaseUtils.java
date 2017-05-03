package rml.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BBLink on 2017/5/2.
 */
public class BaseUtils {

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
