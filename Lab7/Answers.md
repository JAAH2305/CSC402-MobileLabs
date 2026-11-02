# Lab 7 — Written Answers

These are code-based explanations and expected results. Runtime observations and Logcat counts must be verified on a device; no emulator results are claimed here.

## Task 1

**1.1** `setContentView(R.layout.activity_rate)` inflates the XML layout into View objects.

**1.2** Imperative: `label.text = ...` directly changes an existing widget. Compose describes the text using the current state.

**1.3** `addToBackStack("news")` records the transaction so Back can reverse it. Without it, Back does not undo that transaction and may leave the Activity.

**1.4**

| Old piece | Compose replacement | Task |
| --- | --- | --- |
| RatingBar in XML | StarRating wrapping RatingBar in AndroidView | 2 |
| Second Activity and findViewById | CourseRatingRoute + CourseRatingScreen in the existing Activity | 2 |
| label.text update | Text derived from uiState.stars | 2 |
| NewsFragment and WebView | CampusNewsRoute + CampusNewsScreen + CampusWebPage | 3 |
| Transaction and addToBackStack | NavHost destinations and the navigation back stack | 4 |

## Task 2

**TODO 1**
```kotlin
setOnRatingBarChangeListener { _, value, fromUser ->
    if (fromUser) onStarsChange(value)
}
```

**TODO 2**
```kotlin
update = { view -> view.rating = stars }
```

**2.4** Expected: tapping four stars updates the text to `You gave 4.0 stars`. Reset changes both the text and the stars to zero.

**2.5** Expected in one uninterrupted visit: `factory` prints once per View instance. `update` runs initially and after observed state changes. Three distinct taps normally give at least four update logs in total; dragging can generate more. Record the actual counts, rather than treating this prediction as a measurement.

| Actual measurement to collect | Count |
| --- | --- |
| factory | Not measured |
| update | Not measured |

**2.6** Without update, the text resets but the native stars stay selected. This breaks state flowing down from Compose to the View.

**2.7** A programmatic change has `fromUser == false`, so the listener does not send another event upward.

**2.8** rememberSaveable restores the rating after Activity recreation. remember alone loses it on a normal rotation that recreates the Activity. Restore rememberSaveable afterwards.

## Task 3

**TODO 3** `webViewClient = WebViewClient()`

**TODO 4** `loadUrl(url)`

**3.4** Without WebViewClient, tapped web links can be passed to an external browser instead of staying in this WebView.

**3.5** Without INTERNET permission, network content cannot load; the WebView displays a loading error. Restore the permission afterwards.

**3.6** For this fixed URL, factory starts loading once when creating the View. Calling loadUrl in update could reload it on recomposition and interrupt browsing.

## Task 4

**4.1** The repository currently contains Labs 1–4, so the Lab 5 dependency versions were unavailable. The two new projects use explicitly pinned compatible dependencies: Navigation Compose 2.8.9 and Kotlin/serialization plugin 2.1.20. Gradle configuration is included.

**TODO 5** `navController.navigate(CampusNews)`

**TODO 6**
```kotlin
composable<CampusNews> {
    CampusNewsRoute(onBackClick = { navController.popBackStack() })
}
```

**4.4** Expected back stack (screen destinations, bottom → top):

| Action | Back stack |
| --- | --- |
| App starts | Home |
| Tap Rate CSC 402 | Home → CourseRating("CSC 402") |
| Press system Back | Home |
| Tap Campus news | Home → CampusNews |
| Tap the Back button | Home |

**4.5** navigate replaces the manual transaction that replaces the screen. Navigation automatically adds the destination to its back stack; popBackStack removes it to return to the previous screen.

**4.6** Source audit: one Activity in the manifest; no legacy screen lookup or transaction code; AndroidView only in StarRating and CampusWebPage; Route/Screen separation; only CourseApp accesses navController. Device verification remains pending.
