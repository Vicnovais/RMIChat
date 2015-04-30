package br.fiap;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface utilizada pela MensagemRemota.
 * Cliente e servidor têm acesso a estes métodos
 */

public interface MensagemInterface extends Remote {
	
	public String getName() throws RemoteException; // Obtém nome
	public void send(String msg) throws RemoteException; // Envia mensagem
	public void setClient(MensagemInterface c)throws RemoteException; // Seta cliente
	public MensagemInterface getClient() throws RemoteException; // Obtém cliente 
	public void sendToAll(String s, MensagemInterface from) throws RemoteException; // Manda para todos
	public void tell(String s) throws RemoteException; // Imprime msg

}