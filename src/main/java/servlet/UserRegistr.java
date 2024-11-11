package servlet;

import java.io.IOException;

import dao.RegistrDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/Registr")
public class UserRegistr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ゲット
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String URL = "WEB-INF/jsp/UserRegistr.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(URL);
		dispatcher.forward(request, response);
	}

	//ポスト
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//入力された内容を確認
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String master = request.getParameter("Master");
		String url = "WEB-INF/jsp/RegistrResult.jsp";
		String massage = "";
		//データベースと連携、登録を行う
		System.out.println(master);
		if (master.equals("1234")) {
			if (name.length() > 0 && pass.length() >= 4) {
				RegistrDao dao = new RegistrDao();
				User user = dao.Registr(name, pass);
				if (user != null) {
					request.setAttribute("user", user);
					massage = "ユーザー登録が完了しました。\nIDとパスワードは忘れないようにして下さい";
				}
			} else {
				massage = "氏名、パスワードが未入力もしくは短い可能性があります";
			}
		} else {
			massage = "登録者パスワードが違います";
		}
		request.setAttribute("mas", massage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
