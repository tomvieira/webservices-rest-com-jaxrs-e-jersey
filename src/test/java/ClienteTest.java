
import br.com.alura.loja.Servidor;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author tom
 */
public class ClienteTest {

    @Test
    public void testaQueAConexaoComOServidorFunciona() {
        Client client = ClientBuilder.newClient();
        URI uri = URI.create("http://www.mocky.io");
        WebTarget target = client.target(uri);
        String resposta = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
        System.out.println(resposta);
        Assert.assertTrue(resposta.contains("<rua>Rua Vergueiro 3185"));
    }

    @Test
    public void testaProjetosResource() {
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        URI uri = URI.create("http://localhost:65006");
        HttpServer servidor = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        System.out.println("Servidor rodando");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);
        String resposta = target.path("/projetos").request().get(String.class);
        System.out.println(resposta);
        Assert.assertTrue(resposta.contains("<anoDeInicio>2014"));
        servidor.stop();
    }
}
