package crypto;

import java.security.PublicKey;

public class TransactionOutput {

	private String id;
	
	private String parentTranscationId;
	private PublicKey reciver;
	
	private double amount;

	public TransactionOutput(String parentTranscationId, PublicKey reciver, double amount) {
		super();
		this.parentTranscationId = parentTranscationId;
		this.reciver = reciver;
		this.amount = amount;
		generateId();
	}

	
	public  boolean isMine(PublicKey publickey) {
		return publickey ==reciver;
	}
	private void generateId() {
		this.id= CryptoHelper.getSHA(reciver.toString()+Double.toString(amount)+parentTranscationId);
		
	}

	public String getId() {
		return id;
	}

	
	public String getParentTranscationId() {
		return parentTranscationId;
	}
	public PublicKey getReciver() {
		return reciver;
	}

	public void setId(String id) {
		this.id = id;
	}
	 
	public double getAmount() {
		return amount;
	}

	public void setParentTranscationId(String parentTranscationId) {
		this.parentTranscationId = parentTranscationId;
	}

	
	public void setReciver(PublicKey reciver) {
		this.reciver = reciver;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
