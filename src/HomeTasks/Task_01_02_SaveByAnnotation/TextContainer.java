package HomeTasks.Task_01_02_SaveByAnnotation;

import java.io.*;

@SaveTo(path="D:\\Java\\JavaPRO\\out\\production\\JavaPRO\\HomeTasks\\Task_01_02_SaveByAnnotation\\myfile.txt")
public class TextContainer {
    private String text;

    public TextContainer(String text) {
        this.text = text;
    }

    @Save
    public void save(String path) {
        System.out.println("Saving to: " + path);
        System.out.println("\tInfo: " + text);


        try (FileWriter out = new FileWriter(new File(path))) {
            out.write(text);
        } catch (FileNotFoundException e) {
            System.out.println("File '" + path + "' NOT FOUND.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("I/O exception. File not saved.");
            e.printStackTrace();
        }
    }

}
