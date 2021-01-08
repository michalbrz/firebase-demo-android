# firebase-demo-android
Demo app for implementing Firebase SDK analytics and App Links

### Events

We suggest to track the following in-app events via Firebase:

| Event                 |  Triggered...                             |
| --------------------- | ----------------------------------------- |
| [purchase][1]         |  when a user completes a purchase         |
| [view_item][2]        |  when a user views an item                |
| [view_item_list][3]   |  when a user views a list of items        |
| [add_to_cart][4]      |  when a user adds an item to cart         |

More suggested events for e-commerce app: https://support.google.com/firebase/answer/6317499?hl=en&ref_topic=6317484

### Credits
https://github.com/liwentao

  [1]: https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Event#PURCHASE
  [2]: https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Event#VIEW_ITEM
  [3]: https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Event#VIEW_ITEM_LIST
  [4]: https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics.Event#ADD_TO_CART
