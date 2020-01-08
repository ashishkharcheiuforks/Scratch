# Scratch

This is a sandbox with examples of how to setup and solve common issues.

## Download file in background (using WorkManager)
https://github.com/appspell/Scratch/tree/download-in-background

Download file from server using Retrofit with WorkerManager. [here](https://github.com/appspell/Scratch/blob/download-in-background/app/src/main/java/com/appspell/scratchapplication/features/downloader/DownloadWorker.kt)

`Worker` `WorkManager` `Retrofit` `Permissions`


## Simple custom view with overriden `onDraw()` method
https://github.com/appspell/Scratch/tree/custom_view

Simple [CustomView.kt](https://github.com/appspell/Scratch/blob/custom_view/app/src/main/java/com/appspell/scratchapplication/features/CustomView.kt) that draws a square at the middle of the view. 

`CustomView` `Canvas`

## Using Room along wirh RxJava2
https://github.com/appspell/Scratch/tree/db

Add and remove entities using RxJava. [here](https://github.com/appspell/Scratch/blob/db/app/src/main/java/com/appspell/scratchapplication/features/MainActivity.kt)

App adds a new row into a table per each second and observes result using RxJava Flowable in real-time.

`RxJava` `Room` `Flowable` `Dagger`

## Simple MVVM implementation
https://github.com/appspell/Scratch/tree/mvvm

Request a list of items from a server and show it in the RecyclerView using a simple implementation of DiffUtils. RecyclerView's adapter uses DataBinding.

`AndroidX` `Retrofit` `Dagger` `RxJava2` `ViewModel` `LiveData` `DataBindings`
