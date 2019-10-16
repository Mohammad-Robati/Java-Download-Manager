package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class InProcessItemPanel extends ItemPanel {

    /**
     * subclass of ItemPanel and used for inserting
     * processing panel
     */

    private JLabel startOrPauseButton;
    private DownloadInfoFrame downloadInfoFrame;
    private JProgressBar progressBar;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JLabel speedAndTimeLabel = new JLabel("speedAndTimeLabel");
    private JLabel progressPercentLabel = new JLabel("progressPercentLabel");
    private JLabel alreadyDownloadedLabel = new JLabel("alreadyDownloadedLabel");

    public Download getDownload() {
        return download;
    }

    Download download;

    public InProcessItemPanel(Download download){
        super(new JLabel(Icons.LOAD),
                new JLabel(Icons.OPENFOLDER_ITEM),
                new JLabel(Icons.REMOVE_ITEM));

        this.download = download;
        startOrPauseButton = new JLabel(Icons.PLAY_ITEM);
        startOrPauseButton.setToolTipText("Start/Pause");
        downloadInfoFrame = new DownloadInfoFrame();

        setURL(download.getUrl());
        setName_(download.getUrl().substring(download.getUrl().lastIndexOf('/') + 1).substring(0,
                download.getUrl().substring(download.getUrl().lastIndexOf('/') + 1).lastIndexOf('.')+4));
        getNameLabel().setText(download.getUrl().substring(download.getUrl().lastIndexOf('/') + 1).substring(0,
                download.getUrl().substring(download.getUrl().lastIndexOf('/') + 1).lastIndexOf('.')+4));

        progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
        progressBar.setForeground(Color.BLACK);
        progressBar.setBackground(new Color(243,233,167));
        progressBar.setBorderPainted(false);

        progressBar1 = new JProgressBar(SwingConstants.HORIZONTAL);
        progressBar1.setForeground(Color.BLACK);
        progressBar1.setBackground(new Color(243,233,167));
        progressBar1.setBorderPainted(false);

        progressBar2 = new JProgressBar(SwingConstants.HORIZONTAL);
        progressBar2.setForeground(Color.BLACK);
        progressBar2.setBackground(new Color(243,233,167));
        progressBar2.setBorderPainted(false);

        MouseListenerForProcessItem mouseListenerForProcessItem = new MouseListenerForProcessItem();
        startOrPauseButton.addMouseListener(mouseListenerForProcessItem);
        getOpenFolderButton().addMouseListener(mouseListenerForProcessItem);
        getRemoveFileButton().addMouseListener(mouseListenerForProcessItem);

        JButton virtualUpdateButton = new JButton();
        virtualUpdateButton.addActionListener(e -> {
            progressBar.setValue((int)download.getThreadDownloads()[0].getProgress());
            progressBar1.setValue((int)download.getThreadDownloads()[1].getProgress());
            progressBar2.setValue((int)download.getThreadDownloads()[2].getProgress());

            alreadyDownloadedLabel.setText((download.getDownloaded()/1000) + "KB / " + (download.getSize()/1000) + "KB");
            if(String.valueOf(download.getProgress()).length()>=5)
            progressPercentLabel.setText(download.getProgress().substring(0,4) + "%");
            speedAndTimeLabel.setText(download.getSpeed());
            setSize_(String.valueOf(download.getSize()));
        });

        new UpdateInfoOnDownloadPanel(virtualUpdateButton,download);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(getImageLabel())
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                .addComponent(getNameLabel())
                                                .addGap(10,20,Short.MAX_VALUE)
                                                .addComponent(progressPercentLabel)
                                        )
                                        .addGroup(
                                               layout.createSequentialGroup()
                                                       .addComponent(progressBar)
                                                       .addComponent(progressBar1)
                                                       .addComponent(progressBar2)
                                        )
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addComponent(startOrPauseButton,-2,20,-2)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(getOpenFolderButton(),-2,20,-2)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(getRemoveFileButton(),-2,20,-2)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(speedAndTimeLabel)
                                                        .addGap(10,20,Short.MAX_VALUE)
                                                        .addComponent(alreadyDownloadedLabel)
                                        )
                        )
                        .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(getImageLabel())
                                        )
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(getNameLabel())
                                                                        .addComponent(progressPercentLabel)
                                                        )
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(progressBar)
                                                                        .addComponent(progressBar1)
                                                                        .addComponent(progressBar2)
                                                        )
                                                        .addComponent(progressBar)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(startOrPauseButton,-2,20,-2)
                                                                        .addComponent(getOpenFolderButton(),-2,20,-2)
                                                                        .addComponent(getRemoveFileButton(),-2,20,-2)
                                                                        .addComponent(speedAndTimeLabel)
                                                                        .addComponent(alreadyDownloadedLabel)
                                                        )
                                        )

                        )
                .addContainerGap()
        );
    }

    private class MouseListenerForProcessItem implements java.awt.event.MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==startOrPauseButton){
                if(download.getStatus()!=3) {
                    if (download.getStatus() == 1) {
                        download.resume();
                    } else {
                        download.pause();
                    }
                }
            }  else if (e.getSource()==getRemoveFileButton()) {
                InProcessItemPanel.this.setBackground(new Color(239, 100, 63));
                MainFrame.menuBarForFrame.getRemove().doClick();
                InProcessItemPanel.this.setVisible(false);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==startOrPauseButton){
                startOrPauseButton.setIcon(Icons.PLAY_ITEM_CLICKED);
            } else if (e.getSource()==getOpenFolderButton()) {
                getOpenFolderButton().setIcon(Icons.OPENFOLDER_ITEM_CLICKED);
            } else if (e.getSource()==getRemoveFileButton()) {
                getRemoveFileButton().setIcon(Icons.REMOVE_ITEM_CLICKED);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getSource()==startOrPauseButton){
                startOrPauseButton.setIcon(Icons.PLAY_ITEM);
            } else if (e.getSource()==getOpenFolderButton()) {
                getOpenFolderButton().setIcon(Icons.OPENFOLDER_ITEM);
            } else if (e.getSource()==getRemoveFileButton()) {
                getRemoveFileButton().setIcon(Icons.REMOVE_ITEM);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    @Override
    public void showDownloadInfoFrame() {
        super.showDownloadInfoFrame();
        downloadInfoFrame.name.setText("Name:     " + getName_());
        downloadInfoFrame.size.setText("Size:       " + getSize_());
        downloadInfoFrame.date.setText("Date:       " + getDate());
        downloadInfoFrame.path.setText("Path:       " + getPath());
        downloadInfoFrame.URL.setText("URL:        " + getURL());
        System.out.println();
        downloadInfoFrame.setVisible(true);
    }

    public String createHumanReadableData(){
        return "௳" + getName_() + "௳" + getSize_() +
                "௳" + getDate() + "௳" + getPath() +
                "௳" + getURL();
    }

    public JLabel getProgressPercentLabel() {
        return progressPercentLabel;
    }

}
