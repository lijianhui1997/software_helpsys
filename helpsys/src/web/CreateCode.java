package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 创建验证码图片
 * @author TRY
 *
 */
public class CreateCode extends HttpServlet {
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1.创建一张空白的内存图片
		BufferedImage image = new BufferedImage(100, 30,
				BufferedImage.TYPE_INT_RGB);
		// 2.获取图片的画笔对象
		Graphics g = image.getGraphics();
		// 3.绘制背景
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(0, 0, 100, 30);
		// 4.设置画笔颜色和字体
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font(null, Font.BOLD, 24));
		// 5.获取随机的字符串
		String str = getString(4);
		// 使用session存储随机的验证码
		HttpSession session = request.getSession();
		session.setAttribute("code", str);
		// 6.绘制字符串
		g.drawString(str, 5, 23);
		// 7.保存图片，输出图片
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
	}

	private String getString(int size) {
		String str = "";
		String con = "ABCDEFGHJKLMNPQRS" + "TUVWXYZ23456789";
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			char ch = con.charAt(r.nextInt(con.length()));
			str += ch;
		}
		return str;
	}

}
