# Yatri-Mitra: Shared Mobility Tracker 🛺
### Project ID: 84 | MindMatrix VTU Internship Program

**Yatri-Mitra** is a communal transit tracking application designed to bring predictability to shared transportation in smaller towns and rural areas. This project focuses on implementing real-time simulation logic, smooth UI rendering using Canvas, and robust state management.

---

## 📖 1. The Problem Statement
In smaller towns, commuters (specifically the rural labor force and students) have no reliable way of knowing when the next shared auto-rickshaw or bus will arrive at their stop. This lack of information leads to significant wasted time, uncertainty, and inefficient travel planning.

## 🌟 2. Detailed Description (The Vision)
Yatri-Mitra is a transit tracker that visualizes "ETA Logic" by allowing commuters to see simulated vehicles moving on a predefined route. It calculates the exact arrival time based on live distance data, transforming a traditionally unpredictable commute into a managed experience similar to city metro systems.

## 📱 3. App Usage & User Flow
*   **Route Map**: Users see a clear line representing the local transit route (e.g., Town Center to College Gate).
*   **Live Simulation**: Icons representing shared autos move smoothly along the route in real-time.
*   **ETA Calculator**: The app dynamically calculates and displays the arrival time at the user's specific stop.

## 🛠 4. Technical Implementation
*   **Language**: Kotlin
*   **UI Framework**: Jetpack Compose (Material 3)
*   **State Management**: `StateFlow` used to push real-time position updates from the logic layer to the UI.
*   **UI Layer**: Custom `Canvas` implementation for rendering vehicle markers and route lines with high performance.
*   **Architecture**: MVVM (Model-View-ViewModel) for clean separation of concerns and survival through configuration changes (like screen rotation).

### 🧮 Math Logic: The Simulation
The core "Simulation Logic" follows the physical formula:
> **ETA = Distance / Speed**

*   **Distance**: Calculated as the difference between the User's Stop (constant) and the Vehicle's Current Position (dynamic).
*   **Speed**: A constant progress rate defined in the ViewModel.
*   **Smoothness**: The UI updates every 100ms to ensure the vehicle moves across the screen without "jumping," meeting strict success criteria for animation quality.

## 🎯 5. Impact Goals
*   **Optimized Commute**: Significantly reducing waiting times for commuters.
*   **Urban-Rural Connectivity**: Bringing modern transit predictability to rural areas.
*   **Fuel Efficiency**: Helping drivers optimize trips based on passenger clusters.

## ✅ 6. Success Criteria Met
1.  **Smooth Animation**: Vehicle icons move fluidly across the Canvas.
2.  **Real-time ETA**: The countdown timer updates accurately every second.
3.  **Rotation Handling**: The simulation state persists flawlessly when rotating the device.
4.  **Documented Logic**: High-quality, documented Kotlin code for the simulation engine.

---

## 🚀 Installation & Setup
1.  Clone the repository:
    ```bash
    git clone https://github.com/your-username/project1.git
    ```
2.  Open the project in **Android Studio (Ladybug or newer)**.
3.  Ensure you have **SDK 36** (Android 16 Preview) or **SDK 35** installed.
4.  Sync Gradle and run on an emulator or physical device.

---

### **Developer Information**
**Name:** Mansi P Ram  
**Program:** MindMatrix VTU Internship Program  
**Project:** Android App Development using GenAI
