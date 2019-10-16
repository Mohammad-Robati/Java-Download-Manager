package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainFrame {

    /**
     * Java Download Manager !!
     *
     *
     * Main class for running the program
     * note that the main frame of the whole program
     * is a static field in this class...
     * @author Moh.Robati
     * @version 1.00
     */

    public static JFrame frame = new JFrame("Java Download Manager");
    public static MenuBarForFrame menuBarForFrame;

    public static void main(String[] args) throws IOException {

        MainPanel mainPanel = new MainPanel();
        frame.setLocation(210,70);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Icons/icon.png"));
        frame.add(mainPanel);
        menuBarForFrame = new MenuBarForFrame();
        frame.setJMenuBar(menuBarForFrame);
        frame.setSize(900,600);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        tray();
        new LoadDownloads();
        SaveDownloads saveDownloads = new SaveDownloads();
        saveDownloads.clearFileProcessing();
        saveDownloads.clearFileCompleted();
        saveDownloads.clearFileQueue();
        saveDownloads.clearFileRestrictedURLs();
        saveDownloads.clearFileSettings();
        for(ItemPanel q : MainPanel.queuesPanel.getPanels()){
            if(!(((QueueItem)q).getTimer().equals("null") && ((QueueItem)q).getStartAt().equals("null")))
            new RunQueue((QueueItem) q);
        }
        frame.setVisible(true);
    }

    private static void tray() {

        SystemTray tray = SystemTray.getSystemTray();
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("src/Icons/icon.png"));
        try {
            tray.add(trayIcon);

        } catch (AWTException e)  {
            e.printStackTrace();
        }
        trayIcon.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                frame.setVisible(true);
            }
        });
    }
}
