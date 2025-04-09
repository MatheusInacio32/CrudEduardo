
## Endpoints

- **Cadastrar Personagem**  
  `POST /personagens`  
  (nome, nomeAventureiro, classe, level, forca, defesa).  
  *Regras:* Soma de força e defesa deve ser ≤ 10 pontos.

- **Listar Personagens**  
  `GET /personagens`  
  
- **Buscar Personagem por ID**  
  `GET /personagens/{id}`  
 
- **Atualizar Personagem**  
  `PUT /personagens/{id}`  

- **Remover Personagem**  
  `DELETE /personagens/{id}`  

### Itens Mágicos

- **Listar Itens Mágicos**  
  `GET /itens`  

- **Buscar Item Mágico por ID**  
  `GET /itens/{id}`  

- **Cadastrar Item Mágico**  
  `POST /itens`  
  (nome, tipo, forca, defesa).  

- **Adicionar Item Mágico a um Personagem**  
  `POST /personagens/{id}/itens`  

- **Listar Itens Mágicos de um Personagem**  
  `GET /personagens/{id}/itens`  

- **Remover Item Mágico de um Personagem**  
  `DELETE /personagens/{id}/itens/{itemId}`  

- **Buscar Amuleto do Personagem**  
  `GET /personagens/{id}/amuleto`  
