package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import dao.ITaskDAO;
import dao.TaskDAOImpl;
import model.Task;
import model.User;

@WebServlet("/excelDownload")
public class DynamicExcelFile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String filePath = "\\resources\\files\\excelTaskLists.xls";
	private static final int BUFFER_SIZE = 4096;

	private void createHeading(HSSFWorkbook workbook , HSSFSheet sheet) {
		// Create Heading
		Row rowHeading = sheet.createRow(0);
		rowHeading.createCell(0).setCellValue("E-Mail");
		rowHeading.createCell(1).setCellValue("Task Date");
		rowHeading.createCell(2).setCellValue("Task Name");
		rowHeading.createCell(3).setCellValue("Task Description");
		for (int i = 0; i < 4; i++) {
			CellStyle stylerRowHeading = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setFontHeightInPoints((short) 11);
			stylerRowHeading.setFont(font);
			stylerRowHeading.setVerticalAlignment(VerticalAlignment.CENTER);
			rowHeading.getCell(i).setCellStyle(stylerRowHeading);
		}
	}
	
	private void dataWrite(HSSFWorkbook workbook , HSSFSheet sheet,List<Task> tasksList) {
		int r = 1;
		for (Task t : tasksList) {
			Row row = sheet.createRow(r);

			Cell cellEmail = row.createCell(0);
			cellEmail.setCellValue(t.getUserEmail());

			Cell cellTaskDate = row.createCell(1);
			CellStyle styleCreationDate = workbook.createCellStyle();
			HSSFDataFormat dfCreationDate = workbook.createDataFormat();
			styleCreationDate.setDataFormat(dfCreationDate.getFormat("yyyy-mm-dd"));
			cellTaskDate.setCellStyle(styleCreationDate);
			cellTaskDate.setCellValue(t.getTaskDate());
			
			Cell cellTaskName = row.createCell(2);
			cellTaskName.setCellValue(t.getTaskName());

			Cell cellDescription = row.createCell(3);
			cellDescription.setCellValue(t.getTaskDescription());
			/*--Para İçin Format Ayarlama
			Cell cellPrice = row.createCell(3);
			cellPrice.setCellValue(...);
			CellStyle stylePrice = workbook.createCellStyle();
			HSSFDataFormat cf = workbook.createDataFormat();
			stylePrice.setDataFormat(cf.getFormat("$#,##0.00));
			cellPrice.setCellStyle(stylePrice);
			*/
			r++;
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasksList = new ArrayList<>();
		ITaskDAO taskDao = new TaskDAOImpl();
		HttpSession session = req.getSession();
		User user =null;
		if(session.getAttribute("userInformation")!=null) {
			user = (User) session.getAttribute("userInformation");
			tasksList = taskDao.getTasks(user.geteMail());
		}
		if(tasksList!=null && tasksList.size()!=0) {
			
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("List Tasks");
		
		createHeading(workbook, sheet);

		dataWrite(workbook,sheet,tasksList);
		
		
		// AutoFit
		for (int i = 0; i < 4; i++) {
			sheet.autoSizeColumn(i);
		}

		String fullPath = getFilePath();
		
		FileOutputStream out = new FileOutputStream(new File(fullPath));
		workbook.write(out);
		out.close();
		workbook.close();

		File downloadFile = new File(fullPath);
		resp = setResponseConfiguration(resp, fullPath, downloadFile);

		FileInputStream inputStream = new FileInputStream(downloadFile);
		OutputStream outStream = resp.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
		}else {
			resp.sendRedirect("taskController");
		}
	}

	private HttpServletResponse setResponseConfiguration(HttpServletResponse resp, String path,File file) {
		// İsteğe dönen response'un ayarları yapılması gerekiyor.
		// Yani ben dosya gönderiyorum diye bildiriyoruz.
		String mimeType = getServletContext().getMimeType(path);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", file.getName());

		int fileLength = (int) file.length();

		resp.setContentType(mimeType);
		resp.setContentLength(fileLength);
		resp.setHeader(headerKey, headerValue);
		return resp;
	}
	
	private String getFilePath() {
		String contextPath = getServletContext().getRealPath("");
		String fullPath = contextPath + filePath;
		return fullPath;
	}
}
