import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

    // socket
    private final Socket socket;

    // constructor
    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try{
            // initialize input and output streams
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            // instantiate a new request
            Request request = new Request(input);
            request.parse();

            // instantiate a new response
            Response response = new Response(output);
            response.setRequest(request);

            // process the request
            if (request.getUri() != null) {

                // dynamic request
                if (request.getUri().startsWith("/servlet")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                }

                // static request
                else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }
            }

            // close the socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
