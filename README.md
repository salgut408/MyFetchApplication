# MyFetchApplication

- 100% Kotlin & UI written in Jetpack Compose 
- Dependacy Injections with Hilt 
- Local storage with Room
- UI state handling (Screen Ui states)
- Jetpack Compose Nagivation
- ViewModels
- Retrofit
- MVVM Architecture
- Coroutines

### Features
This app shows a sorted list of items retrieved from an API using Retrofit. You are able to add an item to your favorites list and view a list of all items you have saved by tapping the "favorites" icon in the bottom toolbar.

Each UI 'screen' has its own ViewModel, and exposes a single StateFlow containing the entire view state for the screen. 
The ViewModel is responsible for subscribing to  data streams  for a view, and exposes functions to allow the UI to react to UI events.

A ViewModel is implemented as `MainScreenViewModel`, exposing a `StateFlow` for the UI to observe and react to. `MainScreenUiState` contains the view state for the screen as a data class... 
The UI uses `MainScreenViewModel`, and observes the `MainScreenViewState` as Compose State, using `collectAsStateWithLifecycle()`

### Saved state across configuration changes
- ViewModels are used to save state across any configuration changes.
- Certain composable states are saved across configurations changes using `rememberSaveable` in the `SaveItemButton`


The Repository class is responsible for handling the data fetching of all information, as well as a centralized place for all data changes*

Single activity app, `MainActivity` contains `MyFetchApp()` composable which is entrypoint to the app. 
`MyFetchApp()` sets up a layout at the app level using  `Scaffold` consisting to a scrolling top app bar and a bottom bar. The content in this `Scaffold`
is the app's `Navigation()` composable where the app's NavHost is defined. `MyFetchAppState` holds the actual NavHostController for all of the app.





### Screens
This app contains 2 screens:
- __MainScreen__ is where a sorted list of Items is displayed. You can click to add an item to a "Favorites list" which is stored to a FavoriteItems table in Room. The `SaveItemButton` will change to show you you have added the item to your favorites list by changing. This screen allows you to navigate to the `FavoritesScreen`

- __FavoritesScreen__ is where a list of favorited items is displayed. 






