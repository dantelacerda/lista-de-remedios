---
description: Configuração geral dos subagents do projeto
---

## Subagents Configuration

Este arquivo define como e quando os subagents devem ser ativados no projeto.

### Test Runner Subagent
- **Arquivo**: `.github/agents/test-runner.md`
- **Ativação automática**: Sim
- **Contexto**: Sempre que houver criação ou modificação de código
- **Integração**: Paralela com tarefas de desenvolvimento
- **Relatório**: Resultados de testes devem ser reportados imediatamente

### Como adicionar novos subagents

1. Criar arquivo em `.github/agents/[nome].md`
2. Definir triggers de ativação em `rules.instructions.md`
3. Documentar no README.md
4. Testar integração com tarefas de desenvolvimento