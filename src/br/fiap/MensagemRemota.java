package br.fiap;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Classe MensagemRemota
 * Possui implementa��o da interface MensagemInterface
 */

public class MensagemRemota extends UnicastRemoteObject implements MensagemInterface{

	public String name;
	public MensagemInterface client=null;
	private Hashtable l = new Hashtable();
	
    protected MensagemRemota(String name) throws RemoteException {
        this.name = name;
    }
    
    /**
     * Obt�m o nome
     */
    @Override
    public String getName() throws RemoteException {
		return this.name;
	}
 
    /**
     * Seta um novo cliente
     * @throws RemoteException 
     */
    @Override
	public void setClient(MensagemInterface c) throws RemoteException{
    	l.put(c.getName(), c);
		client = c;
	}
 
	/**
	 * Obt�m um cliente
	 */
    @Override
	public MensagemInterface getClient(){
		return client;
	}

	/**
	 * Mensagem a ser enviada
	 */
	@Override
	public void send(String msg) throws RemoteException {
		System.out.println(msg);
	}
	
	public void sendToAll(String s, MensagemInterface from) throws RemoteException{
		System.out.println("\n["+from.getName()+"] "+s);
		Enumeration usernames = l.keys();
        while(usernames.hasMoreElements()){
		       String user=(String) usernames.nextElement();
		       MensagemInterface m=(MensagemInterface)l.get(user);
		       if (user.equals(from.getName())){continue;}
		       
		       try{
		    	   m.tell("\n["+from.getName()+"] "+s);
		       }catch(Exception e){e.printStackTrace();}
        }
	}
	
	public void tell(String s) throws RemoteException{		
		System.out.println(s);		
	}

}