package com.salvatorealbano.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name ="tasks")
public class Task extends PanacheEntity {
	@Column(name = "text")
	private String text;

    @Column(name = "day")
    private String day;
    
    @Column(name = "reminder")
    private boolean reminder;
    
    public Task(String text, String day, boolean reminder) {
    	this.text = text;
    	this.day = day;
    	this.reminder = reminder;
    }
    
    public Task() {}
    
    public String getText() { return this.text;}
    public String getDay() { return this.day;}
    public boolean getReminder() { return this.reminder;}
    
    public void setText(String text) { this.text = text;}
    public void setDay(String day) { this.day = day;}
    public void setReminder(boolean reminder) { this.reminder = reminder;}

}
