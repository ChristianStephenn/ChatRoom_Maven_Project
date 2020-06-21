# <img src = https://user-images.githubusercontent.com/63917571/83533154-7ed13400-a4ef-11ea-85c0-d857717a7c31.png width="100" high="100"> Projet Génie logiciel POO - Livrable
## Présentation
### Membre de l'équipe
AYMARD Théo, LAPY Arnaud, MARIASTEPHEN Christian, MOORGHEN Mégane, MOORGHEN Shéréna, THIAO-LAYEL Vincent

### Objectif
Le but du projet génie logiciel est de nous faire travailler de manière bien organisée sur un sujet donné, en mettant à l’œuvre les notions du cours, en améliorant vos compétences en POO et Java, et en produisant tous les artefacts d’un processus de développement de projet.

### Projet
Développer une application (bureau) qui est une salle de discussions (Chat room) et où plusieurs utilisateurs peuvent se connecter sur un ou plusieurs PCs différents se trouvant dans le même sous-réseau.

## Organisation du projet
### Répartition et description des rôles
<table>
  <tr>
    <th>Étudiants</th>
    <th>Rôles</th>
    <th>Description</th>
  </tr>
  <tr>
    <th>AYMARD Théo</th>
    <td>Construction & Vérification</td>
    <td><ul>
     <li> Chargé de la partie code sous IntelliJ et la partie réseau, tout en suivant l’architecture MVC </li>
     <li> Rédige la documentation avec JavaDoc </li>
     <li> Rédige les tests unitaires </li>
  </ul></td>
  </tr>
  <tr>
    <th>LAPY Arnaud</th>
    <td>Conception & Test</td>
    <td><ul>
     <li> Chargé de la documentation d’architecture système </li>
     <li> Rédige les tests unitaires </li>
     <li> Rédige un rapport de test (tests exécutés, liste des bugs…) </li>
  </ul></td>
  </tr>
  <tr>
    <th>MARIASTEPHEN Christian</th>
    <td>Construction & Vérification</td>
    <td><ul>
     <li> Chargé de la partie code sous IntelliJ et la partie réseau, tout en suivant l’architecture MVC </li>
     <li> Rédige le script Maven pour le build </li>
     <li> Rédige une archive jar </li>
  </ul></td>
  </tr>
  <tr>
    <th>MOORGHEN Mégane</th>
    <td>Design & Analyse</td>
    <td><ul>
     <li> Chargé de l’interface graphique sous IntelliJ </li>
     <li> Analyse de la conception selon les principes SOLID </li>
     <li> Rédige le plan de test pour vérifier la conformité du programme aux exigences </li>
  </ul></td>
  </tr>
  <tr>
    <th>MOORGHEN Shéréna</th>
    <td>Conception & Analyse</td>
    <td><ul>
     <li> Chargé de la documentation d’architecture système </li>
     <li> Analyse de la conception selon les principes SOLID </li>
     <li> Rédige tout l’argumentaire du projet </li>
     <li> Rédige le plan de test pour vérifier la conformité du programme aux exigences </li>
  </ul></td>
  </tr>
  <tr>
    <th>THIAO-LAYEL Vincent</th>
    <td>Design & Test</td>
    <td><ul>
     <li> Chargé de l’interface graphique sous IntelliJ </li>
     <li> Rédige les test unitaires </li>
     <li> Rédige un rapport de test (tests exécutés, liste des bugs…) </li>
  </ul></td>
  </tr>
</table>

### Méthode de développement
#### Méthode Agile : Kanban
<ul>
  <li> Les rôles ont été déjà définis à l’avance et seront respectés, ainsi le but du projet et ses conditions/exigences requises. </li>
  <li> Grâce à Trello, nous avons une vue d’ensemble sur les exigences demandées. Les possibles changements au cours du projet peuvent être alors facilement faites et aussi vus par toute l’ équipe. Il ne s’agira que de légères modifications pour améliorer le projet. </li>
  <li> L’avancement se faisant de manière incrémentale, nous permettra de mieux visualiser notre progression et de se concentrer sur les points à aborder, selon le planning défini. </li>
  <li> Chacun avance son rythme dans ses différents travaux, à condition de ne pas ralentir son ou ses partenaires. </li>
  </ul>

#### Avancement du projet sur trello
<table>
  <tr>
    <th>Début</th>
    <th>Phase 1</th>
    <th>Phase 2</th>
  </tr>
  <tr>
    <td><img src = https://user-images.githubusercontent.com/63917571/83624606-1cca0a80-a593-11ea-828b-f025c6f8ae96.png></td>
    <td><img src = https://user-images.githubusercontent.com/63917571/83624846-729eb280-a593-11ea-81da-af7decceb3a2.png></td>
    <td><img src = https://user-images.githubusercontent.com/63917571/83625027-9e219d00-a593-11ea-8f4a-9b114c7abc55.png></td>
  </tr>
