package MainPagePanels;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenAFileOrFolder {

    /**
     * contains static methods for the file and folder opening
     * @param path file path
     * @param name file name
     */

    public static void openFile(String path,String name) {
        File file = new File(path + "\\" + name + "");

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void openFolder(String path) throws IOException {
        Desktop.getDesktop().open(new File(path));
    }

}
