# NIT3213 Mobile Application Development – Final Assignment

This repository contains the Android application developed as the final assignment for the NIT3213 Mobile Application Development unit at Victoria University. The application demonstrates proficiency in Android development, including user authentication, data retrieval from an API, and dynamic UI rendering. This project belongs to Student of Victoria University with ID s8066611 - Josh Clayton.

## 📱 Features

* **User Authentication**: Allows users to log in using their credentials.
* **Entity Dashboard**: Displays a list of entities retrieved from a remote API.
* **Entity Details View**: Shows detailed information about a selected entity.
* **Dynamic Field Detection**: Automatically identifies and displays relevant fields from the API response.
* **Responsive UI**: Ensures compatibility across various device screen sizes.
* **Material Design Implementation**:
    * Custom-styled cards and components
    * Sophisticated dark theme with carefully chosen color palette
    * Enhanced typography using Montserrat font family
    * Proper spacing and visual hierarchy

## 🛠️ Technologies & Dependencies

The application leverages modern Android development tools and libraries:

* **Kotlin**: Primary programming language.
* **Android Jetpack Components**:
    * **ViewModel**: Manages UI-related data in a lifecycle-conscious way.
    * **LiveData**: Provides data that is lifecycle-aware.
* **Retrofit**: Handles HTTP requests and integrates with the API.
* **Gson**: Parses JSON data from the API responses.
* **Hilt**: Facilitates dependency injection.
* **Coroutines**: Manages asynchronous tasks.
* **Material Design Components**: Implements modern Android UI elements.
* **Custom Fonts**: Montserrat font family integration.

## ⚙️ Setup Instructions

To set up and run the application locally:

1. **Clone Repository**  
   git clone https://github.com/JoshClays/NIT3213-Mobile-Application-Development.git  
   cd NIT3213-Mobile-Application-Development
2. **Open in Android Studio**
    * Open the project directory using Android Studio.
    * Sync Gradle files if prompted.
    * Run the app on an emulator or physical device.

## 🎨 Design Choices

The application features a carefully crafted UI with:

* **Color Scheme**:
    * Dark theme background (#1C1B21)
    * Turquoise accent color (#7FCFCF) for genre indicators
    * High contrast text for better readability

* **Typography**:
    * Montserrat font family with multiple weights
    * Proper text hierarchy and spacing
    * Enhanced readability across all screens

* **Layout**:
    * Consistent 16dp corner radius
    * Proper elevation and spacing
    * Intuitive information hierarchy
    * Person icon integration for author display