package MainPagePanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

class MenuBarForFrame extends JMenuBar {

    private JMenuItem newDownload = new JMenuItem("New Download");
    private JMenuItem start = new JMenuItem("Start");
    private JMenuItem pause = new JMenuItem("Pause");
    private JMenuItem cancel = new JMenuItem("Cancel");
    private JMenuItem remove = new JMenuItem("Remove");
    private JMenuItem settings = new JMenuItem("Settings");
    private JMenuItem export = new JMenuItem("Export");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem details = new JMenuItem("Details");
    private JRadioButtonMenuItem english = new JRadioButtonMenuItem("English");
    private JRadioButtonMenuItem farsi = new JRadioButtonMenuItem("Farsi");
    private JFrame detailsFrame = new JFrame("Details");
    private JMenu language = new JMenu("Language");
    private JMenu help = new JMenu("Help");
    JMenu download = new JMenu("Download");

    MenuBarForFrame() throws IOException {

        new Language();

        download.setMnemonic(KeyEvent.VK_D);

        help.setMnemonic(KeyEvent.VK_H);

        download.add(newDownload);
        newDownload.setMnemonic(KeyEvent.VK_N);
        newDownload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));

        download.add(start);
        start.setMnemonic(KeyEvent.VK_S);
        start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));

        download.add(pause);
        pause.setMnemonic(KeyEvent.VK_P);
        pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));

        download.add(cancel);
        cancel.setMnemonic(KeyEvent.VK_C);
        cancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));

        download.add(remove);
        remove.setMnemonic(KeyEvent.VK_R);
        remove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));

        download.add(settings);
        settings.setMnemonic(KeyEvent.VK_T);
        settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));

        download.add(export);
        export.setMnemonic(KeyEvent.VK_X);
        export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.ALT_MASK));

        download.add(exit);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));

        help.add(details);
        details.setMnemonic(KeyEvent.VK_D);
        details.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));

        language.add(english);
        language.add(farsi);

        help.add(language);
        language.setMnemonic(KeyEvent.VK_L);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(english);
        buttonGroup.add(farsi);
        english.setSelected(true);

        ItemListenerForMenuBar itemListenerForMenuBar = new ItemListenerForMenuBar();
        newDownload.addActionListener(itemListenerForMenuBar);
        start.addActionListener(itemListenerForMenuBar);
        pause.addActionListener(itemListenerForMenuBar);
        cancel.addActionListener(itemListenerForMenuBar);
        remove.addActionListener(itemListenerForMenuBar);
        settings.addActionListener(itemListenerForMenuBar);
        export.addActionListener(itemListenerForMenuBar);
        exit.addActionListener(itemListenerForMenuBar);
        details.addActionListener(itemListenerForMenuBar);

        LanguageListener languageListener = new LanguageListener();
        english.addActionListener(languageListener);
        farsi.addActionListener(languageListener);

        makeDetailsFrame();

        add(download);
        add(help);
        setBackground(Color.BLACK);
        download.setForeground(Color.gray);
        help.setForeground(Color.gray);

    }

    private void makeDetailsFrame() {
        JPanel panel = new JPanel();
        JLabel logo = new JLabel();
        logo.setIcon(Icons.LOGO);
        JLabel txt1 = new JLabel("Mohammad Robati Shirzad ");
        JLabel txt2 = new JLabel("9631028");
        JLabel txt3 = new JLabel("Java Download Manager V.01");
        JLabel txt4 = new JLabel("From 2 May, 2018");
        panel.setLayout(new FlowLayout());
        panel.add(logo);
        panel.add(txt1);
        panel.add(txt2);
        panel.add(txt3);
        panel.add(txt4);
        panel.setBackground(new Color(243,233,167));
        detailsFrame.add(panel);
        detailsFrame.setSize(200, 310);
        detailsFrame.setResizable(false);
        detailsFrame.setLocationRelativeTo(MainFrame.frame);
        detailsFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Icons/icon.png"));
    }

    private class ItemListenerForMenuBar implements ActionListener {

        InsertANewDownloadPanel insertANewDownloadPanel = new InsertANewDownloadPanel();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newDownload) {
                if (MainPanel.tabsPanel.getCompletedPanel().isEnabled() || MainPanel.tabsPanel.getProcessingPanel().isEnabled()) {
                    insertANewDownloadPanel.setVisible(true);
                } else if (MainPanel.tabsPanel.getQueuesPanel().isEnabled()) {
                    MainPanel.tabsPanel.getQueueSettingsPanel().setLocationRelativeTo(MainFrame.frame);
                    MainPanel.tabsPanel.getQueueSettingsPanel().setVisible(true);
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
                        System.out.println("");
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
                                    System.out.println("");
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
                        System.out.println("");
                    }
                }
            } else if (e.getSource() == remove) {
                if (MainPanel.tabsPanel.getCompletedPanel().isEnabled()) {
                    try {
                        for (ItemPanel p : MainPanel.completedPanel.getPanels()) {
                            if (p.getBackground().equals(new Color(239, 100, 63))) {
                                MainPanel.completedPanel.getPanels().remove(p);
                                (new SaveDownloads()).saveRemovedDownload(p);
                                p.setVisible(false);
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
                                System.out.println();
                            }
                        }
                    } catch (Exception ignored) {

                    }
                }
                } else if (e.getSource() == settings) {
                    ToolbarPanel.frameForSettingsPanel.setVisible(true);
                } else if (e.getSource() == export) {
                    String[] jdmFiles = new String[6];
                    jdmFiles[0] = "src/Cache/settings.jdm";
                    jdmFiles[1] = "src/Cache/removed.jdm";
                    jdmFiles[2] = "src/Cache/queueList.jdm";
                    jdmFiles[3] = "src/Cache/processingList.jdm";
                    jdmFiles[4] = "src/Cache/filter.jdm";
                    jdmFiles[5] = "src/Cache/completedList.jdm";
                    saveEverything();
                    Zip.generateZip("src/Cache/archive.zip",jdmFiles);
                    try {
                        (new SaveDownloads()).clearAllFiles();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

            } else if (e.getSource() == exit) {
                    saveEverything();
                System.exit(0);
                } else if (e.getSource() == details) {
                    detailsFrame.setVisible(true);
                }
                MainFrame.frame.validate();
            }

            private void saveEverything(){
                MainPanel.processingPanel.saveState();
                MainPanel.completedPanel.saveState();
                MainPanel.queuesPanel.saveState();
                for(int i=0;i<=MainPanel.toolbarPanel.settingsPanel.getRestrictedURLs().getModel().size()-1;i++) {
                    (new SaveDownloads()).saveRestrictedURL
                            (MainPanel.toolbarPanel.settingsPanel.getRestrictedURLs().getModel().get(i));
                }
                (new SaveDownloads()).saveSettings();
            }
        }
    public JMenuItem getRemove() {
        return remove;
    }

    private class LanguageListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==english) {
                if(english.isSelected()) {
                    download.setText(Language.ENGLISH.DOWNLOAD.toString());
                    newDownload.setText(Language.ENGLISH.NEW_DOWNLOAD.toString());
                    start.setText(Language.ENGLISH.START.toString());
                    pause.setText(Language.ENGLISH.PAUSE.toString());
                    cancel.setText(Language.ENGLISH.CANCEL.toString());
                    remove.setText(Language.ENGLISH.REMOVE.toString());
                    settings.setText(Language.ENGLISH.SETTINGS.toString());
                    export.setText(Language.ENGLISH.EXPORT.toString());
                    exit.setText(Language.ENGLISH.EXIT.toString());
                    help.setText(Language.ENGLISH.HELP.toString());
                    language.setText(Language.ENGLISH.LANGUAGE.toString());
                    english.setText(Language.ENGLISH.ENGLISH.toString());
                    farsi.setText(Language.ENGLISH.FARSI.toString());
                    details.setText(Language.ENGLISH.DETAILS.toString());
                    TabsPanel.updateLanguage_EN.doClick();
                }
            } else if (e.getSource()==farsi) {
                if(farsi.isSelected()) {
                    download.setText(Language.FARSI.DOWNLOAD.toString());
                    newDownload.setText(Language.FARSI.NEW_DOWNLOAD.toString());
                    start.setText(Language.FARSI.START.toString());
                    pause.setText(Language.FARSI.PAUSE.toString());
                    cancel.setText(Language.FARSI.CANCEL.toString());
                    remove.setText(Language.FARSI.REMOVE.toString());
                    settings.setText(Language.FARSI.SETTINGS.toString());
                    export.setText(Language.FARSI.EXPORT.toString());
                    exit.setText(Language.FARSI.EXIT.toString());
                    help.setText(Language.FARSI.HELP.toString());
                    language.setText(Language.FARSI.LANGUAGE.toString());
                    english.setText(Language.FARSI.ENGLISH.toString());
                    farsi.setText(Language.FARSI.FARSI.toString());
                    details.setText(Language.FARSI.DETAILS.toString());
                    TabsPanel.updateLanguage_FA.doClick();
                }
            }
        }
    }
}
