package servlet;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/Chack")
public class ClockChackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//出退勤確認ページへとぶ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User now = (User)session.getAttribute("user");
		if(now == null) {
			request.setAttribute("mess", "不具合が発生したためログイン画面に戻りました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ClockCheck.jsp");
		dispatcher.forward(request, response);
	}
	
	//確認する年月を取得しこちらへ結果を "WEB-INF/jsp/ClockChackResult.jsp" へフォワード
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "WEB-INF/jsp/ClockCheck.jsp";
		String y = request.getParameter("year");
		String m = request.getParameter("month");
		if(y.length()<= 0){
			request.setAttribute("mess", "年が入力されていません。");
		}else if(m.length()<= 0) {
			request.setAttribute("mess", "月が入力されていません。");
		}else {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			ClockDao dao = new ClockDao();
			List<Dete> inList = dao.listUp(user,y, m, "'出勤'");
			List<Dete> outList = dao.listUp(user,y, m,"'退勤'");
			//セッションスコープに保存
			session.setAttribute("inList" , inList);
			session.setAttribute("outList", outList);
			request.setAttribute("year",y);
			request.setAttribute("month",m);
			url = "WEB-INF/jsp/ClockChackResult.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}