package MainPagePanels;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

class LoadDownloads {

    /**
     * Reads .jdm files and loads data
     */

    LoadDownloads() {
        FileReader fileReader1;
        FileReader fileReader2;
        FileReader fileReader3;
        FileReader fileReader4;
        FileReader fileReader5;
        try {
                fileReader1 = new FileReader("src/Cache/processingList.jdm");
                fileReader2 = new FileReader("src/Cache/completedList.jdm");
                fileReader3 = new FileReader("src/Cache/queueList.jdm");
                fileReader4 = new FileReader("src/Cache/filter.jdm");
                fileReader5 = new FileReader("src/Cache/settings.jdm");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
            BufferedReader bufferedReader4 = new BufferedReader(fileReader4);
            BufferedReader bufferedReader5 = new BufferedReader(fileReader5);
            String line;
            String[] lineParts;
            while ((line = bufferedReader1.readLine()) != null) {
                lineParts = line.split("௳");
                InProcessItemPanel inProcessItemPanel = new InProcessItemPanel(new Download(new URL(lineParts[5])));
                inProcessItemPanel.getDownload().pause();
                inProcessItemPanel.setName_(lineParts[1]);
                inProcessItemPanel.getNameLabel().setText(lineParts[1]);
                inProcessItemPanel.setSize_(lineParts[2]);
                inProcessItemPanel.setDate(lineParts[3]);
                inProcessItemPanel.setPath(lineParts[4]);
                inProcessItemPanel.setURL(lineParts[5]);
                MainPanel.processingPanel.insertDownload(inProcessItemPanel);
            }
            while ((line = bufferedReader2.readLine()) != null) {
                lineParts = line.split("௳");
                CompletedItemPanel completedItemPanel = new CompletedItemPanel(GetWindowsDefaultIcon.getIcon(lineParts[4],lineParts[1]));
                completedItemPanel.setName_(lineParts[1]);
                completedItemPanel.getNameLabel().setText(lineParts[1]);
                completedItemPanel.setSize_(lineParts[2]);
                if(lineParts[2].length()>4)
                completedItemPanel.getSizeLabel().setText((Integer.parseInt(lineParts[2])/1000)+"KB");
                else completedItemPanel.getSizeLabel().setText(lineParts[2]+"B");
                completedItemPanel.setDate(lineParts[3]);
                completedItemPanel.getDateLabel().setText(lineParts[3]);
                completedItemPanel.setPath(lineParts[4]);
                completedItemPanel.setURL(lineParts[5]);
                MainPanel.completedPanel.insertDownload(completedItemPanel);
            }
            while ((line = bufferedReader3.readLine()) != null) {
                lineParts = line.split("௳");
                QueueItem queueItem = new QueueItem(lineParts[1],lineParts[2],lineParts[3]);
                queueItem.setDate(lineParts[4]);

                if(queueItem.getStartAt().equals("null")) {
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    if(simpleDateFormat.format(date).compareTo(RunQueue.getProperTimer(queueItem.getTimer(),queueItem.getDate()))>0){
                        queueItem.getStatusLabel().setText("Status: Expired");
                    } else {
                        queueItem.getStatusLabel().setText("");
                    }
                }
                queueItem.getDateLabel().setText(lineParts[4]);

                for(int i=5;i<=lineParts.length-1;i++) {
                    queueItem.getQueueBase().addTolist(lineParts[i]);
                }
                if(lineParts[2].equals("null") && lineParts[3].equals("null"))
                    queueItem.getStartButton().setVisible(true);
                MainPanel.queuesPanel.insertDownload(queueItem);
            }
            while ((line = bufferedReader4.readLine()) != null) {
                    MainPanel.toolbarPanel.settingsPanel.getRestrictedURLs().getModel().add(
                            MainPanel.toolbarPanel.settingsPanel.getRestrictedURLs().getModel().size(),line);
            }
            while ((line = bufferedReader5.readLine()) != null) {
                    lineParts = line.split("௳");
                    if(lineParts[0].equals("false")){
                        MainPanel.toolbarPanel.settingsPanel.checkBox.setSelected(false);
                        MainPanel.toolbarPanel.settingsPanel.limitSpinner.setValue(Integer.parseInt(lineParts[1]));
                        MainPanel.toolbarPanel.settingsPanel.limitSpinner.setEnabled(true);

                    }
                    switch (lineParts[2]) {
                        case "Metal":
                            MainPanel.toolbarPanel.settingsPanel.getDefaultRadioButton().setSelected(true);
                            break;
                        case "Nimbus":
                            MainPanel.toolbarPanel.settingsPanel.getNimbusRadioButton().setSelected(true);
                            break;
                        case "Windows":
                            MainPanel.toolbarPanel.settingsPanel.getWindowsRadioButton().setSelected(true);
                            break;
                    }
                    SettingsPanel.currentPath = lineParts[3];
                    MainPanel.toolbarPanel.settingsPanel.getOkButton().doClick();
            }
            fileReader1.close();
            fileReader2.close();
            fileReader3.close();
            fileReader4.close();
            fileReader5.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
