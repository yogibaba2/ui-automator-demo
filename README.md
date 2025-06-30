# UI Automator Demo

This project demonstrates automated UI testing for Android applications using [UIAutomator2](https://developer.android.com/training/testing/other-components/ui-automator) and TestNG/JUnit frameworks.

## Project Structure

- **app/**: Main Android application module.
  - **src/androidTest/**: Contains UIAutomator test cases and supporting classes.
    - **tests/junit/**: JUnit-based UI tests.
    - **tests/testng/**: TestNG-based UI tests.
    - **util/**: Utility classes for device actions.
    - **watcher/**: UIAutomator watchers for handling dialogs.
    - **assets/testng.xml**: TestNG suite configuration.
  - **src/main/**: Application source code and resources.
- **gradle/**: Gradle version catalogs and wrapper.
- **build.gradle.kts**: Project-level Gradle configuration.

## Features

- Automated UI tests for system and third-party apps (e.g., Calculator, Camera, Settings).
- TestNG and JUnit integration for flexible test execution.
- UIAutomator watchers for handling runtime permission dialogs.
- Utility methods for common device actions (launching apps, swiping, etc.).

## Running Tests

1. **Build the project:**
   ```sh
   ./gradlew assembleDebug
   ```

2. **Run instrumented tests:**
   ```sh
   ./gradlew connectedAndroidTest
   ```

   - JUnit tests will run automatically.
   - To run TestNG suites, use the [`TestNGInstrumentationRunner`](app/src/androidTest/java/com/example/myapplication/tests/testng/TestNGInstrumentationRunner.java) or configure your test runner accordingly.

## Dependencies

- AndroidX Test
- UIAutomator2
- JUnit
- TestNG

See [app/build.gradle.kts](app/build.gradle.kts) and [gradle/libs.versions.toml](gradle/libs.versions.toml) for details.

## Customization

- Add new UI tests in the appropriate package under `app/src/androidTest/java/com/example/myapplication/tests/`.
- Update `testng.xml` in `app/src/androidTest/assets/` to configure TestNG suites.

## License

This project is licensed under the Apache License 2.0.