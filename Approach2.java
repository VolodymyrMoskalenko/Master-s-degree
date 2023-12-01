import java.util.*;

class Approach2 {

  public int furthestElement(int[] array, int smallValue, int largeValue) {
    // Створення черги пріоритетів з компаратором для побудови максимальної купи
    Queue<Integer> smallValuesAllocations = new PriorityQueue<>((a, b) -> b - a);

    for (int i = 0; i < array.length - 1; i++) {
      int diff = array[i + 1] - array[i];
      // Якщо різниця між елементами негативна – продовжуємо далі цикл
      if (diff <= 0) {
        continue;
      }

      // Інакше виділяємо велике значення
      smallValuesAllocations.add(diff);
      smallValue -= diff;

      // Якщо були використані усі smallValue, і немає жодного largeValue , то
      // не можливо ітеруватись далі циклом.
      if (smallValue < 0 && largeValue == 0) {
        return i;
      }

      // Інакше, якщо були використані усі smallValue, ми маємо замінити найбільше
      // smallValue на largeValue.
      if (smallValue < 0) {
        smallValue += smallValuesAllocations.remove();
        largeValue--;
      }
    }

    // Якщо ми потрапили сюди, це означає, що у нас було достатньо матеріалів,
    // щоб покрити кожну різницю між елементами.
    return array.length - 1;
  }
}
