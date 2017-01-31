
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
}
