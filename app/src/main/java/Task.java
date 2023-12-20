package com.example.todoapp;

import java.util.Date;
import java.util.UUID;

class Task {
    private String id;
    private String text;
    private boolean isCompleted;
    private Date created;

    public Task(String text){
        id = UUID.randomUUID().toString();
        this.text=text;
        created = new Date();
        isCompleted = false;
    }

    public String getId() {
        return id;
    }

    /* public void setId(String id) {
        this.id = id;
    }
     */

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getCreated() {
        return created;
    }

    /* public void setCreated(Date created) {
        this.created = created;
    }
     */

}
