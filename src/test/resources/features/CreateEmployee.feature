Feature:Creer un employe
  Creer un employe dans la plateforme
  Scenario Outline: cree un employe
    Given je vais à la page de login "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And j_insere un username <"username">
    And j_insere un password <"password">
    And je cliqe sur le bouton connexion
    And je clique sur pim
    And je clique sur +Add
    And j_insere un firstname <"firstname">
    And j_insere un middlename <"middlename">
    And j_insere un lastname <"lastname">
    When je click sur le bouton save
    Then je vois apparaitre l_icone succes d_enregistrement
    And je click sur le bouton save de la page employe info
    Examples:
      |username|paswword|titre|
      |Admin   |admin123|PIM  |