# IM3080 Design & Innovation Project - Group 3
We are a team of passionate information engineers innovating to craft collaborative solutions.

Problem Statement: In an era where we are required to live, work and play from home, how do we ensure that anyone and everyone can cook and live sustainably?

Team Name: IM3080 DIP Group 3

Team Members: LIM WEE JEN AARON, LEW SUE XIN, LEE SEOW WEI, WU YIXUAN, LEE JIA WEI, LOKE SENG, THEODORE, CHENG CHU LING, MADELINE, WEE RONGXUAN, JOEY CHENG XUAN YUN, KERLYN ONG SHI YI, ZHU HAN ZHUO

## Contents

1. [Short description](#short-description)
1. [Demo video](#demo-video)
1. [The architecture](#the-architecture)
1. [Getting started](#getting-started)
1. [Built with](#built-with)

## Short description

### What's the problem?

With the COVID-19 pandemic upon the world, more people are living, working and studying from home than ever before. Not everyone would, however, be able to cook to live sustainably. Constantly ordering in would also be unhealthy for the average individual, while cooking at home will bring about health benefits as individuals will be able to regulate and control the ingredients they use.

### How can technology help?

The use of cutting-edge cloud technologies from Google can greatly assist in the creation of a mobile platform that is both easy to use and yet engaging. Built using Java in Android Studio, an app utilising Google Cloud Vision API can be built to recognise foodstuff easily and with great accuracy, while the storage of data on Firebase, a realtime cloud-hosted database, will allow for 24/7 availability.

### The idea

With our app, we aim to provide a fun and engaging experience, essentially cooking for novices. By scanning items from your fridge, simple recipes for one to cook will be suggested, with step-by-step videos and a progress bar to assist in the process.

Designed with pastel colours, our app will also encourage simple enagagement in a community through the ability to leave comments on one's end result.

## Demo video

[![Watch the video](https://github.com/wlim087/IM3080_DIP_GROUP3/blob/master/Images/VideoImage.jpg)](https://youtu.be/B4dnPVZ2MV0)

## The architecture

![Database Architecture](https://github.com/wlim087/IM3080_DIP_GROUP3/blob/master/Images/DatabaseArchitecture_ERDiagram.PNG)

1. A user will first have to sign up for an account, and thereafter login.
2. Upon logging in, the user will be brought to the home page, where he/she can then opt to take a photo of a food item or scroll through various pre-suggested recipes.
3. Upon taking a picture of the foodstuff, the user can then proceed to have the image analysed.
4. Once the image is analysed, it will then check of recipes that require that particular food item.
5. It will then suggest to the user recipes, especially once all requisite ingredients have been scanned.
6. The user can then proceed to go through a step-by-step process of cooking the meal, following through with videos and a progress bar.
7. Upon completion, the user can then take a picture of the end result and upload it for other peers in the community to comment on.

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What do you need to build and run our prototype?

Android Studio, Android Virtual Device/Android Phone/Tablet

### Action

Clone the repository and build and run the app, either by running it in the virtual device or through downloading and running the APK file on a local device.

## Built with

### App Development Platforms

* [Android Studio](https://developer.android.com/studio)

### Design & UI/UX Wireframing
* [Blender](https://www.blender.org/)
* [Pencil2D Animation](https://www.pencil2d.org/)
* [Figma](http://www.figma.com/)

### Database Management
* [Firebase](https://firebase.google.com/)
