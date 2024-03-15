# GitHub Trending Repositories

GitHub Trending Repositories is an Android application that allows users to fetch GitHub repositories based on certain criteria,
filter them by date (1 day, 7 days, 30 days), search for repositories, add favorites (saved to a local database),
and access favorites without an internet connection.

## Architecture
- MVVM architecture pattern with base SOLID principles, clear and simple 

## Features

- Fetch GitHub repositories based on various criteria.
- Filter repositories by date (1 day, 1 week, 1 month).
- Search for repositories using keywords.
- Add repositories to favorites, which are saved to a local database.
- Access favorite repositories without an internet connection.

## Technologies Used

- **Retrofit:** For making network requests to the GitHub API.
- **Java:** The primary programming language used for the Android application.
- **Room Database:** For storing and managing favorite repositories locally.
- **Data Binding:** For binding UI components in layout files to data sources.
- **Navigation Component:** For managing navigation and handling fragment transactions.
- **Fragment:** For building flexible and modular user interfaces.
- **Coil Image Loader:** For loading and displaying images efficiently.
- **RecyclerView:** For displaying a list of repositories and favorites.

## Not Implemented Features

- **No Internet User Experience:** Improve user experience when there's no internet connection.
- **Support for Tablets:** Make the app compatible with tablet devices.

## Features/Decisions for Product Variant of the App

- **Integration of Dependency Injection (DI):** Utilize Dagger or Hilt for better code organization.
- **Handling Different Screen States:** Enhance user experience by effectively managing no content, no internet, and server error states.
- **UI Enhancements:** Update color scheme and UI elements for a modern look.
- **Dimensions Management:** Centralize dimensions in dimens.xml for easier management.
- **Support for Different Screen Sizes:** Optimize layouts for various screen sizes and densities.
