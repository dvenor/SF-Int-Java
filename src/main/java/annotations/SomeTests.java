package annotations;

//@RunMe
public class SomeTests {
//  @RunMe
  String data = "hello";

  @RunMe(name="Fred", value=3)
  public void tryThis() {
    System.out.println("running test tryThis");
  }

//  @RunMe(name="Jim")
//  @RunMe()
//  @RunMe(value=3)
  @RunMe(3)
  private void tryThat() {
    System.out.println("Running test tryThat");
  }

  public void dontTryTheOther() {
    System.out.println("oops, not supposed to run this");
  }
}
