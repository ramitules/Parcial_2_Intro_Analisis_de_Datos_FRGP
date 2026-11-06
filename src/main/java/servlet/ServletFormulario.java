package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

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
		float monto = Float.parseFloat(request.getParameter("txtMonto"));
		
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
		
		request.setAttribute("BPAnual", montoAnual(BPTotal, monto));
		request.setAttribute("BNAnual", montoAnual(BNTotal, monto));
		request.setAttribute("BHAnual", montoAnual(BHTotal, monto));
		
		request.setAttribute("BPTrimestral", montoTrimestral(BPTotal, monto));
		request.setAttribute("BNTrimestral", montoTrimestral(BNTotal, monto));
		request.setAttribute("BHTrimestral", montoTrimestral(BHTotal, monto));
		
		request.setAttribute("BPMensual", montoMensual(BPTotal, monto));
		request.setAttribute("BNMensual", montoMensual(BNTotal, monto));
		request.setAttribute("BHMensual", montoMensual(BHTotal, monto));
		
		request.setAttribute("MasConveniente", masConveniente(BPTotal, BNTotal, BHTotal));
		
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
	
	private String masConveniente(float bancoProvincia, float bancoNacion, float bancoHipotecario) {
		if (bancoProvincia > bancoNacion && bancoProvincia > bancoHipotecario) {
			return "Banco Provincia";
		}
		if (bancoNacion > bancoProvincia && bancoNacion > bancoHipotecario) {
			return "Banco Nacion";
		}
		
		return "Banco Hipotecario";
	}
	
	private float montoAnual(float tasaBanco, float monto) {
		return monto * ((tasaBanco / 100.0f) + 1.0f);
	}
	
	private float montoTrimestral(float tasaBanco, float monto) {
		int TRIMESTRES = 4;
		float total = monto;
		float tasaTrimestral = tasaBanco / 100.0f / TRIMESTRES;
		
		for (int i = 0; i < TRIMESTRES; i++) {
			total += total * tasaTrimestral;
		}
		
		return total;
	}
	
	private float montoMensual(float tasaBanco, float monto) {
		float total = monto;
		float tasaMensual = tasaBanco / 12 / 100;
		
		for (int i = 0; i < 12; i++) {
			total += (total * tasaMensual);
		}
		
		return total;
	}
}
