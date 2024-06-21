# TutorIn App 📖

## Overview 🖊

TutorIn is an innovative application designed to simplify the process of finding tutors and mentors for learners and professionals seeking skill development. By leveraging advanced AI and NLP technologies, TutorIn matches learners with the most suitable tutors based on personalized criteria, enhancing the learning experience and ensuring optimal compatibility.
Features

- Tutor Search and Matching: Learners can search for tutors based on various criteria such as personality, teaching style, subject, schedule, and location. TutorIn uses AI to recommend the best tutors, allowing learners to choose the most suitable one.
- Chat Functionality: Built-in chat feature for seamless communication between learners and tutors regarding lessons and schedules.
- Mentorship for Skill Development: In addition to private tutors, learners can find mentors for career development through seminars, webinars, or private lessons.
- AI-Powered Recommendations: Utilizing Natural Language Processing (NLP), TutorIn efficiently matches tutor profiles with learner inputs, ensuring personalized and accurate recommendations.

## Architecture 📱

The TutorIn app is built using a robust and maintainable architecture, incorporating the following components:

- MVVM (Model-View-ViewModel): Ensures a clear separation of concerns by handling data operations in the Model layer, managing UI-related data in the ViewModel layer, and displaying data in the View layer.
- LiveData: Allows the UI to automatically update when data changes, held and observed within the ViewModel.
- Room: Provides an abstraction layer over SQLite for efficient database management and persistence.
- Android Intent and Navigation Graph: Facilitates seamless communication between components and consistent navigation within the app.
- Retrofit and Repository Pattern: Handles network operations and mediates between different data sources, providing a clean API for data access.
- ViewBinding: Simplifies UI component interactions, reducing the risk of null pointer exceptions.
- DataStore: Manages application preferences and stores small amounts of data securely and asynchronously.
- Database Injection: Ensures controlled provision of database instances, promoting testability and reducing tight coupling.

## Getting Started 📜

To get started with the TutorIn app, follow these steps:

1. Clone the Repository:

```
1 | https://github.com/tutorinedutech/tutorin-mobile.git
2 | cd tutorin-app
```

2. Setup Environment:
    
    - Ensure you have Android Studio installed.
    - Open the project in Android Studio.
    - Sync the project with Gradle files.

3. Build and Run:
    - Connect your Android device or start an emulator.
    - Build and run the project from Android Studio.

## Contribution 🕹

We welcome contributions to improve TutorIn. To contribute:

- Fork the repository.
- Create a new branch (git checkout -b feature-branch).
- Commit your changes (git commit -am 'Add new feature').
- Push to the branch (git push origin feature-branch).
- Create a new Pull Request.

## Project Tree 🌳

