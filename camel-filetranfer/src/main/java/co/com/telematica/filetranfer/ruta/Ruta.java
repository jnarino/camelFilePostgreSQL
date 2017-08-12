package co.com.telematica.filetranfer.ruta;

import org.apache.camel.builder.RouteBuilder;

public class Ruta extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("timer:test?repeatCount=3").setBody(simple("hola")).log(" Esta recibiendo  ${body}").end();
	}

}
