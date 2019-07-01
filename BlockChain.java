package crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockChain {

	public static Map<String, TransactionOutput> UTXOS;
	public static List<Block> blockChain;

	public BlockChain() {
		this.blockChain = new ArrayList<Block>();
		this.UTXOS = new HashMap<String, TransactionOutput>();
	}

	public void addBlock(Block block) {
		BlockChain.blockChain.add(block);

	}

	public int size() {
		return this.blockChain.size();
	}

	public List<Block> getBlockchain() {
		return this.blockChain;
	}

	@Override
	public String toString() {

		String blockchain = "";
		for (Block block : this.blockChain) {
			blockchain += block.toString() + "\n";
		}
		return blockchain;
	}

	public Map<String, TransactionOutput> getUTXOS() {
		return UTXOS;
	}

	public void setUTXOS(Map<String, TransactionOutput> uTXOS) {
		UTXOS = uTXOS;
	}

	public List<Block> getBlockChain() {
		return blockChain;
	}

	public void setBlockChain(List<Block> blockChain) {
		this.blockChain = blockChain;
	}

}
