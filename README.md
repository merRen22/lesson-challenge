# Code Challenge

This is a technical test build for consuming an endpoint and displaying the data as well as saving the data to a local database

![alt text](https://raw.githubusercontent.com/merRen22/lesson-challenge/main/showcase/1.png)
![alt text](https://raw.githubusercontent.com/merRen22/lesson-challenge/main/showcase/2.png)

### Libraries used

- Navigation (Fragment transitions)
- View Binding (Bind views)
- ViewModel (Store and manage UI-related data)
- LveData (Observable data)
- Kotlin Coroutine (Light-weight threads)
- Dagger2 (Dependency Injection)
- Hilt (Dependency Injection for Android)
- Retrofit (HTTP client)
- Multi Module App
- Compose

## Modules

The project contains the following modules:

- App ( Initial module with the main activity of the project. Because single activity was used on the project )
- Base ( Contains the navigation between the UI modules and generic classes )
- Data ( Used for making request to the API and the the local BD)
- Model ( Declares all the entities used in the project )
- Lesson ( The user interact and completes a lesson )
- Summary ( Contains the data of all the lessons completed by the user ) (this modules was build using dynamic-feature so its not built on the original version of the app instead is download on demand )

## Architecture

This app was build using MVVM and following the guidelines explain [here](https://developer.android.com/jetpack/docs/guide). This app also makes use of the pattern single activity.

All the transitions for the app where made using [Navigation](https://developer.android.com/guide/navigation)

## Run the app locally
As the app makes usd of dynamic modules in order to test it locally you need to generate a universal version containing all the modules. The following commands allow to generate the apk 👇

```sh
//En windows
gradlew :app:packageDebugUniversalApk
//En mac/linux
./gradlew :app:packageDebugUniversalApk
```