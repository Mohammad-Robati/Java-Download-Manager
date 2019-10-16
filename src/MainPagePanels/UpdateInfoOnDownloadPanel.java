package MainPagePanels;

import javax.swing.*;
import java.util.ConcurrentModificationException;

public class UpdateInfoOnDownloadPanel implements Runnable {

    /**
     * This class constantly updates the info on the inProcessingItemPanel
     * note that there is a virtual button in the InProcessingItemPanel
     * which has an actionListener to update info
     * this class will doClick this virtualButton
     */

    private JButton button;
    Download download;

    UpdateInfoOnDownloadPanel(JButton button, Download download) {
        this.button = button;
        this.download = download;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (download.getStatus()!=2) {
            button.doClick();
            if(download.getProgress().substring(0,3).equals("100"))
                download.setStatus(2);
            if(download.getStatus()==3) {
                download.setSpeed("     Canceled!");
                download.setDownloaded(0);
                download.setSize(0);
            }
        }
        CompletedItemPanel completedItemPanel = null;
            for(ItemPanel i : MainPanel.processingPanel.panels) {
                if(i instanceof InProcessItemPanel) {
                    if(((InProcessItemPanel) i).getProgressPercentLabel().getText().equals("100.%")
                          &&   i.isVisible()) {
                        i.setVisible(false);
                        completedItemPanel = new CompletedItemPanel(GetWindowsDefaultIcon.getIcon(i.getPath(),i.getName_()));
                        completedItemPanel.setName_(i.getName_());
                        completedItemPanel.getNameLabel().setText(i.getName_());
                        if(i.getSize_().length()>4)
                        completedItemPanel.getSizeLabel().setText((Integer.parseInt(i.getSize_())/1000) + "KB");
                        else completedItemPanel.getSizeLabel().setText(i.getSize_()+"B");
                    }
                }
            }

        if (completedItemPanel != null) {
            completedItemPanel.setURL(download.getUrl());
            completedItemPanel.setSize_(String.valueOf(download.getSize()));
        MainPanel.completedPanel.insertDownload(completedItemPanel);
        }
            try {
                for (ItemPanel i : MainPanel.processingPanel.panels) {
                    if (i instanceof InProcessItemPanel) {
                        if (!i.isVisible()) {
                            MainPanel.processingPanel.panels.remove(i);
                        }
                    }
                }
            } catch (ConcurrentModificationException ignored){

            }
    }
}
