package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dete;
import model.User;

public class ClockDao {
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

	public boolean ClockSet(User user, Dete dete, String inout) {
		//チェック
		tryDriver();

		//データベース接続
		try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//ユーザーを格納しているテーブル
			String sql = "INSERT INTO CLOCK(ID ,NAME ,YEAR_ ,MONTH_ ,DAY_ ,HOUR_ ,MINUTE_ ,IN_OUT) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, user.getId());
			pStmt.setString(2, user.getName());
			pStmt.setInt(3, dete.getYear());
			pStmt.setInt(4, dete.getMonth());
			pStmt.setInt(5, dete.getDay());
			pStmt.setInt(6, dete.getHour());
			pStmt.setInt(7, dete.getMinute());
			pStmt.setString(8, inout);
			int result = pStmt.executeUpdate();
			if (result != 1) {
				System.out.println("ClockDaoがうまくいっていない");
				return false;
			}

		} catch (SQLException e) {
			//例外発生時はエラー発生の経緯を記載
			System.out.println("ClockSetで例外が発生しました。");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Dete ClockCheck(User user, Dete dete, String inout) {
		//チェック
		tryDriver();

		//データベース接続
		try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//ユーザーを格納しているテーブル
			String sql = "SELECT * FROM CLOCK WHERE ID = "+user.getId()+" AND YEAR_ = " + dete.getYear() + " AND MONTH_  = " + dete.getMonth()
					+ " AND DAY_  = " + dete.getDay() + ";";
			PreparedStatement pStmt = connection.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			if (rs.equals(null)) {
				System.out.println("データがないからnullを返す");
				return null;
			}
			while (rs.next()) {
				int yy = rs.getInt("YEAR_");
				int mm = rs.getInt("MONTH_");
				int dd = rs.getInt("DAY_");
				int hh = rs.getInt("HOUR_");
				int mn = rs.getInt("MINUTE_");
				String io = rs.getString("IN_OUT");
				if (io.equals(inout)) {
					Dete d = new Dete(yy, mm, dd, hh, mn, "");
					return d;
				}
			}
		} catch (SQLException e) {
			//例外発生時はエラー発生の経緯を記載
			System.out.println("ClockCheckで例外が発生しました。");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<Dete> listUp(User user, String y, String m,String io) {
		//チェック
		tryDriver();
		List<Dete> list = new ArrayList<>();
		//データベース接続
		try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//ユーザーを格納しているテーブル
			String sql = "SELECT * FROM CLOCK WHERE ID = "+user.getId()+" AND YEAR_ = " + y + " AND MONTH_  = " + m + " AND IN_OUT  = " + io + " ORDER BY DAY_";
			PreparedStatement pStmt = connection.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			if (rs.equals(null)) {
				System.out.println("データがないからnullを返す");
				return null;
			}
			while (rs.next()) {
				int yy = rs.getInt("YEAR_");
				int mm = rs.getInt("MONTH_");
				int dd = rs.getInt("DAY_");
				int hh = rs.getInt("HOUR_");
				int mn = rs.getInt("MINUTE_");
				String inout = rs.getString("IN_OUT");
				Dete d = new Dete(yy, mm, dd, hh, mn, inout);
				list.add(d);
			}
		} catch (SQLException e) {
			//例外発生時はエラー発生の経緯を記載
			System.out.println("listUpで例外が発生しました。");
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
