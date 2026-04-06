# Intel by Social Masla: Noir Editorial App

**Intel by Social Masla** is a premium, high-performance Android application built with Jetpack Compose. It serves as a dual-feed intelligence hub: delivering real-time AdTech news (Intel) and a direct portal to Social Masla's elite digital services.

## 1. Visual Identity (Noir Design System)
- **High-Contrast Noir**: Immersive Pure Black (`#000000`) and Pure White (`#FFFFFF`) aesthetic.
- **Intel Deck**: Black-themed immersive news cards with Emerald-accented progress bars.
- **Services Hub**: White-themed minimalist portfolio for maximum clarity.
- **Signature Accent**: Social Masla Emerald Green (`#16a34a`).
- **Typography**: **Outfit** (Headlines) and **Inter** (Text).

## 2. Core Features
- **Intel Deck (Intelligence Layer)**: 
    - Real-time "Advertising Technology" news via GNews API.
    - Bidirectional swipe gestures and 30s auto-timer engine.
    - Robust "Verified Intel" fallback feed for offline reliability.
- **Services Portfolio**:
    - Direct booking links for Performance Marketing, AI Automations, and WhatsApp API.
    - Staggered slide-up entry animations for a premium feel.
- **Identity**: Custom branded icon and Play Store-ready Noir launch screens.

## 3. Technical Setup
- **API Setup**: To enable live news, add `GNEWS_API_KEY=your_key` to `local.properties`.
- **Architecture**: MVVM + StateFlow.
- **UI Framework**: Jetpack Compose.
- **Networking**: Retrofit 2 + Moshi.
- **Tracking**: Firebase Analytics + Google Ads SDK linkage for ACe (App Campaign for Engagement) demonstrations.

## 4. Performance Marketing Strategy
In our YouTube tutorials, we use this app to demonstrate:
1.  **Conversion Events**: Logging "Read More" and "Swipe" events for audience segmenting.
2.  **App Campaign Optimization**: Using in-app custom events as target conversions for Google Ads ACe campaigns.
3.  **Retention Design**: Using the intelligence layer (Intel) to drive high Daily Active Users (DAU).
