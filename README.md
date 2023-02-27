## MyFetchApplication

- 100% Kotlin & UI written in Jetpack Compose 
- Dependacy Injections with Hilt 
- Local storage with Room
- UI state handling (Screen Ui states)
- Jetpack Compose Nagivation
- Retrofit
- MVVM Architecture

## Features
Each UI 'screen' has its own ViewModel, which exposes a single StateFlow containing the entire view state. 
Each ViewModel is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.

This pattern is used across the different screens: The ViewModel is implemented as [MainScreenViewModel], which exposes a [StateFlow] for the UI to observe and react to. MainScreenUiState contains the complete view state for the screen as an @Immutable data class. The UI uses [MainScreenViewModel], and observes it's [MainScreenViewState] as Compose State, using [collectAsStateWithLifecycle()]


The Repository class is responsible for handling the data fetching of all information, as well as a centralized place for all data changes*




