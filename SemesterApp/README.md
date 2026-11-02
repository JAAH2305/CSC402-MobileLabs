# Study Planner — Semester App Part 4

A single-Activity Material 3 Compose application with a reusable task row, five sample tasks, computed progress, typed navigation to task details, and an ACTION_SEND share Intent.

## Open and run

Open the `SemesterApp` folder directly in Android Studio. Sync Gradle and run `app` on an API 26+ emulator or phone. Use JDK 17 and install Android SDK Platform 35 / Build Tools 35.0.0 if requested. See `../Lab7/README.md` for terminal build commands.

No earlier Study Planner implementation was present in this repository (only guided Labs 1–4). This is a new baseline including the navigation, share action, and saved state required by Parts 2–3. It does not claim to merge an unseen earlier project. Continue this project for later Semester App parts.

## Requirement mapping

| Requirement | Implementation |
| --- | --- |
| P4.1 | See Submission.md; draw and photograph your own paper sketch |
| P4.2 | model/StudyTask.kt; ui/PlannerUiState.kt with computed getters |
| P4.3 | ui/components/TaskItem.kt: stateless row and Checkbox callbacks |
| P4.4 | ui/PlannerHome.kt: LazyColumn with stable task.id keys |
| P4.5 | PlannerHomeRoute owns saved tasks; map + copy updates; PlannerApp handles task-id navigation |
| P4.6 | TaskItemNotDonePreview, TaskItemDonePreview, PlannerHomeFiveTasksPreview |
| P4.7 | One Activity; Compose screens and Material 3 widgets |

Titles and courses are fixed sample data. The detail route resolves its task by id and displays the title/course; the mutable completion list remains owned by PlannerHomeRoute. A listSaver preserves the list on rotation and when navigating back. No ViewModel, database, or persistence across force-stop/relaunch is added in this part.

## Verify on your device

1. Open the home list: five tasks, initially 0 of 5 done.
2. Tick two tasks: progress should say 2 of 5 done. Ticking a checkbox must not navigate.
3. Tap a task title: the matching detail screen opens. Share task should open Android's chooser.
4. Return with Back: completion state should remain.
5. Rotate the device: completion state should remain.
6. Open the split/design view of TaskItem.kt and PlannerHome.kt and render all three @Preview functions.

A unit test checks computed progress and an unchanged original list. Full Android build, preview rendering, device execution, and screenshots require your Android environment; see `../Lab7/Verification.md`.
