import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Objects;
import java.util.Scanner;



public class ClientHandler implements Runnable {

    Socket socket;

    Scanner scanner;
    Formatter formatter;



    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        scanner = new Scanner(socket.getInputStream());
        formatter = new Formatter(socket.getOutputStream());
    }

    @Override
    public void run() {
        String command = scanner.nextLine();

        while (!Objects.equals(command, "[q]")){
            String[] values = command.split(",");

            if(Objects.equals(values[0], "[SIGN IN]")){

            }
        }



    }
}
