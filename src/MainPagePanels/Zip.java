package MainPagePanels;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    /**
     * Contains a static method to make the zip file for the .jdm files.
     * @param zipFile name
     * @param srcFiles path
     */
    public static void generateZip(String zipFile, String[] srcFiles) {
        try {

            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (String srcFile1 : srcFiles) {
                File srcFile = new File(srcFile1);
                FileInputStream fis = new FileInputStream(srcFile);
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
        } catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    }
}
