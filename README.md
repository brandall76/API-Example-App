# Saiy® API Example App

This application demonstrates some methods Saiy exposes in order to allow developers of other Android applications to use features of Saiy.

## Installation

The project is built using Java 7 - Android SDK (API 26 - Min 16)

In Android studio, import as a new project from version control from the downloadable zip.

There is a direct dependency to the Saiy Library project. Once that project is compiled you'll need to add the generated aar file as a module to the example project.

## Usage Examples

### Basic Fragment

Here you can choose from the available providers for Text to Speech, Speech to Text and your NLP backend.

To test almost all of these providers, you will need to enter your credentials in the SaiyConfiguration file. Only store them here for testing purposes. As you would expect, the same applies to having a project setup for any NLP cloud provider.

I hope it goes without saying that any keys or secrets sent to Saiy to use, will be disposed of immediately after the request and never stored.

### Demo Fragment

Here you can register a specific command to the main Saiy Application, which will be forwarded directly to your app to process. Think of it as registering a voice intent. The example given is how Spotify would register commands.

### Interaction Fragment

Here you can see how user interaction undertaken via Saiy, can be used by your app to create an interactive experience.

## Further Examples

Once you get to grips with the above and have created a half decent NLP backend, you'll be able to handle your users asking FAQs via Saiy, instead of reading through a mass of information. By giving Saiy your custom context on each request, such as 'FragmentShare', it will be easy for you to process your user asking 'Where is the option for Instagram?' and allow Saiy to inform them 'that it will be available in the next release'. You get the idea... 

Additionally, you could construct a voice tutorial, or ask them which theme they prefer etc etc - all of which will keep them engaged.

## License & Copyright

The project is licensed under the GNU Affero General Public License V3. This is a copyleft license. See [LICENSE](https://github.com/brandall76/Saiy-PS/blob/master/LICENSE) 

The license grant is not for Saiy's trademarks, which include the logo designs. Saiy reserves all trademark and copyright rights in and to all [Saiy trademarks](https://trademarks.ipo.gov.uk/ipo-tmcase/page/Results/1/UK00003168669?legacySearch=False).

Copyright © 2017 Saiy® Ltd.
