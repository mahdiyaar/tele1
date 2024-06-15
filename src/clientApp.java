import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Objects;
import java.util.Scanner;

public class clientApp {

    static int PORT = 8080;
    static String IP = "192.168.28.140";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket server = null;
        Scanner receiver = null;
        Formatter sender = null;
        try {
            server = new Socket(IP, PORT);
            System.out.println("connected ...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            receiver = new Scanner(server.getInputStream());
            sender = new Formatter(server.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        exit : while (true){
            System.out.println("menu:");
            System.out.println("1- login");
            System.out.println("2- sign up");
            System.out.println("3- delete");
            System.out.println("4- exit");

            int input = Integer.parseInt(scanner.nextLine());
            switch (input){
                case 1: {
                    String username, password;
                    System.out.println("please enter");
                    System.out.print("username:");
                    username = scanner.nextLine();
                    System.out.print("password:");
                    password = scanner.nextLine();

                    sender.format("[LOGIN]," +
                            username + "," +
                            password + "," + "\n");

                    String result = receiver.nextLine();

                    if (Objects.equals(result, "[successful]")) {
                        System.out.println("login successfully done");
                    } else {
                        System.out.println("username or password is not valid");
                        break;
                    }

                    logout : while (true){
                        System.out.println("user menu:");
                        System.out.println("1- Echo");
                        System.out.println("2- Send messages");
                        System.out.println("3- get messages");
                        System.out.println("4- logout");

                        int input2 = Integer.parseInt(scanner.nextLine());
                        switch (input2) {
                            case 1: {
                                System.out.println("enter a text");
                                sender.format("[ECHO]," + scanner.nextLine() + "\n");
                                System.out.println("SERVER : " + receiver.nextLine());
                                break ;
                            }
                            case 2: {
                                System.out.print("enter massage:");
                                String message = scanner.nextLine();

                                sender.format("[SENDER]," + message);

                                String result2 = receiver.nextLine();
                                if (Objects.equals(result2, "[successful]")) {
                                    System.out.println("your message successfully sent");
                                } else {
                                    System.out.println("username not found");
                                }
                                break ;
                            }
                            case 3:{
                                sender.format("[get]");

                                String[] result2 = receiver.nextLine().split(",");

                                if (Objects.equals(result2, "[successful]")) {
                                    for (int i = 1; i < result2.length; i++) {
                                        System.out.println(i + "- " + result2[i]);
                                    }
                                } else {
                                    System.out.println("ERROR");
                                }
                                break ;
                            }
                            case 4:{
                                sender.format("[LOGOUT]");

                                String result2 = receiver.nextLine();
                                if (Objects.equals(result2, "[successful]")) {
                                    System.out.println("you are successfully loged out");
                                    break logout;
                                } else {
                                    System.out.println("ERROR");
                                }
                                break;
                            }
                        }
                    }

                    break;
                }
                case 2: {
                    String username, password, firstname, lastname;
                    System.out.println("please enter");
                    System.out.print("username:");
                    username = scanner.nextLine();
                    System.out.print("password:");
                    password = scanner.nextLine();
                    System.out.print("firstname:");
                    firstname = scanner.nextLine();
                    System.out.print("lastname:");
                    lastname = scanner.nextLine();

                    sender.format("[SIGN IN]," +
                            username + "," +
                            password + "," +
                            firstname + "," +
                            lastname + "," + "\n"
                    );

                    String result = receiver.nextLine();
                    System.out.println(result);
                    if (Objects.equals(result, "[successful]")) {
                        System.out.println("sign up successfully done");
                        System.out.println("please enter login for login");
                    } else {
                        System.out.println("your username is taken");
                        System.out.println("please try again");
                    }
                    break;
                }
                case 3:{
                    String username, password;
                    System.out.println("please enter");
                    System.out.print("username:");
                    username = scanner.nextLine();
                    System.out.print("password:");
                    password = scanner.nextLine();

                    sender.format("[DELETE]," +
                            username + "," +
                            password + "," + "\n");

                    String result = receiver.nextLine();

                    if (Objects.equals(result, "[successful]")) {
                        System.out.println("login successfully done");
                    } else {
                        System.out.println("username or password is not valid");
                        break;
                    }
                    break ;
                }
                case 4:
                    sender.format("[q]");
                    break exit;
                default:
                    System.out.println("invalid number");
                    break;
            }
        }
        System.out.println("bye");
    }



}
