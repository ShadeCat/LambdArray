import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.out;
import static java.lang.System.in;

public class LambdArray {
    public static void main(final String[] args) {
        final int userMod = getUserMod();
        final int arrayLength = 10;
        int[] array1 = getFilledArray(arrayLength, userMod, Integer::sum);
        out.println(Arrays.toString(array1));
        int[] array2 = getFilledArray(arrayLength, userMod, ((i, modification) -> i * modification));
        out.println(Arrays.toString(array2));
        int[] array3 = getFilledArray(arrayLength, userMod, ((i, modification) -> {
            if (i % 2 == 0) {
                return i / 2 + modification;
            } else {
                return i * i - modification;
            }
        }));
        out.println(Arrays.toString(array3));
        // если кратен модификатору - обнулить
        int[] array4 = getFilledArray(arrayLength, userMod, ((i, modification) -> {
            if (i % modification == 0) {
                return 0;
            } else {
                return i;
            }
        }));
        out.println(Arrays.toString(array4));
        // если меньще модифкатора, то заменить на модификатор
        int[] array5 = getFilledArray(arrayLength, userMod, (Math::max));
        out.println(Arrays.toString(array5));
    }

    private static int getUserMod() {
        boolean isInputValid = false;
        int userMod = 0;
        while (!isInputValid) {
            try {
                Scanner scanner = new Scanner(in, "UTF-8");
                out.println("Введите модификатор");
                userMod = scanner.nextInt();
                scanner.close();
                isInputValid = true;
            } catch (NoSuchElementException e) {
                out.println("Нужно ввести число");
                isInputValid = false;
            }
        }
        return userMod;
    }

    private static int[] getFilledArray(final int length, final int modification, final FillArray method) {
        int[] newArray = new int[length];
        for (int i = 0; i < length; i++) {
            newArray[i] = method.process(i, modification);
        }
        return newArray;
    }
}