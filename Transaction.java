package crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
	private String transcationId;

	private PublicKey sender;

	private PublicKey reciver;

	private double amount;
	private byte[] signature;
	
	private List<TransactionInput> inputs= new ArrayList<TransactionInput>();
	private List<TransactionOutput> outputs= new ArrayList<TransactionOutput>();
	
	
	public Transaction(PublicKey sender, PublicKey reciver, double amount, byte[] signature,
			List<TransactionInput> transcationInput, List<TransactionOutput> transcationOutput) {
		super();
		this.sender = sender;
		this.reciver = reciver;
		this.amount = amount;
		this.signature = signature;
		this.inputs = transcationInput;
		this.outputs = transcationOutput;
		calulateHash();
		
	}
	
	public Transaction(PublicKey sender, PublicKey reciver, double amount, List<TransactionInput> inputs) {
		 
		
		this.sender = sender;
		this.reciver = reciver;
		this.amount = amount;
		 
		this.inputs = inputs;
		 
	}

	public boolean verifyTranscations() {
		
		if(!verifySignature()) {
			System.out.println("invalid transcation becaouse of invalid signature");
		return false;
		}
		for(TransactionInput transactionInput:inputs) {
			transactionInput.setUTXO(BlockChain.UTXOS.get(transactionInput.getTransactionOutputid()));
		}
		
		outputs.add(new TransactionOutput(this.transcationId, this.reciver, this.amount));
		outputs.add(new TransactionOutput(this.transcationId, this.sender, getInputSum()-this.amount));
		
		for(TransactionOutput transactionOutput :outputs) {
			BlockChain.UTXOS.put(transactionOutput.getId(),transactionOutput);
		}
		for(TransactionInput transactionInput:inputs) {
			if(transactionInput.getUTXO()!=null) {
				BlockChain.UTXOS.remove(transactionInput.getUTXO().getId());
			}
		}
		return true;
	}
	
	public double getInputSum()
	
	{
		double sum = 0;
		
		for(TransactionInput transactionInput:inputs) {
			sum+=transactionInput.getUTXO().getAmount();
		}
		return sum;
	}
	
	public void generateSignature(PrivateKey privatekey) {
		String data =sender.toString()+reciver.toString()+Double.toString(amount);
		this.signature=CryptoHelper.applyECOSASignature(privatekey, data);
	}

	public boolean verifySignature()
	{
		String data =sender.toString()+reciver.toString()+Double.toString(amount);
		return CryptoHelper.verifyECDSASignature(sender, data, signature);
	}

	private void calulateHash() {
	 String hashData = sender.toString()+reciver.toString()+Double.toString(amount);
	 this.transcationId=CryptoHelper.getSHA(hashData);
		
	}


	public String getTranscationId() {
		return transcationId;
	}


	public PublicKey getSender() {
		return sender;
	}


	public PublicKey getReciver() {
		return reciver;
	}


	public double getAmount() {
		return amount;
	}


	public byte[] getSignature() {
		return signature;
	}


	public List<TransactionInput> getInputs() {
		return inputs;
	}


	public List<TransactionOutput> getOutputs() {
		return outputs;
	}

	public void setSender(PublicKey sender) {
		this.sender = sender;
	}

	public void setReciver(PublicKey reciver) {
		this.reciver = reciver;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public void setInputs(List<TransactionInput> inputs) {
		this.inputs = inputs;
	}

	public void setOutputs(List<TransactionOutput> outputs) {
		this.outputs = outputs;
	}

	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}
	
	

}
