package br.fiap;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface utilizada pela MensagemRemota.
 * Cliente e servidor t�m acesso a estes m�todos
 */

public interface MensagemInterface extends Remote {
	
	public String getName() throws RemoteException; // Obt�m nome
	public void send(String msg) throws RemoteException; // Envia mensagem
	public void setClient(MensagemInterface c)throws RemoteException; // Seta cliente
	public MensagemInterface getClient() throws RemoteException; // Obt�m cliente 
	public void sendToAll(String s, MensagemInterface from) throws RemoteException; // Manda para todos
	public void tell(String s) throws RemoteException; // Imprime msg

}