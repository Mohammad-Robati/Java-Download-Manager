package MainPagePanels;


public class DownloadWaiting implements Runnable {

    /**
     * When a download cannot be inserted duo to
     * download limit, this class will make it wait
     * in a thread until one of the downloads gets
     * removed from the download list
     */

    Download download;

    DownloadWaiting(Download download) {
        this.download = download;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public synchronized void run() {
        while (!((int)MainPanel.toolbarPanel.getSettingsPanel().limitSpinner.getValue()>(MainPanel.processingPanel.panels.size()))){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("still here!");
        }
        MainPanel.processingPanel.insertDownload(new InProcessItemPanel(download));
    }
}
