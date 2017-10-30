package app.complyglobal.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by SUDARSHAN REDDY on 29-10-2017.
 */

public class ComplianceDTO implements Serializable {

    private int jectdId;
    private String complianceTaskname;
    private String complianceTypeName;
    private String complianceCaetgoryName;
    private String complianceSubCategoryName;
    private String status;
    private boolean isOverDue;
    private Timestamp dueDate;
    private String dueDateString;
    private String StateName;
    private String entityName;
    private boolean isWorkFlow;
    private String helpText;
    private String information;
    private String Severity;


    public String getComplianceSubCategoryName() {
        return complianceSubCategoryName;
    }

    public void setComplianceSubCategoryName(String complianceSubCategoryName) {
        this.complianceSubCategoryName = complianceSubCategoryName;
    }

    public int getJectdId() {
        return jectdId;
    }

    public void setJectdId(int jectdId) {
        this.jectdId = jectdId;
    }

    public String getComplianceTaskname() {
        return complianceTaskname;
    }

    public void setComplianceTaskname(String complianceTaskname) {
        this.complianceTaskname = complianceTaskname;
    }

    public String getComplianceTypeName() {
        return complianceTypeName;
    }

    public void setComplianceTypeName(String complianceTypeName) {
        this.complianceTypeName = complianceTypeName;
    }

    public String getComplianceCaetgoryName() {
        return complianceCaetgoryName;
    }

    public void setComplianceCaetgoryName(String complianceCaetgoryName) {
        this.complianceCaetgoryName = complianceCaetgoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOverDue() {
        return isOverDue;
    }

    public void setOverDue(boolean overDue) {
        isOverDue = overDue;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDateString() {
        return dueDateString;
    }

    public void setDueDateString(String dueDateString) {
        this.dueDateString = dueDateString;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isWorkFlow() {
        return isWorkFlow;
    }

    public void setWorkFlow(boolean workFlow) {
        isWorkFlow = workFlow;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSeverity() {
        return Severity;
    }

    public void setSeverity(String severity) {
        Severity = severity;
    }
}
