package servlet;

import java.io.IOException;

import dao.LoginDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ログアウトや不具合発生時に戻ります。
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User now = (User)session.getAttribute("user");
		if(now == null) {
			request.setAttribute("mess", "不具合が発生したためログイン画面に戻りました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//セッションスコープの破棄
		session.invalidate();
		
		//ログイン画面に戻る
		String url = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	//ログイン画面からログインを押すとこちらにとぶ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		//入力情報の取得
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		//インスタンス生成
		User user = new User(id,pass);
		LoginDao dao = new LoginDao();
		//ログインのチェック
		boolean chack = dao.LoinChack(user);
		//フォワードするようのパスを設定
		if(chack) {
			url = "WEB-INF/jsp/Home.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("mess", user.getName() + "さんがログインしました。");
		}else {
			request.setAttribute("mess", "ログインに失敗しました。");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
