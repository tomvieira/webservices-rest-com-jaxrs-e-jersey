package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


/**
 *
 * @author tom
 */
public class Servidor {
    public static void main(String[] args) {
        try {
            HttpServer servidor = startaServidor();
            System.in.read();
            servidor.stop();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Falha", ex);
        }
    }

    public static HttpServer startaServidor() {
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        URI uri = URI.create("http://localhost:65006");
        HttpServer servidor = GrizzlyHttpServerFactory.createHttpServer(uri,config);
        System.out.println("Servidor rodando");
        return servidor;
    }
}
