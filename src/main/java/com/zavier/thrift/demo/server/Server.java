package com.zavier.thrift.demo.server;

import com.zavier.thrift.demo.UserService;
import com.zavier.thrift.demo.UserServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Server {
    public static void main(String[] args) throws Exception {
        TServerTransport serverTransport = new TServerSocket(12345);

        UserService.Processor<UserServiceImpl> processor = new UserService.Processor<>(new UserServiceImpl());
        final TServer.Args serverArgs = new TServer.Args(serverTransport).processor(processor);

        TServer server = new TSimpleServer(serverArgs);
        System.out.println("Starting the simple server...");
        server.serve();
    }
}
