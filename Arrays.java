import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {
        var input = new Scanner(System.in);

        System.out.print("Введите числа через пробел: ");
        var numbers = input.nextLine().split(" ");

        var array = new int[numbers.length];
        for (var i = 0; i < numbers.length; i++) {
            array[i] = Integer.parseInt(numbers[i]);
        }
        System.out.println("Итоговый массив: " + java.util.Arrays.toString(array));

        var minAbs = findMinAbs(array);
        System.out.println("Минимальное элемент по модулю: " + minAbs);
    }

    public static int findMinAbs(int[] array) {
        var minAbs = array[0];
        for (var i = 1; i < array.length; i++) {
            if (Math.abs(array[i]) < minAbs) {
                minAbs = array[i];
            }
        }
        return minAbs;
    }
}
