import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class HttpServer {

	//----------------------------------------------------------------------//
	// PARAMETERS:

	// WEB_ROOT --> root of the web content folder
	public static String WEB_ROOT = "D:\\Documents\\GitHub\\SP-Servlet\\SP-Servlet\\WebContent";

	// Directory that contains static content
	public static final String StaticContentDirectory = WEB_ROOT + File.separator + "StaticContentRepository";

	// name of the directory that contains servlets
	public static final String ServletDirectory = WEB_ROOT + File.separator + "ServletRepository";

	// 404 not found filename
	public static final String Error404Link = "404.html";

	// Address
	private static final String host = "127.0.0.1";

	// port
	private static final int port = 8080;

	//----------------------------------------------------------------------//


	// main method
	public static void  main(String[] args) {

		// initialize a servlet hast table to store loaded servlets
		ServletHashTable servletHashTable = new ServletHashTable();

		// initialize a management console to manage servlets
		ManagementConsole managementConsole = new ManagementConsole();
		managementConsole.start();

		// initialize an http server
		HttpServer myHttpServer = new HttpServer();
		myHttpServer.await();
		//System.out.println("Exiting...");
	}


	// constructor
	HttpServer() {

	}

	// method that waits for connection requests
	public void await() {

		// initialize a new server socket
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName(host));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}


		while (!Shutdown.flag) {
			// Socket socket = null;
			InputStream input = null;
			OutputStream output = null;


			try {
				serverSocket.setSoTimeout(2000);

				// accept a new request
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(2000);

				// initialize input and output streams
				input = socket.getInputStream();
				output = socket.getOutputStream();

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


			} catch (SocketTimeoutException se) {
				if (Shutdown.flag){
					return;
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
