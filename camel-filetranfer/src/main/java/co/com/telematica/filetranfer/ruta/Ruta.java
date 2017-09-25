package co.com.telematica.filetranfer.ruta;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class Ruta extends RouteBuilder {

	public int i = 4;

	@Override
	public void configure() throws Exception {

		from("file:{{directorio.in}}?noop=true&move=.done").process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {

				System.out.println("Entra a tomar nombres");

				String fileName = exchange.getIn().getHeader("CamelFileName").toString();
				String fileLength = exchange.getIn().getHeader("CamelFileLength").toString();
				String fileModified = exchange.getIn().getHeader("CamelFileLastModified").toString();
				System.out.println(fileName + "-" + fileLength + "-" + fileModified);
				exchange.getIn().setHeader("ids", i);
				i += 1;

			}
		}).log("El archivo con nombre ${header.CamelFileName} ha sido tranferido").to("sqlPostgres:{{insert.register}}")
				.log("${body}").to("file:{{directorio.out}}").end();

		// from("timer:pepito?repeatCount=1")
	}

}
