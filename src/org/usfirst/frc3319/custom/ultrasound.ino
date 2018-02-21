#include <Wire.h>
#include <ArduinoJson.h>

/*
* Ultrasonic Sensor HC-SR04 and Arduino Tutorial
*
* Crated by Dejan Nedelkovski,
* www.HowToMechatronics.com
*
*/

// NOTE: DISCARD MEASUREMENT IF ABOVE 1200

// defines pins numbers
const int uxSelectPin = 7;
const int trigPin1 = 8;
const int echoPin1 = 9;
const int trigPin2 = 5;
const int echoPin2 = 6;

bool useSensor1 = true;

void setup() {
  pinMode(trigPin1, OUTPUT); // Sets the trigPin as an Output
  pinMode(echoPin1, INPUT); // Sets the echoPin as an Input
  pinMode(trigPin2, OUTPUT); // Sets the trigPin as an Output
  pinMode(echoPin2, INPUT); // Sets the echoPin as an Input
  Serial.begin(115200); // Starts the serial communication
}

void loop() {
  // check to see which sensor we should be reading from
  if (digitalRead(uxSelectPin) == LOW) {
    useSensor1 = true;
  } else {
    useSensor1 = false;
  }
  
  // read distance from ultrasonic sensor
  int d = 0;
  if (useSensor1) {
    d = readUx(trigPin1,echoPin1);
  } else {
    d = readUx(trigPin2,echoPin2);
  }
  
  // Send distance over Serial via JSON
  StaticJsonBuffer<21> jsonBuffer; // (SET TO 42 FOR DEBUG)
  JsonObject& root = jsonBuffer.createObject();
  root["d"] = d;
  root.printTo(Serial);

  // DEBUG: send ux device id
  //root.remove("d");
  //if (useSensor1) { root["id"] = 1; }
  //else { root["id"] = 2; }
  //root.printTo(Serial);
  
  // Prints the distance on the Serial Monitor
  // Serial.print("Distance: ");
  // Serial.println(distance);
}

int readUx(int trigPin,int echoPin) {
  // Clears the trigPin
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  // Sets the trigPin on HIGH state for 10 micro seconds
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  // Reads the echoPin, returns the sound wave travel time in microseconds
  long duration;
  duration = pulseIn(echoPin, HIGH);
  // Calculate the distance
  int distance;
  distance = duration*340*.000001*39.3701/2;
  return distance;
}

