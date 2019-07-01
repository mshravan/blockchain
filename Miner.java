package crypto;

public class Miner {
	private double reward;
	
	
	public void mine(Block block,BlockChain blockchain) {
		 int diffculty= Constant.DIFFICULTY;
	while(notGlodenHash(block,diffculty)) {
		block.generateHash();
		block.increamentaNonce();
		
	}
	
	System.out.println(block+"block just added" );
	System.out.println("hash is :"+block.getHash());
	blockchain.addBlock(block);
	reward+=Constant.MINER_REWARD;
	}

	private boolean notGlodenHash(Block block, int diffculty) {
		String leadingZeros= new String (new char[diffculty]).replace('\0', '0');
		return !block.getHash().substring(0,diffculty).equals(leadingZeros);
	}
	
	  public static void main(String args[]) {
			String leadingZeros= new String (new char[5]).replace('\0', '0');
		System.out.println( "dddd"+leadingZeros); ;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

}
