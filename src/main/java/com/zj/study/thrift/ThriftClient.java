package com.zj.study.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

	public static void main(String[] args) throws DataException, TException {
		TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
		TProtocol protocol = new TCompactProtocol(transport);
		PersonService.Client client = new PersonService.Client(protocol);

		try {
			transport.open();

			Person person = client.getPersonByUsername("zj");

			System.out.println(person.getUsername());
			System.out.println(person.getAge());
			System.out.println(person.isMarried());

			System.out.println("-----------------------");
			Person person2 = new Person();
			person2.setUsername("ws");
			person2.setAge(40);
			person2.setMarried(true);

			client.savePerson(person2);
		} finally {
			transport.close();
		}
	}

}
