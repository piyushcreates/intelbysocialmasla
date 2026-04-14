# Intel by Social Masla: Noir Editorial App

**Intel by Social Masla** is a premium, high-performance Android application built with Jetpack Compose. It serves as a dual-feed intelligence hub: delivering real-time AdTech news (Intel) and a direct portal to Social Masla's elite digital services.

## 1. Visual Identity (Noir Design System)
- **High-Contrast Noir**: Immersive Pure Black (`#000000`) and Pure White (`#FFFFFF`) aesthetic.
- **Intel Deck**: Pure White background with immersive Black cards.
- **Services Hub**: Pure White minimalist portfolio for maximum clarity.
- **Typography**: **Outfit** (Headlines) and **Inter** (Text).

## 2. Core Features
- **Intel Deck (Intelligence Layer)**: 
    - Real-time "Advertising Technology" news via GNews API.
    - **Smart Caching**: 30-minute fetch window for maximum data efficiency.
    - Bidirectional swipe gestures and 30s auto-timer engine.
    - Robust "Verified Intel" fallback feed for offline reliability.
- **Services Portfolio**:
    - Direct booking links for Performance Marketing, AI Automations, and WhatsApp API.
    - Staggered slide-up entry animations for a premium feel.
- **Identity**: Custom branded icon and **Unified BIG Splash** (256dp) launch sequence.

## 3. Technical Setup
- **Architecture**: MVVM + StateFlow.
- **UI Framework**: Jetpack Compose.
- **Networking**: Retrofit 2 + Moshi.
- **Tracking**: Dual-SDK integration for **Meta Ads** and **Google Ads (Firebase)**.
- **Optimization**: Unified `AnalyticsHelper` using **Standard Events** (`ViewContent`, `Contact`, `Lead`) for automated attribution.

## 4. Performance Marketing Strategy
In our YouTube tutorials, we use this app to demonstrate:
1.  **Conversion Events**: Logging "Read More" and "Swipe" events for audience segmenting.
2.  **App Campaign Optimization**: Using in-app custom events as target conversions for Google Ads ACe campaigns.
3.  **Retention Design**: Using the intelligence layer (Intel) to drive high Daily Active Users (DAU).
