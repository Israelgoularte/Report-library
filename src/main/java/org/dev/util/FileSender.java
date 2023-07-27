import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileSender {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) throws IOException {
        // Get the file to send
        File file = new File(args[0]);

        // Create a socket to connect to the server
        Socket socket = new Socket("localhost", 8080);

        // Open an input stream to read from the file
        InputStream inputStream = new FileInputStream(file);

        // Open an output stream to write to the socket
        OutputStream outputStream = socket.getOutputStream();

        // Copy the file to the socket
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Close the streams
        inputStream.close();
        outputStream.close();

        // Close the socket
        socket.close();
    }
}
