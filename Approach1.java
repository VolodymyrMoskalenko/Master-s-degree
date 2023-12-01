import java.util.*;

class Approach1 {
  public int furthestElement(int[] array, int smallValue, int largeValue) {
    // Створення черги пріоритетів з компаратором для побудови мінімальної купи
    Queue<Integer> largeValuesAllocations = new PriorityQueue<>((a, b) -> a - b);

    for (int i = 0; i < array.length - 1; i++) {
      int diff = array[i + 1] - array[i];
      // Якщо різниця між елементами негативна – продовжуємо далі цикл
      if (diff <= 0) {
        continue;
      }
      // Інакше виділяємо велике значення
      largeValuesAllocations.add(diff);

      // Якщо не перевищено кількість largeValue, більше нічого не потрібно робити.
      if (largeValuesAllocations.size() <= largeValue) {
        continue;
      }

      // В іншому випадку нам потрібно буде піднятися з largeValuesAllocations
      smallValue -= largeValuesAllocations.remove();

      // Якщо це спричинило від’ємне значення smallValue, ми не зможемо дістатися // до i + 1
      if (smallValue < 0) {
        return i;
      }
    }

    // Якщо ми потрапили сюди, це означає, що у нас було достатньо матеріалів,
    // щоб покрити кожну різницю між елементами.
    return array.length - 1;
  }
}
