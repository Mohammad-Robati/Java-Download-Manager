package MainPagePanels;

import java.io.*;

public class SaveDownloads {

    /**
     * simply save data in the .jdm files.
     */

    private BufferedWriter bw = null;

    public void saveDownload(ItemPanel itemPanel,String str) {

        try {
            if(itemPanel instanceof InProcessItemPanel)
                bw = new BufferedWriter(new FileWriter("src/Cache/processingList.jdm", true));
            else
                bw = new BufferedWriter(new FileWriter("src/Cache/completedList.jdm", true));

            if(itemPanel instanceof InProcessItemPanel) {
                bw.write("Processing" + str);
                ((InProcessItemPanel) itemPanel).getDownload().cancel();
            } else {
                bw.write("Completed" + str);
            }
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try { bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }

    }
    public void saveRemovedDownload(ItemPanel itemPanel) {
        try {
                bw = new BufferedWriter(new FileWriter("src/Cache/removed.jdm", true));
                if(itemPanel instanceof CompletedItemPanel)
                bw.write("Completed௳Removed௳" + itemPanel.getName_() + "௳" + itemPanel.getURL());
                else if (itemPanel instanceof InProcessItemPanel)
                    bw.write("InProcessing௳Removed௳" + itemPanel.getName_() + "௳" + itemPanel.getURL());
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try { bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    }
    public void clearFileCompleted() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/Cache/completedList.jdm");
        pw.close();
    }
    public void clearFileProcessing() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/Cache/processingList.jdm");
        pw.close();
    }
    public void clearFileQueue() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/Cache/queueList.jdm");
        pw.close();
    }
    public void clearFileRestrictedURLs() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/Cache/filter.jdm");
        pw.close();
    }

    public void clearFileSettings() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/Cache/settings.jdm");
        pw.close();
    }

    public void clearAllFiles() throws FileNotFoundException {
        clearFileSettings();
        clearFileQueue();
        clearFileCompleted();
        clearFileProcessing();
        clearFileRestrictedURLs();
    }

    public void saveQueue(QueueItem queueItem) {
        try {
            bw = new BufferedWriter(new FileWriter("src/Cache/queueList.jdm", true));
            bw.write("Queue௳" + queueItem.getName_() + "௳" + queueItem.getStartAt() + "௳" + queueItem.getTimer() + "௳" + queueItem.getDate());
            for(int i=0;i<queueItem.getQueueBase().getModel().size();i++) {
                bw.write("௳" + queueItem.getQueueBase().getModel().getElementAt(i));
            }
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try { bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    }

    public void saveRestrictedURL(String str){
        try {
            bw = new BufferedWriter(new FileWriter("src/Cache/filter.jdm", true));
            bw.write(str);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try { bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    }

    public void saveSettings() {
        String lookandfeel = "";
        if(MainPanel.toolbarPanel.settingsPanel.getNimbusRadioButton().isSelected()){
            lookandfeel="Nimbus";
        } else if (MainPanel.toolbarPanel.settingsPanel.getDefaultRadioButton().isSelected()) {
            lookandfeel="Metal";
        } else if (MainPanel.toolbarPanel.settingsPanel.getWindowsRadioButton().isSelected()) {
            lookandfeel="Windows";
        }
        try {
            bw = new BufferedWriter(new FileWriter("src/Cache/settings.jdm", true));
            if(MainPanel.toolbarPanel.settingsPanel.checkBox.isSelected()) {
                bw.write("" + true + "௳" + null
                        + "௳" + lookandfeel + "௳" + SettingsPanel.currentPath);
            } else {
                bw.write(false + "௳" + MainPanel.toolbarPanel.settingsPanel.limitSpinner.getValue()
                        + "௳" + lookandfeel + "௳" + SettingsPanel.currentPath);
            }
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally { // always close the file
            if (bw != null) {
                try { bw.close();
                } catch (IOException ioe2) {
                    // just ignore it
                }
            }
        }
    }

}
