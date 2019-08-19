import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        /*
        这个emoji是win10键盘的emoji表情，不知道微信的emoji是否一致
         */
        try{
            //原本emoji
            String str = "12😂哇哇哇";
            System.out.println("原本含有emoji的字符："+str);
            //将原本emoji转换，入库
            String s = emojiConvertToUtf(str);
            System.out.println("将此字符入库："+s);
            //取库后转换
            String s1 = utfemojiRecovery(s);
            System.out.println("取库后转换为需要的："+s1);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }

    /**
     * @Description emoji表情转换
     *
     * @param str 待转换字符串
     * @return 转换后字符串
     * @throws UnsupportedEncodingException
     */
    public static String emojiConvertToUtf(String str)
            throws UnsupportedEncodingException {
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(
                        sb,
                        "[[" + URLEncoder.encode(matcher.group(1),
                                "UTF-8") + "]]");
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @Description 还原emoji表情的字符串
     *
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException
     */
    public static String utfemojiRecovery(String str)
            throws UnsupportedEncodingException {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb,
                        URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
