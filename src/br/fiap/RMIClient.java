package br.fiap;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Classe RMIClient
 * Função: atuará como o cliente que se conectará ao servidor
 */

public class RMIClient {

    public static void main(String[] args) {
        try {
        	Scanner s = new Scanner(System.in);
	    	System.out.println("Entre com seu nome:");
	    	String name = s.nextLine().trim(); // Obtém nome do cliente		    		    	
	    	MensagemInterface client = new MensagemRemota(name); // Associa cliente à mensagem remota
	    	
            MensagemInterface intf = (MensagemInterface) Naming.lookup("//127.0.0.1:1099/mensagem");
            String msg = "["+client.getName()+"] conectou!";
	    	intf.send(msg);
	    	System.out.println("O chat está pronto!");
	    	intf.setClient(client);
	    	
	    	Scanner s2 = new Scanner(System.in);
	    	do {
		    	System.out.println(client.getName() + " digite sua mensagem: ");
		    	String mensagem = s2.nextLine().trim();	
		    	intf.sendToAll("\n" + client.getName() + " mandou: " + mensagem, client);
	    	}while(true);
	    	
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

}