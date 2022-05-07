<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
-->


<!-- PROJECT LOGO 
<br />
<p align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a> -->

<h3 align="center">Flexchain Dapp</h3>

  <p align="center">
    A decentralized application for flexible execution of business processes on blockchain.
    <br />
   <!-- <a href="https://github.com/othneildrew/Best-README-Template"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/othneildrew/Best-README-Template">View Demo</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Report Bug</a>
    ·
    <a href="https://github.com/othneildrew/Best-README-Template/issues">Request Feature</a>-->
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
   <!-- <li><a href="#roadmap">Roadmap</a></li> -->
   <!-- <li><a href="#contributing">Contributing</a></li> -->
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

This project was built as a Dapp to execute Business Processes on the blockchain in a flexible way. The BPMN notation is used to draw diagrams representing the Business Processes that are converted into smart-contract hosted by the blockchain. The flexible part is given to us by splitting the logic of the contract from the state itself. So the smart-contract stores the state, keeping all the variables and values while the logic is translated into rules stored in the same variables. This means that changing the rules, stored into the contract, will modify the logic of the Business Process without any need of deploying another smart-contract for the update process. In the way this app is designed the rules aren't actually saved in the smart-contract but in the IPFS(InterPlanetary File System) and just the CID used to index the rules is stored in the contract, allowing to cut the costs of blockchain transactions.

### Built With

* [React-Bootstrap](https://react-bootstrap.github.io/)
* [React](https://it.reactjs.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)



<!-- GETTING STARTED -->
## Getting Started
Make sure to follow the steps below to set up what's needed to run the app.
<!--This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.-->

### Prerequisites

*  Node.js & npm
  ```sh
  Install Node.js and npm at https://nodejs.org/it/download/
  ```
* Ganache
 ```sh
 Install Ganache at https://trufflesuite.com/ganache/ to run your blockchain locally.
  ```
* Metamask
 ```sh
 Install Metamask on your browser at https://metamask.io/download/ to connect to the blockchain.
  ```
### Installation

1. In Metamask create a new network for Ganache and connect your accounts using the secret recovery phrase.
2. Clone the repo
   ```sh
   git clone https://github.com/francioniniccolo/Flexchain-DApp.git
   ```
3. Install NPM packages
   ```sh
   npm install
   ```
4. Enter your account private-key from Metamask in `BlockchainConfig.java`
   ```JS
   MONITOR_ADDRESS="ENTER THE ADDRESS HERE";
   PRIVATE_KEY="ENTER YOUR KEY HERE";
   ```



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_



<!-- ROADMAP -->
<!--## Roadmap

See the [open issues](https://github.com/othneildrew/Best-README-Template/issues) for a list of proposed features (and known issues). -->



<!-- CONTRIBUTING -->
<!--## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
-->


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Niccolò Francioni - niccolo.francioni@studenti.unicam.it

Project Link: [Flexchain Dapp](https://github.com/francioniniccolo/Flexchain-DApp/)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Chor-js](https://github.com/bptlab/chor-js)
* [Web3js](https://web3js.readthedocs.io/)
* [Material UI](https://mui.com/)
* [Web3j](https://docs.web3j.io/)






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
<!--[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png-->

