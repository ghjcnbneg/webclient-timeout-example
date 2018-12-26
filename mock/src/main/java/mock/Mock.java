package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Mock {

    private static final String APPLICATION_JSON_VALUE = "application/json";

    private static WireMockServer wireMockServer;

    public static void main(String[] args) throws IOException {

        wireMockServer = new WireMockServer(WireMockConfiguration.options().jettyAcceptors(5).port(6060));
        wireMockServer.start();

        wireMockServer.stubFor(get(urlPathEqualTo("/data"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", APPLICATION_JSON_VALUE)
//                        .withHeader("Content-Encoding", "UTF-8")
                        .withStatus(200)
                        .withBody(readJsonResource("service-check.json"))));
    }

    private static String readJsonResource(String fileName) throws IOException {
        return Resources.toString(Resources.getResource("json/" + fileName), Charsets.UTF_8);
    }
}
