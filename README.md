# Equalizer Layout
### Version: 2.0.0

Create a custom layout which can be expanded and collapsed easily!

## Sample Usage
Set directions with:
```
//EXPAND_DOWN, EXPAND_UP, EXPAND_RIGHT, EXPAND_LEFT
equalizerLayout.setDirection(SlidingCase.EXPAND_DOWN);
```
Collapsed size(minSize) and Expanded size(maxSize).
```
equalizerLayout.setMinSize(100f);
equalizerLayout.setMaxSize(300f);
```
You can add a listener to handle if the layout is collapsed or expanded:
```
equalizerLayout.setStatusListener(new StatusListener() {
    @Override
    public void status(boolean isExpanded) {
        //your code
    }
});
```
And define status if you want layout to be expanded at start or not:
```
equalizerLayout.setExpandedAtStart(true);
```
Also you can turn on/off the Swipe Listener or Click Listener:
```
equalizerLayout.hasSwipeListener(true);
```
## View Group
Layout:
```
 <com.coffeebreakcodes.slidinglayout.SlidingGroupLayout
        android:id="@+id/slidingGroupLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:independent_layouts="true">
 </com.coffeebreakcodes.slidinglayout.SlidingGroupLayout>
```
**Independent Layouts:**
If you want to expand the rest of all layouts when you collapse any SlidingLayout, use ```app:independent_layouts="false"```

**Caution:** Using *statusListener* overrides *independent_layouts* attribute.
Please use *expandOthers* for setting the listener and using the dependency at the same time.
```
 slidingLayout.setStatusListener(new StatusListener() {
            @Override
            public void status(boolean isExpanded) {
                slidingGroupLayout.expandOthers(slidingLayout, isExpanded);
            }
        });
```




### Simple Controllers
```
equalizerLayout.autoToggle();
equalizerLayout.expand();
equalizerLayout.collapse();
```
### Sample Layout
```
<com.coffeebreakcodes.equalizerlayout.EqualizerLayout
        android:id="@+id/equalizerlayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
### Gradle
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
