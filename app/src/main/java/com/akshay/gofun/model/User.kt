package com.akshay.gofun.model

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class User {

    var firstName: String? = null
    var lastName: String? = null
    var username: String? = null
    var email: String? = null
    var password: String? = null

    constructor(firstName: String?, lastName: String?, username: String?, email: String?, password: String?) {
        this.firstName = firstName
        this.lastName = lastName
        this.username = username
        this.email = email
        this.password = password
    }
}