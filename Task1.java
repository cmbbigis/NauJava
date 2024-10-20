import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        var random = new Random();

        System.out.print("Введите количество чисел: ");
        var n = Integer.parseInt(input.nextLine());

        var array = new int[n];
        for (var i = 0; i < n; i++) {
            array[i] = random.nextInt();
        }
        System.out.println("Итоговый массив: " + java.util.Arrays.toString(array));

        var minAbs = findMinAbs(array);
        System.out.println("Минимальное элемент по модулю: " + minAbs);
    }

    public static int findMinAbs(int[] array) {
        var min = array[0];
        var minAbs = Math.abs(min);
        for (var val : array) {
            if (Math.abs(val) < minAbs) {
                min = val;
                minAbs = Math.abs(min);
            }
        }
        return min;
    }
}
