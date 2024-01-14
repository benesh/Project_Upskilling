# Projet Upskilling
## Famework utilisé pour l'automatisation UI
### TestNG 
Projet dans le cadre du upskilling testsur le site Orange HRM

### Résumé des features dans le test
* Projet codé en langage java
* Fluent interface pour une meilleurs écriture des test
* Page Object pour une meilleures réutilisation du code 
* Parallel exécution pour obtimiser la durée avec l'utilisation de fichier Testng Suite XMl
* Multiple exécution de l'ensemble des suites de test pour navigateurs disponibles
* Selenium Grid pour une exécution avec remote seveur


### Préparation de l'exécution avec les différents fochiers de conf
* Fichier de configuration globale 
>chemin de fichier de cofiguration globale : src/ main/ resources/ config/ configtestng/ config.properties

Les différentes valeurs fixes sont en tableau 
```properties
title=Testsuite.   
default_browser=CHROME // navigateur par défaut d'une session
explicitwait = 5      
implicitwait = 3   
headless = NO  // {NO,YES}    
pathscreenshot = screenshots/          // répertoire des screenshot
pathreport = target/extentreport/         // répertoire du rapport 
ducomenttitle = Rapport Test Automatisé     
rapportname = Rapport Test ORANGE HRM      
user = Ben-Omar          
env = LOCAL // {REMOTE,LOCAL}        
softwaretest = OrangeHRM
url_hub = http://172.24.192.1:4444
```
* Testng xml suite config paramétrize 
  * Testng fichier XML suite 
> Exécution non parallel pour le navigateur firefox.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="suite ORANGE HRM"  >

    <listeners>
        <listener class-name="listeners.ReportListerner" />
        <listener class-name="app.getxray.xray.testng.listeners.XrayJsonReporter" />
    </listeners>
    
    <parameter name="config" value="src/main/resources/config/configtestng/config.properties"/>

    <test name="TestWithFirefox">
        <parameter name="browser" value="FIREFOX"/>
      <!--Parameter for the browser-->
      <parameter name="datainput" value="src/main/resources/test_data_input/testng_data/scenario1/data.properties"/>
      <!--Parameter for the data user for test-->
      <groups>
            <dependencies>
                <group depends-on="createemp" name="adminUser"/>
                <group depends-on= "adminUser" name= "feuilletemp"/>
                <group depends-on= "adminUser" name= "form"/>
                <group depends-on= "adminUser" name= "uppload"/>
            </dependencies>
        </groups>
        <classes>
            <class name="testsuite.TestCreateEmployee"/>
            <class name="testsuite.TestCreateAdmin"/>
            <class name="testsuite.TestFeuilleDeTemp"/>
            <class name="testsuite.TestRemplirFormulaire"/>
            <class name="testsuite.TestUploadFichier"/>
        </classes>
    </test>
    
</suite> 
```

> 
>*Si on souhaite exécuter pour les trois navigateurs disponibles, 
> répéter la section <test> deux fois* .
> Non parallèl avec les 3 navigateurs 
```xml
<suite>
  <test name="TestWithFirefox">
          <parameter name="browser" value="FIREFOX"/>
          ....
  </test>
  <test name="TestWithChrome">
          <parameter name="browser" value="CHROME"/>
          ....
  </test>
  <test name="TestWithEdge">
          <parameter name="browser" value="EDGE"/>
          ....
  </test>
</suite>
```
> *Exécution parallel par block de test*

```xml
<suite name="suite ORANGE HRM" parallel="tests" thread-count="2"  >
  <test name="TestWithFirefox">
          <parameter name="browser" value="FIREFOX"/>
          ....
  </test>
  <test name="TestWithChrome">
          <parameter name="browser" value="CHROME"/>
          ....
  </test>
  <test name="TestWithEdge">
          <parameter name="browser" value="EDGE"/>
          ....
  </test>
</suite>
```
Ici on parallelise les block de test ( donc démarrage des trois navigateurs en même tesmps) mais pas des classes ou suite de test

* Ci-dessous une exécution parallel sur les block de test et aussi sur les suite de test ou class
```xml
<suite name="suite ORANGE HRM" parallel="tests" thread-count="2"  >
  <test name="TestWithFirefox" parallel="tests" thread-count="2">
          <parameter name="browser" value="FIREFOX"/>
          ....
  </test >
  <test name="TestWithChrome" parallel="tests" thread-count="2">
          <parameter name="browser" value="CHROME"/>
          ....
  </test>
  <test name="TestWithEdge" parallel="tests" thread-count="2">
          <parameter name="browser" value="EDGE"/>
          ....
  </test>
</suite>
```

>Pour lancer l'exécuter, exécuter le bash ci dessous:
>```bash
>maven clean test
>```

## Execution par maven et par xml du cucumber n'est pas encore implémenté