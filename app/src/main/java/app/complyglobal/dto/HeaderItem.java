package app.complyglobal.dto;

/**
 * Created by SUDARSHAN REDDY on 29-10-2017.
 */

public class HeaderItem implements ListItem {

    private String headerItem;

    public String getHeaderItem() {
        return headerItem;
    }

    public void setHeaderItem(String headerItem) {
        this.headerItem = headerItem;
    }

    @Override
    public int getType() {
        return 0;
    }
}
