import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import org.springframework.validation.BindingResult; 

@Controller 
class SystemSpecificClassCouplingExample { // Compliant 
	
  private OrderRepository orderRepository;
  private LogRepository logRepository;
  private Set<String> names = new HashSet<>();

  @RequestMapping("/updateOrder") 
  public String updateOrder(Order order,BindingResult result) { 
	List<Integer> numbers = new ArrayList<>();
    return null;
  }
  
  
  public class OrderRepository {
	  
  }
  
  
  public class LogRepository {
	  
  }

  public class Order {
    String ordered;   
    
    public Order(Order delegate) {    
    }
    
    public void setOrdered(String value) {
    	this.ordered = value;
    }
  }
}
