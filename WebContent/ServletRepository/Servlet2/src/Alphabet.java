import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Alphabet extends HttpServlet {
    //private String message;

    public Alphabet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int sleepTime = 400;

        // build the header
        String header1 = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length: ";
        String header2 = "\r\n\r\n";
        String example = "<div> A </div>";

        int length = (example.length()) * 30 - 2;
        
        String header = header1 + length + header2;

        try {
           
            // send the header
            response.getWriter().println(header);
            
            // Print each letter with a delay 
            for (char letter = 'A'; letter <= 'Z'; letter++) {

                response.getWriter().println("<div> " + letter + " </div>");
                response.getWriter().flush();

                //System.out.println("<div> " + letter + " </div>");

                // Sleep 
                Thread.sleep(sleepTime);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}