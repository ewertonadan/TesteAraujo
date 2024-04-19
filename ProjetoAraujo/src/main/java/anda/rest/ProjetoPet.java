package anda.rest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matcher;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;



public class ProjetoPet {

	// 1. Cadastrar Novo Pedido de Pet com Sucesso (POST /store/order)

	@Test
	public void cadastrarNovoPedidoPet() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		given().contentType("application/json")
				.body("{ \"id\": 1, \"petId\": 1, \"quantity\": 1, \"status\": \"placed\", \"complete\": true }").when()
				.post("/store/order").then().statusCode(200);
	}

//2. Pesquisar por um Pet Inexistente (GET /pet/{petId})

	@Test
	public void pesquisarPetInexistente() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		given().when().get("/pet/0").then().statusCode(404); // Supondo que 404 é o status para pet não encontrado

	}



//3. Atualizar Dados de um Pet Existente (PUT /pet)

	@Test
	public void atualizarDadosPetExistente() {
    RestAssured.baseURI = "https://petstore.swagger.io/v2";
    given().contentType("application/json")
           .body("{ \"id\": 1, \"name\": \"Rex\", \"status\": \"available\" }")
           .when().put("/pet")
           .then().statusCode(200);
}
//	4. Pesquisar por Pets com Status “Pending” (GET /pet/findByStatus)

	@Test
	public void pesquisarPetsStatusPending() {
	    RestAssured.baseURI = "https://petstore.swagger.io/v2";
	    given().queryParam("status", "pending")
	           .when().get("/pet/findByStatus")
	           .then().statusCode(200)
	           .and().body("status", everyItem(equals("pending")));
	}



}