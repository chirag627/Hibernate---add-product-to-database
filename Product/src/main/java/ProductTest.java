
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.samples.domain.Product;
import com.samples.utils.HibernateUtil;

@WebServlet("/ProductTest")
public class ProductTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String category = req.getParameter("category");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product(name, price, category);
		session.save(product);
		session.getTransaction().commit();
		session.close();
		
		PrintWriter pw=resp.getWriter();
		pw.println("Product inserted Successfully in the database");

	}
}
