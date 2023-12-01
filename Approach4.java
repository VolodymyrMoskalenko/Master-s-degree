import java.util.*;

class Approach4 {

  public int furthestElement(int[] array, int smallValue, int largeValue) {
    // Формувавання сортованого списку усіх значень
    List<int[]> sortedDiffs = new ArrayList<>();

    for (int i = 0; i < array.length - 1; i++) {
      int diff = array[i + 1] - array[i];
      if (diff <= 0) {
        continue;
      }
      sortedDiffs.add(new int[] { diff, i + 1 });
    }

    Collections.sort(sortedDiffs, (a, b) -> a[0] - b[0]);

    // Бінарний пошук
    int leftHeight = 0;
    int rightHeight = array.length - 1;

    while (leftHeight < rightHeight) {
      int middleElement = leftHeight + (rightHeight - leftHeight + 1) / 2;
      if (isSusceptible(middleElement, sortedDiffs, smallValue, largeValue)) {
        leftHeight = middleElement;
      } else {
        rightHeight = middleElement - 1;
      }
    }
    return rightHeight;
  }

  private boolean isSusceptible(
    int elementIndex,
    List<int[]> diffs,
    int smallValue,
    int largeValue
  ) {
    for (int[] diff : diffs) {
      // Отримаємо інформацію для різниці між елементами
      int diffElement = diff[0];
      int indexElement = diff[1];

      // Перевірка чи елемент знаходиться в певних межах
      if (indexElement > elementIndex) {
        continue;
      }

      // Виділення smallValue якщо достатня кількість наявна
      // Інакше виділяємо largeValue якщо хоча б одна величина є в наявності.

      if (diffElement <= smallValue) {
        smallValue -= diffElement;
      } else if (largeValue >= 1) {
        largeValue -= 1;
      } else {
        return false;
      }
    }

    return true;
  }
}
