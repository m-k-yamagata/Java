package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class RegistrDao {
	private static final int List = 0;
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/Attendance";
	private final String DB_USER = "sa";
	private final String DB_PASS = "sa";
	private final String DRIVER = "org.h2.Driver";

	//ドライバーへ接続確認
	void tryDriver() {
		//ドライバの確認＿無いとコンソールにエラー文を出力し強制終了
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした。");
		}
	}

	public User Registr(String NAME,String PASS) {
		//チェック
		tryDriver();
		//データベース接続
		try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//ユーザーを格納しているテーブル
			String sql = "SELECT ID FROM EMPLOYEE";
			PreparedStatement pStmt = connection.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			//IDを設定する
			int rowCount = 1;
			while (rs.next()) {
			    rowCount++;
			}
			String nowID = "0000" + String.valueOf(rowCount);
			nowID = nowID.substring(nowID.length() - 4);
			//ユーザークラスに格納
			User user = new User(nowID,PASS);
			user.setName(NAME);
			//データベースに登録
			sql = "INSERT INTO EMPLOYEE(ID,PASS,NAME) VALUES(?,?,?)";
		    pStmt = connection.prepareStatement(sql);
		    pStmt.setString(1, user.getId());
		    pStmt.setString(2, user.getPass());
		    pStmt.setString(3, user.getName());
		    pStmt.executeUpdate();
			return user;
		} catch (SQLException e) {
			//例外発生時はエラー発生の経緯を記載
			System.out.println("Registrで例外が発生しました。");
			e.printStackTrace();
			return null;
		}
	}
}
