# Equalizer Layout
### Version: 1.0.1

Create a custom layout which can be expanded and collapsed easily!

## Sample Usage
Set directions with
```
//EXPAND_DOWN, EXPAND_UP, EXPAND_RIGHT, EXPAND_LEFT
equalizerLayout.setDirection(SlidingCase.EXPAND_DOWN);
```
Collapsed size(minSize) and Expanded size(maxSize)
```
equalizerLayout.setMinSize(100f);
equalizerLayout.setMaxSize(300f);
```
You can add a listener to handle if the layout is collapsed or expanded
```
equalizerLayout.setStatusListener(new StatusListener() {
    @Override
    public void status(boolean isExpanded) {
        //your code
    }
});
```
And define status if you want layout to be expanded at start or not
```
equalizerLayout.setExpandedAtStart(true);
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
compile 'com.coffeebreakcodes.equalizerlayout:equalizer-layout:1.0.1'
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
