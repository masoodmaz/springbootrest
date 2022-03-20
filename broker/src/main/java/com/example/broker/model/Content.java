package com.example.broker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Content {
    private String totalCount;
    @JsonProperty("task")
    private List<Task> taskList;

    public Content(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Content(String totalCount, List<Task> taskList) {
        this.totalCount = totalCount;
        this.taskList = taskList;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
