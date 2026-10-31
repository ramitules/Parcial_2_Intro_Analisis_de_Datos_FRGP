package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletFormulario")
public class ServletFormulario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFormulario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float anioUno, anioDos, anioTres;
		try {
			anioUno = Float.parseFloat(request.getParameter("anioUno"));
			anioDos = Float.parseFloat(request.getParameter("anioDos"));
			anioTres = Float.parseFloat(request.getParameter("anioTres"));
		} catch (Exception e) {
			return;
		}
		
		float total = calcularPromedio(anioUno, anioDos, anioTres);
		
		request.setAttribute("promedio", total);
		request.getRequestDispatcher("Formulario.jsp").forward(request, response);
	}

	private float calcularPromedio(float anioUno, float anioDos, float anioTres) {
		return (anioUno + anioDos + anioTres) / 3;
	}
}
