import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.persistence.Entity;

@Controller
class HelloController {

  @RequestMapping("/updateOrder") 
  public String updateOrder(Order order) { // Noncompliant {{You must avoid connect request parameters with your model}}
    return null;
  }
  
  @PostMapping("/updateOrder")  
  public String updateOrderPost(Order order) { // Noncompliant {{You must avoid connect request parameters with your model}}
	  return null;
  }
  
  @GetMapping("/updateOrder")  
  public String updateOrderGet(Order order) { // Noncompliant {{You must avoid connect request parameters with your model}}
	  return null;
  }
  
  @PutMapping("/updateOrder")  
  public String updateOrderPut(Order order) { // Noncompliant {{You must avoid connect request parameters with your model}}
	  return null;
  }
  
  @DeleteMapping("/updateOrder")  
  public String updateOrderDelete(Order order) { // Noncompliant {{You must avoid connect request parameters with your model}}
	  return null;
  }

  @Entity
  public class Order {
    String ordered;
  }
}
