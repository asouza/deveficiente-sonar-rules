import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.persistence.Entity;

@Controller
class DoNotChangeParametersReferenceRuleExample {

  @RequestMapping("/updateOrder") 
  public String updateOrder(Order order) { 
	order.setOrdered("bla"); // Noncompliant {{Do not change parameter state}}
    return null;
  }
 

  public class Order {
    String ordered;
    
    public void setOrdered(String value) {
    	this.ordered = value;
    }
  }
}
