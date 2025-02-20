# Nutritional Book

**Nutritional Book** is an Android app designed to provide users with detailed nutritional information about various foods. The app includes features like exploring food nutritional details, viewing a breakdown of key nutrients, and fetching up-to-date data.

## Features

- **Food List**: A list of foods displayed with key nutritional information such as calories, proteins, fats, and carbohydrates.
- **Food Details**: Tap a food item to access detailed information, including an image and complete nutritional breakdown.
- **Pull to Refresh**: Swipe the screen to refresh and get the latest data from the server.
- **Offline Support**: Store nutritional information locally using Room database, so users can access data even without an internet connection.
- **Networking**: Fetch nutritional information from a remote server using Retrofit.
- **Navigation**: Navigate smoothly between screens with Android's Navigation Component.

## Technologies

- **Kotlin** – Used as the main programming language for the app.
- **Android Jetpack** – Employed throughout the app for components like Navigation, ViewModel, LiveData, and Room.
- **Retrofit** – Utilized for making network calls to fetch food data.
- **Room** – For local database handling and storing food data offline.
- **Glide** – For image loading and caching.
- **SwipeRefreshLayout** – To implement the swipe-to-refresh functionality for list updating.

## Prerequisites

- **Android Studio** (version 2021.1.1 or higher recommended)
- **Android SDK 24+**
- **Internet Access** for data fetching

## App Usage

- **Main Screen**: The main screen shows a list of foods with basic nutritional details such as calories and protein.
- **Food Detail View**: Tapping on any food item opens a new screen that shows more detailed nutritional data and an image of the food.
- **Refresh Feature**: You can refresh the list by swiping down on the main screen to pull the latest data from the server.

## App Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture, ensuring separation of concerns and better maintainability:

- **Model**: Represents data, handles network calls, and interacts with the Room database.
- **View**: Responsible for the UI components that display the data to the user.
- **ViewModel**: Manages UI-related data lifecycle-consciously and handles the business logic for UI updates.

