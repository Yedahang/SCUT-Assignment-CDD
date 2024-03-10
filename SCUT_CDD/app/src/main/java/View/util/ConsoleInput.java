package View.util;

import java.util.Scanner;
import java.util.Vector;

public class ConsoleInput {
    public static int[] getInputNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入数字，数字之间用空格隔开：");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        int[] numArray = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numArray[i] = Integer.parseInt(inputArray[i]);
        }
        System.out.println("输入的数字为：");
        for (int num : numArray) {
            System.out.print(num + " ");
        }
        return numArray;
    }

    public static int getInputNumber() {
        Scanner scanner = new Scanner(System.in);
       // System.out.print("请输入数字：");
        int input = scanner.nextInt();

        //System.out.println("输入的数字为："+input);

        return input;
    }
    public static Vector<Integer> getSelectedCardIndexInput(){
        System.out.println("请输入你想要出的牌的顺序索引值（从1开始从左到右）：");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String[] inputArray = s.split(" ");
        Vector<Integer> index=new Vector<>();
        for (String value : inputArray) {
            index.add(Integer.parseInt(value));
        }
        return  index;
    }

}
