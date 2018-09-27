package edu.eci.pdsw.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;
import edu.eci.pdsw.validator.EmployeeValidator;
import edu.eci.pdsw.validator.ErrorType;
import edu.eci.pdsw.validator.SalaryValidator;

/**
 * Servlet class for employee validation
 */
@WebServlet(urlPatterns = "/validate")
public class ValidateServlet extends HttpServlet {

	/**
	 * Auto generated serial version id
	 */
	private static final long serialVersionUID = -2768174622692970274L;

	/**
	 * The employee validator to use
	 */
	private EmployeeValidator validator;

	public ValidateServlet() {
		this.validator = new SalaryValidator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();

		// TODO Add the corresponding Content Type, Status, and Response
		resp.setContentType("ok");
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		responseWriter.write(readFile("form.html"));
		resp.setContentType("text/html");
		responseWriter.flush();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer responseWriter = resp.getWriter();
		resp.setContentType("text/html");
		// TODO Create and validate employee
		Employee empleado = new Employee();
		
		empleado.setPersonId(Integer.parseInt(req.getParameter("personID")));
		empleado.setSalary(Long.parseLong(req.getParameter("salary")));
		empleado.setSocialSecurityType(SocialSecurityType.valueOf(req.getParameter("SocialSecurity")));
		Optional<ErrorType> response = validator.validate(empleado);
		
		// TODO Add the Content Type, Status, and Response according to validation response	
		if(response.get().equals(ErrorType.INVALID_PERSONID)) {
			responseWriter.write("El número de identificaión personal no es válido");
			resp.setStatus(404);
			responseWriter.write(String.format(readFile("result.html"), response.map(ErrorType::name).orElse("Success")));
		}
		
		else if(response.get().equals(ErrorType.INVALID_SALARY)) {
			responseWriter.write("El salario no es válido");
			resp.setStatus(404);
			responseWriter.write(String.format(readFile("result.html"), response.map(ErrorType::name).orElse("Success")));			
		}
		
		else if(response.get().equals(ErrorType.INVALID_SISBEN_AFFILIATION)) {
			responseWriter.write("La afiliación al SISBEN es inválida");
			resp.setStatus(404);
			responseWriter.write(String.format(readFile("result.html"), response.map(ErrorType::name).orElse("Success")));			
		}
		
		else if(response.get().equals(ErrorType.INVALID_EPS_AFFILIATION)) {
			responseWriter.write("La afiliacion a la EPS es inválida");
			resp.setStatus(404);
			responseWriter.write(String.format(readFile("result.html"), response.map(ErrorType::name).orElse("Success")));			
		}
		
		else if(response.get().equals(Optional.empty())) {
			responseWriter.write("La validación fue exitosa");
			resp.setStatus(200);
			responseWriter.write(String.format(readFile("result.html"), response.map(ErrorType::name).orElse("Success")));
			
		}
		
		else{
			responseWriter.write("Parametro valido");
			System.out.println(ex.getMessage());
			resp.setStatus(500);;
		}
		
		responseWriter.flush();
	}

	/**
	 * Reads a file from the resources folder
	 * 
	 * @param path The file path
	 * @return the file content
	 * @throws IOException if the file doesn't exist
	 */
	public String readFile(String path) throws IOException {
		StringBuilder html = new StringBuilder();
		try (BufferedReader r = new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(path)))) {
			r.lines().forEach(line -> html.append(line).append("\n"));
		}
		return html.toString();
	}

}
