package ch.fhnw.ip6.citycourier.mqttservice

// HiveMQ Broker Options


const val HIVEMQ_ANDROID_CLIENT_USER_NAME = "hivemq-android-client"
const val HIVEMQ_ANDROID_CLIENT_PASSWORD = "<your-service-password>"
const val HIVEMQ_MQTT_HOST = "tcp://127.0.0.1:1883"

// Other options
const val BROKER_CONNECTION_TIMEOUT = 3
const val BROKER_CONNECTION_KEEP_ALIVE_INTERVAL = 60
const val BROKER_CONNECTION_CLEAN_SESSION = true
const val BROKER_CONNECTION_RECONNECT = true