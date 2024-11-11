import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader userInput = null;

        try {
            // Connect to the server at localhost on port 12345
            socket = new Socket("localhost", 12345);
            System.out.println("Connected to server.");

            // Set up input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            String response;

            // Send messages to the server
            while (true) {
                System.out.print("Enter message to send to server (or 'exit' to quit): ");
                message = userInput.readLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(message);  // Send message to server
                response = in.readLine();  // Read server's response
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            try {
                if (userInput != null) userInput.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
