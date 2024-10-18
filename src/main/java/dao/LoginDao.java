package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
public class LoginDao {
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

	public boolean LoinChack(User user) {
		//チェック
		tryDriver();
		//データベース接続
		try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//ユーザーを格納しているテーブル
			String sql = "SELECT * FROM EMPLOYEE";
			PreparedStatement pStmt = connection.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			//IDとパスワードが登録されているか？
			while (rs.next()) {
				String id = rs.getString("ID");
				String pass = rs.getString("PASS");
				if(id.equals(user.getId())&&pass.equals(user.getPass())) {
					user.setName(rs.getString("NAME"));
					return true;
				}
			}

		} catch (SQLException e) {
			//例外発生時はエラー発生の経緯を記載
			System.out.println("LoginDaoで例外が発生しました。");
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