```
root
├── app
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── src
│       ├── androidTest
│       │   └── java
│       │       └── com
│       │           └── dicoding
│       │               └── tutorinedutech
│       │                   └── ExampleInstrumentedTest.kt
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── dicoding
│       │   │           └── tutorinedutech
│       │   │               ├── data
│       │   │               │   ├── db
│       │   │               │   │   ├── learner
│       │   │               │   │   │   ├── LearnerDao.kt
│       │   │               │   │   │   ├── LearnerDatabase.kt
│       │   │               │   │   │   └── Learner.kt
│       │   │               │   │   └── tutor
│       │   │               │   │       ├── ClassDao.kt
│       │   │               │   │       ├── ClassDetailDao.kt
│       │   │               │   │       ├── ClassDetail.kt
│       │   │               │   │       ├── Classes.kt
│       │   │               │   │       ├── ClassIncomingDao.kt
│       │   │               │   │       ├── ClassIncoming.kt
│       │   │               │   │       ├── TutorDao.kt
│       │   │               │   │       ├── TutorDatabase.kt
│       │   │               │   │       └── Tutor.kt
│       │   │               │   ├── di
│       │   │               │   │   └── Injection.kt
│       │   │               │   ├── repository
│       │   │               │   │   ├── LearnerRepository.kt
│       │   │               │   │   ├── TutoringRepository.kt
│       │   │               │   │   └── UserRepository.kt
│       │   │               │   ├── response
│       │   │               │   │   ├── ResponseBase.kt
│       │   │               │   │   ├── ResponseDetailClassTutoring.kt
│       │   │               │   │   ├── ResponseDetailLearning.kt
│       │   │               │   │   ├── ResponseDetailLearningUpdate.kt
│       │   │               │   │   ├── ResponseDetailTutor.kt
│       │   │               │   │   ├── ResponseHomeLearner.kt
│       │   │               │   │   ├── ResponseHomeTutor.kt
│       │   │               │   │   ├── ResponseLogOut.kt
│       │   │               │   │   ├── ResponseProfileTutorFile.kt
│       │   │               │   │   ├── ResponseProfileTutor.kt
│       │   │               │   │   ├── ResponseRatingTutoring.kt
│       │   │               │   │   ├── ResponseSearchTutor.kt
│       │   │               │   │   ├── ResponseSignIn.kt
│       │   │               │   │   ├── ResponseSignUp.kt
│       │   │               │   │   ├── ResponseUpdateDateTimeTutoring.kt
│       │   │               │   │   └── ResponseUpdateDetailClass.kt
│       │   │               │   └── retrofit
│       │   │               │       ├── ApiConfig.kt
│       │   │               │       └── ApiService.kt
│       │   │               ├── helper
│       │   │               │   ├── Helper.kt
│       │   │               │   ├── ResultState.kt
│       │   │               │   └── ViewModelFactory.kt
│       │   │               ├── ui
│       │   │               │   ├── auth
│       │   │               │   │   ├── login
│       │   │               │   │   │   ├── Login.kt
│       │   │               │   │   │   └── LoginVM.kt
│       │   │               │   │   ├── onboarding
│       │   │               │   │   │   ├── Onboarding.kt
│       │   │               │   │   │   └── OnboardingVM.kt
│       │   │               │   │   └── register
│       │   │               │   │       ├── learner
│       │   │               │   │       │   ├── RegisterLearner.kt
│       │   │               │   │       │   └── RegisterLearnerVM.kt
│       │   │               │   │       ├── main
│       │   │               │   │       │   ├── RegisterMain.kt
│       │   │               │   │       │   └── RegisterMainVM.kt
│       │   │               │   │       └── tutor
│       │   │               │   │           ├── LoadingTestGradient.kt
│       │   │               │   │           ├── RegisterPreTest.kt
│       │   │               │   │           ├── RegisterTest.kt
│       │   │               │   │           ├── RegisterTestVM.kt
│       │   │               │   │           ├── RegisterTutor.kt
│       │   │               │   │           ├── RegisterTutorResult.kt
│       │   │               │   │           ├── RegisterTutorVM.kt
│       │   │               │   │           ├── UploadKtp.kt
│       │   │               │   │           └── UploadKtpVM.kt
│       │   │               │   ├── learner
│       │   │               │   │   ├── home
│       │   │               │   │   │   ├── HomeLearner.kt
│       │   │               │   │   │   ├── HomeLearnerVM.kt
│       │   │               │   │   │   ├── PopularTutorAdapter.kt
│       │   │               │   │   │   └── SessionAdapter.kt
│       │   │               │   │   ├── notification
│       │   │               │   │   │   └── NotificationLearner.kt
│       │   │               │   │   ├── profile
│       │   │               │   │   │   ├── ProfileLearner.kt
│       │   │               │   │   │   └── ProfileLearnerVM.kt
│       │   │               │   │   └── tutors
│       │   │               │   │       ├── result
│       │   │               │   │       │   ├── detail
│       │   │               │   │       │   │   ├── DetailTutor.kt
│       │   │               │   │       │   │   └── DetailTutorVM.kt
│       │   │               │   │       │   └── ListTutors.kt
│       │   │               │   │       ├── search
│       │   │               │   │       │   ├── LoadingSearch.kt
│       │   │               │   │       │   ├── SearchTutor.kt
│       │   │               │   │       │   └── SearchTutorVM.kt
│       │   │               │   │       ├── tutoring
│       │   │               │   │       │   ├── detail
│       │   │               │   │       │   │   ├── DetailTutoring.kt
│       │   │               │   │       │   │   ├── DetailTutoringVM.kt
│       │   │               │   │       │   │   └── TutoringAdapter.kt
│       │   │               │   │       │   ├── rating
│       │   │               │   │       │   │   ├── RatingTutoring.kt
│       │   │               │   │       │   │   └── RatingTutoringVM.kt
│       │   │               │   │       │   └── validate
│       │   │               │   │       │       ├── ValidatePhoto.kt
│       │   │               │   │       │       └── ValidatePhotoVM.kt
│       │   │               │   │       ├── TutorsLearnerAdapter.kt
│       │   │               │   │       ├── TutorsLearner.kt
│       │   │               │   │       ├── TutorsLearnerVM.kt
│       │   │               │   │       └── TutorsSessionAdapter.kt
│       │   │               │   ├── main
│       │   │               │   │   ├── CameraActivity.kt
│       │   │               │   │   ├── MainActivity.kt
│       │   │               │   │   ├── SplashScreenActivity.kt
│       │   │               │   │   └── SplashScreenVM.kt
│       │   │               │   └── tutor
│       │   │               │       ├── detail_tentoring
│       │   │               │       │   ├── DetailTentoringTutor.kt
│       │   │               │       │   └── DetailTentoringTutorVM.kt
│       │   │               │       ├── home
│       │   │               │       │   ├── HomePagerAdapter.kt
│       │   │               │       │   ├── HomeTutor.kt
│       │   │               │       │   ├── HomeVM.kt
│       │   │               │       │   ├── incoming
│       │   │               │       │   │   ├── ListIncomingAdapter.kt
│       │   │               │       │   │   ├── TabIncomingTutor.kt
│       │   │               │       │   │   └── TabIncomingVM.kt
│       │   │               │       │   └── ongoing
│       │   │               │       │       ├── ListOngoingAdapter.kt
│       │   │               │       │       ├── TabOngoingTutor.kt
│       │   │               │       │       └── TabOngoingVM.kt
│       │   │               │       └── profile
│       │   │               │           ├── ProfileTutor.kt
│       │   │               │           ├── ProfileTutorVM.kt
│       │   │               │           ├── UpdatePassword.kt
│       │   │               │           └── UpdatePasswordVM.kt
│       │   │               └── utils
│       │   │                   ├── AppExecutor.kt
│       │   │                   ├── Event.kt
│       │   │                   ├── ImageProcessors.kt
│       │   │                   ├── InputValidation.kt
│       │   │                   ├── JsonBodyParser.kt
│       │   │                   ├── PrefLearner.kt
│       │   │                   ├── PrefMain.kt
│       │   │                   ├── PrefTutor.kt
│       │   │                   ├── UserType.kt
│       │   │                   ├── UserTypeManager.kt
│       │   │                   └── ValidationPhotoData.kt
│       │   └── res
│       │       ├── anim
│       │       │   ├── enter_from_right.xml
│       │       │   ├── exit_to_left_shrink.xml
│       │       │   ├── pop_enter_grow.xml
│       │       │   └── pop_exit_to_right.xml
│       │       ├── drawable
│       │       │   ├── backward.xml
│       │       │   ├── baseline_access_time_filled_24.xml
│       │       │   ├── baseline_check_box_24.xml
│       │       │   ├── baseline_check_box_outline_blank_24.xml
│       │       │   ├── baseline_error_24.xml
│       │       │   ├── bg_btn_square.xml
│       │       │   ├── border_camera_shutter.xml
│       │       │   ├── bro.xml
│       │       │   ├── camera_switch.xml
│       │       │   ├── camera.xml
│       │       │   ├── chat.xml
│       │       │   ├── check.xml
│       │       │   ├── circle_active.xml
│       │       │   ├── circle_disable.xml
│       │       │   ├── close.xml
│       │       │   ├── custom_checkbox.xml
│       │       │   ├── datetime.xml
│       │       │   ├── done.xml
│       │       │   ├── dropdown_arrow.xml
│       │       │   ├── edit_square.xml
│       │       │   ├── empty_image.png
│       │       │   ├── forward.xml
│       │       │   ├── gradient_loading_search_1.xml
│       │       │   ├── gradient_loading_search_2.xml
│       │       │   ├── gradient_loading_test_1.xml
│       │       │   ├── gradient_loading_test_2.xml
│       │       │   ├── gradient_loading_test_3.xml
│       │       │   ├── home_learner_bg.xml
│       │       │   ├── ic_chat.xml
│       │       │   ├── ic_clock_2.xml
│       │       │   ├── ic_launcher_background.xml
│       │       │   ├── ic_location.xml
│       │       │   ├── ic_logout.xml
│       │       │   ├── ic_stars.xml
│       │       │   ├── ic_star.xml
│       │       │   ├── loading_test.xml
│       │       │   ├── location.xml
│       │       │   ├── login.xml
│       │       │   ├── onboarding_1.xml
│       │       │   ├── onboarding_2.xml
│       │       │   ├── onboarding_3.xml
│       │       │   ├── onboarding_4.xml
│       │       │   ├── person.xml
│       │       │   ├── rectangle_border_10_gradient.xml
│       │       │   ├── rectangle_border_10.xml
│       │       │   ├── rectangle_border_11.xml
│       │       │   ├── rectangle_border_14.xml
│       │       │   ├── rectangle_border_21.xml
│       │       │   ├── rectangle_border_28.xml
│       │       │   ├── register_mentor_wait.xml
│       │       │   ├── register.xml
│       │       │   ├── setting.xml
│       │       │   ├── splash_screen.xml
│       │       │   ├── star_active.xml
│       │       │   ├── star_inactive.xml
│       │       │   ├── star_rating.xml
│       │       │   ├── test_accepted.xml
│       │       │   ├── top_profile.xml
│       │       │   ├── tutorin.xml
│       │       │   └── user_detail.xml
│       │       ├── drawable-v24
│       │       │   └── ic_launcher_foreground.xml
│       │       ├── font
│       │       │   ├── plus_jakarta_sans_bold_italic.ttf
│       │       │   ├── plus_jakarta_sans_bold.ttf
│       │       │   ├── plus_jakarta_sans_extrabold_italic.ttf
│       │       │   ├── plus_jakarta_sans_extrabold.ttf
│       │       │   ├── plus_jakarta_sans_extralight_italic.ttf
│       │       │   ├── plus_jakarta_sans_extralight.ttf
│       │       │   ├── plus_jakarta_sans_light_italic.ttf
│       │       │   ├── plus_jakarta_sans_light.ttf
│       │       │   ├── plus_jakarta_sans_medium_italic.ttf
│       │       │   ├── plus_jakarta_sans_medium.ttf
│       │       │   ├── plus_jakarta_sans_regular_italic.ttf
│       │       │   ├── plus_jakarta_sans_regular.ttf
│       │       │   ├── plus_jakarta_sans_semibold_italic.ttf
│       │       │   ├── plus_jakarta_sans_semibold.ttf
│       │       │   └── plus_jakartas.xml
│       │       ├── layout
│       │       │   ├── activity_camera.xml
│       │       │   ├── activity_main.xml
│       │       │   ├── activity_splash_screen.xml
│       │       │   ├── card_sesi_tutor.xml
│       │       │   ├── card_tutoring_tutor.xml
│       │       │   ├── dialog_layout.xml
│       │       │   ├── fragment_detail_tentoring_tutor.xml
│       │       │   ├── fragment_detail_tutoring_learner.xml
│       │       │   ├── fragment_detail_tutor.xml
│       │       │   ├── fragment_home_learner.xml
│       │       │   ├── fragment_home_tutor.xml
│       │       │   ├── fragment_list_tutors.xml
│       │       │   ├── fragment_loading_search.xml
│       │       │   ├── fragment_loading_test_gradient.xml
│       │       │   ├── fragment_login.xml
│       │       │   ├── fragment_notification_learner.xml
│       │       │   ├── fragment_onboarding.xml
│       │       │   ├── fragment_profile_learner.xml
│       │       │   ├── fragment_profile_tutor.xml
│       │       │   ├── fragment_rating_tutoring.xml
│       │       │   ├── fragment_register_learner.xml
│       │       │   ├── fragment_register_main.xml
│       │       │   ├── fragment_register_pre_test.xml
│       │       │   ├── fragment_register_test.xml
│       │       │   ├── fragment_register_tutor_result.xml
│       │       │   ├── fragment_register_tutor.xml
│       │       │   ├── fragment_search_tutor.xml
│       │       │   ├── fragment_tab_incoming_tutor.xml
│       │       │   ├── fragment_tab_ongoing_tutor.xml
│       │       │   ├── fragment_tutors_learner.xml
│       │       │   ├── fragment_update_password.xml
│       │       │   ├── fragment_upload_ktp.xml
│       │       │   ├── fragment_validate_photo.xml
│       │       │   ├── item_detail_tutoring.xml
│       │       │   ├── item_list_time_session.xml
│       │       │   ├── item_popular_tutor.xml
│       │       │   ├── item_suitable_result_tutor.xml
│       │       │   ├── item_today_activity.xml
│       │       │   ├── item_tutor_list.xml
│       │       │   └── item_unsuitable_result_tutor.xml
│       │       ├── mipmap-anydpi-v26
│       │       │   ├── ic_launcher_round.xml
│       │       │   └── ic_launcher.xml
│       │       ├── mipmap-hdpi
│       │       │   ├── ic_launcher_round.webp
│       │       │   └── ic_launcher.webp
│       │       ├── mipmap-mdpi
│       │       │   ├── ic_launcher_round.webp
│       │       │   └── ic_launcher.webp
│       │       ├── mipmap-xhdpi
│       │       │   ├── ic_launcher_round.webp
│       │       │   └── ic_launcher.webp
│       │       ├── mipmap-xxhdpi
│       │       │   ├── ic_launcher_round.webp
│       │       │   └── ic_launcher.webp
│       │       ├── mipmap-xxxhdpi
│       │       │   ├── ic_launcher_round.webp
│       │       │   └── ic_launcher.webp
│       │       ├── navigation
│       │       │   ├── nav_auth.xml
│       │       │   ├── nav_learner.xml
│       │       │   ├── nav_main.xml
│       │       │   └── nav_tutor.xml
│       │       ├── values
│       │       │   ├── colors.xml
│       │       │   ├── dimens.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       ├── values-night
│       │       │   └── themes.xml
│       │       └── xml
│       │           ├── backup_rules.xml
│       │           ├── data_extraction_rules.xml
│       │           ├── fragment_onboarding_scene.xml
│       │           └── fragment_register_main_scene.xml
│       └── test
│           └── java
│               └── com
│                   └── dicoding
│                       └── tutorinedutech
│                           └── ExampleUnitTest.kt
├── build.gradle.kts
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle.kts
```

## License ⚖️

This project is licensed under the MIT License.
Contact

For any questions or inquiries, please reach out to [tutorin.edutech@gmail.com].

Thank you for using TutorIn! We hope it enhances your learning and teaching experience.
