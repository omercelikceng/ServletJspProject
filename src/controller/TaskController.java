package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.ITaskDAO;
import dao.TaskDAOImpl;
import model.Result;
import model.Task;
import model.User;

@WebServlet(urlPatterns="/taskController",name="taskControllerName")
public class TaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ITaskDAO taskDao = new TaskDAOImpl();
		if(req.getParameter("deleteOperation")!=null) {
			if(req.getParameter("taskId")!=null) {
				taskDao.removeTask(Integer.parseInt(req.getParameter("taskId")));
			}
		}
		else if(req.getParameter("editOperation")!=null) {
			if(req.getParameter("taskId")!=null) {
				//Ilerde eklemeler yapılabilir.
			}
		}
		User user=null;
		List<Task> taskList=null;
		if(req.getSession().getAttribute("userInformation")!=null) {
			 user = (User)req.getSession().getAttribute("userInformation");
			 taskList = taskDao.getTasks(user.geteMail());
		}
		req.setAttribute("taskLists", taskList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("view/UserOperation.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		resp.setContentType("application/json; charset=UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
		StringBuffer jsonData = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			jsonData.append(line);
		}
		Gson gson = new Gson();
		User user =null;
		List<Task> taskList = null;
		ITaskDAO taskDao = new TaskDAOImpl();
		if(req.getSession().getAttribute("userInformation")!=null) {
			user = (User)req.getSession().getAttribute("userInformation");
		}
		
		// Form'da boş bir eleman gelirse json datasından "" bu şekilde bir veri varsa
		// null gelmiş diyerek kontrol yapıyoruz.
		if(!jsonData.toString().contains("\"\"")) {
			StringDecodeAndEncode encodeAndDecode = new StringDecodeAndEncode();
			String encodeString = encodeAndDecode.encodeOperation(jsonData.toString());
			Task task = gson.fromJson(encodeString, Task.class);		
			task.setUserEmail(user.geteMail());
			
			taskDao.inserTask(task);
		}
		taskList = taskDao.getTasks(user.geteMail());
		req.setAttribute("taskLists", taskList);
		
		Result result = new Result(200, "Başarılı"); 
		String jsonResult = gson.toJson(result);
		PrintWriter pw = resp.getWriter();
		pw.write(jsonResult);
		pw.close();
		
	}
}
