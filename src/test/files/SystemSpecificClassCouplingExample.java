import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import org.springframework.validation.BindingResult; 

class Example1 { // Noncompliant 
	
	T1 a1;    
	T2 a2;    
	T3 a3;    
	T4 a4;    
	T5 a5;
	T6 a6;
	T7 a7;
	T8 a8;
	T9 a9;
	T10 a10;	
  
}

class Example2 { // Compliant 
	
	T1 a1;    
	T2 a2;    
	T3 a3;    
	T4 a4;    
	T5 a5;
	T6 a6;
	T7 a7;
	T8 a8;
	T9 a9;	
  
}

class ExampleWithJavaRuntimeTypes { // Compliant 
	
	T1 a1;    
	T2 a2;    
	T3 a3;    
	T4 a4;    
	T5 a5;
	T6 a6;
	T7 a7;
	T8 a8;
	T9 a9;	
	Set<String> a10;
	List<String> a11;
	HashSet<String> a12;
  
}

class ExampleWithJavaRuntimeTypesAndStringType { // Compliant 
	
	T1 a1;    
	T2 a2;    
	T3 a3;    
	T4 a4;    
	T5 a5;
	T6 a6;
	T7 a7;
	T8 a8;
	T9 a9;	
	Set<String> a10;
	List<String> a11;
	HashSet<String> a12;
	BindingResult result;
  
}

class SpringControllerExampleWithJavaRuntimeTypes { // Compliant 
	
	T1 a1;    
	T2 a2;    
	T3 a3;    
	T4 a4;    
	T5 a5;
	T6 a6;
	T7 a7;
	T8 a8;
	T9 a9;	
	Set<String> a10;
	List<String> a11;
	HashSet<String> a12;	
	
	public void test(BindingResult result) {
		
	}
  
}

class SpringControllerExampleWithJavaRuntimeTypesWithSystemTypeVariable { // Noncompliant 
	
	T1 a1;    
	T2 a2;    
	T3 a3;    
	T4 a4;    
	T5 a5;
	T6 a6;
	T7 a7;
	T8 a8;
	T9 a9;	
	Set<String> a10;
	List<String> a11;
	HashSet<String> a12;	
	
	public void test(BindingResult result) {
		T10 t10;
	}
  
}
