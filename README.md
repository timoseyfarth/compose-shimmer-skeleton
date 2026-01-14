# Compose Shimmer Skeleton

[![Maven Central](https://img.shields.io/maven-central/v/dev.seyfarth/compose-shimmer-skeleton)](https://central.sonatype.com/artifact/dev.seyfarth/compose-shimmer-skeleton)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

A **Jetpack Compose** library providing a customizable shimmer effect modifier and predefined skeleton loaders for **Jetpack Compose**, and **Kotlin Multiplatform** (incl. Compose Multiplatform) (Android, iOS, Desktop).

✨ Special about this library: Seamless Global Synchronization: Create a unified loading experience with zero effort. Shimmer effects are automatically coordinated across your entire screen, ensuring animations flow perfectly from left-to-right. Just apply the modifier—the library handles the timing for you. 

Note: Watch the demo below to see global sync in action. Notice how elements on the right side of the screen wait for the shimmer "pulse" to reach them, creating a unified transition.

![Shimmer Demo Darkmode](https://github.com/user-attachments/assets/0c71f479-a19e-4cdd-b809-6d0a5158974e)

![Shimmer Demo Lightmode](https://github.com/user-attachments/assets/5ed8b7c3-bad3-4239-8258-39bf98fcef61)
---

## 📦 Installation

### Standard Gradle
Add the dependency to your `commonMain` source set or module-level `build.gradle.kts`:

```kts
implementation("dev.seyfarth:compose-shimmer-skeleton:1.0.0")
```

### Version Catalog `libs.versions.toml`
If you use a version catalog, add the following to your `gradle/libs.versions.toml` file:
```toml
[versions]
composeShimmerSkeleton = "1.0.0"

[libraries]
compose-shimmer-skeleton = { module = "dev.seyfarth:compose-shimmer-skeleton", version.ref = "composeShimmerSkeleton" }
```

Then in your `commonMain` source set or module-level `build.gradle.kts`, you can use the dependency like this:

```kts
implementation(libs.compose.shimmer.skeleton)
```

## 🚀 Usage

The shimmer effect is accessible via the `shimmerEffect` modifier.
```kotlin
@Composable
fun LoadingItem(isLoading: Boolean) {
    Box(
        modifier = Modifier
            .size(128.dp)
            .shimmerEffect(
                visible = isLoading,
                shape = RoundedCornerShape(8.dp)
            )
    )
}
```

### Parameters:

| Parameter            | Description                                                                                    | Default Value                                                      |
|----------------------|------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| `visible`            | Controls whether the shimmer effect is shown. When `false`, the shimmer animation is disabled. | `true`                                                             |
| `shape`              | The shape of the shimmer effect (rounded corners, circle, etc.).                               | `RoundedCornerShape(4.dp)`                                         |
| `baseColor`          | The base background color of the shimmer effect.                                               | `Color(0xFF2C2C2C)` (Light mode) / `Color(0xFFE0E0E0)` (Dark Mode) |
| `highlightColor`     | The highlight color that sweeps across the base color.                                         | `Color(0xFF3A3A3A)` (Light Mode) / `Color(0xFFF5F5F5)` (Dark Mode) |
| `durationMillis`     | Duration of one complete shimmer animation cycle in milliseconds.                              | `2300`                                                             |
| `widthOfShadowBrush` | The width of the shimmer gradient brush.                                                       | `500.dp`                                                           |
| `shimmerTravel`      | The distance the shimmer effect travels during animation.                                      | `300.dp`                                                           |
| `angle`              | The angle (in degrees) at which the shimmer sweeps across the component.                       | `30f`                                                              |

## ✨ Features & Info

- **Multiplatform Ready**: Works on Android, iOS, and JVM (Desktop) targets supported by Compose Multiplatform.
- **Modifier Based**: Apply a shimmer effect to any Composable using `Modifier.shimmerEffect()`.
- **Coordinated Animation**: Shimmers are automatically synchronized across the screen, creating a polished, unified loading experience.
- **Customizable**: Fine-tune colors, duration, width, travel distance, and angle of the shimmer.
- **Lightweight**: Minimal overhead, optimized for performance.

## 🚗 Roadmap

- [ ] Add pre-defined skeleton loaders.
- [ ] Add better Light/Dark theme support.
- [ ] Add support integrating the shimmer effect better in the individual App's Theme.


