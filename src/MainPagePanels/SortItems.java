package MainPagePanels;

import java.util.ArrayList;
import java.util.Collections;

public class SortItems {

    /**
     * Static methods which contains sort algorithms.
     */

    public static void sort(ArrayList<ItemPanel> items, DownloadsContainerPanel downloadsContainerPanel,String sortBy) {
        for(int i=0;i<items.size();i++) {
            for(int j=0;j<items.size();j++) {
                switch (sortBy) {
                    case "Name":
                        if (items.get(i).getName_().toLowerCase().compareTo(items.get(j).getName_().toLowerCase()) < 0) {
                            Collections.swap(items, i, j);
                        }
                        break;
                    case "Size":
                        if (items.get(i).getSize_().toLowerCase().compareTo(items.get(j).getSize_().toLowerCase()) < 0) {
                            Collections.swap(items, i, j);
                        }
                        break;
                    case "Date":
                        if (items.get(i).getDate().toLowerCase().compareTo(items.get(j).getDate().toLowerCase()) > 0) {
                            Collections.swap(items, i, j);
                        }
                        break;
                }
            }
        }
        ArrayList<ItemPanel> tmp = new ArrayList<>(items);
        downloadsContainerPanel.eraseAllDownloads();
        for(ItemPanel i : tmp) {
            downloadsContainerPanel.insertDownload(i);
        }
        MainFrame.frame.validate();
    }

    public static void reverseSort(ArrayList<ItemPanel> items, DownloadsContainerPanel downloadsContainerPanel) {
        int i=0,j=items.size()-1;
        while (i<j) {
            Collections.swap(items,i,j);
            i++;
            j--;
        }
        ArrayList<ItemPanel> tmp = new ArrayList<>(items);
        downloadsContainerPanel.eraseAllDownloads();
        for(ItemPanel itemPanel : tmp) {
            downloadsContainerPanel.insertDownload(itemPanel);
        }
        MainFrame.frame.validate();
    }

}
