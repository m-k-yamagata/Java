package model;

import java.io.Serializable;

public class Dete implements Serializable {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String in_out;
	//コンストラクタ
	public Dete() {}
	public Dete(int year,int month,int day,int hour,int minute,String in_out) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.in_out = in_out;
	}
	//ゲッター
	public int getYear() {return year;}
	public int getMonth() {return month;}
	public int getDay() {return day;}
	public int getHour() {return hour;}
	public int getMinute() {return minute;}
}
