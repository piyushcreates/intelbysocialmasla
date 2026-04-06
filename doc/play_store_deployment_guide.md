# Play Store Deployment Guide: Intel by Social Masla

This guide provides the exact operational steps to move from your local terminal to the global Google Play Store.

---

## Phase 1: Local Preparation (One-Time Setup)

### 1. Generate Your Production Keystore
You need a unique digital signature for your app. Run this in your terminal:
```bash
keytool -genkey -v -keystore intel_release.jks -keyalg RSA -keysize 2048 -validity 10000 -alias intel-alias
```
> [!IMPORTANT]
> **DO NOT LOSE THIS FILE.** If you lose the keystore, you can never update your app again. Keep a backup in a secure cloud drive (e.g., Google Drive or iCloud).

### 2. Configure Credentials
Open `local.properties` and add these keys (replace with your actual values):
```properties
RELEASE_STORE_FILE=/Users/piyush/Downloads/personal/otherprojects/dummyapp/intel_release.jks
RELEASE_STORE_PASSWORD=your_password
RELEASE_KEY_ALIAS=intel-alias
RELEASE_KEY_PASSWORD=your_password
```

---

## Phase 2: Generate the App Bundle (.aab)

The Play Store requires an **Android App Bundle (.aab)**, not an APK.

### Method A: Via Android Studio (Recommended)
1.  Go to **Build** > **Generate Signed Bundle / APK...**
2.  Select **Android App Bundle** and click **Next**.
3.  Choose your `intel_release.jks` file and enter your passwords.
4.  Select **release** variant and click **Finish**.

### Method B: Via Terminal
Run this command from the project root:
```bash
./gradlew bundleRelease
```
The file will be created at: `app/build/outputs/bundle/release/app-release.aab`

---

## Phase 3: Play Console Submission

### 1. Create App
- Go to [Google Play Console](https://play.google.com/console/).
- Click **Create app**.
- **App Name**: Intel by Social Masla
- **Default Language**: English
- **App or Game**: App
- **Free or Paid**: Free (Recommended for your AdTech pulse strategy)

### 2. Set Up Store Listing
Use the metadata from `walkthrough.md`:
- **Short Description**: High-performance AdTech intelligence and elite digital service portal. Noir Edition.
- **Full Description**: (Copy from `walkthrough.md`)
- **Graphics**: 
    - **Icon**: 512x512
    - **Feature Graphic**: 1024x500

### 3. Data Safety Form
- Your app uses **RudderStack** and **Firebase Analytics**.
- Mark that you collect **Usage Data** (App interactions) and **Device IDs**.
- Mark that this data is used for **Analytics**.

### 4. Create Release
1.  Go to **Production** in the left menu.
2.  Click **Create new release**.
3.  Upload the `app-release.aab` file.
4.  Review and **Start rollout to Production**.

---

## Troubleshooting
- **Build Fails**: If R8 (minification) causes an error, check `app/proguard-rules.pro`. Currently, our rules are standard for Retrofit/Moshi.
- **API Key Missing**: Ensure you haven't deleted `GNEWS_API_KEY` from `local.properties`.

**Mission Status: v2.1.0 Ready.** Your app is staged for the world.
