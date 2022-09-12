<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![Quality][quality-shield]][quality-url]

<!-- PROJECT LOGO -->
<!--suppress ALL -->
<br />
<p align="center">
  <a href="https://github.com/LeoMeinel/VitalFly">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">VitalFly</h3>

  <p align="center">
    Fly on Spigot and Paper
    <br />
    <a href="https://github.com/LeoMeinel/VitalFly"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/LeoMeinel/VitalFly">View Demo</a>
    ·
    <a href="https://github.com/LeoMeinel/VitalFly/issues">Report Bug</a>
    ·
    <a href="https://github.com/LeoMeinel/VitalFly/issues">Request Feature</a>
  </p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#description">Description</a></li>
        <li><a href="#features">Features</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#commands-and-permissions">Commands and Permissions</a></li>
        <li><a href="#configuration - config.yml">Configuration</a></li>
		<li><a href="#configuration - messages.yml">Configuration</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

### Description

VitalFly is a Plugin that gives players the ability to fly.

This plugin is perfect for any server wanting their players to fly in survival.

### Features

- Toggle fly on and off for command sender and other players
- Set fly speed for command sender and other players
- Keep fly on world change
- Keep fly toggled on rejoin when player is falling
- Keep fly toggled when player changes back to survival

### Built With

- [Gradle 7](https://docs.gradle.org/7.4/release-notes.html)
- [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)

<!-- GETTING STARTED -->

## Getting Started

To get the plugin running on your server follow these simple steps.

### Commands and Permissions

1. Permission: `vitalfly.fly`

- Command: `/fly`
- Description: Toggle fly

2. Permission: `vitalfly.fly.others`

- Command: `/fly <player>`
- Description: Toggle fly for other players

3. Permission: `vitalfly.flyspeed`

- Command: `/flyspeed <flyspeed>`
- Description: Set flyspeed

4. Permission: `vitalfly.flyspeed.others`

- Command: `/flyspeed <player> <flyspeed>`
- Description: Set flyspeed for other players

5. Permission: `vitalfly.fly.worldchange`

- Description: Keep fly on worldchange

6. Permission: `vitalfly.fly.gamemodechange`

- Description: Keep fly on gamemodechange

7. Permission: `vitalfly.fly.login`

- Description: Keep fly on login

### Configuration - config.yml

```
flyspeed:
  # Values from 1-10
  # Don't use negative or floating-point numbers
  limit: 10
```

### Configuration - messages.yml

```
cmd: "&fUsage: &b/fly <player> &for &b/flyspeed <player> <flyspeed>"
now-flying: "&fFly toggled &aON"
no-perms: "&cYou don't have enough permissions!"
player-only: "&cThis command can only be executed by players!"
not-online: "&cPlayer is not online!"
same-player: "&cYou can't use that on yourself!"
now-flying-disabled: "&fFly toggled &cOFF"
player-now-flying-disabled: "&fFly toggled &cOFF &ffor &b%player%"
player-now-flying: "&fFly toggled &aON &ffor &b%player%"
flyspeed-changed: "&fFlySpeed set to &b%flyspeed%"
invalid-amount: "&cInvalid amount!"
player-flyspeed-changed: "&fFlyspeed set to &b%flyspeed% &ffor &b%player%"
beyond-limit: "&cThe number is too high!"
```

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/LeoMeinel/VitalFly/issues) for a list of proposed features (and known
issues).

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be, learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the GNU General Public License v3.0. See `LICENSE` for more information.

<!-- CONTACT -->

## Contact

Leopold Meinel - [leo@meinel.dev](mailto:leo@meinel.dev) - eMail

Project Link - [VitalFly](https://github.com/LeoMeinel/VitalFly) - GitHub

<!-- ACKNOWLEDGEMENTS -->

### Acknowledgements

- [README.md - othneildrew](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors-anon/LeoMeinel/VitalFly?style=for-the-badge
[contributors-url]: https://github.com/LeoMeinel/VitalFly/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/LeoMeinel/VitalFly?label=Forks&style=for-the-badge
[forks-url]: https://github.com/LeoMeinel/VitalFly/network/members
[stars-shield]: https://img.shields.io/github/stars/LeoMeinel/VitalFly?style=for-the-badge
[stars-url]: https://github.com/LeoMeinel/VitalFly/stargazers
[issues-shield]: https://img.shields.io/github/issues/LeoMeinel/VitalFly?style=for-the-badge
[issues-url]: https://github.com/LeoMeinel/VitalFly/issues
[license-shield]: https://img.shields.io/github/license/LeoMeinel/VitalFly?style=for-the-badge
[license-url]: https://github.com/LeoMeinel/VitalFly/blob/main/LICENSE
[quality-shield]: https://img.shields.io/codefactor/grade/github/LeoMeinel/VitalFly?style=for-the-badge
[quality-url]: https://www.codefactor.io/repository/github/LeoMeinel/VitalFly
