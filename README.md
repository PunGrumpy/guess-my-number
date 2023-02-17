# Java Game

![MIT License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-19.0.2-ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/PunGrumpy/java-game?style=for-the-badge)
![KMITL](https://img.shields.io/badge/KMITL-Computer%20Science-f04e25.svg?style=for-the-badge&logo=kmitl&logoColor=white)

## Analytics Contributions

![Repobeats analytics image](https://repobeats.axiom.co/api/embed/a8fecf15d3b803f8b30d2e0d36d9e31034d23a6f.svg)

## Table of contents

```bash
├─── .github
│   └─── workflows
├─── src
│   └─── main
│   └─── inputs
├─── .gitignore
├─── pom.xml
├─── LICENSE
└─── README.md
```

## TODOs

- [x] Inputs
  - [x] Add inputs and make sure they respond to events on keyboard and mouse
  - [x] Move the rectangle with our new inputs
- [x] Game Loop
  - [x] Repaint game loop
  - [x] Make a fps counter
  - [x] Add visuals
- [x] Images
  - [x] Add images
  - [x] Add images to the game
  - [x] Add animations

## TIPs

### Git

```bash
# How to clone a repository
git clone https://github.com/PunGrumpy/java-game.git

# How to create a new branch
git checkout -b <branch-name>

# How to add a file to the staging area
git add <file-name>

# How to commit changes
git commit -m "<commit-message>"

# How to push changes to the remote repository
git push origin <branch-name>

# How to create a pull request
# 1. Go to the repository
# 2. Click on the "Pull requests" tab
# 3. Click on the "New pull request" button
# 4. Select the branch you want to merge into the main branch
# 5. Click on the "Create pull request" button
# 6. Add a title and description to your pull request
# 7. Click on the "Create pull request" button
```

### Java

```bash
# How to run the game for development
javac -d ./bin **/*.java
java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp ./src/bin main.MainClass
```
