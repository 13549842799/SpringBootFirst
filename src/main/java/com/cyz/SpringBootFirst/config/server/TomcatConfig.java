package com.cyz.SpringBootFirst.config.server;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>{

	/**
	 * 定制tomcat
	 */
	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		System.out.println("进入定制工厂");
		factory.setPort(8085);
	/*	factory.setUriEncoding(Charset.forName("UTF8"));*/
	}

	/**
	 * 增加一个新的自定义容器
	 * @return
	 */
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createSslConnector());
		System.out.println("进入容器");
		return tomcat;
	}

	private Connector createSslConnector() {
		System.out.println("创建连接");
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		//try {
			/*File keystore = new ClassPathResource("keystore").getFile();
			File truststore = new ClassPathResource("keystore").getFile();*/
			//connector.setScheme("https");
			//connector.setSecure(true);
			connector.setPort(8443);
			//protocol.setSSLEnabled(true);
			//protocol.setKeystoreFile(keystore.getAbsolutePath());
			//protocol.setKeystorePass("changeit");
			//protocol.setTruststoreFile(truststore.getAbsolutePath());
			//protocol.setTruststorePass("changeit");
			//protocol.setKeyAlias("apitester");
			return connector;
		//}
		/*catch (IOException ex) {
			throw new IllegalStateException("can't access keystore: [" + "keystore"
					+ "] or truststore: [" + "keystore" + "]", ex);
		}*/
	}
}
