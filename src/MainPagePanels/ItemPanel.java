package MainPagePanels;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {

    /**
     * Parent class for CompletedItemPanel, InProcessItemPanel, QueueItem
     */

    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel openFolderButton;
    private JLabel removeFileButton;

    private String name_ = "Download";
    private String size_ = "25";
    private String date = "2018/08/15";
    private String path = "Desktop";
    private String URL = "www.google.com";


    ItemPanel(JLabel imageLabel, JLabel openFolderButton, JLabel removeFileButton) {

        this.imageLabel = imageLabel;
        this.openFolderButton = openFolderButton;
        this.removeFileButton = removeFileButton;
        nameLabel = new JLabel(getName_());

        setDate(DateGenerator.generate());
        setPath(SettingsPanel.currentPath);

        openFolderButton.setToolTipText("Open File Location");
        removeFileButton.setToolTipText("Remove File");
        setBackground(new Color(239,178,63));

    }

    JLabel getImageLabel() {
        return imageLabel;
    }

    JLabel getNameLabel() {
        return nameLabel;
    }

    JLabel getOpenFolderButton() {
        return openFolderButton;
    }

    JLabel getRemoveFileButton() {
        return removeFileButton;
    }

/*    public String getURL() { return URL; }

    public String getPath() { return path; }*/

    /**
     * This class creates the frame for downloads details
     * which will be appeared if u click the right button of mouse
     * on download panels...
     */

    class DownloadInfoFrame extends JFrame {

        JPanel panel = new JPanel();
        JLabel name = new JLabel("Name:     " + getName_());
        JSeparator separator1 = new JSeparator();
        JLabel size = new JLabel("Size:       " + getSize_());
        JSeparator separator2 = new JSeparator();
        JLabel date = new JLabel("Date:       " + getDate());
        JSeparator separator3 = new JSeparator();
        JLabel path = new JLabel("Path:       " + getPath());
        JSeparator separator4 = new JSeparator();
        JLabel URL = new JLabel("URL:        " + getURL());
        JSeparator separator5 = new JSeparator();


        DownloadInfoFrame() {


            separator1.setForeground(Color.BLACK);
            separator2.setForeground(Color.BLACK);
            separator3.setForeground(Color.BLACK);
            separator4.setForeground(Color.BLACK);
            separator5.setForeground(Color.BLACK);

            panel.setBackground(new Color(243,233,167));
            add(panel);
            setSize(300,300);
            setIconImage(Toolkit.getDefaultToolkit().getImage("" +
                    "src/Icons/icon.png"));

            GroupLayout layout = new GroupLayout(panel);
            panel.setLayout(layout);

            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(name)
                                            .addComponent(separator1)
                                            .addComponent(size)
                                            .addComponent(separator2)
                                            .addComponent(date)
                                            .addComponent(separator3)
                                            .addComponent(path)
                                            .addComponent(separator4)
                                            .addComponent(URL)
                                            .addComponent(separator5)
                            )
                            .addContainerGap()

            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGap(25)
                            .addComponent(name)
                            .addComponent(separator1)
                            .addComponent(size)
                            .addComponent(separator2)
                            .addComponent(date)
                            .addComponent(separator3)
                            .addComponent(path)
                            .addComponent(separator4)
                            .addComponent(URL)
                            .addComponent(separator5)

            );
        }
    }
    public void showDownloadInfoFrame(){

    }

    public String getName_() { return name_; }

    public void setName_(String name_) { this.name_ = name_; }

    public String getSize_() { return size_; }

    public void setSize_(String size_) { this.size_ = size_; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public String getURL() { return URL; }

    public void setURL(String URL) { this.URL = URL; }

}
