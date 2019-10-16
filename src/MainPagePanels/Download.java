package MainPagePanels;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Download {

    /**
     * it contains an array of three ThreadDownloads
     * in order to download in multi-Threaded form
     * which increases the speed
     * this class is actually like a bridge between the
     * getting data from the internet and showing them in the app
     */

    private URL url;

    private int size;

    private int remainingByte;

    private ThreadDownload[] threadDownloads = new ThreadDownload[3];

    public Download(URL url) {
        this.url = url;
        initiateInformation();
    }

    private void initiateInformation() {

        try {

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            size = ((connection.getContentLength()));

            remainingByte = size % 3;

            size /= 3;

        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }


        int first = 0, last = size - 1;

        for (int i = 0; i < 3; i++) {
            if (i != 2) {
                threadDownloads[i] = new ThreadDownload(url, first, last);
            } else {
                threadDownloads[i] = new ThreadDownload(url, first, last + remainingByte);
            }
            first = last + 1;
            last += size;
        }

    }

    public String getUrl() {
        return url.toString();
    }

    public ThreadDownload[] getThreadDownloads() {
        return threadDownloads;
    }

    public String getSpeed() {
        return threadDownloads[1].getSpeed();
    }

    public void setSpeed(String speed) {
        threadDownloads[0].setSpeed(speed);
        threadDownloads[1].setSpeed(speed);
        threadDownloads[2].setSpeed(speed);
    }

    public void setDownloaded(int downloaded) {
        threadDownloads[0].setDownloaded(downloaded);
        threadDownloads[1].setDownloaded(downloaded);
        threadDownloads[2].setDownloaded(downloaded);
    }


    public void setSize(int size) {
        this.size = size;
    }

    public int getDownloaded() {
        return threadDownloads[0].getDownloaded() + threadDownloads[1].getDownloaded() + threadDownloads[2].getDownloaded();
    }


    public int getSize() {
        return size*3;
    }

    public String getProgress() {
        return String.valueOf(((double)getDownloaded()/getSize())*100);
    }

    public int getStatus() {
        for(int i=0;i<5;i++) {
            if (threadDownloads[0].getStatus() == i && threadDownloads[1].getStatus() == i && threadDownloads[2].getStatus() == i) {
                return i;
            }
        }
        return 0;
    }

    public void setStatus(int i){
        threadDownloads[0].setStatus(i);
        threadDownloads[1].setStatus(i);
        threadDownloads[2].setStatus(i);
    }

    public void pause() {
        setStatus(1);
    }

    // Resume this download.
    public void resume() {
        setStatus(0);
        threadDownloads[0].download();
        threadDownloads[1].download();
        threadDownloads[2].download();
    }

    // Cancel this download.
    public void cancel() {
        setStatus(3);
    }

}