import org.springframework.stereotype.Service;

@Service
class Example1 { // Compliant 
	
	T1 t1;
	
	public void execute() {
		t1.method();
	}	
  
}

@Service
class Example2 { // Compliant 
		
	public void execute() {
		new T1().method();
		new T1().method();
	}	
  
}

@Service 
class Example3 { // Noncompliant
	
	T1 t1;
	T2 t2;
		
	public void execute() {
		t1.method();		
	}	
  
}

@Service
class Example4 { // Noncompliant
	
	T1 t1;
		
	public void execute() {		
	}	
  
}

@Service
class Example5 { // Compliant
	
	T1 t1;
	T2 t2;
		
	public void execute() {
		t1.method();
		t2.method();
	}	
  
}


class T1 {
	public void method() {
		
	}
}

class T2 {
	public void method() {
		
	}
}
