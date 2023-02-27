## MyFetchApplication

- 100% Kotlin & UI written in Jetpack Compose 
- Dependacy Injections with Hilt 
- Local storage with Room
- UI state handling (Screen Ui states)
- Jetpack Compose Nagivation
- ViewModels
- Retrofit
- MVVM Architecture

## Features
This app shows a sorted list of items retrieved from an API using Retrofit. You are able to add an item to your favorites list and view a list of all items you have saved by tapping the "favorites" icon in the bottom toolbar.

Each UI 'screen' has its own ViewModel, which exposes a single StateFlow containing the entire view state. 
Each ViewModel is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.

This pattern is used across the different screens: The ViewModel is implemented as `MainScreenViewModel`, which exposes a `StateFlow` for the UI to observe and react to. `MainScreenUiState` contains the complete view state for the screen as a data class. 
The UI uses `MainScreenViewModel`, and observes it's `MainScreenViewState` as Compose State, using `collectAsStateWithLifecycle()`

#Saved state across configuration changes
ViewModels are used to save state across any configuration changes.
Certain composable states are saved across configurations changes using `rememberSaveable` in the `SaveItemButton`


The Repository class is responsible for handling the data fetching of all information, as well as a centralized place for all data changes*

Single activity app, `MainActivity` contains `MyFetchApp()` composable which is entrypoint to the app. 
`MyFetchApp()` sets up a layout at the app level using  `Scaffold` consisting to a scrolling top app bar and a bottom bar. The content in this `Scaffold`
is the app's `Navigation()` composable where the app's NavHost is defined. `MyFetchAppState` holds the actual NavHostController for all of the app.





## Screens
This app contains 2 screens:
- __MainScreen__ is where a sorted list of Items is displayed. You can click to add an item to a "Favorites list" which is stored to a FavoriteItems table in Room. The `SaveItemButton` will change to show you you have added the item to your favorites list by changing 

__FavoritesScreen__ is where a list of favorited items is displayed. 






