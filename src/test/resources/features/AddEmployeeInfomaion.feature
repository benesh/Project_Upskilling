#Language : en

Feature : Remplir le formulaire
  Scenario Outline:Remplir le formulaire de l'employee
    Given je vais à la page delogin
    And je rentre le "<username">
    And je rentre le "<password">
    And je clique sur Login
    And je vais la page PIM
    And je clique sur ADD employée
    And j_insere un "<firstname>"
    And j_insere un "<middlename>"
    And j_insre un "<lastname>"
    And je clique sur le bouton save
    And j_insere une "<dateNaissance>"
    And j_insere un "<Genre>"
    And j_insere un "<groupane sanguin>" anletoire
    And je click sur le bouton save
    When Je rafraishi la page
    Then Je verifie que les informations sont bien sauvegarde
    Examples:
    |username|paswword|titre|
    |Admin   |admin123|PIM  |
