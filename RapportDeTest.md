# Rapport de test 

Plan de Test :
  -	ServerUserTest
  -	ServerThreadTest
  -	SimpleUserTest
  -	UserThreadTest
  -	MessageTest
  -	UserTest
  -	ChatRoomTest
  -	GUI
  -	LoginGUI


Tout d’abord, nous avons effectué des tests sur la classe Server, pour cela nous avons utilisé la fonction assertEquals avec les méthodes addUser, logout, saveMessage.

<p align="center">
<img src="/image/Test2.png" width="600" height="250"</img>
</p>

Par exemple, ici on teste si on arrive bien à connecter des utilisateurs et à envoyer des messages.
Néanmoins, nous avons rencontré des problèmes lors des tests unitaires contenant des sockets. Ainsi, nous n’avons pas pu tester nos méthodes send et connect. Cependant ces dernières pouvaient être testé facilement lors de nos tests globaux.
Ensuite, pour ServerThread et UserThread, étant donné que nous n’avons pas pu faire de tests unitaires sur les sockets et que ces 2 classes sont basées essentiellement sur le transfert d’informations avec des sockets, nous n’avons pas aboutit à des tests unitaires concluants pour ces classes.

<p align="center">
<img src="/image/Test1.png" width="600" height="200"</img>
</p>
 

Pour la suite nos tests, il s’agit en grande partie de la partie GUI, donc vu qu’il s’agit de création d’objets et d’affichage, nous n’avons pas énormément de tests unitaires à faire.
Pour résumer, grâce à IntelliJ nous pouvons voir le pourcentage de test unitaires que nous avons effectué.

<p align="center">
<img src="/image/TestPourcentage.png" width="800" height="50"</img>
</p>

Nous avons donc testé 18% des classes, 24% des méthodes et 14% des lignes. Notre impossibilité d’effectuer des tests sur les sockets explique ce faible pourcentage.  
