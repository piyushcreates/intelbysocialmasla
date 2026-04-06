# Universal Tracking Guide (RudderStack)

This guide explains how to manage your marketing tracking across **Google Ads** and **Meta Ads** using the single **RudderStack** SDK integrated into the app.

---

## 1. Credentials Setup
To activate tracking, replace the placeholders in `MainActivity.kt` with your RudderStack credentials:

1.  **Write Key**: Found in your RudderStack Source settings.
2.  **Data Plane URL**: The endpoint where your events are sent (e.g., `https://socialmasla.rudderstack.com`).

```kotlin
// MainActivity.kt
RudderClient.getInstance(this, "YOUR_WRITE_KEY", config)
```

---

## 2. Connecting Destinations (No Code Required)
You do **not** need to add any more SDKs to the app. Instead, follow these steps in the [RudderStack Dashboard](https://app.rudderstack.com/):

### Google Ads (Firebase/GTM)
1.  Add a **Destination**: `Google Ads`.
2.  Connect it to your `Android` Source.
3.  Mapping: Map the `learn_more_clicked` event to your Google Ads Conversion Action.

### Meta Ads (Facebook)
1.  Add a **Destination**: `Facebook App Events` (or Meta Conversions API).
2.  Connect it to your `Android` Source.
3.  Mapping: Map `learn_more_clicked` to the `ViewContent` or `Lead` standard event.

---

## 3. Verified Tracking Events
The following events are currently being emitted by the app:

| Event Name | Trigger | Priority |
| :--- | :--- | :--- |
| `learn_more_clicked` | "LEARN MORE" button on Services screen | **CRITICAL (Conversion)** |
| `page_view` | Every time a screen is opened (Auto-tracked) | Medium |
| `cta_click` | "READ MORE" button on News cards | Low |

---

## 4. Testing
Use the **Live Stream** feature in the RudderStack dashboard to verify that events are reaching the server in real-time as you use the app.
