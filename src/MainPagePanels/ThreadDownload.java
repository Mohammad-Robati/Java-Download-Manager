package MainPagePanels;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class ThreadDownload implements Runnable{

    /**
     * It will download data from the internet.
     * note that it gets the startByte and endByte
     * because it serves the multi-segment downloading
     * algorithm.
     */

    // Max size of download buffer.
    private static final int MAX_BUFFER_SIZE = 1024;

    // These are the status codes.
    private static final int DOWNLOADING = 0;
    //private static final int PAUSED = 1;
    private static final int COMPLETE = 2;
    //private static final int CANCELLED = 3;
    private static final int ERROR = 4;
    private URL url; // download URL
    private int size; // size of download in bytes
    private String speed;
    private int downloaded; // number of bytes downloaded
    private int status; // current status of download
    private int startByte;
    private int endByte;


    ThreadDownload(URL url, int startByte, int endByte) {
        this.url = url;
        this.startByte = startByte;
        this.endByte = endByte;

        size = -1;
        downloaded = 0;
        status = DOWNLOADING;

        // Begin the download.
        download();
    }

    // Get this download's URL.
    public String getUrl() {
        return url.toString();
    }

    // Get this download's size.
    public int getSize() {
        return size;
    }

    // Get this download's progress.
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }

    // Get this download's status.
    public int getStatus() {
        return status;
    }

    /*
    // Pause this download.
    public void pause() {
        status = PAUSED;
    }

    // Resume this download.
    public void resume() {
        status = DOWNLOADING;
        download();
    }


    // Cancel this download.
    public void cancel() {
        status = CANCELLED;
    }

    */
    public void setStatus(int status) {
        this.status = status;
    }

    // Mark this download as having an error.
    private void error() {
        status = ERROR;
    }

    // Start or resume downloading.
    public void download() {
        Thread thread = new Thread(this);
        thread.start();
    }


    // Download file.
    public void run() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile(SettingsPanel.currentPath + "/" +
                    getUrl().substring(getUrl().lastIndexOf('/') + 1).substring(0,
                            getUrl().substring(getUrl().lastIndexOf('/') + 1).lastIndexOf('.')+4)
                    , "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStream stream = null;

        try {
            // Open connection to URL.
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            // Specify what portion of file to download.
            connection.setRequestProperty("Range", "bytes=" + (startByte+downloaded) + "-" + endByte);

            // Connect to server.
            connection.connect();

            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2) {
                error();
            }

            size = endByte - startByte + 1;

            // Get file name portion of URL.

            // Open file and seek to the end of it.
            // if you pause the download, as you chose to resume, it will set the file pointer after downloaded!

            if (file != null) {
                file.seek(startByte + downloaded);
            }

            stream = connection.getInputStream();

            while (status == DOWNLOADING) {
        /* Size buffer according to how much of the
           file is left to download. */
                byte buffer[];
                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[size - downloaded];
                }

                // Read from server into buffer and calculate speed.
                Long start = System.nanoTime();
                int read = stream.read(buffer);
                Long end = System.nanoTime();

                if (read == -1){
                    break;
                }


                speed = String.valueOf((double)read/(end-start)*10000).substring(0,6) + "KB/sec";

                // Write buffer to file.
                if (file != null) {
                    file.write(buffer, 0, read);
                }
                downloaded += read;
            }

      /* Change status to complete if this point was
         reached because downloading has finished. */
            if (status == DOWNLOADING) {
                status = COMPLETE;
            }
        } catch (Exception e) {
            error();
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception ignored) {}
            }

            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ignored) {}
            }
        }
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(int downloaded) {
        this.downloaded = downloaded;
    }

}
