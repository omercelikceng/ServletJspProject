package captcha;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.servlet.StickyCaptchaServlet;

@WebServlet("/simpleCaptcha.jpg")
public class CaptchaServlet extends StickyCaptchaServlet {
	//Bunu bir img'in src'sine url'i yazıyoruz. Ve istek geldiğinde
	//bir captcha(resim) dönüyor.
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		GradiatedBackgroundProducer bg = new GradiatedBackgroundProducer();
		bg.setFromColor(Color.gray);
		bg.setToColor(Color.white);

		Captcha captcha = new Captcha.Builder(250, 40).addText().addNoise().addBackground(bg).addBorder().build();
		session.setAttribute(Captcha.NAME, captcha);
		CaptchaServletUtil.writeImage(resp, captcha.getImage());
		
	}
}
