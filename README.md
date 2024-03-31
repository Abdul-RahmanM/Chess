## What is it?
This is a chess game created for my high school project using Java.

## Setup
Pull the code onto local machine

Install JavaFX and add as a dependency to your IDE
![image](https://github.com/Abdul-RahmanM/Chess/assets/95952874/398bb409-c48d-4cb6-aae5-d1a8aa3456f8)

## Usage
Run the program to play against a random move picking AI

Comment Line 49 to Line 51 to play locally against someone


```Java
// Comment to play against local opponent
if (!Chess.getInstance().getTurn()) {
    Chess.getInstance().getAI().playMove();
}
```
## Product Image

![image](https://github.com/Abdul-RahmanM/Chess/assets/95952874/e79b7579-1f9e-44d3-a320-a4924c2745af)
