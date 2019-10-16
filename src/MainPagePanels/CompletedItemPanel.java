package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CompletedItemPanel extends ItemPanel {

    /**
     * subclass of ItemPanel and used for inserting
     * completed download panel
     */

    private JLabel openFileButton;
    private JLabel dateLabel;
    private JLabel sizeLabel;
    private DownloadInfoFrame downloadInfoFrame;

    CompletedItemPanel(ImageIcon imageIcon){

        super(new JLabel(imageIcon),
                new JLabel(Icons.OPENFOLDER_ITEM),
                new JLabel(Icons.REMOVE_ITEM));
        dateLabel = new JLabel(getDate());
        sizeLabel = new JLabel(getSize_());
        openFileButton = new JLabel(Icons.PLAY_ITEM);
        downloadInfoFrame = new DownloadInfoFrame();
        openFileButton.setToolTipText("Open File");

        MouseListenerForCompletedItem mouseListenerForCompletedItem = new MouseListenerForCompletedItem();
        openFileButton.addMouseListener(mouseListenerForCompletedItem);
        getOpenFolderButton().addMouseListener(mouseListenerForCompletedItem);
        getRemoveFileButton().addMouseListener(mouseListenerForCompletedItem);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(20)
                .addComponent(getImageLabel())
                .addGap(25)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getNameLabel())
                        .addGroup(
                                layout.createSequentialGroup()
                                .addComponent(openFileButton,-2,20,-2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getOpenFolderButton(),-2,20,-2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getRemoveFileButton(),-2,20,-2)
                        )
                )
                .addGap(20,40,Short.MAX_VALUE)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(dateLabel, GroupLayout.Alignment.TRAILING)
                        .addComponent(sizeLabel, GroupLayout.Alignment.TRAILING)
                )
                .addGap(20)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(10)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(
                                        layout.createSequentialGroup()
                                        .addGap(10)
                                                .addComponent(getImageLabel())
                                )
                                .addGroup(
                                        layout.createSequentialGroup()
                                        .addGap(5)
                                        .addComponent(getNameLabel())
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(
                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(openFileButton,-2,20,-2)
                                                        .addComponent(getOpenFolderButton(),-2,20,-2)
                                                        .addComponent(getRemoveFileButton(),-2,20,-2)
                                        )
                                )
                                .addGroup(
                                        layout.createSequentialGroup()
                                        .addGap(5)
                                        .addComponent(dateLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sizeLabel)
                                )
                )

                .addGap(20)
        );
    }
    private class MouseListenerForCompletedItem implements java.awt.event.MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==openFileButton){
                OpenAFileOrFolder.openFile(getPath(),getName_());
            } else if (e.getSource()==getOpenFolderButton()) {
                try {
                    OpenAFileOrFolder.openFolder(getPath());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource()==getRemoveFileButton()) {
                CompletedItemPanel.this.setBackground(new Color(239, 100, 63));
                MainFrame.menuBarForFrame.getRemove().doClick();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==openFileButton){
                openFileButton.setIcon(Icons.PLAY_ITEM_CLICKED);
            } else if (e.getSource()==getOpenFolderButton()) {
                getOpenFolderButton().setIcon(Icons.OPENFOLDER_ITEM_CLICKED);
            } else if (e.getSource()==getRemoveFileButton()) {
                getRemoveFileButton().setIcon(Icons.REMOVE_ITEM_CLICKED);
                System.out.println();
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getSource()==openFileButton){
                openFileButton.setIcon(Icons.PLAY_ITEM);
            } else if (e.getSource()==getOpenFolderButton()) {
                getOpenFolderButton().setIcon(Icons.OPENFOLDER_ITEM);
            } else if (e.getSource()==getRemoveFileButton()) {
                getRemoveFileButton().setIcon(Icons.REMOVE_ITEM);
                System.out.println();
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
        downloadInfoFrame.setVisible(true);
    }

    public String createHumanReadableData(){
        return "௳" + getName_() + "௳" + getSize_() +
                "௳" + getDate() + "௳" + getPath() +
                "௳" + getURL();
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JLabel getSizeLabel() {
        return sizeLabel;
    }

}
