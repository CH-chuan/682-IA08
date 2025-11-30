# Jetpack Compose Doodle App

A modern Android drawing application built entirely with **Kotlin** and **Jetpack Compose**. This project demonstrates how to create a custom drawing surface without using XML layouts or the legacy Android View system.

## Features

- **Pure Compose UI**: 100% Kotlin and Jetpack Compose. No XML layouts.
- **Drawing Surface**: Custom drawing engine using Compose `Canvas` and gesture detection.
- **Tool Panel**:
  - **Color Picker**: Select from preset colors (Black, Red, Blue, Green, Yellow).
  - **Brush Size**: Adjustable stroke width via slider.
  - **Stroke Styles**: Support for Solid, Dashed, and Dotted lines.
  - **Eraser**: Switch to eraser mode to correct mistakes.
  - **Clear Canvas**: Instantly reset the drawing area.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material3)
- **State Management**: Compose `State` (`remember`, `mutableStateOf`, `mutableStateListOf`)

## Project Structure

- `MainActivity.kt`: Entry point and main screen layout.
- `DrawingCanvas.kt`: Handles the drawing logic, `Canvas` composable, and touch gestures.
- `ToolPanel.kt`: UI for the bottom control panel (colors, slider, buttons).
- `DrawingState.kt`: Data classes and enums for stroke data (`Stroke`, `StrokeStyle`).

## Setup & Installation

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/CH-chuan/682-IA08.git
    ```
2.  **Open in Android Studio**:
    - Launch Android Studio.
    - Select **Open** and navigate to the cloned directory.

3.  **Sync Gradle**:
    - Allow Android Studio to download dependencies and sync the project.
4.  **Run the App**:
    - Connect an Android device or start an Emulator.
    - Click the **Run** button (green play icon).

### Command Line
Alternatively, you can build and install the app using the Gradle wrapper:

```bash
# Build and install debug APK
./gradlew installDebug
```

## How to Use

1.  **Draw**: Drag your finger across the white canvas area to draw.
2.  **Change Color**: Tap a color circle in the bottom panel.
3.  **Adjust Size**: Slide the "Brush Size" slider to change thickness.
4.  **Change Style**: Tap "Solid", "Dashed", or "Dotted" to change the line pattern.
5.  **Erase**: Tap the "Eraser" button to remove parts of the drawing.
6.  **Clear**: Tap "Clear Canvas" to remove everything.

## References

The following resources were used to build this prototype:

-   **Jetpack Compose**: [Official Documentation](https://developer.android.com/jetpack/compose)
-   **Graphics & Drawing**: [Compose Graphics](https://developer.android.com/jetpack/compose/graphics)
-   **Gestures**: [Detecting Gestures in Compose](https://developer.android.com/jetpack/compose/touch-input/pointer-input)
-   **Material Design 3**: [Material3 Components](https://developer.android.com/jetpack/compose/designsystems/material3)
