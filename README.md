# Lifting Calculations by PT

An Android 8.0+ (API 26) Jetpack Compose app providing on-site lifting, rigging and crane calculator tools inspired by LEO Rigging Calculator and Crangle. Designed for field use with offline-first calculations and best practice safety references.

## Features

- Sling Tension calculator (2–4 legs, angle factor, eccentric COG support)
- Ground Bearing Pressure (GBP - Outrigger mode; includes soil checks, mat sizing)
- Extendable for:
    - Boom Clearance / Obstacle check
    - Tailing Crane Load Share
    - Wind Advisory
    - Metal/Shape Weight and COG
    - Planner for lift site diagrams
- PDF export (planned)
- Inputs and outputs can be toggled for metric/imperial units

## Architecture

- Kotlin, Jetpack Compose, Material 3 UI
- Clean multi-module project:
    - :app (Main app UI)
    - :core-math (Formulas, solvers, unit conversions)
    - :domain (Models/interfaces)
    - :data (Room/DAO, stubs)
    - :ui (Composable calculator screens)
    - :export (PDF/report generation stubs)
- Android minSdk 26, targetSdk latest
- GitHub Actions CI: builds debug APK & release AAB on every push

## How To Build

1. Clone the repo  
   `git clone https://github.com/puneett56/PT-Lifting-Calculations.git`
2. Open with Android Studio Hedgehog or newer
3. Let Gradle sync; run the app module on any Android emulator/device (API 26+)

## Continuous Integration with GitHub Actions

- On push to main, CI produces:
    - `app/build/outputs/apk/debug/app-debug.apk`
    - `app/build/outputs/bundle/release/app-release.aab`
- Artifacts can be downloaded from the Actions tab after each successful workflow run.

## Disclaimer

Calculations are for planning and field aid only.
- Always use the latest OEM crane/rigger charts, site geotechnical reports, and local lift safety standards.
- This app does not substitute for engineering design, official lift plan, or OEM software/tools.

## Extending the App

To add new calculators (e.g., Crawler GBP, Range Chart Planner), copy the pattern of separate math and UI modules, then register the screen in `HomeScreen.kt`. For more features or PDF formatting, see stubs in the export module.

## License

[MIT] or specify your licensing.

---

For further expansion (e.g., sample UI, implementation notes), see developer notes in the domain and core-math modules. For typical soil values, mat sizing, and safety tables, reference project PDFs or CICA/CANZ/ASME standards.

---

You can copy/paste this README.md into your repo to match the architecture, safety usage, and developer workflow agreed so far.
