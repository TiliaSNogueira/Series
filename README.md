# Series App

O Series App é um aplicativo que lista as séries da API TvMaze a partir da URL base [https://api.tvmaze.com/](https://api.tvmaze.com/)
Para mais detalhes sobre TvMaze acesse o link [https://www.tvmaze.com/api](https://www.tvmaze.com/api) 


## Features

O aplicativo exibe em sua tela inicial uma lista das séries, contendo o poster e o título das mesmas.
Ao selecionar uma das séries, o usuário é direcionado à tela de detalhes, onde são exibidos o poster, título, pontuação, gêneros e sumário da série selecionada.
Nesta mesma tela de detalhes é possível salvar a série na lista de favoritos.
O aplicativo conta também com a funcionalidade de pesquisar séries pelo nome.


## Tecnologias utilizadas

* Kotlin
* Arquitetura MVVM
* LiveData
* Retrofit para requisições
* Hilt para Injeção de dependências
* Room para persistência de dados
* Fragments
* Navigation
* Safe args
* Mockito para testes 
* Espresso para testes de interface do usuário

## Telas do aplicativo

#### Splash screen e lista inicial de séries:
![Gifs](https://github.com/TiliaSNogueira/Series/blob/master/splash%20and%20list.gif?raw=true)

#### Pesquisa de séries:
![Gifs](https://github.com/TiliaSNogueira/Series/blob/master/search.gif?raw=true)

#### Detalhes da série:
![Gifs](https://github.com/TiliaSNogueira/Series/blob/master/details.gif?raw=true)

#### Lista de favoritos com ação de swipe para remover favorito:
![Gifs](https://github.com/TiliaSNogueira/Series/blob/master/favoritos.gif?raw=true)

          











