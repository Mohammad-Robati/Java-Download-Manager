package MainPagePanels;

import javax.swing.*;

public class LookAndFeelChanges {

    private String lookAndFeel = "Metal";

    public void update(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(MainFrame.frame);
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public void setLookAndFeel(String lookAndFeel) {
        this.lookAndFeel = lookAndFeel;
    }
}

