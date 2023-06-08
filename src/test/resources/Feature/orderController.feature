Feature: Service order flow

  Scenario: Service Order Creation and Fulfillment
    When call service post "/v1/createOrder"
    Then I call the query pending order service "/v1/consultOrderPendding"
    Then Start order fulfillment no path "/v1/startOrder"
    Then I add an annotation to the order no path "/v1/registerNote"
    And Perform the closing of the service order "/v1/closeOrder"
    Then Consult all orders "/v1/consultServiceOrder"
    And Delete all order records "/v1/deleteOrderService/{orderNumber}"