package app.complyglobal.dto;

import java.io.Serializable;

/**
 * Created by SUDARSHAN REDDY on 01-10-2017.
 */
public class NotificationDTO implements Serializable {

    private String bigContentTitle;
    private String summaryText;
    private String notificationContent;

    public String getBigContentTitle() {
        return bigContentTitle;
    }

    public void setBigContentTitle(String bigContentTitle) {
        this.bigContentTitle = bigContentTitle;
    }

    public String getSummaryText() {
        return summaryText;
    }

    public void setSummaryText(String summaryText) {
        this.summaryText = summaryText;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }
}
