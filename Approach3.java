import java.util.*;

class Approach3 {

  public int furthestElement(int[] array, int smallValue, int largeValue) {
    // Виконано двійковий пошук у масиві масиву, щоб знайти остаточну досяжну будівлю.

    int lowBorder = 0;
    int highBorder = array.length - 1;

    while (lowBorder < highBorder) {
      int middleValue = lowBorder + (highBorder - lowBorder + 1) / 2;
      if (isSusceptible(middleValue, array, smallValue, largeValue)) {
        lowBorder = middleValue;
      } else {
        highBorder = middleValue - 1;
      }
    }

    return highBorder;
  }

  private boolean isSusceptible(
    int elementIndex,
    int[] array,
    int smallValue,
    int largeValue
  ) {
    // Складено список усіх різниць між елементами, які нам потрібно зробити, щоб досягти elementIndex.

    List<Integer> diffs = new ArrayList<>();

    for (int i = 0; i < elementIndex; i++) {
      int leftHeight = array[i];
      int rightHeight = array[i + 1];
      if (rightHeight <= leftHeight) {
        continue;
      }
      diffs.add(rightHeight - leftHeight);
    }

    Collections.sort(diffs);

    // А тепер визначимо, чи можна покрити всі ці різниці між елементами за допомогою smallValue та largeValue.

    for (int diff : diffs) {
      if (diff <= smallValue) {
        smallValue -= diff;
      } else if (largeValue >= 1) {
        largeValue -= 1;
      } else {
        return false;
      }
    }

    return true;
  }
}
