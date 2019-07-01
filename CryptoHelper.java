package crypto;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

// Java program to calculate SHA hash value 

public class CryptoHelper {

	public static String getSHA(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] messageDigest = digest.digest(input.getBytes());

			StringBuffer heaxadecimalString = new StringBuffer();

			for (int i = 0; i < messageDigest.length; i++) {
				String heaxa = Integer.toHexString(0xff & messageDigest[i]);

				if (heaxa.length() == 1)

					// System.out.println("testing-"+heaxa.toString());
					heaxadecimalString.append("0");
				// System.out.println("ddd-"+heaxa);

				heaxadecimalString.append(heaxa);
			}
			return heaxadecimalString.toString();
		} catch (Exception e) {

		}
		return input;

	}

	public static KeyPair ellipticCurveCrypt() {
		try {

			KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec params = new ECGenParameterSpec("secp192k1");
			keypairgenerator.initialize(params, secureRandom);
			KeyPair keypair = keypairgenerator.generateKeyPair();
			return keypair;
		}
		catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] applyECOSASignature(PrivateKey privateKey, String input) {
		Signature signature;
		byte[] output = new byte[0];

		try {
			signature = Signature.getInstance("ECDSA", "BC");
			signature.initSign(privateKey);

			byte[] strbyte = input.getBytes();
			signature.update(strbyte);
			byte[] realsignature = signature.sign();
			output = realsignature;

		} catch (Exception e) {
              throw new RuntimeException(e);
		}

		return output;

	}

	
	public static boolean verifyECDSASignature(PublicKey publicKey, String data, byte [] signature) {
		try {
			Signature ecdaSignature = Signature.getInstance("ECDSA","BC");
			ecdaSignature.initVerify(publicKey);
			ecdaSignature.update(data.getBytes());
			 return ecdaSignature.verify(signature);
			
		}catch (Exception e) {
			 throw new RuntimeException(e);
		}
	}
}