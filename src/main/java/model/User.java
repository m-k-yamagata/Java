package model;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String pass;
	private String name;
	
	//コンストラクタ
	public User() {}
	public User(String id,String pass) {
		this.id = id;
		this.pass = pass;
	}
	//セッター
	public void setName(String name) {this.name = name;}
	//ゲッター
	public String getId() {return id;}
	public String getPass() {return pass;}
	public String getName() {return name;}
}
