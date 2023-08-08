package com.hanul.broovebackdev;

import com.klaytn.caver.Caver;
import com.klaytn.caver.contract.Contract;
import com.klaytn.caver.contract.SendOptions;
import com.klaytn.caver.kct.kip17.KIP17;
import com.klaytn.caver.methods.response.TransactionReceipt;
import com.klaytn.caver.wallet.keyring.SingleKeyring;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.exceptions.TransactionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Map;

@SpringBootTest
@Slf4j
class caverTest {

    private final String IKIP17 = "0x80ac58cd";
    private final String IKIP17TokenReceiver = "0x6745782b";
    private final String IERC721TokenReceiver = "0x150b7a02";
    private final String IKIP17Metadata = "0x5b5e139f";
    private final String IKIP17Enumerable = "0x780e9d63";
    private final String IKIP17Mintable = "0xeab83e20";
    private final String IKIP17MetadataMintable = "0xfac27f46";
    private final String IKIP17Burnable = "0x42966c68";
    private final String IKIP17Pausable = "0x4d5507ff";

    private final String contractAddress = "0x4c70d907F17280e888Ef295683a0821713D8e89d";

    @Test
    void contextLoads() throws IOException, CipherException, TransactionException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Caver caver = new Caver("https://api.baobab.klaytn.net:8651");
        KIP17 contract = caver.kct.kip17.create(contractAddress);


        String name = contract.name();
        log.info(name);

        BigInteger totalSupply = contract.totalSupply();
        log.info(totalSupply.toString());

        log.info("IKIP17 = {}", contract.supportInterface(IKIP17));
        log.info("IKIP17TokenReceiver = {}", contract.supportInterface(IKIP17TokenReceiver));
        log.info("IERC721TokenReceiver = {}", contract.supportInterface(IERC721TokenReceiver));
        log.info("IKIP17Metadata = {}", contract.supportInterface(IKIP17Metadata));
        log.info("IKIP17Enumerable = {}", contract.supportInterface(IKIP17Enumerable));
        log.info("IKIP17Mintable = {}", contract.supportInterface(IKIP17Mintable));
        log.info("IKIP17MetadataMintable = {}", contract.supportInterface(IKIP17MetadataMintable));
        log.info("IKIP17Burnable = {}", contract.supportInterface(IKIP17Burnable));
        log.info("IKIP17Pausable = {}", contract.supportInterface(IKIP17Pausable));
    }

    @Test
    void tokenUpload() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, TransactionException {
        Caver caver = new Caver("https://api.baobab.klaytn.net:8651");
        KIP17 contract = caver.kct.kip17.create(contractAddress);

        SingleKeyring keyring = caver.wallet.keyring.create("0xa78eeefb586819964020f8cf2233573f7538b074", "0x55acb8784dd771cad048aeb36c641f78d8216b8f98d8538a7ea76812cdbbfc6a");
        caver.wallet.add(keyring);
//        contract.setWallet(caver.wallet);

        SendOptions sendOptions = new SendOptions();
        sendOptions.setGas(BigInteger.valueOf(3000000L));
        sendOptions.setValue(BigInteger.valueOf(3000L));
        sendOptions.setFrom("0xa78eeefb586819964020f8cf2233573f7538b074");

        TransactionReceipt.TransactionReceiptData transactionReceiptData = contract.mintWithTokenURI(
                "0x74628a5b757e61578184250a51001ba65a7dd647",
                BigInteger.ONE,
                "ipfs://QmWS3X8ifeDFo1LcfJniKrKnHH34EBV1aqBCvoiyx6hCx6",
                sendOptions
        );
        log.info("getStatus = {}", transactionReceiptData.getStatus());
        log.info("err={}", transactionReceiptData.getTxError());
    }

    @Test
    void contractTest() throws IOException {
        Caver caver = new Caver("https://api.baobab.klaytn.net:8651");
    }
}

