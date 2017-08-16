# Equalizer Layout
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16) 
### Version: 2.0.0

Create a custom layout which can be expanded and collapsed easily!

## Sample Usage
Set directions with:
```java
//EXPAND_DOWN, EXPAND_UP, EXPAND_RIGHT, EXPAND_LEFT
slidingLayout.setDirection(SlidingCase.EXPAND_DOWN);
```
Collapsed size(minSize) and Expanded size(maxSize).
```java
slidingLayout.setMinSize(100f);
slidingLayout.setMaxSize(300f);
```
You can add a listener to handle if the layout is collapsed or expanded:
```java
slidingLayout.setStatusListener(new StatusListener() {
    @Override
    public void status(boolean isExpanded) {
        //your code
    }
});
```
And define status if you want layout to be expanded at start or not:
```java
slidingLayout.setExpandedAtStart(true);
```
Also you can turn on/off the Swipe Listener or Click Listener:
```java
slidingLayout.hasSwipeListener(true);
```
## View Group
Layout:
```xml
 <com.coffeebreakcodes.slidinglayout.SlidingGroupLayout
        android:id="@+id/slidingGroupLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:independent_layouts="false">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">

                <com.coffeebreakcodes.slidinglayout.SlidingLayout
                    android:layout_weight=".5"
                    android:id="@+id/slidingLayoutTop"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:background="@color/colorPrimary"/>

                <com.coffeebreakcodes.slidinglayout.SlidingLayout
                    android:layout_weight=".5"
                    android:id="@+id/slidingLayoutBottom"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:background="@color/colorAccent"/>

            </LinearLayout>
    </com.coffeebreakcodes.slidinglayout.SlidingGroupLayout>
```
**Independent Layouts:**
If you want to expand the rest of all layouts when you collapse any SlidingLayout, use ```app:independent_layouts="false"```

**Caution:** Using *statusListener* overrides *independent_layouts* attribute.
Please use *expandOthers* for setting the listener and using the dependency at the same time.
```java
 slidingLayout.setStatusListener(new StatusListener() {
            @Override
            public void status(boolean isExpanded) {
                slidingGroupLayout.expandOthers(slidingLayout, isExpanded);
            }
        });
```




### Simple Controllers
```java
slidingLayout.autoToggle();
slidingLayout.expand();
slidingLayout.collapse();
```
### Single Layout
```xml
<com.coffeebreakcodes.slidinglayout.SlidingLayout
        android:id="@+id/slidingLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
### Gradle
[ ![Download](https://api.bintray.com/packages/kagkartal/equalizerlayout/slidinglayout/images/download.svg?version=2.0.0) ](https://bintray.com/kagkartal/equalizerlayout/slidinglayout/2.0.0/link)

```
compile 'com.coffeebreakcodes.slidinglayout:sliding-layout:2.0.0'
```

# License

Copyright 2017 [Kagan Kartal](https://www.linkedin.com/in/kkartal/)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
