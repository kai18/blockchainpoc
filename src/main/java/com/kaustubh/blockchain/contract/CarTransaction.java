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
public class CarTransaction {
	/*private static final String BINARY = "608060405234801561001057600080fd5b50610a48806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631ba728b01461005c5780638da5cb5b1461010f578063bed439bd14610166575b600080fd5b34801561006857600080fd5b5061010d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610298565b005b34801561011b57600080fd5b506101246105a9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561017257600080fd5b506101cd600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506105ce565b604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001848152602001838152602001828103825285818151815260200191508051906020019060200280838360005b83811015610280578082015181840152602081019050610265565b50505050905001965050505050505060405180910390f35b60006001826040518082805190602001908083835b6020831015156102d257805182526020820191506020810190506020830392506102ad565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902090508060030160008154809291906001019190505550848160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550838160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550828160040181905550806005018590806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050806001836040518082805190602001908083835b60208310151561044b5780518252602082019150602081019050602083039250610426565b6001836020036101000a0380198251168184511680821785525050505050509050019150509081526020016040518091039020600082018160000190805460018160011615610100020316600290046104a59291906108db565b506001820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506002820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160020160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506003820154816003015560048201548160040155600582018160050190805461059e929190610962565b509050505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060606000806001866040518082805190602001908083835b60208310151561060e57805182526020820191506020810190506020830392506105e9565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060010160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166001876040518082805190602001908083835b60208310151561069d5780518252602082019150602081019050602083039250610678565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060020160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166001886040518082805190602001908083835b60208310151561072c5780518252602082019150602081019050602083039250610707565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206005016001896040518082805190602001908083835b60208310151561079a5780518252602082019150602081019050602083039250610775565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206003015460018a6040518082805190602001908083835b60208310151561080957805182526020820191506020810190506020830392506107e4565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060040154828054806020026020016040519081016040528092919081815260200182805480156108c157602002820191906000526020600020905b8160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019060010190808311610877575b505050505092509450945094509450945091939590929450565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109145780548555610951565b8280016001018555821561095157600052602060002091601f016020900482015b82811115610950578254825591600101919060010190610935565b5b50905061095e91906109b4565b5090565b8280548282559060005260206000209081019282156109a35760005260206000209182015b828111156109a2578254825591600101919060010190610987565b5b5090506109b091906109d9565b5090565b6109d691905b808211156109d25760008160009055506001016109ba565b5090565b90565b610a1991905b80821115610a1557600081816101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055506001016109df565b5090565b905600a165627a7a723058205e8d44efbc3077a9b6b304935f8499cb896f73c3d8bbde4ed3511645e389d03e0029";

	public static final String FUNC_BUY = "buy";

	public static final String FUNC_OWNER = "owner";

	public static final String FUNC_GETCARINFO = "getCarInfo";

	protected CarTransaction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	protected CarTransaction(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public RemoteCall<TransactionReceipt> buy(String seller, String buyer, BigInteger price, String vin) {
		final Function function = new Function(FUNC_BUY,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(seller),
						new org.web3j.abi.datatypes.Address(buyer), new org.web3j.abi.datatypes.generated.Int256(price),
						new org.web3j.abi.datatypes.Utf8String(vin)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<String> owner() {
		final Function function = new Function(FUNC_OWNER, Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public RemoteCall<Tuple5<String, String, List<String>, BigInteger, BigInteger>> getCarInfo(String vin) {
		final Function function = new Function(FUNC_GETCARINFO,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(vin)),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}, new TypeReference<Address>() {
				}, new TypeReference<DynamicArray<Address>>() {
				}, new TypeReference<Int256>() {
				}, new TypeReference<Int256>() {
				}));
		return new RemoteCall<Tuple5<String, String, List<String>, BigInteger, BigInteger>>(
				new Callable<Tuple5<String, String, List<String>, BigInteger, BigInteger>>() {
					@Override
					public Tuple5<String, String, List<String>, BigInteger, BigInteger> call() throws Exception {
						List<Type> results = executeCallMultipleValueReturn(function);
						return new Tuple5<String, String, List<String>, BigInteger, BigInteger>(
								(String) results.get(0).getValue(), (String) results.get(1).getValue(),
								convertToNative((List<Address>) results.get(2).getValue()),
								(BigInteger) results.get(3).getValue(), (BigInteger) results.get(4).getValue());
					}
				});
	}

	public static RemoteCall<CarTransaction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return deployRemoteCall(CarTransaction.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
	}

	public static RemoteCall<CarTransaction> deploy(Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return deployRemoteCall(CarTransaction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
	}

	public static CarTransaction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return new CarTransaction(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static CarTransaction load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new CarTransaction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}*/
}
