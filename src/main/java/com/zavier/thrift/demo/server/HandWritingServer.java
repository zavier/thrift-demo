package com.zavier.thrift.demo.server;

import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransport;

public class HandWritingServer {

    public static void main(String[] args) throws Exception {
        TServerTransport serverTransport = new TServerSocket(12345);
        serverTransport.listen();
        final TTransport transport = serverTransport.accept();

        TProtocol protocol = new TBinaryProtocol(transport);

        while (true) {
            String name = "";
            final TMessage tMessage = protocol.readMessageBegin();
            if (tMessage.type == TMessageType.CALL) {
                protocol.readStructBegin();
                final TField tField = protocol.readFieldBegin();
                // name
                if (tField.id == 1) {
                    name = protocol.readString();
                }
                protocol.readFieldEnd();
                protocol.readStructEnd();
            }
            protocol.readMessageEnd();

            System.out.println("Read param name:" + name);

            // TODO: 实际业务计算，获取结果

            final TMessage respMessage = new TMessage("searchUsers", TMessageType.REPLY, tMessage.seqid);
            protocol.writeMessageBegin(respMessage);

            protocol.writeStructBegin(new TStruct("success"));
            protocol.writeFieldBegin(new TField("success", TType.STRUCT, (short) 0));

            protocol.writeStructBegin(new TStruct("searchResult"));
            final TField tField = new TField("users", TType.LIST, (short) 1);
            protocol.writeFieldBegin(tField);
            protocol.writeListBegin(new TList(TType.STRUCT, 2));

            protocol.writeStructBegin(new TStruct("user"));
            protocol.writeFieldBegin(new TField("name", TType.STRING, (short) 1));
            protocol.writeString("zhangsan1");
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(new TField("age", TType.I32, (short) 2));
            protocol.writeI32(18);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();

            protocol.writeStructBegin(new TStruct("user"));
            protocol.writeFieldBegin(new TField("name", TType.STRING, (short) 1));
            protocol.writeString("lisi1");
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(new TField("age", TType.I32, (short) 2));
            protocol.writeI32(19);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();

            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();

            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();

            protocol.writeMessageEnd();

            protocol.getTransport().flush();
        }
    }

}
