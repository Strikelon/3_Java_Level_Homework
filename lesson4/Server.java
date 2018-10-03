package hmJava3.hm4_Chat;

import hmJava3.hm2Chat.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket serverSocket;
    private AuthService authService;
    private List<ClientHandler> clients = new ArrayList<ClientHandler>();

    public ExecutorService executorService = Executors.newCachedThreadPool();

    public Server(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        }
    }

    public void close() {
        try {
            executorService.shutdownNow();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        AuthService baseAuthService = new BaseAuthService();
        Server server = new Server(baseAuthService);
        server.start();
    }

    private void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }

//------------------------------------------------------------------------------

    public boolean sendTargetMessage(String target,String nick,String targetMessage){
        if(isNickTaken(target)){

            for (ClientHandler client : clients) {
                if(target.equals(client.getNick()))
                    client.sendMessage(targetMessage);
                if(nick.equals(client.getNick()))
                    client.sendMessage(targetMessage);
            }

            return true;


        }else return false;

    }

//------------------------------------------------------------------------------

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        for (ClientHandler client : clients) {
            if (nick.equals(client.getNick()))
                return true;
        }
        return false;
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        System.out.println(msg);
        clients.remove(clientHandler);
    }
}

