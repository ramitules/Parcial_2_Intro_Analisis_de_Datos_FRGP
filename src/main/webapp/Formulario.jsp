<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	<jsp:include page="CSS\StyleSheet.css"></jsp:include>
</style>
<title>Calculadora de inversion</title>
</head>
<body>
	<div class="calculadora">
		<h3>Ingrese el rendimiento historico del plazo fijo anual de cada banco en los ultimos tres años</h3>
		<form action="ServletFormulario" method="POST">
		<label for="txtMonto">Monto a invertir: </label>
		<input type="number" name="txtMonto" value="850000">
		<% Boolean calculado = request.getAttribute("BPPromedio") != null; %>
			<table>
				<thead>
					<tr>
						<th></th>
						<th>Año 1</th>
						<th>Año 2</th>
						<th>Año 3</th>
						<% if (calculado) {%>
							<th class="resultado">Promedio anual</th>
							<th class="resultado">Invirtiendo anualmente</th>
							<th class="resultado">Reinvirtiendo trimestralmente</th>
							<th class="resultado">Reinvirtiendo mensualmente</th>
						<%} %>
					</tr>
				</thead>
				<tbody>
					<tr <%= "Banco Provincia".equals(request.getAttribute("MasConveniente")) ? "class=\"masConveniente\"" : "" %>>
						<td>Banco Provincia</td>
						<td><input type="number" required name="txtBPAnio1"></td>
						<td><input type="number" required name="txtBPAnio2"></td>
						<td><input type="number" required name="txtBPAnio3"></td>
						<% if (calculado) {%>
							<td class="resultado"><%= request.getAttribute("BPPromedio") %></td>
							<td class="resultado"><%= request.getAttribute("BPAnual") %></td>
							<td class="resultado"><%= request.getAttribute("BPTrimestral") %></td>
							<td class="resultado"><%= request.getAttribute("BPMensual") %></td>
						<%} %>
					</tr>
					<tr <%= "Banco Nacion".equals(request.getAttribute("MasConveniente")) ? "class=\"masConveniente\"" : "" %>>
						<td>Banco Nacion</td>
						<td><input type="number" required name="txtBNAnio1"></td>
						<td><input type="number" required name="txtBNAnio2"></td>
						<td><input type="number" required name="txtBNAnio3"></td>
						<% if (calculado) {%>
							<td class="resultado"><%= request.getAttribute("BNPromedio") %></td>
							<td class="resultado"><%= request.getAttribute("BNAnual") %></td>
							<td class="resultado"><%= request.getAttribute("BNTrimestral") %></td>
							<td class="resultado"><%= request.getAttribute("BNMensual") %></td>
						<%} %>
					</tr>
					<tr <%= "Banco Hipotecario".equals(request.getAttribute("MasConveniente")) ? "class=\"masConveniente\"" : "" %>>
						<td>Banco Hipotecario</td>
						<td><input type="number" required name="txtBHAnio1"></td>
						<td><input type="number" required name="txtBHAnio2"></td>
						<td><input type="number" required name="txtBHAnio3"></td>
						<% if (calculado) {%>
							<td class="resultado"><%= request.getAttribute("BHPromedio") %></td>
							<td class="resultado"><%= request.getAttribute("BHAnual") %></td>
							<td class="resultado"><%= request.getAttribute("BHTrimestral") %></td>
							<td class="resultado"><%= request.getAttribute("BHMensual") %></td>
						<%} %>
					</tr>
				</tbody>
			</table>
			<input type="submit" class="btnCalcular" name="btnCalcular" value="Calcular">
			<% if (calculado) {%>
				<label>Tu opcion mas conveniente por rentabilidad es <%= request.getAttribute("MasConveniente") %></label>
			<%} %>
		</form>
	</div>
</body>
</html>