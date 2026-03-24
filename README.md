# 📋 Lista de Remédios

<div style="text-align: center; background: linear-gradient(45deg, #667eea 0%, #764ba2 100%); color: white; padding: 20px; border-radius: 10px; margin-bottom: 20px;">
  <h2>🏥 Sistema de Gerenciamento de Remédios</h2>
  <p>Uma aplicação Spring Boot para gerenciar bulas e remédios de forma eficiente.</p>
</div>

## 📚 Regras do Projeto

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

## MCP Servers (Opcional)

Os Model Context Protocol (MCP) Servers configurados para este projeto são **opcionais**:

- **Context7** — Acesso a documentação e exemplos de código de bibliotecas e frameworks
  - Configuração: `.vscode/mcp.json`
  - Uso: `mcp_context7_query-docs`, `mcp_context7_resolve-library-id`
  - Útil para consultar versões e padrões de bibliotecas em tempo real

## Subagents

Subagents automatizados para melhorar a qualidade do código:

- **Test Runner** — Especialista em automação de testes
  - Localização: `.github/agents/test-runner.md`
  - Ativação: Automática em paralelo quando criando código novo
  - Propósito: Executar testes proativamente e corrigir falhas
  - Gatilhos: Criação de classes, métodos, endpoints ou modificações que possam impactar testes

Para mais detalhes sobre configuração de subagents: `.github/agents/README.md`

## 🛠️ Ferramentas de Desenvolvimento

### RTK (Repository Toolkit)

**RTK** é uma ferramenta de linha de comando que filtra e comprime saídas de comandos antes que cheguem ao contexto do LLM, ajudando a economizar tokens e melhorar a eficiência das interações com IA.

- **Repositório**: [rtk-ai/rtk](https://github.com/rtk-ai/rtk)
- **Função principal**: Filtragem e compressão de outputs de comandos para otimização de tokens
- **Benefício**: Reduz o consumo de tokens em conversas com LLMs, mantendo apenas informações relevantes

#### ✅ Modo 1: Com RTK Ativado (Recomendado)

Este projeto está **configurado para usar RTK automaticamente** através do arquivo `.github/instructions/rules.instructions.md`. Sempre que você solicitar um comando ao agente (ex: `git status`), ele irá sugerir a versão otimizada com RTK (ex: `rtk git status`).

**Pré-requisito**: Instale o RTK
```bash
npm install -g @rtk-ai/rtk
# ou via Homebrew
brew install rtk-ai/rtk/rtk
```

**Exemplo de uso**:
```bash
rtk git status        # Output comprimido do git
rtk test npm test     # Resultados apenas de falhas
rtk docker ps         # Lista compacta de containers
```

#### ⚙️ Modo 2: Sem RTK (Funcional)

Se você **não quiser usar RTK**, basta modificar o arquivo `.github/instructions/rules.instructions.md` e **remover ou comentar** a seção "RTK Command Optimization". Tudo funcionará normalmente com comandos padrão, apenas sem a otimização de tokens.

Neste modo:
- ✅ Todos os comandos funcionam normalmente
- ✅ Nenhuma mudança de comportamento na aplicação
- ⚠️ Seus comandos consumirão mais tokens em conversas com LLMs

