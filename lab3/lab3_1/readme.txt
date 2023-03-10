José Luca Baptsita Pereira 97689


a)


AssertJ expressive method chaining é usado por exemplo no ficheiro "D_EmployeeRestControllerT.java":

1)
	 mvc.perform(get("/api/employees").contentType(MediaType.APPLICATION_JSON))
		        .andDo(print())
		        .andExpect(status().isOk())
		        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
		        .andExpect(jsonPath("$[0].name", is("bob")))
		        .andExpect(jsonPath("$[1].name", is("alex")));

2)
	 assertThat(found).extracting(Employee::getName).containsOnly("bob");

 e no ficheiro "E_EmployeeRestControllerT":
 1)
	 assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
 
 b)

No ficheiro "B_EmployeeService_UnitTest.java" , é feito um mock do repositorio através da anotação @Mock da framewok mockito de maneira a criar um mock de um repositorio sem ter de realmente ter a base 
de dados. Assim permitindo  fazer testes unitários

@Mock( lenient = true)
    private EmployeeRepository employeeRepository;
 
 
 
c)
 A principal e mais importante diferença entre os dois, é que @Mock é usado para testes unitários e @MockBean é usado para testes integrados.

@Mock é usada na framework mockito e usa-se para criar um mock de uma classe ou interface, assim permite-nos isolar partes de uma dada aplicação ou serviço e testa-las 
separadamente. Por exemplo na pergunta 2 é usado para criar um mock do repositório.

@MOckBEan pertence á framework Spring Boot Test e é usada para dar mock a um objecto de um Spring Bean. Assim o mock vai substituir um bean existente no contexto da aplicação/serviço.

d)
Este ficheiro é o ficheiro de configuração para os testes de integração do Spring Boot.
O Spring Boot usa o ficheiro application.proprities para configurar a aplicação. Porem quando usamos o Spting Boot Test podemos usar o ficheiro application-integrationtest.propriteis para dar override á confguração default apenas para os testes.

e)




