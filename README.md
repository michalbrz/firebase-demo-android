# firebase-hackathon-android
Demo app for implementing Firebase SDK analytics and App Links

### Events

We suggest to track the following in-app events via Firebase:

| Event                 |  Triggered...                             |  Parameters                                                                             |
| --------------------- | ----------------------------------------- | --------------------------------------------------------------------------------------- |  
| ecommerce_purchase    |  when a user completes a purchase         |  currency, value                                                                        |
| view_item             |  when a user views an item                |  item_id, item_name, item_category, currency, price                   |
| view_item_list        |  when a user views a list of items        |  item_category                                                                          |   
| add_to_cart           |  when a user adds an item to cart         |  item_id, item_name, item_category, currency, price, quantity, value  |  

More suggested events for e-commerce app: https://support.google.com/firebase/answer/6317499?hl=en&ref_topic=6317484

### Credits
https://github.com/liwentao
