package com.example.io_project

object Constants {
    //App
    const val TAG = "AppTag"

    //Collection References
    const val USERS = "users"

    //User fields
    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val PHOTO_URL = "photoUrl"
    const val CREATED_AT = "createdAt"

    //Names
    const val SIGN_IN_REQUEST = "signInRequest"
    const val SIGN_UP_REQUEST = "signUpRequest"

    //Buttons
    const val SIGN_IN_WITH_GOOGLE = "Sign in with Google"
    const val SIGN_OUT = "Sign-out"
    const val REVOKE_ACCESS = "Revoke Access"

    //Screens
    const val AUTH_SCREEN = "auth_screen"
    const val ADD_ACTIVITY_DIALOG = "add_activity_dialog"
    const val ASSISTANT_DIALOG = "assistant_dialog"
    const val ADD_GROUP_DIALOG = "add_group_dialog"
    const val ADD_FRIEND_DIALOG = "add_friend_dialog"
    const val ADD_USER_TO_GROUP_DIALOG = "add_user_to_group_dialog"
    const val ADD_EVENT_TO_GROUP_DIALOG = "add_event_to_group_dialog"
    const val EVENT_DETAILS_DIALOG = "event_details_dialog"
    const val PROFILE_SCREEN = "profile_screen"
    const val GOALS_SCREEN = "goals_screen"
    const val TASKS_SCREEN = "tasks_screen"
    const val SOCIAL_SCREEN = "social_screen"
    const val SPLASH_SCREEN = "splash_screen"
    const val GROUP_SCREEN = "group_screen"
    const val HOME_SCREEN = "home_screen"
    const val CALENDAR_SCREEN = "calendar_screen"
    const val ARCHIVE_SCREEN = "archive_screen"
    const val STATS_SCREEN = "stats_screen"
    // TODO: Pozamieniac stringi w kodzie na consty

    //Values
    const val SPLASH_DELAY: Long = 300
    const val DATE_FORMATTER_PATTERN: String = "EEE, MMM d yyyy"
    const val DAY_BEGINNING_TIME: Int = 7
    const val DAY_ENDING_TIME: Int = 19
    const val DEFAULT_COLOR_HEX: String = "#FF073B4C"
    const val GROUP_COLOR_HEX: String = "#FFFFD166"
    const val FRIEND_COLOR_HEX: String = "#FF454B56"

    //Messages
    const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
}