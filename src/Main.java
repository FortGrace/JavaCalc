import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final int MAX_VALUE = 10;
    static final int MIN_VALUE = 1;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(calc(input));
        sc.close();
    }

    public static String calc(String input) {
        Converter converter = new Converter();
        String[] number = new String[0];
        char operator = 0;
        if (input.contains("+")) {
            number = input.split("\\+");
            operator = '+';
        } else if (input.contains("-")) {
            number = input.split("-");
            operator = '-';
        } else if (input.contains("*")) {
            number = input.split("\\*");
            operator = '*';
        } else if (input.contains("/")) {
            number = input.split("/");
            operator = '/';
        } else {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Строка не является математической операцией");
                System.exit(0);
            }
        }
        if (number.length != 2) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
        }
        number[0] = number[0].replaceAll("\\s", "");
        number[1] = number[1].replaceAll("\\s", "");

        if ((converter.isRome(number[0]) != converter.isRome(number[1])) || (number[0].matches("\\d+") != number[1].matches("\\d+"))) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Используются одновременно разные системы счисления");
                System.exit(0);
            }
        }

        int a, b;
        boolean isRome = converter.isRome(number[0]);
        if (isRome) {
            a = converter.romeToInt(number[0]);
            b = converter.romeToInt(number[1]);
        } else {
            a = Integer.parseInt(number[0]);
            b = Integer.parseInt(number[1]);
        }

        if (a > MAX_VALUE || b > MAX_VALUE || a < MIN_VALUE || b < MIN_VALUE) {
            try {
                throw new ArithmeticException();
            } catch (ArithmeticException e) {
                System.out.println("Значения должны быть от 1 до 10 или от I до X");
                System.exit(0);
            }
        }
        int result = 0;
        result = switch (operator) {
            case ('+') -> a + b;
            case ('-') -> a - b;
            case ('*') -> a * b;
            case ('/') -> a / b;
            default -> result;
        };
        if (isRome) {
            if (result < 1) {
                try {
                    throw new ArithmeticException();
                } catch (ArithmeticException e) {
                    System.out.println("В римской системе нет отрицательных чисел");
                    System.exit(0);
                }
            }
            return converter.intToRome(result);
        } else {
            return Integer.toString(result);
        }
    }
}
