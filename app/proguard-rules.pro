# Retrofit
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepattributes AnnotationDefault
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }

# Moshi
-keep class com.squareup.moshi.** { *; }
-keep interface com.squareup.moshi.** { *; }
-dontwarn com.squareup.moshi.**

# Keep data classes and their members to allow Moshi reflection/parsing
-keep class com.socialmasla.intel.data.remote.** { *; }
-keep class com.socialmasla.intel.data.model.** { *; }

# Keep Moshi @Json annotations
-keepclassmembers class ** {
    @com.squareup.moshi.Json <fields>;
}

# RudderStack
-keep class com.rudderstack.** { *; }

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-dontwarn okio.**
