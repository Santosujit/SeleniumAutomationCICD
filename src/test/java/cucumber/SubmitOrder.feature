
  @tag
  Feature: Purchase the order from Ecommerce website.
  
  Background:
  Given I landed on Ecommerce Page.

  @tag2
  Scenario Outline: Positive test for submitting the order.
    Given I log in with username <username> and password <password>
    When I add product <productName> to cart.
    And checkout <productName> and submit the order.
    Then "THANKYOU FOR THE ORDER." message is displayed.

    Examples: 
      | userName  			| password 			|  productName |
      |santosh@gmail.com |IloveRahul$1956 | ZARA COAT 3 |
