# AI Agent Instructions: Intel by Social Masla

You are tasked with building and maintaining **Intel by Social Masla** following the official **Noir Editorial Design System**.

## 1. Product Philosophy
- **Strict Noir**: High-contrast Pure Black (`#000000`) and Pure White (`#FFFFFF`) only. No emerald, no cream, no charcoal.
    - Intel Deck: Pure White background with Black cards/text.
    - Services Hub: Pure White background with Black buttons/icons.
- **Editorial Experience**: Use **Outfit** for headings and **Inter** for body. Simplified "MMM D, YYYY" dates. Professional grayscale imagery using Coil.
- **Precision Driven**: News search strictly focused on Headlines (`in=title`) for AdTech/Marketing relevance.
- **Conversion Focused**: High-value CTAs (WhatsApp Pulse, Services) tied to Analytics.

## 2. Technical Rules (Kotlin/Compose)
- **Live News**: Integrated with GNews API (Search endpoint). 
    - Forced Relevance: Use `in=title` and focused AdTech/Marketing keyword strings.
    - Noir Visuals: Remote images must use a 100% saturation filter (grayscale) in Coil.
    - Fallback: Robust "Intel Brief" feed when API results or keys are missing.
- **Identity**: Branded launcher icon from user assets. Portrait orientation locked.
- **Motion**: Snappy **Slide-Up & Ease-Out Quart** transitions for all navigation. No generic crossfades.
- Use **Retrofit** with **Moshi Kotlin** (KotlinJsonAdapterFactory) for networking.

## 3. SEO & Discovery
- App metadata must be optimized for "Performance Marketing", "AdTech", and "Digital Advertising" audiences.

## 4. Tracking Logic
- Baseline Tracking: `app_launch`
- Intel Tab engagement: `intel_card_swipe`, `intel_read_more`
- Services Tab engagement: `services_cta_click` (High-Value Conversion)
