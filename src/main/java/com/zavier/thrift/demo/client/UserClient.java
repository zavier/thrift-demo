package com.zavier.thrift.demo.client;

import com.zavier.thrift.demo.UserSearchResult;
import com.zavier.thrift.demo.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class UserClient {

    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("localhost", 12345);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);

        UserSearchResult userRes = client.searchUsers("zhangsan");
        System.out.println(userRes);

        transport.close();
    }
}
