package ZZZtest;

import java.util.*;

public class StringCondition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取输入的字符串个数
        int n = scanner.nextInt();
        scanner.nextLine();

        // 读取字符串并存储到列表中
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            strings.add(str);
        }

        // 检查每个字符串是否符合条件
        List<String> result = new ArrayList<>();
        for (String str : strings) {
            if (isStringConditionMet(str, strings)) {
                result.add(str);
            }
        }

        // 按字典序升序排序
        Collections.sort(result);

        // 输出结果
        System.out.println(result.size());
        for (String str : result) {
            System.out.println(str);
        }
    }

    private static boolean isStringConditionMet(String str, List<String> strings) {
        // 检查每个字符串是否可以通过拼接后去除前缀和后缀得到当前字符串
        for (String s : strings) {
            if (s.equals(str)) {
                continue;  // 跳过当前字符串本身
            }

            if (str.startsWith(s) || str.endsWith(s)) {
                return true;
            }
        }

        return false;
    }
}