#Language : en

Feature : Remplir le formulaire
  Scenario Outline:Remplir le formulaire de l'employee
    Given je vais à la page delogin
    And je rentre le username
    And je rentre le password
    And je clique sur Login
    And je vais la page PIM
    And je clique sur ADD employée
    And