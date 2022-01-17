package nl.hans.rest.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

@RequestScoped
@Path("test")
public class RootResource {
  @Inject
  Test1Resource test1Resource;

  @Inject
  Test2Resource test2Resource;

  /* sub resources */

  @Path("1")
  public Test1Resource test1() {
    return this.test1Resource;
  }

  @Path("2")
  public Test2Resource test2() {
    return test2Resource;
  }

}
