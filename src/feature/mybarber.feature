#language: pt

Funcionalidade: Blog
    Como usuário desejo ver a página do Blog

    Cenario: Entrar Home do Blog
        Dado que desejo entra no Blog
        Quando abrir o browser
        Entao deverá aparecer o site

    Cenario: Pesquisa no Blog
        Dado que desejo entra no Blog
        Quando abrir o browser
        E digitar 'oauth' no campo de busca
        E clicar no botao pesquisar
        Entao deverá aparecer o resultado da busca