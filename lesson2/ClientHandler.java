package hmJava3.hm2Chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ClientHandler {
    //    private Socket socket;
    private Server server;
    private PrintWriter pw;
    private Scanner sc;
    private String nick;
    boolean timeout=false;
    boolean authentication = false;

    public ClientHandler(Socket socket, Server server) {
//        this.socket = socket;
        this.server = server;

        try {
            sc = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                Date currentTime = new Date();
                auth(currentTime.getTime());
                if(!timeout) {
                    System.out.println(nick + " handler waiting for new massages");
                    while (socket.isConnected()) {
                        String s = sc.nextLine();
                        if (s != null && s.equals("/exit"))
                            server.unsubscribe(this);
                        if (s != null && !s.isEmpty()) {

                            if (s.startsWith("/w")) {
                                String[] commands = s.split(" ");
                                if (commands.length >= 3) {
                                    String target = commands[1];
                                    String targetMessage = nick + " (private message) : ";
                                    for (int i = 2; i < commands.length; i++) {
                                        targetMessage += commands[i] + " ";
                                    }
                                    if (!server.sendTargetMessage(target, nick, targetMessage)) {
                                        pw.println("There is no " + target + " in chat!");
                                    }
                                } else {
                                    pw.println("Invalid command!");
                                }
                            } else if(s.startsWith("/chlog")){
                                String[] commands = s.split(" ");
                                if (commands.length >= 3){
                                    String login = commands[1];
                                    String pass = commands[2];

                                    String nick = server.getAuthService()
                                            .authByLoginAndPassword(login, pass);
                                    if (nick == null) {
                                        String msg = "Invalid login or password";
                                        System.out.println(msg);
                                        pw.println(msg);
                                    } else if (server.isNickTaken(nick)) {
                                        String msg = "Nick " + nick + " already taken!";
                                        System.out.println(msg);
                                        pw.println(msg);
                                    } else {
                                        this.nick = nick;
                                        String msg = "Auth ok!";
                                        System.out.println(msg);
                                        pw.println(msg);
                                    }
                                }

                            } else {
                                server.sendBroadcastMessage(nick + " : " + s);
                            }

                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait for command: "/auth login1 pass1"
     */
    private void auth(long lastTime) {
        while (true) {

            Thread threadTimeout = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        Date currentTime = new Date();
                        if((currentTime.getTime()-lastTime)==120000){
                            if(!authentication) {
                                pw.println("Timeout 120 seconds! The connection is closed");
                                timeout = true;
                            }
                            break;
                        }
                    }
                }
            });

            threadTimeout.start();

            if(timeout){
                break;
            }

            if (!sc.hasNextLine()) continue;
            String s = sc.nextLine();
            if (s.startsWith("/auth")&&!timeout) {
                String[] commands = s.split(" ");// /auth login1 pass1
                if (commands.length >= 3) {
                    String login = commands[1];
                    String password = commands[2];
                    System.out.println("Try to login with " + login + " and " + password);
                    String nick = server.getAuthService()
                            .authByLoginAndPassword(login, password);
                    if (nick == null) {
                        String msg = "Invalid login or password";
                        System.out.println(msg);
                        pw.println(msg);
                    } else if (server.isNickTaken(nick)) {
                        String msg = "Nick " + nick + " already taken!";
                        System.out.println(msg);
                        pw.println(msg);
                    } else {
                        authentication=true;
                        this.nick = nick;
                        String msg = "Auth ok!";
                        System.out.println(msg);
                        pw.println(msg);
                        server.subscribe(this);
                        break;
                    }
                }
            } else {
                if(!timeout) {
                    pw.println("Invalid command!");
                }
            }
        }
    }

    public void sendMessage(String msg) {
        pw.println(msg);
    }

    public String getNick() {
        return nick;
    }
}
