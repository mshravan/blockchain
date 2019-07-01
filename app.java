package crypto;

import java.security.Security;

public class app {

	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		Wallet userA= new Wallet();
		Wallet userB= new Wallet();
		Wallet lender= new Wallet();
		 
		 BlockChain chain= new BlockChain();
		 Miner miner= new Miner();
		 
		 Transaction genesisTransaction = new Transaction(lender.getPublickey(), userA.getPublickey(), 500, null);
		 genesisTransaction.generateSignature(lender.getPrivateKey());
		 genesisTransaction.setTranscationId("0");
		 
		 genesisTransaction.getOutputs().add(new TransactionOutput( genesisTransaction.getTranscationId(),genesisTransaction.getReciver(), genesisTransaction.getAmount()));
		 BlockChain.UTXOS.put(genesisTransaction.getOutputs().get(0).getId(), genesisTransaction.getOutputs().get(0));
		 
		 
		 
		 System.out.print("constructing genesis block");
	 Block gensis= new Block(Constant.GENESIS_PREV_HASH);
	 
	 gensis.addTranscation(genesisTransaction);
	 miner.mine(gensis, chain);
	 
	 
	 Block block1 = new Block(gensis.getHash());
	 System.out.println("\n user a balance"+ userA.calculateBalance());
	 System.out.println("\n user b balance"+ userB.calculateBalance());
	 System.out.println("\n tries transfer money ");
	 block1.addTranscation(userA.transferMoney(userB.getPublickey(), 120));
	 miner.mine(block1, chain);
	 System.out.println("\n user a balance"+ userA.calculateBalance());
	 System.out.println("\n user b balance"+ userB.calculateBalance());
	 
	 Block block2 = new Block(gensis.getHash());
	 System.out.println("\n user a balance"+ userA.calculateBalance());
	 System.out.println("\n user b balance"+ userB.calculateBalance());
	 System.out.println("\n tries transfer money 600");
	 block1.addTranscation(userA.transferMoney(userB.getPublickey(), 600));
	 miner.mine(block2, chain);
	 System.out.println("\n user a balance"+ userA.calculateBalance());
	 System.out.println("\n user b balance"+ userB.calculateBalance());
	 

	 Block block3 = new Block(gensis.getHash());
	 System.out.println("\n user a balance"+ userA.calculateBalance());
	 System.out.println("\n user b balance"+ userB.calculateBalance());
	 System.out.println("\n tries transfer money 600");
	 block1.addTranscation(userB.transferMoney(userA.getPublickey(), 600));
	 miner.mine(block3, chain);
	 System.out.println("\n user a balance"+ userA.calculateBalance());
	 System.out.println("\n user b balance"+ userB.calculateBalance());
	 
	 System.out.println("miner reward:"+miner.getReward());
	}

}
