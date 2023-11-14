# Readme Upskilling

## Selenium

>> #### PRE-REQUIS Pour Selenium
> - Creer un projet maven project
> - Ajouter les dépendances pour Sélenium 
> - make sure to intall lambok if you have Intellij as a IDE

* Exercice 
>  Objectif  :
>  Bases pour effectuer un test UI pour avec le téléchargement de driver pour firefox 
>
> > Cette exercice est fait pour permetd d'automatiser les étaps suivante:
> 1. Ouvrir le site www.amazon.fr
> 2. Maximiser ou le plein écran la page 
> 3. Valider les cookies
> 4. Effectuer une recherche de 'iphone 14 vert' an niveau de la barre de recherche
> 5. Ajouter le premier dans le panier le premier élément de la résultat
> 6. Ouvrir le panier
> 7. Fermer le navigateur
> 
> >Le(s) ficher(s) concerné
> 
> TestAmazon_2 : src/test/java/testsuite/TestAmazon_2.java


* Exercice Sauce
> Objectif: Implémenter le design pattern Page Object et le Fluent Pattern.
> - PageObejt: Chaque page aura une classe
> - Fluent Pattern : Permets de faire des appels de fonction continue sans 
> des varibales intermédiaire dans le fichiers TestSuite
> - On aura aussi la création de rapport et screenshot.
> 
> > Cette exercice est fait pour permetd d'automatiser les étaps suivante:
> 1. Ouvrir le site https://www.saucedemo.com/
> 2. Maximiser ou le plein écran la page
> 3. Se connecter avec les logins donnée
> 4. Ajouter le BACK PACK dans le panier
> 5. Ouvrir le panier
> 6. Vérifier le titre de la page est bien celui du panier
> 7. Fermer le navigateur
>
> >Les fichers concernés
>
> - BaseDriver : [saucedemopages](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fsaucedemopages)
> - Tous les fichiers du Repertoire saudemopages : [saucedemopages](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fsaucedemopages)
> - TestPatternPageObject : [TestPatternPageObject.java](src%2Ftest%2Fjava%2Ftestsuite%2FTestPatternPageObject.java)

## Cucumber

>> #### PRE-REQUIS Pour Cucumber
> - Creer un projet maven project
> - Ajouter les dépendances pour Sélenium
> - make sure to intall lambok if you have Intellij as a IDE

> Structure du projet
> 

* Exercice

>  Objectif:
>  Bases introductuve sur Cucumber pour effectuer des tests UI
> 
> * Structure de base Projet Cumcumber:
>   * Répertoire contenant les Page Objets dans src/domainName/pagebjetrepertoire
>   * Répertoire contenant le runner du test : src/test/java/runner
>   * Répertoire contenant les steps ou la traduction du gherkin src/test/steps
>   * Répertoire contenant les staps en gerkin src\test\resources\features
> 
> Etapes à autumatiser
> 1. Ouvrir le site https://www.saucedemo.com/
> 2. Maximiser ou le plein écran la page
> 3. Se connecter avec les logins donnée
> 4. Ajouter le BACK PACK dans le panier
> 5. Ouvrir le panier
> 6. Vérifier le titre de la page est bien celui du panier
> 7. Fermer le navigateur

