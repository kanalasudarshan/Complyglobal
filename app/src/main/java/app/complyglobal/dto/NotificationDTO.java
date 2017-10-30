package app.complyglobal.dto;

import java.io.Serializable;

/**
 * Created by SUDARSHAN REDDY on 01-10-2017.
 */
public class NotificationDTO implements Serializable {

    private String bigContentTitle;
    private String summaryText;
    private String notificationContent;
    private int notificaitonId;
    private String notificaionName;
    private String scheduleTime;
    private String frequencyType;
    private int isActive;


    public int getNotificaitonId() {
        return notificaitonId;
    }

    public void setNotificaitonId(int notificaitonId) {
        this.notificaitonId = notificaitonId;
    }

    public String getNotificaionName() {
        return notificaionName;
    }

    public void setNotificaionName(String notificaionName) {
        this.notificaionName = notificaionName;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(String frequencyType) {
        this.frequencyType = frequencyType;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

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

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "bigContentTitle='" + bigContentTitle + '\'' +
                ", summaryText='" + summaryText + '\'' +
                ", notificationContent='" + notificationContent + '\'' +
                ", notificaitonId=" + notificaitonId +
                ", notificaionName='" + notificaionName + '\'' +
                ", scheduleTime='" + scheduleTime + '\'' +
                ", frequencyType='" + frequencyType + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
