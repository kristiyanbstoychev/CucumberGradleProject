Feature: Checkout

  Scenario: Verify that customers are able purchase an item
    Given that a customer is successfully logged in
    And he successfully adds an item to his shopping cart
    Then the should be able to finish the checkout process