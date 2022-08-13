package com.app.lovebandhan.Screen.ChatScreen;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

public class ChatModel {

    private String DocumentID;
    private String text;
    @ServerTimestamp
    private Timestamp time;
    private boolean isCurrent;
    private String imageurl;
    private String docPath = null;
    private String messageID;
    private String DoctoId;
    private String PatientID;


    public ChatModel() {
    }

    public ChatModel(String documentID, String text, Timestamp time, boolean isDoctor, String imageurl, String docPath) {
        DocumentID = documentID;
        this.text = text;
        this.time = time;
        this.isCurrent = isDoctor;
        this.imageurl = imageurl;
        this.docPath = docPath;
    }


    public ChatModel(String text, Timestamp time, boolean isCurrent, String imageurl, String messageid, String PatientID) {
        this.text = text;
        this.time = time;
        this.isCurrent = isCurrent;
        this.imageurl = imageurl;
        this.messageID = messageid;
        this.DoctoId = DoctoId;
        this.PatientID = PatientID;
    }



    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }


    public String getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(String documentID) {
        DocumentID = documentID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }


    public String getDoctoId() {
        return DoctoId;
    }

    public void setDoctoId(String doctoId) {
        DoctoId = doctoId;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

}
