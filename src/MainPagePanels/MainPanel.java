package MainPagePanels;
import javax.swing.*;

public class MainPanel extends JPanel {

    /**
     * This class holds creates the main panel
     * which main frame will hold it
     * note that tab panel,toolbar panel, and download container panel are static
     * fields in this class to provide access all over classes...
     */

    public static DownloadsContainerPanel processingPanel = new DownloadsContainerPanel();
    public static DownloadsContainerPanel completedPanel = new DownloadsContainerPanel();
    public static DownloadsContainerPanel queuesPanel = new DownloadsContainerPanel();
    static TabsPanel tabsPanel = new TabsPanel();
    static ToolbarPanel toolbarPanel = new ToolbarPanel();

    MainPanel() {

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        processingPanel.setVisible(false);
        queuesPanel.setVisible(false);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(tabsPanel,-2,-1,-2)
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolbarPanel,-1,-1,Short.MAX_VALUE)
                        .addComponent(processingPanel, GroupLayout.Alignment.TRAILING,-1,-1,Short.MAX_VALUE)
                        .addComponent(completedPanel,GroupLayout.Alignment.TRAILING,-1,-1,Short.MAX_VALUE)
                        .addComponent(queuesPanel,GroupLayout.Alignment.TRAILING,-1,-1,Short.MAX_VALUE)
                )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(tabsPanel,-1,-1,Short.MAX_VALUE)
                .addGroup(
                        layout.createSequentialGroup()
                        .addComponent(toolbarPanel,-2,85,-2)
                        .addComponent(processingPanel,-1,220,Short.MAX_VALUE)
                        .addComponent(completedPanel,-1,220,Short.MAX_VALUE)
                        .addComponent(queuesPanel,-1,-1,Short.MAX_VALUE)
                )
        );

    }
}
