package crypto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shravan.m
 *
 */
public class Block {
	
	private int id;
	private int nonce;
	private String hash;
	private String preivousHash;
	private long timestamp;
	private List<Transaction> transcations;
	
	public Block(String previouHash) {
		this.transcations= new ArrayList<Transaction>();
		this.preivousHash=previouHash;
		this.timestamp= new Date().getTime();
		
		generateHash();
	}
	
	public Block(int id, String preivousHash, List<Transaction> transcations) {
		super();
		this.id = id;
		this.preivousHash = preivousHash;
		this.transcations = transcations;
		this.timestamp=new Date().getTime();
		generateHash();
	}

	public void generateHash() {
		 String dataToHash =Integer.toString(id)+preivousHash+Long.toString(timestamp)+Integer.toString(nonce)+
				 transcations;
		 String hash = CryptoHelper.getSHA(dataToHash);
		 this.hash=hash;
		
	}

	public Block(int id, int nonce, String hash, String preivousHash, long timestamp, List<Transaction>transcations) {
		super();
		this.id = id;
		this.nonce = nonce;
		this.hash = hash;
		this.preivousHash = preivousHash;
		this.timestamp = timestamp;
		this.transcations = transcations;
	}
	
	
	public boolean addTranscation(Transaction trancation) {
		if(trancation == null)return false;
		
		if(!preivousHash.equals(Constant.GENESIS_PREV_HASH)) {
			System.out.println("transcatin not valid");
			return false;
		}
		transcations.add(trancation);
		System.out.println("transcation is valid and iadded to the block"+this);
		return true;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPreivousHash() {
		return preivousHash;
	}
	public void setPreivousHash(String preivousHash) {
		this.preivousHash = preivousHash;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public List<Transaction>  getTranscations() {
		return transcations;
	}
	public void setTranscations(List<Transaction>transcations) {
		this.transcations = transcations;
	}
	
	public void increamentaNonce() {
		this.nonce++;
	}
	@Override
	public String toString() {
		return "Block [id=" + id + ", preivousHash=" + preivousHash + ", transcations=" + transcations + "]";
	}

}
