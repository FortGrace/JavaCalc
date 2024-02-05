import java.util.TreeMap;

public class Converter {
    TreeMap<Character, Integer> romeKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabKeyMap = new TreeMap<>();

    public Converter() {
        romeKeyMap.put('I', 1);
        romeKeyMap.put('V', 5);
        romeKeyMap.put('X', 10);

        arabKeyMap.put(1, "I");
        arabKeyMap.put(4, "IV");
        arabKeyMap.put(5, "V");
        arabKeyMap.put(9, "IX");
        arabKeyMap.put(10, "X");
        arabKeyMap.put(40, "XL");
        arabKeyMap.put(50, "L");
        arabKeyMap.put(90, "XC");
        arabKeyMap.put(100, "C");

    }

    public boolean isRome(String number) {
//        System.out.println("ss");
        return romeKeyMap.containsKey(number.charAt(0));
    }

    public int romeToInt(String str) {
        int end = str.length() - 1;
        char[] arr = str.toCharArray();
        int arab;
        int result = romeKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arab = romeKeyMap.get(arr[i]);
            if (arab < romeKeyMap.get(arr[i + 1])) {
                result -= arab;
            } else {
                result += arab;
            }
        }
        return result;
    }

    public String intToRome(int number) {
        String rome = "";
        int arabKey;
        do {
            arabKey = arabKeyMap.floorKey(number);
            rome += arabKeyMap.get(arabKey);
            number -= arabKey;
        } while (number != 0);
        return rome;
    }
}
