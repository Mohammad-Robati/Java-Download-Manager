package MainPagePanels;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunQueue implements Runnable {

    /**
     * It will play the downloadings for the queues
     * it implements runnable and works in a thread
     * which does three things:
     * first, runs when a normal queue starts
     * seconds, runs when a queue timer is up
     * third, runs when start-at time of a queue is reached.
     */

    private QueueItem queueItem;

    RunQueue(QueueItem queueItem) {
        this.queueItem = queueItem;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public synchronized void run() {
        int flag=0;
        Date date;
        SimpleDateFormat simpleDateFormat;
        while (queueItem.getQueueBase().getModel().size()!=0) {
            if(queueItem.getTimer().equals("null")) {
                date = new Date();
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                if (simpleDateFormat.format(date).equals(queueItem.getStartAt()) && flag==0) {
                    try {
                        downloadSequencially(queueItem.getQueueBase().getModel());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    flag=1;
                }
            } else if (queueItem.getStartAt().equals("null")) {
                date = new Date();
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                if (simpleDateFormat.format(date).equals(getProperTimer(queueItem.getTimer(),queueItem.getDate()))
                        && flag==0) {
                    try {
                        downloadSequencially(queueItem.getQueueBase().getModel());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    flag=1;
                }
            }
            if (queueItem.getStartAt().equals("null") && queueItem.getTimer().equals("null")) {
                System.out.println("asd");
                try {
                    downloadSequencially(queueItem.getQueueBase().getModel());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void downloadSequencially(DefaultListModel<String> model) throws IOException {
            Download download = new Download(new URL(model.get(0)));
            insertDownloadWithLimitCheck(download);
            model.remove(0);
            while (true) {
                if(download.getStatus()==2){
                    download = new Download(new URL(model.get(0)));
                    insertDownloadWithLimitCheck(download);
                    model.remove(0);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(model.size()==0) {
                    queueItem.setOnline(false);
                    break;
                }
            }
    }

    public static String getProperTimer(String str, String str2) {

        int hours = Integer.parseInt(str.substring(0,2));
        int minutes = Integer.parseInt(str.substring(3,5));
        int seconds = Integer.parseInt(str.substring(6,8));
        if(str.endsWith("AM") && (hours==12)){
            hours = 0;
        }
        if(str.endsWith("PM") && (hours!=12)){
            hours = 12 + hours;
        }

        int init_hours = Integer.parseInt(str2.substring(11,13));
        int init_minutes = Integer.parseInt(str2.substring(14,16));
        int init_seconds = Integer.parseInt(str2.substring(17,19));

        Date date1 = new Date(0,0,0,hours,minutes,seconds);
        Date date2 = new Date(0,0,0,init_hours,init_minutes,init_seconds);

        long sum = date1.getTime() + date2.getTime();
        Date sumDate = new Date(sum);

        return sumDate.toString().substring(11,19);
    }

    private void insertDownloadWithLimitCheck(Download download) {

        if (MainPanel.toolbarPanel.getSettingsPanel().checkBox.isSelected()
                || ((int) MainPanel.toolbarPanel.getSettingsPanel().limitSpinner.getValue() < (MainPanel.processingPanel.panels.size()))) {
            MainPanel.processingPanel.insertDownload(new InProcessItemPanel(download));
            MainPanel.toolbarPanel.getSort().setSelectedItem("By Name");
            MainPanel.toolbarPanel.getSort().setSelectedItem("By Date");
            MainPanel.processingPanel.validate();
        } else {
            if (((int) MainPanel.toolbarPanel.getSettingsPanel().limitSpinner.getValue()) > (MainPanel.processingPanel.panels.size())) {
                MainPanel.processingPanel.insertDownload(new InProcessItemPanel(download));
                MainPanel.toolbarPanel.getSort().setSelectedItem("By Name");
                MainPanel.toolbarPanel.getSort().setSelectedItem("By Date");
                MainPanel.processingPanel.validate();
            } else {
                JOptionPane.showMessageDialog(MainFrame.frame, "You Have Reached Your Download Limit!", "Warning", JOptionPane.INFORMATION_MESSAGE
                        , Icons.CANCEL_TOOLBAR);
                new DownloadWaiting(download);
            }
        }

    }
}
