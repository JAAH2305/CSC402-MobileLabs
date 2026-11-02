# Lab 7 — Old Views, New Screens

## Open the guided exercise

In Android Studio, choose **Open** and select `Lab7/Lab07CourseFeedback` (the folder containing settings.gradle.kts). Wait for Gradle Sync, select an API 26+ emulator or phone, and Run `app`.

The home screen opens the rating screen and the university news page. System Back returns from the rating screen. The news screen also has a Back button.

- `Lab07CourseFeedback/`: complete standalone Android Studio project for Tasks 1–4.
- `Answers.md`: concise English answers and completed back-stack table.
- `../SemesterApp/`: the individual Study Planner Part 4 project. Keep extending this same folder in future parts.

## Build environment

JDK 17; Android SDK Platform 35; Build Tools 35.0.0; Gradle 8.11.1; Android Gradle Plugin 8.9.2; Kotlin 2.1.20; Compose BOM 2025.04.01; Navigation Compose 2.8.9. Android Studio may offer to download SDK components. These projects pin a compatible toolchain rather than changing older labs.

Windows terminal, from the project folder:
```bat
gradlew.bat assembleDebug testDebugUnitTest
```
Linux/macOS:
```sh
./gradlew assembleDebug testDebugUnitTest
```

## Guided checks to perform

1. Open Rate CSC 402, set four stars, then Reset: both stars and text should become zero.
2. Filter Logcat by `Lab07`; collect factory/update counts after three distinct taps.
3. Set four stars and rotate: the value should remain.
4. Open Campus news, follow a link, and verify it stays inside the app. Internet access is required; the university site's behavior and availability are external dependencies.
5. Verify the Back paths using the table in Answers.md.
6. Do the temporary breaking experiments from the exercise and restore the working code.

See `Verification.md` for checks actually performed. Screenshots and Logcat evidence must come from a real emulator/device run.

## References

- [Views in Compose](https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose)
- [Navigation Compose](https://developer.android.com/develop/ui/compose/navigation)
- [AGP 8.9 compatibility](https://developer.android.com/build/releases/agp-8-9-0-release-notes)
