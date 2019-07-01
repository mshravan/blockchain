package crypto;

public class TransactionInput {

	private String transactionOutputid;
	
	private TransactionOutput UTXO;

	public TransactionInput(String transactionOutputid) {
		 
		this.transactionOutputid = transactionOutputid;
	}

 

	public TransactionOutput getUTXO() {
		return UTXO;
	}

	public void setUTXO(TransactionOutput uTXO) {
		UTXO = uTXO;
	}



	public String getTransactionOutputid() {
		return transactionOutputid;
	}



	public void setTransactionOutputid(String transactionOutputid) {
		this.transactionOutputid = transactionOutputid;
	}
	
	
}
