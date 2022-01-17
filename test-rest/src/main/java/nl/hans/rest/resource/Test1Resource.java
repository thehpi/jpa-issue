package nl.hans.rest.resource;

import nl.hans.entities.test.Bar;
import nl.hans.entities.test.Foo;
import nl.hans.service1.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class Test1Resource {

  @Inject
  Service service;

  @Path("{id}")
  @GET
  public Foo get(@PathParam("id") String id) {
    return service.find(Foo.class, id);
  }

  @Path("{test}")
  @POST
  public Foo post(@PathParam("test") String test) {
    Foo foo = new Foo();
    foo.setId1(UUID.randomUUID().toString());
    foo.setId2(UUID.randomUUID().toString());
    foo.setTest1(test + " 1");
    foo.setTest2(test + " 2");

    Bar bar = new Bar();
    foo.setBar(bar);
    bar.setBartest1("bar test 1");
    bar.setBartest2("bar test 2");

    service.persist(foo);

    return foo;
  }
}
