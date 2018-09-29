package hmJava3.hm3Chat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ClientController implements Controller {
    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;
    private ClientUI ui;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;
    private int index = new Random().nextInt(3) + 1;

    //------------------------------------------------------------------

    BufferedWriter historyWriter;
    BufferedReader historyReader;
    private ArrayList<String> historyList = new ArrayList<>();

    //------------------------------------------------------------------

    public ClientController() {
        initConnection();
    }

    public void showUI(ClientUI ui) {
        this.ui = ui;

        int fromLineHistory=historyList.size()>100?(historyList.size()-100):0;
        for(int i=fromLineHistory;i<historyList.size();i++){
            ui.addMessage(historyList.get(i));
        }

        ui.showUI();
        sendMessage("/auth login" + index + " pass" + index);
    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), true);
            historyReader = new BufferedReader(new FileReader("History.txt"));
            String str;
            while ((str = historyReader.readLine()) != null ){
                historyList.add(str);
            }
            historyReader.close();
            historyWriter = new BufferedWriter(new FileWriter("History.txt",true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while (true) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        if (w.startsWith("end session")) break;
                        ui.addMessage(w);
                        historyWriter.write(w+"\n");
                    }
                }
            } catch (Exception e) {
            }
        }).start();
    }

    @Override
    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void closeConnection() {
        try {
            sendMessage("/exit");
            sock.close();
            out.close();
            in.close();
            historyWriter.close();
        } catch (IOException exc) {
        }
    }
}

