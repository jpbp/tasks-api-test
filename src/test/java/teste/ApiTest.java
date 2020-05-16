package teste;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {
	//base da uri já definida
	@BeforeClass
	public static void setup(){
		RestAssured.baseURI="http://localhost:8001/tasks-backend";
	}
	//verificando todasas tasks
	@Test
	public void deveRetornaTarefas() {
		RestAssured.given().when().get("/todo").then().statusCode(200);
	}
	
	//adicionando uma nova task com sucesso
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given().body("{\"task\":\"Teste via api\",\"dueDate\":\"2030-10-10\"}").contentType(ContentType.JSON).when().post("/todo").then().statusCode(201);
	}
	//verificando se ele não vai adicionar uma data passada, mandando uma data passada, esperando uma bad request
	@Test
	public void NãodeveAdicionarTarefaInvalida() {
		RestAssured.given().body("{\"task\":\"Teste via api\",\"dueDate\":\"2010-10-10\"}").contentType(ContentType.JSON).when().post("/todo").then().statusCode(400);
	}
}
