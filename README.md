# xy-inc
Projeto XY-Inc. A API está em um servidor da Heroku, portanto a primeira requisição pode ser um pouco mais demorada. 
A seguir estão os passos para acessar os end-points da API, utilizando-se de algum cliente HTTP como o Postman.

ENDPOINTS:
- GET "https://xy-inc-thales.herokuapp.com/pontos": retornará todos os Pontos de Interesse cadastrados.

- GET "https://xy-inc-thales.herokuapp.com/pontos/proximos?x=20&y=10&d=10": retornará todos os Pontos de Interesse de acordo com os parâmetros passados na url. 
  No exemplo, é passado 20 para x e 10 para y e d.

- POST "https://xy-inc-thales.herokuapp.com/pontos": retornará código de resposta 201 caso POI seja cadastrado. Não é possível inserir POI com nome menor de 5 caracteres ou já cadastrado, nem com coordenadas negativas. 
  Também é necessário passar os atributos nome, x e y em JSON no body na requisição, como no modelo a seguir:
	Modelo body da requisição: 
		{
		 "nome": "Barbearia",
		 "x": "13",
		 "y": "8"
		}

- GET "https://xy-inc-thales.herokuapp.com/pontos/{id}": retornará o Ponto de Interesse cujo id seja o passado na url da requisição. 
Caso não haja esse id cadastrado, é retornado resposta 404.
