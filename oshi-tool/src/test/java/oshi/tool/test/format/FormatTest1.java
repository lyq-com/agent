package oshi.tool.test.format;

import org.junit.Test;
import oshi.util.platform.unix.openbsd.FstatUtil;

/**
 * https://www.cnblogs.com/wqbin/p/11234659.html
 * https://blog.csdn.net/wenqiangluyao/article/details/96474361
 * https://segmentfault.com/a/1190000019350486
 * https://www.cnblogs.com/zhongjunbo555/p/11383159.html
 */
public class FormatTest1 {

    @Test
    public void test(){

        /**
         * %s 字符串占位符。
         * 输出结果：Hi 张三、李四、王五
         */
        String format = String.format("Hi %s、%s、%s", "张三", "李四", "王五");
        System.out.println(format);

        /**
         * %c 字符占位符。
         * 输出结果：字符：【a b c ! $】
         */
        String format2 = String.format("字符：【%c %c %c %c %c】", 'a', 'b', 'c', '!', '$');
        System.out.println(format2);

        /**
         * %b 布尔类型占位符
         * 输出结果：3>7的结果是 false
         */
        String format3 = String.format("3>7的结果是 %b", false);
        System.out.println(format3);


        /**
         * %d：十进制。
         * %x：十六进制。
         * %o：八进制
         * 输出结果：230：【十进制：230、十六禁止：e6、八进制：346】
         */
        String format1 = String.format("230：【十进制：%d、十六进制：%x、八进制：%o】", 230, 230, 230);
        System.out.println(format1);


        /**
         * %f 浮点类型。
         * %g 通用浮点类型（f 和 e 类型中较短的）
         * 输出结果：3/2=1.500000 ; 3/2=1.50000
         */
        String format4 = String.format("3/2=%f ; 3/2=%g", 3.0 / 2, 3.0/2);
        System.out.println(format4);

        /**
         * %e 指数类型
         * 输出结果：100.2 的指数是： 1.002000e+02
         */
        String format5 = String.format("100.2 的指数是： %e", 100.2);
        System.out.println(format5);

        /**
         * %h 散列码。
         * 输出结果：字母A的散列码是：41
         */
        String format6 = String.format("字母A的散列码是：%h", "A");
        System.out.println(format6);

        /**
         * %% 百分比类型。
         * 输出结果：百分比：85%
         */
        String format7 = String.format("百分比：%d%%", 85);
        System.out.println(format7);

        /**
         * %n 换行符
         * 输出结果：
         * 爱好:
         * 打球、唱歌、游泳
         */
        String format8 = String.format("%s: %n%s、%s、%s", "爱好", "打球", "唱歌", "游泳");
        System.out.println(format8);
    }
}
