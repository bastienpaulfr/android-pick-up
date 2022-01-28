# android-pick-up

Android library to get File from Uri

## Motivation

Explain why this library exists and what problems it solves.

## Requirements

Add a link to requirements file or system

## Download

Include instructions on how to integrate the library into your projects. For instance install in your build.gradle:

```groovy
dependencies {

}
```

## Usage

Provide instructions on how to use and integrate the library into a project.

If there's some special pieces for testing (ie Mocks) explain those here as well.

### Logs

This library uses [SLF4J](http://www.slf4j.org/) for logging. Please use android binding to
log into logcat. More info on [Android binding](http://www.slf4j.org/android/)

```groovy
dependencies {
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-android
    implementation 'org.slf4j:slf4j-android:1.7.30'
}
```

You can also bind SLF4J to timber. In this case please use this dependency

```groovy
dependencies {
    implementation 'com.arcao:slf4j-timber:3.1'
}
```

To activate verbose logging, please add this into your code :

```java
LogDefines.setVerbose(true);
```

Sometimes, it can have log for profiling, in this case, to activate them please add
this in code :

```java
LogDefines.setProfile(true);
```

One `TAG` is used for all logging from lib. It would be easy to filter on this tag if you
want to disable all logging from lib. Filtering can be done with `Timber` and a `Tree`
from [Treessence](https://github.com/bastienpaulfr/Treessence)


```groovy
dependencies {
    implementation 'com.github.bastienpaulfr:Treessence:1.0.5'
}
```

## License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

