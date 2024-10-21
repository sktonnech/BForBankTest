# BForBankTest
 - affichage d'une liste
 - pagination
 - gestion d'erreur
 - recherche dans la liste


## Architecture : 

![Capture d’écran 2024-10-21 à 04 31 52](https://github.com/user-attachments/assets/48991841-b9bb-4401-b6df-175fc1c43b14)
![Capture d’écran 2024-10-21 à 04 32 39](https://github.com/user-attachments/assets/3d24d737-7b6e-4efa-9884-ed248e45b658)
![Capture d’écran 2024-10-21 à 04 32 59](https://github.com/user-attachments/assets/5f875fd8-b907-414c-af7f-80c2f1e0afa4)

L'architecture du projet est basé sur : 
  - la Clean archi avec un module par couche (data/domain/presentation)
  - décomposition en **feature** (exemple : pokemon) mais on peut imaginer plusieur autres features.
  - Aussi dans chaque feature il y a un découppage en module (data/domain/presentation) de Clean Archi.
  - j'ai ajouté aussi un module **core** qui contient aujourd'hui que le module de **design-system**, mais on peut avoir des autres modules dans core


## Techno :
  - jetpack compose
  - jetpack compose Navigation
  - coroutine/flow
  - injecton de dependence avec Hilt


## Quelques Screens :

![Screenshot_20241021_044409](https://github.com/user-attachments/assets/7f0f6616-9576-4a73-91a7-9446453a4f67)

![Screenshot_20241021_044644](https://github.com/user-attachments/assets/95ef7b5c-5186-4c9e-8dde-4df48ec8fa97)

![Screenshot_20241021_044426](https://github.com/user-attachments/assets/40c5f7dd-f7a1-420e-8274-465a349a7993)

![Screenshot_20241021_044449](https://github.com/user-attachments/assets/a17e4137-6c86-4298-8f94-b2c5cfcc89e6)

![Screenshot_20241021_044511](https://github.com/user-attachments/assets/6ba67da1-91cf-4231-a25f-e241020c69da)
