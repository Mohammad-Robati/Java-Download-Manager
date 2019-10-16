package MainPagePanels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ToolbarPanel extends JPanel{

    /**
     * this class will create a panel
     * for toolbar which will be put in the
     * top side of panel and handle its
     * listeners and ...
     * note that in this program we have made a toolbar
     * and didn't use java Toolbar
     */

    private JLabel newDownload;
    private JLabel start;
    private JLabel pause;
    private JLabel cancel;
    private JLabel remove;
    private JLabel settings;
    private JComboBox<String> sort;
    private JTextField search;
    private JButton reverseSort;
    SettingsPanel settingsPanel;

    static JFrame frameForSettingsPanel;

    /**
     * Constructor for making instances for fields
     * and run GUI
     */

    ToolbarPanel() {
        // making the instances
        newDownload = new JLabel(Icons.NEWDOWNLOAD_TOOLBAR);
        newDownload.setToolTipText("New Download");
        start = new JLabel(Icons.START_TOOLBAR);
        start.setToolTipText("Start");
        pause = new JLabel(Icons.PAUSE_TOOLBAR);
        pause.setToolTipText("Pause");
        cancel = new JLabel(Icons.CANCEL_TOOLBAR);
        cancel.setToolTipText("Cancel");
        remove = new JLabel(Icons.REMOVE_TOOLBAR);
        remove.setToolTipText("Remove");
        settings = new JLabel(Icons.SETTINGS_TOOLBAR);
        settings.setToolTipText("Settings");
        sort = new JComboBox<>();
        sort.setBackground(Color.black);
        sort.setForeground(new Color(243,233,167));
        sort.addItem("By Date");
        sort.addItem("By Name");
        sort.addItem("By Size");
        search = new JTextField();
        search.setBackground(new Color(243,233,167));
        reverseSort = new JButton();
        reverseSort.setToolTipText("Increasing/Decreasing");
        reverseSort.setIcon(Icons.SORT);
        reverseSort.setBackground(new Color(243,233,167));

        sort.addItemListener(e -> {
            if (e.getItem().equals("By Name")) {
                if(MainPanel.tabsPanel.getCompletedPanel().isEnabled()) {
                    SortItems.sort(MainPanel.completedPanel.panels, MainPanel.completedPanel,"Name");
                } else if(MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    SortItems.sort(MainPanel.processingPanel.panels, MainPanel.processingPanel,"Name");
                } else if(MainPanel.tabsPanel.getQueuesPanel().isEnabled()) {
                    SortItems.sort(MainPanel.queuesPanel.panels, MainPanel.queuesPanel,"Name");
                }
            } else if (e.getItem().equals("By Size")) {
                if(MainPanel.tabsPanel.getCompletedPanel().isEnabled()) {
                    SortItems.sort(MainPanel.completedPanel.panels, MainPanel.completedPanel,"Size");
                } else if(MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    SortItems.sort(MainPanel.processingPanel.panels, MainPanel.processingPanel,"Size");
                }
            } else if (e.getItem().equals("By Date")) {
                if(MainPanel.tabsPanel.getCompletedPanel().isEnabled()) {
                    SortItems.sort(MainPanel.completedPanel.panels, MainPanel.completedPanel,"Date");
                } else if(MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    SortItems.sort(MainPanel.processingPanel.panels, MainPanel.processingPanel,"Date");
                }
            }
        });

        search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { show(); }
            @Override
            public void removeUpdate(DocumentEvent e) { show(); }
            @Override
            public void changedUpdate(DocumentEvent e) { show(); }

            private void show(){
                if(MainPanel.tabsPanel.getCompletedPanel().isEnabled()) {
                    for (ItemPanel i : MainPanel.completedPanel.panels) {
                        i.setVisible(false);
                    }
                    for (ItemPanel i : MainPanel.completedPanel.panels) {
                        if (i.getName_().toLowerCase().contains(search.getText().toLowerCase()) || i.getURL().contains(search.getText().toLowerCase())) {
                            i.setVisible(true);
                        }
                    }
                } else if (MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    for (ItemPanel i : MainPanel.processingPanel.panels) {
                        i.setVisible(false);
                    }
                    for (ItemPanel i : MainPanel.processingPanel.panels) {
                        if (i.getName_().toLowerCase().contains(search.getText().toLowerCase()) || i.getURL().contains(search.getText().toLowerCase())) {
                            i.setVisible(true);
                        }
                    }
                } else if (MainPanel.tabsPanel.getQueuesPanel().isEnabled()) {
                    for (ItemPanel i : MainPanel.queuesPanel.panels) {
                        i.setVisible(false);
                    }
                    for (ItemPanel i : MainPanel.queuesPanel.panels) {
                        if (i.getName_().toLowerCase().contains(search.getText().toLowerCase())) {
                            i.setVisible(true);
                        }
                    }
                }
            }
        });

        MouseListener mouseListener = new MouseListener();
        newDownload.addMouseListener(mouseListener);
        start.addMouseListener(mouseListener);
        pause.addMouseListener(mouseListener);
        cancel.addMouseListener(mouseListener);
        remove.addMouseListener(mouseListener);
        settings.addMouseListener(mouseListener);
        reverseSort.addMouseListener(mouseListener);

        settingsPanel = new SettingsPanel();

        frameForSettingsPanel = new JFrame();
        frameForSettingsPanel.add(settingsPanel);
        frameForSettingsPanel.pack();
        frameForSettingsPanel.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Icons/icon.png"));
        setBackground(new Color(255,214,23));

        // now for the main panel
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newDownload,-2,60,-2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(start,-2,60,-2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pause,-2,60,-2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel,-2,60,-2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remove,-2,60,-2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(settings,-2,60,-2)
                .addGap(20,40, Short.MAX_VALUE)
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(search,-2,227,-2)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                .addComponent(reverseSort,-2,25,-2)
                                                        .addGap(2)
                                                        .addComponent(sort,-2,200,-2)
                                        )

                        )
                .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(newDownload,-2,60,-2)
                        .addComponent(start,-2,60,-2)
                        .addComponent(pause,-2,60,-2)
                        .addComponent(cancel,-2,60,-2)
                        .addComponent(remove,-2,60,-2)
                        .addComponent(settings,-2,60,-2)
                                .addGroup(
                                        layout.createSequentialGroup()
                                        .addComponent(search)
                                                .addGap(5)
                                                .addGroup(
                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(reverseSort,-2,25,-2)
                                                                .addComponent(sort,-2,25,-2)
                                                )
                                )
                )
                .addContainerGap(Short.MAX_VALUE,Short.MAX_VALUE)
        );
    }

    /**
     * private class for handling mouse actions...
     */

    private class MouseListener implements java.awt.event.MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            InsertANewDownloadPanel insertANewDownloadPanel = new InsertANewDownloadPanel();
            if(e.getSource()==newDownload) {
                if (MainPanel.tabsPanel.getCompletedPanel().isEnabled() || MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    insertANewDownloadPanel.setVisible(true);
                } else if (MainPanel.tabsPanel.getQueuesPanel().isEnabled()) {
                    MainPanel.tabsPanel.getQueueSettingsPanel().setLocationRelativeTo(MainFrame.frame);
                    MainPanel.tabsPanel.getQueueSettingsPanel().setVisible(true);
                    System.out.print("");
                }
            } else if(e.getSource()==remove) {
                if (MainPanel.tabsPanel.getCompletedPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.completedPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                MainPanel.completedPanel.getPanels().remove(p);
                                (new SaveDownloads()).saveRemovedDownload(p);
                                p.setVisible(false);
                                System.out.println();
                            }
                        }
                    } catch (Exception ignored){

                    }
                } else if (MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.processingPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                MainPanel.processingPanel.getPanels().remove(p);
                                    (new SaveDownloads()).saveRemovedDownload(p);
                                System.out.print("");
                                p.setVisible(false);
                            }
                        }
                    } catch (Exception ignored) {

                    }
                } else if (MainPanel.tabsPanel.getQueuesPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.queuesPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                MainPanel.queuesPanel.getPanels().remove(p);
                                p.setVisible(false);
                            }
                        }
                    } catch (Exception ignored) {

                    }
                }

            } else if(e.getSource()==settings) {
                frameForSettingsPanel.setLocationRelativeTo(MainFrame.frame);
                frameForSettingsPanel.setVisible(true);
            } else if(e.getSource()==reverseSort){
                if(MainPanel.tabsPanel.getCompletedPanel().isEnabled()){
                    SortItems.reverseSort(MainPanel.completedPanel.panels,MainPanel.completedPanel);
                } else if (MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    SortItems.reverseSort(MainPanel.processingPanel.panels,MainPanel.processingPanel);
                } else if (MainPanel.tabsPanel.getQueuesPanel().isEnabled()) {
                    SortItems.reverseSort(MainPanel.queuesPanel.panels,MainPanel.queuesPanel);
                }
            } else if(e.getSource()==pause){
                if (MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.processingPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                if(((InProcessItemPanel) p).download.getStatus()!=3)
                                ((InProcessItemPanel) p).download.pause();
                            }
                        }
                    } catch (Exception ignored) {

                    }
                }
            } else if(e.getSource()==start) {
                if (MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.processingPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                if(((InProcessItemPanel) p).download.getStatus()!=3) {
                                    ((InProcessItemPanel) p).download.resume();
                                } else {
                                    p.setVisible(false);
                                    MainPanel.processingPanel.insertDownload(new InProcessItemPanel(
                                            new Download(new URL(((InProcessItemPanel) p).download.getUrl()))));
                                    MainPanel.processingPanel.panels.remove(p);
                                }

                            }
                        }
                    } catch (Exception ignored) {

                    }
                }
            } else if(e.getSource()==cancel) {
                if (MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.processingPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                ((InProcessItemPanel) p).download.cancel();
                            }
                        }
                    } catch (Exception ignored) {

                    }
                }
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == newDownload) {
                newDownload.setIcon(Icons.NEWDOWNLOAD_TOOLBAR_CLICKED);
                newDownload.setBorder(BorderFactory.createLineBorder(new Color(152,46,0)));
            } else if(e.getSource()==start) {
                start.setIcon(Icons.START_TOOLBAR_CLICKED);
                start.setBorder(BorderFactory.createLineBorder(new Color(152,46,0)));
            } else if(e.getSource()==pause) {
                pause.setIcon(Icons.PAUSE_TOOLBAR_CLICKED);
                pause.setBorder(BorderFactory.createLineBorder(new Color(152,46,0)));
            } else if(e.getSource()==cancel) {
                cancel.setIcon(Icons.CANCEL_TOOLBAR_CLICKED);
                cancel.setBorder(BorderFactory.createLineBorder(new Color(152,46,0)));
            } else if(e.getSource()==remove) {
                remove.setIcon(Icons.REMOVE_TOOLBAR_CLICKED);
                remove.setBorder(BorderFactory.createLineBorder(new Color(152,46,0)));
            } else if(e.getSource()==settings) {
                settings.setIcon(Icons.SETTINGS_TOOLBAR_CLICKED);
                settings.setBorder(BorderFactory.createLineBorder(new Color(152,46,0)));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == newDownload) {
                newDownload.setIcon(Icons.NEWDOWNLOAD_TOOLBAR);
                newDownload.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if(e.getSource()==start) {
                start.setIcon(Icons.START_TOOLBAR);
                start.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if(e.getSource()==pause) {
                pause.setIcon(Icons.PAUSE_TOOLBAR);
                pause.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if(e.getSource()==cancel) {
                cancel.setIcon(Icons.CANCEL_TOOLBAR);
                cancel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if(e.getSource()==remove) {
                remove.setIcon(Icons.REMOVE_TOOLBAR);
                remove.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if(e.getSource()==settings) {
                settings.setIcon(Icons.SETTINGS_TOOLBAR);
                settings.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==newDownload){
                newDownload.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if (e.getSource()==start) {
                start.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if (e.getSource()==pause) {
                pause.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if (e.getSource()==cancel) {
                cancel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if (e.getSource()==remove) {
                remove.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            } else if (e.getSource()==settings) {
                settings.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==newDownload){
                newDownload.setBorder(null);
            } else if (e.getSource()==start) {
                start.setBorder(null);
            } else if (e.getSource()==pause) {
                pause.setBorder(null);
            } else if (e.getSource()==cancel) {
                cancel.setBorder(null);
            } else if (e.getSource()==remove) {
                remove.setBorder(null);
            } else if (e.getSource()==settings) {
                settings.setBorder(null);
            }
        }
    }
    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public JComboBox<String> getSort() {
        return sort;
    }

}
