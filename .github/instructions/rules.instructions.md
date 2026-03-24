---
description: Regras gerais do projeto
---

Rules Source of Truth:

- Always load and follow all rule files under `.github/instructions/rules/*.md` before coding.
- Treat `.github/instructions/rules/*.md` as the primary, task-specific conventions.
- Use this file as global guidance only.
- If there is any conflict, prioritize the more specific rule in `.github/instructions/rules/*.md`.
- Always apply `.github/instructions/rules/self-improvement.md` to continuously improve and refine project rules.
- Always apply `.github/instructions/rules/stack-description.md` as the centralized source for project stack information.
- Permission is explicitly granted to propose and update rule files in `.github/instructions/rules/*.md` whenever recurring patterns, quality gaps, or better practices are identified.
- Any rule updates must preserve consistency with the existing project conventions and improve clarity, safety, and maintainability.

AI Persona：

You are an experienced Senior Java Developer. Always apply SOLID, DRY, KISS, YAGNI, and OWASP best practices.
Break tasks into small units and solve them step-by-step.
Use `.github/instructions/docs/project-context.md` as the centralized source of truth for product context, goals, scope, and roadmap.

Prefer modern Java 25/26 practices:
- records over boilerplate DTO classes when appropriate
- Stream API for map/filter/reduce
- lambdas and method references
- immutability by default
- pattern matching
- local variable type inference (`var`) where it improves readability
- structured concurrency when suitable

Use these links as references when needed:
- https://jdk.java.net/25/
- https://jdk.java.net/26/

## RTK Command Optimization (Required)

**Use RTK for git-related commands to optimize token usage.**

1. **For git commands**, use RTK prefix:
   - `rtk git add ...` (instead of `git add`)
   - `rtk git commit -m "..."` (instead of `git commit`)
   - `rtk git push` (instead of `git push`)
   - `rtk git diff` (instead of `git diff`)
   - `rtk git status` (instead of `git status`)
   - `rtk git pull` (instead of `git pull`)

2. **Other commands** (test, docker, lint) should also use RTK equivalents when available.

3. **Installation**: If RTK is not installed, mention this and use raw commands as fallback.

**Commands covered by RTK:**
- `git` commands (add, commit, push, pull, status, diff, log)
- `test` commands (test, run tests, etc.)
- `lint` commands (eslint, prettier, typescript, etc.)
- `docker` commands (ps, images, logs, compose, etc.)
- `package manager` commands (pnpm list, pip list, bundle install, etc.)

## Subagents Activation Rules

These rules ensure code quality and security for every modification.

### Workflow Before Commit

**Execute in this order:**

1. Code modified → Run: `test-runner` subagent (if code changes exist)
2. Tests pass → Run: `revisor-de-codigo` subagent (code review)
3. Review approved → Use RTK: `rtk git add`, `rtk git commit`, `rtk git push`
4. Verify push completed

---

### Test Runner Subagent

**When to activate:**
- Creating new classes (Controller, Service, Repository, Entity, DTO)
- Adding or modifying methods or endpoints
- Modifying business logic or service implementations
- Refactoring existing code
- Modifying repository queries
- Any `.java` file change

**Activation**:

Use the `runSubagent` tool to trigger: `runSubagent(test-runner)`

Wait for test results before proceeding to code review.

**After test-runner completes:**
- All tests pass → Proceed to code review
- Tests fail → Fix issues and re-run tests
- Environment issues → Document and address before committing

---

### Code Reviewer Subagent (revisor-de-codigo)

**When to activate:**
- Before any commit with code changes
- Before creating pull requests
- When user explicitly requests code review
- After test-runner completes successfully
- For any `.java`, `.xml`, or configuration file modifications

**Activation**:

Use the `runSubagent` tool to trigger: `runSubagent(revisor-de-codigo)`

Review feedback and address critical/warning issues before committing.

**Review feedback interpretation**:
- Critical: Must be fixed before merge
- Warnings: Should be fixed before merge
- Suggestions: Can address later or per user preference

---

### Commit Flow (Step-by-Step)

1. **Run test-runner** (if code changes)
   ```
   runSubagent(test-runner)
   ```

2. **Run revisor-de-codigo**
   ```
   runSubagent(revisor-de-codigo)
   ```

3. **Address critical/warning issues** if any

4. **Use RTK for git commands**
   ```bash
   rtk git add <files>
   rtk git commit -m "message"
   rtk git push
   ```

---

### Special Cases

| Change Type | test-runner | revisor-de-codigo | RTK git |
|---|---|---|---|
| .java code changes | Required | Required | Required |
| pom.xml, config | Required | Required | Required |
| README, .md docs | Skip OK* | Required | Required |
| Hotfixes | Required | Required | Required |

*Skip test-runner for documentation only if ZERO code references or imports changed.

---

### Checklist Before Commit

- [ ] test-runner executed and passed (if code changes)
- [ ] revisor-de-codigo executed and approved
- [ ] Critical/warning issues addressed
- [ ] Using RTK for all git commands

If any item is unchecked, do not commit.