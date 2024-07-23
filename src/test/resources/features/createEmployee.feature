#Language : en

Feature:Creer un employe
  Scenario Outline: cree un employe
    Given je vais à la page de login "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And j_insere un username "<username>"
    And j_insere un password "<password>"
    And je clique sur le bouton connexion
    And je clique sur pim
    And je clique sur +Add de PIM
    And j_insere un firstname "<firstname>" de ajouter Employe
    And j_insere un middlename "<middlename>" de ajouter Employe
    And j_insere un lastname "<lastname>" de ajouter Employe


    When je click sur le bouton save
    Then je constate l_enregistrement avec succes
    And je click sur le bouton save de la page employe info
    Examples:
      | username | password  | firstname | middlename | lastname |
      | Admin    | admin123 | bb25      | bb26    | Bop  |

  Scenario Outline: crerr un use Admin
    Given je vais à la page de login "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And j_insere un username "<username>"
    And j_insere un password "<password>"
    And je clique sur le bouton connexion
    And je clique sur la Admin
    And je clique sur +Add de Admin
    And j_insere un role Admin au user
    And j_insere le status enable au user
    And je recherche et selection le nom complet du user "<firstname>" "<middlename>" "<lastname>"
    And j_insere le username "<username2>" du admin
    And j_insere un password "<password2>" du admin
    And j_insere un mode de passe de confirmation "<password2>"
    And je clique sur le bouton Save de Add Admin Page
    And je vois l'enregistrement s'est effectué avec succes
    And je me deconnecte
    And j_insere un username "<username2>"
    And j_insere un password "<password2>"
    When je clique sur le bouton connexion
    Then je vois que le nom sur le profil correspond a celui cree "<firstname>" "<lastname>"
    Examples:
      | username | password | firstname | middlename | lastname | username2  | password2     |
      | Admin    | admin123 | bb25   | bb26    | Bop  | oklomr4587 | Amadou1235678 |
