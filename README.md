# SimpleLoading
An easy way to show loading for asynchonous processes such as: call APIs, parse html, download files, initialize resourse, write content to file, ... 

Gradle
------------


###### Project Gradle
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

###### Module Gradle
```groovy
dependencies {
	implementation 'com.github.phihung1992:SimpleLoading:1.1'
}
```

Usage
--------
Impliment 2 methods: showLoading() and dismissLoading() to use easily.

```groovy
private SimpleLoadingDialog progressDialog;

private void showLoading() {
    if (progressDialog == null) {
       progressDialog = SimpleLoadingDialog.newInstance()
            .setMessage("Loading data. \nPlease wait for a few seconds or check your internet quality ...", "#327773")
            .setLoadingColor("#327773")
            .setCanceled(true, true);
       }

    if (progressDialog.isAdded()) {
       return;
    }
    progressDialog.show(this);
}

private void dismissLoading() {
    if (progressDialog != null) progressDialog.dismiss();
}
```

