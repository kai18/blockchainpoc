package com.kaustubh.blockchain.contract;

/**
 * <p>
 * Auto generated code.
 * <p>
 * <strong>Do not modify!</strong>
 * <p>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or
 * the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen
 * module</a> to update.
 *
 * <p>
 * Generated with web3j version 3.4.0.
 */
public class Deliverables {
	/*private static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506103fa806100606000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680638da5cb5b146100675780639e39db73146100be578063d659481614610101578063dea9446c14610144575b600080fd5b34801561007357600080fd5b5061007c61019b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100ca57600080fd5b506100ff600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506101c0565b005b34801561010d57600080fd5b50610142600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102dd565b005b34801561015057600080fd5b50610185600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610382565b6040518082815260200191505060405180910390f35b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561021b57600080fd5b60408051908101604052808273ffffffffffffffffffffffffffffffffffffffff1681526020016000815250600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015590505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561033857600080fd5b60018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001018190555050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001015490509190505600a165627a7a72305820815bfc526d2e5004229a0c2ef2729192834c90c4147c2945a67d87ccebaff7e50029";

	public static final String FUNC_OWNER = "owner";

	public static final String FUNC_STORE = "store";

	public static final String FUNC_DELIVERED = "delivered";

	public static final String FUNC_STATUSFOR = "statusFor";

	protected Deliverables(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	protected Deliverables(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public RemoteCall<String> owner() {
		final Function function = new Function(FUNC_OWNER, Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<TransactionReceipt> store(String id) {
		final Function function = new Function(FUNC_STORE, Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> delivered(String id) {
		final Function function = new Function(FUNC_DELIVERED,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<BigInteger> statusFor(String id) {
		final Function function = new Function(FUNC_STATUSFOR,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public static RemoteCall<Deliverables> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return deployRemoteCall(Deliverables.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
	}

	public static RemoteCall<Deliverables> deploy(Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return deployRemoteCall(Deliverables.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
	}

	public static Deliverables load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return new Deliverables(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static Deliverables load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new Deliverables(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}*/
}
