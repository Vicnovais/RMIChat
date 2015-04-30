package br.fiap;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

/**
 * Classe RMIRegister
 * Função: responsável por realizar o registro e atuar como servidor
 */

public class RMIRegister {

    public static void main(String[] args) {
    	try {        	
        	Scanner s = new Scanner(System.in);
        	
	    	System.out.println("Digite seu nome: ");           
            String serverName = s.nextLine().trim(); // Obtém nome do servidor
            
            MensagemRemota server = new MensagemRemota(serverName); // Associa servidor à mensagem remota
            
            LocateRegistry.createRegistry(1099); // Realiza o registro
            Naming.rebind("mensagem", server); // Liga a mensagem ao objeto remoto
            System.out.println("Servico de registro no ar");
            
            while(true){ //Aguarda mensagem do servidor
	    		String msg = s.nextLine().trim(); // Aguarda o servidor digitar a mensagem
	    		if (server.getClient() != null){ // Se houver algum cliente conectado
	    			MensagemInterface client = server.getClient();
	    			msg = "["+server.getName()+"] mandou: " + msg;
	    			client.sendToAll(msg, client); // Envia mensagem ao cliente
	    		}	
	    	}
            
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }       
    }

}