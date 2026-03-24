# lista-de-remedios

## Regras do projeto

O diretório principal de regras e instruções é:

- `.github/instructions/rules/` (arquivos `.md` com padrões de código)
- `.github/instructions/rules.instructions.md` (documento mestre com fontes de verdade)
- `.github/instructions/docs/project-context.md` (contexto do produto e roadmap)

### Uso

- Priorize os arquivos em `.github/instructions` para automatizações e revisões.
- Confira `rules.instructions.md` para entender a hierarquia e onde encontrar a regra correta.

## Stacks do projeto

- `.github/instructions/rules/stack-description.md` (descrição completa de tecnologias e stack)

### Passeio rápido

1. Leia `.github/instructions/rules.instructions.md`
2. Verifique `.github/instructions/rules/stack-description.md`
3. Aplique as regras de convenção nos controllers, services, repositories e DTOs

## MCP Servers

Os Model Context Protocol (MCP) Servers configurados para este projeto:

- **Context7** — Acesso a documentação e exemplos de código de bibliotecas e frameworks
  - Configuração: `.vscode/mcp.json`
  - Uso: `mcp_context7_query-docs`, `mcp_context7_resolve-library-id`
  - Útil para consultar versões e padrões de bibliotecas em tempo real

