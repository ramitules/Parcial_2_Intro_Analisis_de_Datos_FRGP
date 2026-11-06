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
		<% Boolean calculado = request.getAttribute("BPPromedio") != null; %>
			<table>
				<thead>
					<tr>
						<th></th>
						<th>Año 1</th>
						<th>Año 2</th>
						<th>Año 3</th>
						<th class="resultado">Promedio</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Banco Provincia</td>
						<td><input type="number" required name="txtBPAnio1"></td>
						<td><input type="number" required name="txtBPAnio2"></td>
						<td><input type="number" required name="txtBPAnio3"></td>
						<td class="resultado"><%= calculado ? request.getAttribute("BPPromedio") : "" %></td>
					</tr>
					<tr>
						<td>Banco Nacion</td>
						<td><input type="number" required name="txtBNAnio1"></td>
						<td><input type="number" required name="txtBNAnio2"></td>
						<td><input type="number" required name="txtBNAnio3"></td>
						<td class="resultado"><%= calculado ? request.getAttribute("BNPromedio") : "" %></td>
					</tr>
					<tr>
						<td>Banco Hipotecario</td>
						<td><input type="number" required name="txtBHAnio1"></td>
						<td><input type="number" required name="txtBHAnio2"></td>
						<td><input type="number" required name="txtBHAnio3"></td>
						<td class="resultado"><%= calculado ? request.getAttribute("BHPromedio") : "" %></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" name="btnCalcular" value="Calcular">
		</form>
	</div>
</body>
</html>