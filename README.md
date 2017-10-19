# Saiy® API Example App

This application demonstrates methods Saiy exposes in order to allow developers of other Android applications to use features of Saiy.

## Installation

The project is built using Java 7 - Android SDK (API 26 - Min 16)

In Android studio, import as a new project from version control from the downloadable zip.

There is a direct dependency to the [Saiy Library project](https://github.com/brandall76/Saiy-Library). Once that project has compiled you'll need to add the generated aar file as a module to the main project, as described [here](https://stackoverflow.com/q/29826717/1256219).

## License & Copyright

The project is licensed under the GNU Affero General Public License V3. This is a copyleft license. See [LICENSE](https://github.com/brandall76/API-Example-App/blob/master/LICENSE) 

The license grant is not for Saiy's trademarks, which include the logo designs. Saiy reserves all trademark and copyright rights in and to all [Saiy trademarks](https://trademarks.ipo.gov.uk/ipo-tmcase/page/Results/1/UK00003168669?legacySearch=False).

Copyright © 2017 Saiy® Ltd.

## Contributor License Agreements

I need to clarify the most appropriate for the GNU Affero General Public License - will revisit very soon. Any suggestions welcome.

## Providers

 - [Nuance Developers](https://developer.nuance.com) - Text to Speech - Speech to Text - NLU 
 - [Microsoft Cognitive Services](https://azure.microsoft.com/en-gb/services/cognitive-services/) - Text to Speech - NLU - Translate API
 - [IBM Bluemix](https://www.ibm.com/watson/services/speech-to-text/) - Speech to Text  - NLU
 - [Wit](https://wit.ai/) - Speech to Text - NLU
 - [Google Cloud Speech](https://cloud.google.com/speech/) - Speech to Text
 - [Google Chromium Speech](https://www.chromium.org/developers/how-tos/api-keys) - Speech to Text
 - [API AI](https://api.ai/) - Speech to Text - NLU

## Usage Examples

### Basic Fragment

Here you can choose from the available providers for Text to Speech, Speech to Text and your NLP backend.

To test almost all of these providers, you will need to enter your related credentials in the [SaiyConfiguration](https://github.com/brandall76/API-Example-App/blob/master/app/src/main/java/ai/saiy/android/apiexample/configuration/SaiyConfiguration.java) class. Only store them here for testing purposes. As you would expect, you'll also need to create your own project for any NLP cloud provider you wish to use.

I hope it goes without saying that any keys or secrets sent via Saiy to connect to an external API, will be disposed of immediately after the request and never stored.

### Demo Fragment

Here you can register a specific command to the main [Saiy Application](https://github.com/brandall76/Saiy-PS), which will be forwarded directly to your app to process. Think of it as registering a voice intent. The example given is how Spotify would register commands.

### Interaction Fragment

Here you can see how user interaction undertaken via Saiy, can be used by your app to create an interactive experience.

## Further Examples

Once you get to grips with the above and have created a half decent NLP backend (which won't take long), you'll be able to handle your users asking FAQs via Saiy, instead of reading through a mass of information. By giving Saiy your custom context on each request, such as 'FragmentShare', it will be easy for you to process your user asking 'Where is the option for Instagram?' and allow Saiy to inform them 'that it will be available in the next release'. You get the idea... 

Additionally, you could construct a voice tutorial, or ask them which theme they prefer etc etc - all of which will keep them engaged.

## Troubleshooting

Please use the [Stack Overflow tag](https://stackoverflow.com/tags/saiy/info) for compiling related questions and errors.

For code issues and crashes, please open an issue.

For discussion, please use the [XDA development thread](https://forum.xda-developers.com/showthread.php?t=1508195) for now.

