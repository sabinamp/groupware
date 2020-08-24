# groupware

City Courier Android App
- developed with Kotlin 1.4.0 and Jetpack Compose 0.1.0-dev13
- using Jackson for serializing objects
- using minSdk 26 to be able to use LocalDateTime with Android API.
-the app connects with MQTT 3.1.1 to the HiveMQ broker

#Paho Android Service - MQTT Client Library Documentation
https://www.eclipse.org/paho/
#Paho Android Service - MQTT Client Library
https://www.hivemq.com/blog/mqtt-client-library-enyclopedia-paho-android-service/
The MQTT connection is encapsulated within an Android-Service that runs in the background of the Android application.
Paho Android Service was added to the application via Gradle.