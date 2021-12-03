package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String line = "299@qq.ie";
        String line1="@qq.com";

        System.out.println(emailTest(line));
        System.out.println(emailTest(line1));
    }
    public static boolean emailTest(String str){
        String pattern = "[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if(m.matches())
        {
            return true;
        }else
        {
            return false;
        }

    }
}
