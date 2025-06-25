# MyTestApp - Android Category Viewer

## Project Overview
MyTestApp is an Android application that demonstrates fetching category data from a remote API and storing it in a local SQLite database. The app showcases modern Android development practices including:

- RESTful API integration with authentication
- Local SQLite database implementation
- Asynchronous network operations
- Builder pattern for object creation
- Material Design UI components

## Features
- Fetch category data from a remote server
- Store category information in a local SQLite database
- Display category information to users
- Support for offline data access
- Simple and intuitive user interface

## Screenshots
[Add screenshots of your application here]

## Technical Details

### Architecture
The application follows a simple architecture with the following components:
- **MainActivity**: Main entry point for the application with a button to fetch categories
- **DatabaseHelper**: SQLite database management for local storage
- **Category**: Data model for category information using Builder pattern
- **CategoryData**: Contract class for database schema definition
- **NetworkTask**: Asynchronous HTTP request handling with authentication

### User Interface
The application uses Material Design components:
- Toolbar for app navigation
- Floating Action Button for primary actions
- ListView for displaying category data
- Standard Android buttons and text elements

### API Integration
The application connects to a remote API endpoint to fetch category data. It uses:
- HttpURLConnection for network requests
- Basic authentication for API security
- JSON parsing for data processing
- Asynchronous tasks to prevent UI blocking

### Database Schema
The local SQLite database stores category information with the following schema:
- **id**: Primary key for the category
- **name**: Name of the category
- **iconUrl**: URL to the category icon
- **createdBy**: Creator of the category
- **modifiedBy**: Last modifier of the category

## Development Setup

### Prerequisites
- Android Studio 1.5 or higher
- Android SDK with API level 23 (Android 6.0 Marshmallow)
- JDK 7 or higher
- Gradle 2.8 or higher

### Building the Project
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Build and run the application on an emulator or physical device

### Dependencies
- Android Support Library v23.1.1
- Android Design Support Library v23.1.1
- HttpClient v4.4.1.1

## Project Structure
```
app/
├── src/main/
│   ├── java/com/example/satselva/mytestapp/
│   │   ├── MainActivity.java         # Main activity with UI logic
│   │   ├── Category.java             # Data model for categories
│   │   ├── CategoryData.java         # Database contract class
│   │   ├── DatabaseHelper.java       # SQLite database operations
│   │   └── NetworkTask.java          # Async HTTP operations
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml     # Main activity layout
│   │   │   └── content_main.xml      # Content layout with UI elements
│   │   ├── menu/
│   │   │   └── menu_main.xml         # Main menu options
│   │   └── values/
│   │       ├── strings.xml           # String resources
│   │       ├── colors.xml            # Color definitions
│   │       └── styles.xml            # Style definitions
│   └── AndroidManifest.xml           # App manifest with permissions
└── build.gradle                      # App-level build configuration
```

## How to Use
1. Launch the application
2. Press the "List Category" button to fetch categories from the server
3. Categories will be fetched and stored in the local database
4. Check the Android Logcat for debug information about the fetched categories

## Future Enhancements
- Implement article data model and storage
- Add user interface for category browsing
- Implement caching mechanism for offline use
- Add unit and integration tests
- Improve error handling and user feedback
- Add search functionality for categories
- Implement pagination for large datasets

## Troubleshooting
- If categories are not loading, check your internet connection
- For debugging, enable USB debugging and check Logcat output
- The app requires internet permission to function properly

## License
[Add your license information here]

## Contributors
- satselva (Creator)
