# StudyPlanner · CSC 402 Lecture 10

The Today screen from Lecture 8 uses Room to store tasks on the device. It observes the task table through a Flow so the list updates after a task is added or checked.

## Run in Android Studio

Open this folder as a project. Sync Gradle and run the `app` configuration on an Android emulator or phone. The project uses Android SDK 37 and requires JDK 17 or newer.

## Verify persistence

1. Launch the app and check that the empty-list message appears.
2. Add a task and check that the list updates and shows **Task added**.
3. Check off the task and look for **Marked as done**.
4. Close the app from Recent apps, then reopen it. The task and its check mark should still appear.
5. While the app is running, open **App Inspection → Database Inspector** and inspect the `study_tasks` table in `study_database`. The task's `is_done` value should be `1`.
