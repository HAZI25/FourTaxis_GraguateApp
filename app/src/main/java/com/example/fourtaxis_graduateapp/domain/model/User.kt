package com.example.fourtaxis_graduateapp.domain.model

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var bio: String = "",
    var phone: String = "",
    var photoUrl: String = "empty",
    var rideId: String = ""
)
