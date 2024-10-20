package Hw2;

import java.util.ArrayList;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        var input = new Scanner(System.in);

        System.out.print("Введите числа через пробел: ");
        var numbers = input.nextLine().split(" ");

        var list = new ArrayList<Double>();
        for (String number : numbers) {
            list.add(Double.parseDouble(number));
        }
        System.out.println("Итоговый список: " + list);

        quickSort(list, 0, list.size() - 1);

        System.out.print("Отсортированный список: ");
        for (var value : list) {
            System.out.print(value + " ");
        }
    }

    public static void quickSort(ArrayList<Double> list, int low, int high) {
        if (low < high) {
            var pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Double> list, int low, int high) {
        var pivot = list.get(high);
        var i = (low - 1);
        for (var j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                var temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        var temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }
}
