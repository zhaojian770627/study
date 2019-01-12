package com.zj.study.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.THsHaServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.zj.study.thrift.PersonService.Processor;

public class ThriftServer {

	public static void main(String[] args) throws TTransportException {
		TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
		THsHaServer.Args arg = new Args(socket).workerThreads(3);
		PersonService.Processor<PersonServiceImpl> processor = new Processor<PersonServiceImpl>(
				new PersonServiceImpl());
		arg.protocolFactory(new TCompactProtocol.Factory());
		arg.transportFactory(new TFramedTransport.Factory());
		arg.processorFactory(new TProcessorFactory(processor));

		TServer server = new THsHaServer(arg);
		System.out.println("Thrift Server started!");
		server.serve();
	}

}
