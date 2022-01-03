| METHOD 	| ENDPOINT 	| PAYLOAD 	| QUERY 	| RESPONSE 	| ACTION 	|
| :-------:	| :-----------------------:	|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|:-----------------------------------------------------------------------------------:	|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------:	|
| GET 	| /api/v1/articles 	| --- 	| --- 	| [ { "productId": 1, "name": "Serra de Bancada", "category": "Ferramentas", "brand": "FORTGPRO", "price": 1800, "quantity": 9, "freeShipping": true, "prestige": "****" } ] 	| Lista todos os produtos cadastrados 	|
| GET 	| /api/v1/articles? 	| --- 	| product=productName brand=brandName category=categoryName order=0 freeShipping=true 	| 	| Filtra e ordena produtos de acordo com rota query Parâmetro oder aceita valores de 0 a 3 freeShipping é Boolean 	 |
| POST 	| /ap1/v1/purchase-request 	| { "articlesPurchaseRequest": [ { "productId": 1, "name": "Serra de Bancada", "category": "Ferramentas", "brand": "FORTGPRO", "price": 1800, "quantity": 5, "freeShipping": true, "prestige": "****" }, { "productId": 2, "name": "Furadeira", "brand": "Black & Decker", "quantity": 5 } ] }	| --- 	| { "ticket": { "articles": [ { "productId": 1, "name": "Serra de Bancada", "category": "Ferramentas", "brand": "FORTGPRO", "price": 1800, "quantity": 5, "freeShipping": true, "prestige": "****" }, { "productId": 2, "name": "Furadeira", "category": "Ferramentas", "brand": "Black & Decker", "price": 500, "quantity": 5, "freeShipping": true, "prestige": "****" } ], "total": 11500, "id": 530 } } 	| Realiza a solicitação de uma compra e armazena os produtos no carrinho, que é retornado na Response. 	|
| GET 	| /api/v1/users 	| --- 	| --- 	| { "name": "Alien en Ated", "email": "alienenated@spam.com", "estado":"RO" "status": "INACTIVE" } 	| Lista todos os clientes cadastrados 	|
| GET 	| /api/v1/user? 	| --- 	| email=alienenated@spam.com 	| { "name": "Alien en Ated", "email": "alienenated@spam.com", "estado": "RO", "password": password, "id": "28f8096d-3372-4d16-b92d-debf2ca72ad0", "status": "INACTIVE" } 	| Lista o cliente cadastrado por email 	|
| POST 	| /api/v1/users/register 	| { "name": "Alien en Ated", "email": "alienenated@spam.com", "estado":"RO" } 	| --- 	| { "name": "Alien en Ated", "email": "alienenated@spam.com", "estado":"RO" "status": "ACTIVE" } 	| Cadastra novo cliente com os parâmetros passados em payload. Todo novo usuário recebe nome e id (uuid) imutáveis e Status como ativo 	|
| PATCH 	| /api/v1/user/attributes? 	| --- 	| email=alienenated@spam.com emailChange=alien@spam.com password=password 	| { "name": "Alien en Ated", "email": "alien@spam.com", "estado": "RO", "status": "INACTIVE" } 	| Altera parâmetros de cadastro para o cliente com email 'email' apontado por query Parâmetro obrigatório: email Parâmetro opcional: emailChange, password (requer ao menos um) 	|
