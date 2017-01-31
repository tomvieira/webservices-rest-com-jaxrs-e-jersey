package br.com.alura.loja.resources;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tom
 */
@Path("carrinhos")
public class CarrinhoResource {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String busca(){
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        Carrinho carrinho = carrinhoDAO.busca(1L);
        return carrinho.toXML();
    }
}
