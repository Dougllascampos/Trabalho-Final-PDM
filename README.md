# AppDoacao - Android (Kotlin) - Skeleton Project

Este repositório contém um esqueleto do projeto **AppDoacao** seguindo o tutorial
fornecido. Ele inclui:

- Configuração Gradle (simplificada)
- Activities: LoginActivity, MainActivity, EditItemActivity
- Data class: Item
- Adapter: ItemAdapter
- Layouts: activity_login, activity_main, activity_edit_item, item_row
- Recursos básicos (drawable, menu)

**Observações importantes**
- Este é um *skeleton* para acelerar a criação. Você precisa:
  - Abrir no Android Studio (recomendo Android Studio Flamingo/Narwhal ou superior).
  - Adicionar o arquivo `google-services.json` obtido no Firebase em `app/`.
  - Configurar o Firebase project no console (Auth e Firestore).
  - Ajustar versões do Gradle & plugin se necessário.
  - Usar o Gradle wrapper do seu ambiente Android Studio se preferir.

## Como usar
1. Baixe e extraia o projeto.
2. No Android Studio, escolha "Open" e selecione a pasta do projeto.
3. Adicione `google-services.json` em `app/`.
4. Sync e execute.

## Próximos passos sugeridos
- Adicionar Firebase Storage para imagens.
- Implementar regras de segurança do Firestore mais restritivas (permitir editar apenas itens do usuário).
- Melhorar UI com Material Components.
