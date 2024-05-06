# ***CodYngame***
## Table of Contents
1. [Infos général](#infos-général)
2. [Compatibilité système](#compatibilité-système)
3. [Technologies utilisées](#technologies-utilisées)
4. [Téléchargement](#téléchargement)
5. [Lancement](#lancement)

## Infos général

Le site est une librairie en ligne qui permet d'acheter des romans, des mangas et des web novels, on peut se connecter à un compte et faire un formulaire pour contacter le web master.
<br/> 

## Compatibilité système

Le site est compatible avec la plupart des navigateurs récents ( Google, Opera, Edge, etc...).
<br/>

## Technologies utilisées

* Langages de programmations : <code>HTML</code>, <code>CSS</code>, <code>PHP</code>, <code>JavaScript</code>, <code>SQL</code>
<br/>


## Téléchargement

* Télécharger [<code>Book'In</code>](https://github.com/xymunia/projet/archive/refs/heads/main.zip) et extraire le fichier .
* Télécharger une version de [<code>-php</code>], [<code>-php-xml</code>], [<code>-mySQL</code>], [<code>-mysqli</code>] et [<code>-mySQL-workbench</code>] (si cela n'est pas déjà fait).


## Lancement
Il faut d'abord créer la base de données: 
<ul>
<li> Ouvrir mysql-workbench</li>
<li> Créer une connexion avec un serveur root sans mot de passe</li>
<li> Executer le script sql: <code>projet-main/sql/bookln.sql</code></li>
<li> Executer le script sql: <code>projet-main/sql/booklndata.sql</code></li>

</ul>

<br/>
Un serveur Apache ( comme Wamp) peut être utilisé pour pouvoir lancer le site et avoir un localhost dédié
Afin d'utiliser le site, veuillez glisser le fichier dezippé projet dans le dossier du serveur Apache ( ex : C:\wamp64\www).
Une fois le serveur lancé, tapez localhost/projet dans la barre de recherche du navigateur internet.
<br/><br/>
On peut aussi utilisé la fonctionnalité déjà incluse dans PHP, avec la commande éxecutable dans le fichier dézippé projet : <code>php -S localhost:port</code> avec port le port voulu. Pour ouvrir le site, il suffira de lancer localhost.
<br/>
<br/>


Pour se connecter dans connexion sur le site, il faut mettre l'identifiant 'john_doe' et le mot de passe '123'. <br/>
