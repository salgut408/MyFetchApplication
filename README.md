# MyFetchApplication



- UI written in Jetpack Compose 
- Dependency Injections with Hilt 
- Local storage with Room
- Screen Ui states
- Jetpack Compose Navigation
- ViewModels
- Retrofit
- MVVM Architecture
- Coroutines / Flows

### Features
This app shows a sorted list of items retrieved from an API using Retrofit. You are able to add an item to your favorites list and view a list of all items you have saved by tapping the "favorites" icon in the bottom toolbar.

Each UI 'screen' has its own ViewModel, and exposes a  SharedFlow containing the state for the screen. 

### MVVM
A ViewModel is implemented as `MainScreenViewModel`, exposing a `SharedFlow<MainScreenUiState>` for the UI to observe and react to. 
`MainScreenUiState` contains the view state data for the screen as a data class consisting of a sealed interface containing the "Content" (a list of items) and a loading state...
The UI uses `MainScreenViewModel`, and takes the `MainScreenViewState` as State, using `collectAsStateWithLifecycle()` so that it collects in a way that is aware of the UI's lifecycle. Using MVVM allows work to be done on one layer  without distrubing  other layers

The ItemRepository class is responsible for handling the data fetching of data, as well as a centralized place data access

### Sorting and filter
Different strategies were thought about being used for sorting and filtering the information displayed within the app...

-__ViewModel__ using flows the `ItemDao` returns a UNSORTED list of Items with the function `getAllInfoFromDb`, this function is then called in the `ItemRepository` function `getAllInfoFromRepository` the list is finally sorted by the `MainScreenViewModel` using the helper class `ItemComparator` on a flow of data from the repository. This follows the thought that all ui data that will be displayed by the ui will be managed by the ViewModel and uses Flows advantages of have the viewModel operate as an intermediary between the data stream producing `ItemRepository` before consumption by the UI.

-__Database__ the `ItemDao` would return a sorted list with the suspend function `getSortedListExNullsExBlanksFromRepository` this uses a query to sort and filter out items in the list...this way was not used to preserve data integrity coming from the data layer.
  

### Saved state across configuration changes
- ViewModels are used to save state across any configuration changes.
- Certain composable states are saved across configurations changes using `rememberSaveable` in the `SaveItemButton` `rememberSaveable` will save the value in a bundle...

### Navigation
Single activity app, `MainActivity` contains `MyFetchApp()` composable which acts as the entrypoint to the app. 
`MyFetchApp()` sets up a layout at the app level using  `Scaffold` consisting to a scrolling top app bar and a bottom bar. The content in this `Scaffold`
is the app's `Navigation()` composable where the app's NavHost is defined. `MyFetchAppState` holds the actual NavHostController for all of the app.

### Screens
This app contains 2 screens:
- __MainScreen__ is where a sorted list of Items is displayed. You can click to add an item to a "Favorites list" which is stored to a FavoriteItems table in Room. The `SaveItemButton` will animate when you have added or remoed an item from the favorites. This screen allows you to navigate to the `FavoritesScreen`

- __FavoritesScreen__ is where a list of favorited items is displayed. 

### Screenshots

![Screenshot_20230228_162859](https://user-images.githubusercontent.com/61568828/221996345-22ee1e24-4dda-4a60-8fde-c38c5770cd82.png)

![Screenshot_20230228_162916](https://user-images.githubusercontent.com/61568828/221996559-6eb9701e-50f7-47a6-9e95-1f15a8fa805c.png)



