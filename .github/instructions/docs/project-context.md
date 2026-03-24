# 1. Nome do Projeto
Lista de Remedios

# 2. Resumo do Projeto / Visao Geral
Aplicacao para controle domestico de remedios, com foco em acompanhar estoque real com base no tratamento prescrito.
O sistema registra compras, consumo diario e sobras para apoiar a proxima decisao de compra.

# 3. Problema(s) que o Produto Resolve
- Falta de visibilidade sobre quantos comprimidos ainda existem em casa.
- Compras desnecessarias por nao considerar sobras de tratamentos anteriores.
- Dificuldade em planejar reposicao no momento certo (antes de faltar e sem excesso).
- Risco de desperdicio por vencimento de medicamentos estocados sem controle.

# 4. Publico-alvo
- Pessoas e familias que fazem tratamentos recorrentes em casa.
- Pacientes com uso continuo de medicamentos.
- Cuidadores que acompanham tratamento de terceiros.

# 5. Objetivos do Produto
- Permitir controle confiavel de estoque por remedio e por tratamento.
- Ajudar a planejar compras considerando sobras, dose e duracao do tratamento.
- Reduzir desperdicio e evitar compras acima do necessario.
- Melhorar previsibilidade de consumo e reposicao.

# 6. Funcionalidades Principais (Core Features)
- Cadastro de remedios (nome, principio ativo, concentracao, apresentacao, fabricante opcional).
- Registro de compra (quantidade de caixas, comprimidos por caixa, data da compra, validade opcional).
- Cadastro de tratamento (dose por dia, frequencia, data de inicio, duracao prevista).
- Calculo automatico de consumo total previsto e saldo final esperado.
- Controle de estoque atual por remedio (entrada, consumo, sobra).
- Recomendacao de compra com base no saldo atual e no proximo ciclo de tratamento.
- Historico de tratamentos e compras para consulta.

# 7. Escopo / Entregas (MVP)
- API REST para CRUD de remedios, compras e tratamentos.
- Regras de negocio para calcular estoque disponivel, consumo previsto e sobra.
- Endpoint para sugestao de compra (quanto comprar e quando comprar).
- Persistencia com Spring Data JPA e banco H2 para ambiente local.
- Documentacao de endpoints via OpenAPI/Swagger.

# 8. Requisitos Tecnicos (Stack, Integracoes, Plataforma)
- Linguagem: Java 26.
- Framework: Spring Boot 4.0.3 (Spring Web MVC).
- Persistencia: Spring Data JPA.
- Banco local/dev: H2.
- Documentacao API: springdoc OpenAPI UI.
- Build: Maven.
- Arquitetura: API REST backend-first.

# 9. Estrutura e Organizacao do Projeto
## 9.1 Organizacao de Pastas (referencia)
- `src/main/java/com/example/demo/controller`: camada HTTP (entrada/saida).
- `src/main/java/com/example/demo/service`: contratos de servico.
- `src/main/java/com/example/demo/service/impl`: implementacao de regras de negocio.
- `src/main/java/com/example/demo/repository`: acesso a dados.
- `src/main/java/com/example/demo/entity`: mapeamento JPA.
- `src/main/java/com/example/demo/dto`: transporte de dados entre camadas.
- `src/main/java/com/example/demo/exception`: tratamento global de erros.

## 9.2 Divisao de Responsabilidades
- Controller: validar requests de borda, orquestrar chamadas de servico e retornar `ApiResponse`.
- Service/ServiceImpl: concentrar regras de negocio (calculo de consumo, saldo, previsao e sugestao de compra).
- Repository: encapsular consultas e operacoes de persistencia.
- DTO: contrato de entrada e saida entre API e servicos.
- Entity: representacao do modelo persistido.

## 9.3 Documentacao para Evolucao Futura
- Manter este arquivo como contexto funcional central do produto.
- Atualizar `stack-description.mdc` quando houver mudanca de stack/dependencias.
- Registrar novas decisoes arquiteturais no mesmo padrao de secoes (objetivo, impacto, trade-offs).

# 10. Requisitos Nao-funcionais
- Dados consistentes e confiaveis para calculos de estoque.
- API com respostas padronizadas e tratamento centralizado de erros.
- Seguranca de dados e validacao de entrada seguindo boas praticas OWASP.
- Codigo organizado para facilitar manutencao e evolucao.

# 11. Funcionalidades Desejaveis (Nice to Have)
- Alertas de baixo estoque e proximidade de termino de remedio.
- Alertas de validade proxima de vencimento.
- Importacao de receita/plano de tratamento.
- Dashboard com previsao de consumo mensal.
- Multiusuario por residencia (familia/cuidadores).

# 12. Criterios de Sucesso / Metricas
- Usuario consegue registrar compra e tratamento e obter saldo previsto sem calculo manual.
- Sistema informa corretamente a necessidade de compra para o proximo ciclo.
- Reducao percebida de compras desnecessarias e de desperdicio por sobra.
- Tempo medio para registrar uma compra ou tratamento baixo o suficiente para uso cotidiano.

# 13. Exemplo de Regra de Negocio
Se o usuario compra 2 caixas com 8 comprimidos cada (16 total) e o tratamento exige 10 dias com 1 comprimido por dia:
- Consumo previsto: 10 comprimidos.
- Sobra prevista: 6 comprimidos.
- Proxima recomendacao de compra deve considerar esses 6 comprimidos antes de sugerir nova quantidade.
