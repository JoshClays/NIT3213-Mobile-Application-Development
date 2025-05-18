# NIT3213 Mobile Application Development – Final Assignment

This repository contains the Android application developed as the final assignment for the NIT3213 Mobile Application Development unit at Victoria University. The application demonstrates proficiency in Android development, including user authentication, data retrieval from an API, and dynamic UI rendering.

- 🔐 Secure user authentication
- 📚 Browse book collection with pull-to-refresh functionality
- 🎨 Material 3 design implementation
- 📱 Responsive and intuitive UI
- ⚡ Fast and efficient data loading
- 🔄 Real-time updates with SwipeRefreshLayout
- 📝 Detailed book information display

## Technology Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **Dependency Injection**: Hilt
- **Networking**: Retrofit with OkHttp
- **Asynchronous Programming**: Kotlin Coroutines
- **UI Components**: Material 3 Design
- **Testing**: JUnit, MockK
- **Custom Font**: Montserrat font family

## Prerequisites

- Android Studio Arctic Fox or later
- JDK 11 or later
- Android SDK with minimum API level 24
- Gradle 7.0 or later

## Setup and Installation

There are several ways to obtain and set up the project:

### Method 1: Clone with Git

1. Open a terminal and run:
   ```bash
   git clone https://github.com/JoshClays/NIT3213-Mobile-Application-Development.git
   ```

### Method 2: Download ZIP

1. Visit the repository at https://github.com/JoshClays/NIT3213-Mobile-Application-Development
2. Click the green "Code" button
3. Select "Download ZIP"
4. Extract the downloaded ZIP file to your desired location

### Method 3: GitHub Desktop

1. Install GitHub Desktop from https://desktop.github.com/
2. Click File > Clone Repository
3. Select the URL tab
4. Enter: https://github.com/JoshClays/NIT3213-Mobile-Application-Development.git
5. Choose your local path
6. Click "Clone"

### After obtaining the project:

1. Open Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the project directory and click "OK"
4. Wait for the Gradle sync to complete

### Configure Dependencies

Ensure all dependencies are properly downloaded:
```gradle
dependencies {
    // Core Android dependencies
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.8.0'
    
    // Hilt for dependency injection
    implementation 'com.google.dagger:hilt-android:2.44'
    
    // Retrofit for networking
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Other dependencies as specified in build.gradle
}
```

### Final Setup

1. Configure your local.properties file with any necessary API keys or configurations
2. Ensure all required SDK components are installed through Android Studio's SDK Manager
3. Sync project with Gradle files if needed

## Building and Running

1. Connect an Android device or start an emulator

2. Select 'app' from the run configurations dropdown

3. Click the 'Run' button (or press Shift + F10)

## Testing

The application includes comprehensive unit tests for core components:

- Repository Tests
- ViewModel Tests
- API Service Tests

To run tests:

1. Right-click on the test directory
2. Select 'Run Tests'
   or
   Run `./gradlew test` from the command line

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/josh_s8066611finalassignment/
│   │   │   ├── data/
│   │   │   │   ├── api/
│   │   │   │   ├── model/
│   │   │   ├── di/
│   │   │   ├── repository/
│   │   │   ├── ui/
│   │   │   │   ├── dashboard/
│   │   │   │   ├── details/
│   │   │   │   ├── login/
│   │   │   ├── viewmodel/
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   ├── test/
│   │   ├── java/
```

## Architecture

The app follows the MVVM architecture pattern:

- **Model**: Data classes and repositories
- **View**: Activities and custom views
- **ViewModel**: Business logic and state management

## Contributing

This is a final assignment project. No contributions are currently accepted.

## License

This project is created for educational purposes as part of a university assignment.

## Author

Josh - s8066611

## Acknowledgments

- Material 3 Design Guidelines
- Android Development Best Practices
- Modern Android Development Techniques