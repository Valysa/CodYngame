# ***CodYngame***
## Table of Contents
1. [Infos général](#infos-général)
2. [Technologies utilisées](#technologies-utilisées)
3. [Téléchargement](#téléchargement)
4. [Lancement](#lancement)

## Infos général

L'application propose des exercices de programmation dans de multiple langages tels que le C, le PHP, le JavaScript, le Java et le Python
<br/> 


## Technologies utilisées

* Langages de programmations : <code>Java</code>, <code>CSS</code>, <code>JavaScript</code>, <code>SQL</code>
<br/>
* Bibliothéque utilisé : <code>JavaFX</code>, <code>RichTextFX</code>, <code>mySQL</code>
* La JavaDoc est stockée dans le dossier JavaDoc, il suffit de lancer le fichier overview-tree.html qui est présent dedans.


## Téléchargement

* Télécharger [<code>CoDyngame</code>] : git clone https://github.com/Valysa/CodYngame
* Télécharger une version de [<code>-php</code>], [<code>-Java</code>], [<code>-C</code>], [<code>-Python</code>], [<code>-JavaScrypt</code>], [<code>-mySQL</code>], [<code>-mysqli</code>] et [<code>-mySQL-workbench</code>] (si cela n'est pas déjà fait).


## Lancement
Il faut d'abord créer la base de données pour cela nous alons utiliser l'IDE intelliJ: 
<ul>
  <li> Clicker sur View > ToolWindows > Database </li>
  <li> Dans la fenêtre qui s'ouvre à droite appuyez sur le +</li>
  <li> Puis sur Data Source > MySQL </li>
  <li> Rentrez comme port <code>3306</code></li>
  <li> Comme user <code>root</code> </li>
  <li> Et comme Password <code>MyS3cur3P@sswOrd!</code></li>
  <li> Tester votre connextion puis clicker sur Apply puis OK</li>
</ul>
<br/>
Puis il faut, dans le terminal, faire les commande suivante:
<ul>
  <li> <code>mvn clean package</code></li>
  <li> <code>cd target</code></li>
  <li> <code>java -jar "codying-game-1.0.0.jar"</code></li>
</ul>
