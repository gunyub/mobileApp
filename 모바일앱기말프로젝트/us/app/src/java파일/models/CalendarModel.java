package com.example.us.models;

import java.util.List;

public class CalendarModel {
    private String id;
    private String name;
    private String ownerId;
    private List<String> memberIds;

    public CalendarModel() {
        // Firestore에서 객체 생성 시 필요
    }

    public CalendarModel(String id, String name, String ownerId, List<String> memberIds) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.memberIds = memberIds;
    }

    // getter & setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }
}
