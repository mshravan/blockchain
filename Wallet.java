package crypto;

import java.security.CryptoPrimitive;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.CredentialException;

public class Wallet {
	private PrivateKey privateKey;
	private PublicKey publickey;
	
	
	
	public Wallet( ) {
		 KeyPair keypair =CryptoHelper.ellipticCurveCrypt();
		this.privateKey = keypair.getPrivate();
		this.publickey = keypair.getPublic();
	}
	
	 public double calculateBalance() {
		 
		double balance=0;
		for(Map.Entry<String,TransactionOutput>  item : BlockChain.UTXOS.entrySet()) {
			TransactionOutput output= item.getValue();
			if(output.isMine(publickey)) {
			balance+=output.getAmount();
			}
		}
		
		return balance;
	 }
	 
	 public Transaction transferMoney(PublicKey reciver, double amount) {
		 
		 if(calculateBalance()<amount) {
			 System.out.println("no balance");
			 return null;
		 }
		 
		 List<TransactionInput> inputs = new ArrayList<TransactionInput>();
		 for(Map.Entry<String, TransactionOutput> items: BlockChain.UTXOS.entrySet()) {
			 TransactionOutput UTXO= items.getValue();
			 if(UTXO.isMine(this.publickey)) {
				 inputs.add(new TransactionInput(UTXO.getId()));
			 }
			 
		 }
		 
		Transaction newTranscation= new  Transaction(publickey, reciver, amount, inputs); 
		newTranscation.generateSignature(privateKey);
		
		return newTranscation;
		
	 }


	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public PublicKey getPublickey() {
		return publickey;
	}
	public void setPublickey(PublicKey publickey) {
		this.publickey = publickey;
	}
	
	

}
