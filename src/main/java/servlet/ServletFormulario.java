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
	
	private static final int ANIOS = 3;
       
    public ServletFormulario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float[] bancoProvincia = new float[ANIOS];
		float[] bancoNacion = new float[ANIOS];
		float[] bancoHipotecario = new float[ANIOS];
		
		// Obtener datos de inputs
		try {
			for (int i = 0; i < ANIOS; i ++) {
				bancoProvincia[i] = Float.parseFloat(request.getParameter("txtBPAnio" + (i + 1)));
				bancoNacion[i] = Float.parseFloat(request.getParameter("txtBNAnio" + (i + 1)));
				bancoHipotecario[i] = Float.parseFloat(request.getParameter("txtBHAnio" + (i + 1)));
			}
		} catch (Exception e){
			return;
		}
		
		// Calcular promedios
		float BPTotal = calcularPromedioAnual(bancoProvincia);
		float BNTotal = calcularPromedioAnual(bancoNacion);
		float BHTotal = calcularPromedioAnual(bancoHipotecario);
		
		request.setAttribute("BPPromedio", String.format("%.2f", BPTotal));
		request.setAttribute("BNPromedio", String.format("%.2f", BNTotal));
		request.setAttribute("BHPromedio", String.format("%.2f", BHTotal));
		request.getRequestDispatcher("Formulario.jsp").forward(request, response);
	}
	
	private float calcularPromedioAnual(float... anios) {
		float total = 0.0f;
		
		if (anios.length != ANIOS) {
			return total;
		}

		for (float f : anios) {
			total += f;
		}
		
		return total / ANIOS;
	}
}
