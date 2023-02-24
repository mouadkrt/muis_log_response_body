package ma.munisys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@SpringBootApplication
public class Application extends RouteBuilder {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure() {

        from("netty4-http:proxy://0.0.0.0:8089")
            .routeId("muis_log_response_body:muis_route1")
            .toD("netty4-http:"
                + "${headers." + Exchange.HTTP_SCHEME + "}://"
                + "${headers." + Exchange.HTTP_HOST + "}:"
                + "${headers." + Exchange.HTTP_PORT + "}"
                + "${headers." + Exchange.HTTP_PATH + "}")
            .log(LoggingLevel.INFO, "muis_log_response_body : Backend response in.headers: \n${in.headers}")
            .log(LoggingLevel.INFO, "muis_log_response_body : Backend response body: \n${body}");
    }
}