/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import com.sun.mail.imap.ACL;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author jvale
 */
@Path("producto")
public class ProductoResource {

    @Context
    private UriInfo context;
    List<Producto> productos = new ArrayList<>();
    

    /**
     * Creates a new instance of Producto
     */
    public ProductoResource() {
        
    }

    /**
     * Retrieves representation of an instance of ws.Producto
     *
     * @return an instance of entidades.Producto
     */
    @GET
    @Path("/all")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getJson() {
        productos.add(new Producto(1, "Producto1"));
        productos.add(new Producto(2, "Producto2"));
        Producto[] productosArray;

        productosArray = productos.toArray(Producto[]::new);

        return Response.status(Response.Status.OK).entity(productosArray).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id) {
        productos.add(new Producto(1, "Producto1"));
        productos.add(new Producto(2, "Producto2"));
        Producto p1 = null;
        for (int i = 0; i < productos.size(); i++) {

            int idc = Integer.parseInt(id);

            if (productos.get(i).getId() == idc) {
                p1 = productos.get(i);
                return Response.status(Response.Status.OK).entity(p1).build();
            }else{
                return Response.status(Response.Status.OK).entity("No hay producto con ese id").build();
            }

        }
        return Response.status(Response.Status.OK).entity("No hay producto con ese id").build();
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProducto(Producto productoNuevo) {
        productos.add(new Producto(1, "Producto1"));
        productos.add(new Producto(2, "Producto2"));
        productos.add(productoNuevo);
        
        Producto p1 = productoNuevo;
        Producto[] productosArray;

        productosArray = productos.toArray(Producto[]::new);

        return Response.status(Response.Status.OK).entity(productosArray).build();
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProducto(Producto producto){
        Producto p1 = new Producto();
        p1.setId(1);
        p1.setNobmre("Algo");
        productos.add(p1);

        
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getId()==producto.getId()){
                productos.remove(productos.get(i));
                return Response.status(200).entity(producto).build();
            }
        }
        
        Producto[] productosArray;

        productosArray = productos.toArray(Producto[]::new);

        return Response.status(Response.Status.OK).entity(productosArray).build();
    }

    /**
     * PUT method for updating or creating an instance of Producto
     *
     * @param content representation for the resource
     * @return 
     */
    @PUT
    @Path("/put")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response putJson(Producto content) {
        Producto p1 = new Producto();
        p1.setId(1);
        p1.setNobmre("Algo");
        productos.add(p1);
        productos.add(new Producto(2, "Producto2"));
        
        for (int i = 0; i < productos.size(); i++) {
            if(productos.get(i).getId()==content.getId()){
                productos.get(i).setId(content.getId());
                productos.get(i).setNobmre(content.getNobmre());
                return Response.status(200).entity(productos.get(i)).build();
            }
        }
        
        return Response.status(Response.Status.OK).entity("No existe el producto").build();
    }
}
