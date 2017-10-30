package app.complyglobal.dto;

import app.complyglobal.dummy.DummyContent;

/**
 * Created by SUDARSHAN REDDY on 29-10-2017.
 */

public class EventItem implements ListItem {

    ComplianceDTO eventItem;

    public ComplianceDTO getEventItem() {
        return eventItem;
    }

    public void setEventItem(ComplianceDTO eventItem) {
        this.eventItem = eventItem;
    }

    @Override
    public int getType() {
        return 1;
    }
}
