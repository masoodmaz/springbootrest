package com.example.broker.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public class Task {
    //JSON Alias is to map the response attribute coming from the downstream domains to the actual attribute of the class where it needs to be de-serialized.
    @JsonAlias("task_id")
    private String taskid;
    @JsonAlias("task_state")
    private String taskState;
    @JsonAlias("days_remaining")
    private String daysRemaining;
    @JsonAlias("proposal_number")
    private String proposalNumber;
    @JsonAlias("proposal_title")
    private String proposalTitle;
    @JsonAlias("operator_accepts_engineering_reco")
    private String operator_accepts_engineering_reco;
    private String issue_date;
    private String proposal_type;
    private String organization;
    private String kits_ordered_by;
    private String showKitsToBeOrdered="No";
    private String supplier;
    @JsonProperty("document")
    private List<Document> documentList;

    public Task() {
    }

    public Task(List<Document> documentList) {
        this.documentList = documentList;
    }


    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(String daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getProposalTitle() {
        return proposalTitle;
    }

    public void setProposalTitle(String proposalTitle) {
        this.proposalTitle = proposalTitle;
    }

    public String getOperator_accepts_engineering_reco() {
        return operator_accepts_engineering_reco;
    }

    public void setOperator_accepts_engineering_reco(String operator_accepts_engineering_reco) {
        this.operator_accepts_engineering_reco = operator_accepts_engineering_reco;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getProposal_type() {
        return proposal_type;
    }

    public void setProposal_type(String proposal_type) {
        this.proposal_type = proposal_type;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getKits_ordered_by() {
        return kits_ordered_by;
    }

    public void setKits_ordered_by(String kits_ordered_by) {
        this.kits_ordered_by = kits_ordered_by;
    }

    public String getShowKitsToBeOrdered() {
        return showKitsToBeOrdered;
    }

    public void setShowKitsToBeOrdered(String showKitsToBeOrdered) {
        this.showKitsToBeOrdered = showKitsToBeOrdered;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }
}
