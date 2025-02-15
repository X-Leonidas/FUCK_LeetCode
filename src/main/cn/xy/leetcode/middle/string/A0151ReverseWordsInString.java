package cn.xy.leetcode.middle.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangyu
 * @date 2024-12-19 21:39
 * <p>
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * <p>
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 * <p>
 * 示例 3：
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 */
public class A0151ReverseWordsInString {
    public static void main(String[] args) {
        new A0151ReverseWordsInString().reverseWords2("  hello world");
    }

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        char[] charArray = s.toCharArray();
        String temp = "";
        for (char c : charArray) {
            if (c == ' ') {
                if (!temp.isEmpty()) {
                    list.add(temp);
                    temp = "";
                }
            } else {
                temp = temp + c;
            }
        }
        if (!temp.isEmpty()) {
            list.add(temp);
        }

        StringBuilder result = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.append(list.get(i));

            if (i != 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public String reverseWords2(String s) {
        List<String> list = new ArrayList<>();
        char[] charArray = s.toCharArray();
        int start = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                if (start != i) {
                    list.add(s.substring(start, i));
                    start  = i + 1;
                } else {
                    start++;
                }
            }
        }
        if (start != charArray.length) {
            list.add(s.substring(start, charArray.length));
        }

        StringBuilder result = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.append(list.get(i));

            if (i != 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
