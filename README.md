# Order Service
This is a demo Order Service CLI app.

## How to Build/Run
Build the app with `./gradlew build`

Run the app with `java -jar build/libs/orderservice-0.0.1-SNAPSHOT.jar`

In the shell, calculate the subtotal of a list of products with `subtotal Orange,Apple`

To calculate the subtotal of a list of products with discounts: `subtotal --products Orange,Apple --applyDiscount true`

To submit an order: `order Apple,Orange`

OrderService publishes the order was successful, and the MailSubscriber subscribes and then prints the message.
