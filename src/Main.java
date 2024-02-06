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
        String[] number;
        char operator;
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
            throw new ArithmeticException("Строка не является математической операцией");
        }
        if (number.length != 2) {
            throw new ArithmeticException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        number[0] = number[0].replaceAll("\\s", "");
        number[1] = number[1].replaceAll("\\s", "");

        if ((converter.isRome(number[0]) != converter.isRome(number[1])) || (number[0].matches("\\d+") != number[1].matches("\\d+"))) {
            throw new ArithmeticException("Используются одновременно разные системы счисления");
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
            throw new ArithmeticException("Значения должны быть от 1 до 10 или от I до X");
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
                throw new ArithmeticException("В римской системе нет отрицательных чисел");
            }
            return converter.intToRome(result);
        } else {
            return Integer.toString(result);
        }
    }
}
