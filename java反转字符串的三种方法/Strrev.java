package hx.insist.StringUtils;

/**
 * java字符串反转的几种方法
 */

public class Strrev {
    public static void main(String[] args) {
        String str = "123qwe";
        String s = null;

        //1、使用StringBuffer  或  StringBuilder 的reverse 方法
        //s = new StringBuffer(str).reverse().toString();
        //s = new StringBuilder(str).reverse().toString();//“大多数中StringBuilder比 StringBuffer 要快。”

        //2、String的toCharArray 方法转换为字符数组char[]，再反转
        //s = myreser(str);

        //3、String的CharAt 方法取出各个字符，倒叙添加到新的String
        s = myreser2(str);

        System.out.println(s);
    }

    public static String myreser(String str){
        int n = str.length();
        char[] chars = str.toCharArray();
        for(int i=0;i<n/2;i++){
            char c = chars[i];
            chars[i] = chars[n-1-i];
            chars[n-1-i] = c;
        }
        return new String(chars);
    }

    public static String myreser2(String str){
        int n = str.length();
        StringBuilder s = new StringBuilder();
        for (int i=n-1;i>=0;i--){
            s.append(str.charAt(i));
        }
        return s.toString();
    }
}
