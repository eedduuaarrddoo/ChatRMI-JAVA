package tadsrmi.client;

import tadsrmi.ChatInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client2 {
    static String nome;

    public static void main(String[] args) {

        try {
            System.out.println("\uD83D\uDD50 Connecting on server...");
            Scanner scanner = new Scanner(System.in);
            ChatInterface remoteObject = (ChatInterface) Naming.lookup("rmi://localhost:6000/RemoteObj");
            System.out.println("✅ Succefully connected on server");

            new Thread(() -> {
                System.out.println("Digite seu nome: \n");
                String nome = scanner.nextLine();
                Client.nome = nome;
                try {
                    remoteObject.broadcast("O usuário " + nome + " entrou na conversa");
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    System.out.println("Digite sua mensagem: \n");
                    String newMessage = scanner.nextLine();
                    try {
                        if (newMessage.equals("exit")) {
                            remoteObject.broadcast("O Usuário " + Client.nome + " saiu da conversa");
                            System.exit(0);
                        }
                        remoteObject.broadcast(newMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(() -> {
                try {
                    String lastMessage = null;
                    while (true) {
                        String message = remoteObject.getMessage();
                        if (message != null && !message.equals(lastMessage)) {
                            lastMessage = message;
                            System.out.println("Mensagem recebida: " + lastMessage);
                        }
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}