</table>

## Spécification
### Cahier des charges
#### Liste des exigences fonctionnelles et non-fonctionnelles
<table>
  <tr>
    <th>ID</th>
    <th>Nom</th>
    <th>Type</th>
    <th>Description</th>
  </tr>
  <tr>
    <th>CR_REQ_1</th>
    <td>Fonctionnalité principale</td>
    <td>F</td>
    <td>L’application va permettre aux utilisateurs de s’envoyer des messages.</td>
  </tr>
  <tr>
    <th>CR_REQ_2</th>
    <td>Sauvegarde</td>
    <td>F</td>
    <td>La discussion doit être sauvegardée à la fin de chaque fermeture de l’application.</td>
  </tr>
  <tr>
    <th>CR_REQ_3</th>
    <td>Reconnaissance</td>
    <td>F</td>
    <td>Les utilisateurs doivent être capable de reconnaître chaque membre du groupe.</td>
  </tr>
  <tr>
    <th>CR_REQ_4</th>
    <td>Suppression</td>
    <td>F</td>
    <td>L’utilisateur doit pouvoir supprimer la conversation.</td>
  </tr>
  <tr>
    <th>CR_REQ_5</th>
    <td>Interface</td>
    <td>F</td>
    <td>L’application doit permettre aux utilisateurs de communiquer entre eux.</td>
  </tr>
  <tr>
    <th>CR_REQ_6</th>
    <td>Fiabilité</td>
    <td>NF</td>
    <td>L’échange des messages doit fiable et simple.</td>
  </tr>
  <tr>
    <th>CR_REQ_7</th>
    <td>Taille du groupe</td>
    <td>F</td>
    <td>La salle de discussion peut accueillir au minimum deux personnes.</td>
  </tr>
  <tr>
    <th>CR_REQ_8</th>
    <td>Sécurité</td>
    <td>NF</td>
    <td>Les messages envoyés par les utilisateurs doivent être sécurisés.</td>
  </tr>
  <tr>
    <th>CR_REQ_9</th>
    <td>Type de données</td>
    <td>F</td>
    <td>L’application permet d’envoyer des messages de type texte.</td>
  </tr>
  <tr>
    <th>CR_REQ_10</th>
    <td>Portabilité</td>
    <td>NF</td>
    <td>L’application sera disponible sur Mac ou PC Windows.</td>
  </tr>
  <tr>
    <th>CR_REQ_11</th>
    <td>Version</td>
    <td>NF</td>
    <td>L’application sera disponible en version Desktop.</td>
  </tr>
  <tr>
    <th>CR_REQ_12</th>
    <td>Gratuité</td>
    <td>F</td>
    <td>L’envoi des messages doit se faire gratuitement.</td>
  </tr>
</table>

#### Description de la démarche
La liste des exigences s’est établie en plusieurs étapes. Tout d’abord, nous nous sommes tous réunis pour discuter des exigences possibles concernant le projet, notamment sur les exigences fonctionnelles qui sont plus évidentes (ex : Fonctionnalité de base, Reconnaissance, …). Ensuite, il y a eu les séances de TD qui nous ont aidé à améliorer notre travail, surtout les exigences non-fonctionnelles qui demandent un peu plus de réflexion. Après ce travail effectué, les deux membres de la conception se sont chargés de dresser une liste d’exigences reflétant au maximum l’application souhaitée, et cela de manière simple et concise.

#### Diagramme UML des cas d'utilisation
<p align="center">
  <img src = https://user-images.githubusercontent.com/63917571/83626527-d6c27600-a595-11ea-8b27-38c6842cca7d.png width="500" high="800">
</p>

#### Diagramme UML de séquence 
<p align="center">
<img src = https://user-images.githubusercontent.com/63917571/83626627-ffe30680-a595-11ea-9262-2105f342e796.png width="500" high="800">
</p>

## Conception
### Architecture système
#### Style architectural : MVC
Cette architecture a été choisie par l'équipe parce qu'elle permet de distinguer la couche interface graphique des autres couches (modéle et contrôleur) et d'avoir une conception simple et claire de ces dernières. Le MVC va nous permettre de gagner du temps au niveau de l'évolution du projet et nous donner une grande souplesse pour développer l'application bureau, puisque cette architecure nous donne la possibilité d'utiliser les couches par plusieurs développeurs en même temps et de faire des modifications ciblées.

#### Diagramme UML des classes

<img src = https://github.com/ChristianStephenn/ChatRoom_Maven_Project/blob/master/images/UML_Class.PNG width="800" high="1200">

#### Diagramme UML de package

#### Diagramme UML de déploiement

#### Diagramme UML des composants

#### Design Pattern : Singleton
Ce patron permet d'assurer que chaque classe n'ait qu'une instance et donc d'avoir un meilleur contrôle lorque l'on souhaite accéder à une classe.












