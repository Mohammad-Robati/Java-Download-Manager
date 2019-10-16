package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DownloadsContainerPanel extends JScrollPane {

    /**
     * This class will holds the download panels
     * and also is used for holding queue panels
     * it has a arraylist field to hold the count
     * of panel which we put inside it...
     */

    private JPanel panel;
    ArrayList<ItemPanel> panels = new ArrayList<>();

    DownloadsContainerPanel() {
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel,BoxLayout.PAGE_AXIS);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setLayout(layout);
        panel.setBackground(new Color(243,233,167));
        panel.addMouseListener(new MouseListenerForDownloadPanels());
        setViewportView(panel);
    }

    public void insertDownload(ItemPanel itemPanel){

        panel.add(itemPanel);
        panels.add(itemPanel);
        panels.get(panels.size()-1).addMouseListener(new MouseListenerForDownloadPanels());

    }

    public void eraseAllDownloads(){
        for(ItemPanel i : panels)
        panel.remove(i);
        panels.clear();
    }

    private class MouseListenerForDownloadPanels implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for(ItemPanel p : panels){
                if (e.getSource()==p && SwingUtilities.isRightMouseButton(e)){
                    if(p instanceof InProcessItemPanel){
                        p.showDownloadInfoFrame();
                    } else if (p instanceof  CompletedItemPanel) {
                        p.showDownloadInfoFrame();
                    }
                }
            }
            if(e.getSource() instanceof CompletedItemPanel) {
                if (e.getClickCount() == 2) {
                    OpenAFileOrFolder.openFile(((CompletedItemPanel) e.getSource()).getPath(),((CompletedItemPanel) e.getSource()).getName_());
                }
            } else if (e.getSource() instanceof  QueueItem) {
                if(e.getClickCount() == 2) {
                    ((QueueItem) e.getSource()).getQueueBase().setVisible(true);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for(JPanel p : panels){
                if (e.getSource()==p){
                    p.setBackground(new Color(239,100,63));
                } else {
                    p.setBackground(new Color(239,178,63));
                }
            }
            if(e.getSource()==panel){
                for (JPanel p : panels){
                    p.setBackground(new Color(239,178,63));
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            for(JPanel p : panels){
                if (e.getSource()==p){
                    if(!p.getBackground().equals(new Color(239,100,63)))
                    p.setBackground(new Color(197,149,57));
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            for(JPanel p : panels){
                if (e.getSource()==p){
                    if(!p.getBackground().equals(new Color(239,100,63)))
                    p.setBackground(new Color(239,178,63));
                }
            }
        }
    }
    public ArrayList<ItemPanel> getPanels() {
        return panels;
    }

    public void saveState() {
        SaveDownloads saveDownloads = new SaveDownloads();
        for(ItemPanel i : panels){
            if(i instanceof CompletedItemPanel) {
                saveDownloads.saveDownload(i,((CompletedItemPanel) i).createHumanReadableData());
            } else if (i instanceof InProcessItemPanel){
                saveDownloads.saveDownload(i,((InProcessItemPanel) i).createHumanReadableData());
            } else if (i instanceof QueueItem){
                saveDownloads.saveQueue((QueueItem) i);
            }
        }
    }
}
