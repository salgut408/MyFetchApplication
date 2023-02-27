## MyFetchApplication

- 100% Kotlin & UI written in Jetpack Compose 
- Dependacy Injections with Hilt 
- Local storage with Room
- UI state handling (Screen Ui states)
- Jetpack Compose Nagivation
- Retrofit
- MVVM Architecture

## Features
Each UI 'screen' has its own ViewModel, which exposes a single StateFlow containing the entire view state. Each ViewModel is responsible for subscribing to any data streams required for the view, as well as exposing functions which allow the UI to send events.


