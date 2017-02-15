
import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import com.thoughtworks.xstream.XStream;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.logging.LoggingFeature;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tom
 */
public class ClienteTest {

    private HttpServer server;
    private Client client;

    @Before
    public void startaServidor() {
        server = Servidor.startaServidor();
        ClientConfig config = new ClientConfig();
        config.register(LoggingFeature.class);
        this.client = ClientBuilder.newClient(config);
    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
        //ativando loggind do jersey usando config
        WebTarget target = client.target("http://localhost:65006");
        String conteudo = target.path("/carrinhos/1").request().get(String.class);
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
    }

    @Test
    public void testaProjetosResource() {

        URI uri = URI.create("http://localhost:65006");        
        WebTarget target = client.target(uri);
        String resposta = target.path("/projetos/1").request().get(String.class);
        System.out.println(resposta);
        Assert.assertTrue(resposta.contains("<anoDeInicio>2014"));
    }

    @Test
    public void adicionarCarrinho() {
        URI uri = URI.create("http://localhost:65006");
        WebTarget target = client.target(uri);
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
        Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
        String location = response.getHeaderString("Location");
        String conteudo = client.target(location).request().get(String.class);
        Assert.assertTrue(conteudo.contains("Tablet"));
    }

    @After
    public void mataServidor() {
        server.stop();
    }

}
