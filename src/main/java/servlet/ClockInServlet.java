package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import dao.ClockDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Dete;
import model.User;


@WebServlet("/ClockIn")
public class ClockInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User now = (User)session.getAttribute("user");
		if(now == null) {
			request.setAttribute("mess", "不具合が発生したためログイン画面に戻りました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		//日付情報を取得しセッションスコープへ
		LocalDateTime d = LocalDateTime.now();
		Dete dete = new Dete(d.getYear(),d.getMonthValue(),d.getDayOfMonth(),d.getHour(),d.getMinute(),"");
		User user = (User)session.getAttribute("user");
		session.removeAttribute("dete");
		session.setAttribute("dete", dete);
		ClockDao dao = new ClockDao();
		Dete deteIn = dao.ClockCheck(user, dete, "出勤");
		Dete deteOut = dao.ClockCheck(user, dete, "退勤");
		if(deteIn != null)session.setAttribute("in", deteIn);
		if(deteOut != null)session.setAttribute("out", deteOut);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ClockIn.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションから情報を取得しデータベースに追加情報が無い場合は戻る
		HttpSession session = request.getSession();
		User now = (User)session.getAttribute("user");
		if(now == null) {
			request.setAttribute("mess", "不具合が発生したためログイン画面に戻りました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		User user = (User)session.getAttribute("user");
		Dete dete = (Dete)session.getAttribute("dete");
		String inout = request.getParameter("inout");
		ClockDao dao = new ClockDao();
		boolean check = dao.ClockSet(user,dete, inout);
		Dete deteIn = dao.ClockCheck(user, dete, "出勤");
		Dete deteOut = dao.ClockCheck(user, dete, "退勤");
		if(deteIn != null)session.setAttribute("in", deteIn);
		if(deteOut != null)session.setAttribute("out", deteOut);
		String url = "index.jsp";
		//フォワード
		if(check) {
			url = "WEB-INF/jsp/ClockIn.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
