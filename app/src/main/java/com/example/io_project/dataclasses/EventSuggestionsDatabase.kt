package com.example.io_project.dataclasses

class EventSuggestionsDatabase {
    val suggestions: List<EventSuggestion> = listOf(
        // Szkoła
        EventSuggestion(name = "Nauka na sprawdzian", category = "Szkoła"),
        EventSuggestion(name = "Powtórzenie materiału", category = "Szkoła"),
        EventSuggestion(name = "Odbrobienie prac domowych", category = "Szkoła"),
        EventSuggestion(name = "Czytanie lektury", category = "Szkoła"),
        EventSuggestion(name = "Nauka języków obcych", category = "Szkoła"),

        // Inne
        EventSuggestion(name = "Zakupy spożywcze", category = "Inne"),
        EventSuggestion(name = "Sprzątanie mieszkania", category = "Inne"),
        EventSuggestion(name = "Oglądanie filmu", category = "Inne"),
        EventSuggestion(name = "Kurs gotowania", category = "Inne"),
        EventSuggestion(name = "Czytanie książki", category = "Inne"),

        // Praca
        EventSuggestion(name = "Poproś o urlop", category = "Praca"),
        EventSuggestion(name = "Poproś o podwyżkę", category = "Praca"),
        EventSuggestion(name = "Stalkuj szefa", category = "Praca"),
        EventSuggestion(name = "Szukaj pracy", category = "Praca"),
        EventSuggestion(name = "Spotkanie ze współpracownikami", category = "Praca"),

        // Aktywność fizyczna
        EventSuggestion(name = "Trening na siłowni", category = "Aktywność fizyczna"),
        EventSuggestion(name = "Joga w parku", category = "Aktywność fizyczna"),
        EventSuggestion(name = "Bieganie po lesie", category = "Aktywność fizyczna"),
        EventSuggestion(name = "Zajęcia z aerobiku", category = "Aktywność fizyczna"),
        EventSuggestion(name = "Wycieczka rowerowa", category = "Aktywność fizyczna"),

        // Znajomi
        EventSuggestion(name = "Wieczór filmowy", category = "Znajomi"),
        EventSuggestion(name = "Grill w ogrodzie", category = "Znajomi"),
        EventSuggestion(name = "Wyjście do klubu", category = "Znajomi"),
        EventSuggestion(name = "Planszówki u znajomych", category = "Znajomi"),
        EventSuggestion(name = "Wspólne gotowanie", category = "Znajomi")
    )
}