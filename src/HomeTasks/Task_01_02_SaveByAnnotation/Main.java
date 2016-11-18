package HomeTasks.Task_01_02_SaveByAnnotation;

/*
2. Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать:
        1) в какой файл должен сохраниться текст
        2) метод, который выполнит сохранение.
   Написать класс Saver, который сохранит поле класса TextContainer в указанный файл.

        @SaveTo(path=“c:\\file.txt”)
        class Container {
            String text = “…”;
            @Saver
            public void save(..) {…}
        }
*/

public class Main {
    public static void main(String[] args) {
        TextContainer textContainer = new TextContainer("Hello)");
        Saver saver = new Saver(textContainer);

        if (saver.save()) {
            System.out.println("OK!");
        } else {
            System.out.println("Error!");
        }

    }
}
