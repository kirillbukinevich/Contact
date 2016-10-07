/**
 * Created by aefrd on 03.10.2016.
 */

import logic.database.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import web.controller.Controller;

import javax.activation.DataSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static logic.database.ConnectionFactory.getConnection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://127.0.0.1/myschema");
        properties.setProperty("maxActive", "10");
        properties.setProperty("maxIdle", "8");
        properties.setProperty("minIdle", "10");
        properties.setProperty("maxWait", "10");
        properties.setProperty("testOnBorrow", "true");
        properties.setProperty("username", "username");
        properties.setProperty("password", "password");
        properties.setProperty("validationQuery", "SELECT 1");
        properties.setProperty("removeAbandoned", "true");
        properties.setProperty("removeAbandonedTimeout", "1");
        properties.setProperty("logAbandoned", "true");
        DataSource ds = (DataSource) ConnectionFactory.getConnection();
    }

    @Test
    public void test() throws Exception {

  /*HttpServletRequest request = mock(HttpServletRequest.class);
  HttpServletResponse response = mock(HttpServletResponse.class);
  HttpSession session = mock(HttpSession.class);
  RequestDispatcher rd=mock(RequestDispatcher.class);
   */

        when(request.getParameter("command")).thenReturn("contact");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);
        Connection connection = getConnection();
        connection.setAutoCommit(false);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        new Controller().doPost(request, response);

        //Verify the session attribute value
        verify(session).setAttribute("user", "abhinav");

        verify(rd).forward(request, response);

        String result = sw.getBuffer().toString().trim();

        System.out.println("Result: "+result);

        assertEquals("Login successfull...", result);
    }
}
